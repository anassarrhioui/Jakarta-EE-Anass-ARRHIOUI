package me.arrhioui.springmvc.web;

import lombok.AllArgsConstructor;
import me.arrhioui.springmvc.entity.Patient;
import me.arrhioui.springmvc.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    final private PatientRepository patientRepository;

    @GetMapping("/index")
    public String patient(
            Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "keyword", defaultValue = "") String keyword
    ){
        Page<Patient> pagePatient = patientRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("patientList", pagePatient.getContent());
        model.addAttribute("pagesNbr", new int[pagePatient.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id){
        patientRepository.deleteById(id);
        return "redirect:/index";

    }

    @GetMapping("/")
    public String home( Long id){
        return "redirect:/index";
    }
}
