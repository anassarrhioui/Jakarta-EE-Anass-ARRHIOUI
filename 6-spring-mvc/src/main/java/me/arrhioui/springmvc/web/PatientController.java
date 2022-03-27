package me.arrhioui.springmvc.web;

import lombok.AllArgsConstructor;
import me.arrhioui.springmvc.entity.Patient;
import me.arrhioui.springmvc.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
        Page<Patient> pagePatient = patientRepository.findByNomContainsOrderByIdDesc(keyword, PageRequest.of(page, size));
        if(pagePatient.getTotalPages()-1 < page)
            return "redirect:/index?keyword="+keyword;
        System.out.println("page = " + page);
        System.out.println("pagePatient.getTotalPages() = " + pagePatient.getTotalPages());
        model.addAttribute("patientList", pagePatient.getContent());
        model.addAttribute("pagesNbr", new int[pagePatient.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }

    @GetMapping("/formPatient")
    public String formPatient(Model model){
        model.addAttribute("patient", new Patient());
        return "formPatient";
    }

    @PostMapping("/addPatient")
    public String addPatient(@Valid Patient patient, Model model, BindingResult bindingResult){
        System.out.println("Hello");
        if(bindingResult.hasErrors()){
            model.addAttribute("patient", patient);
            return "formPatient";
        }
        patientRepository.save(patient);
        return "redirect:/index";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam Long id, @RequestParam String keyword, @RequestParam int page){
        patientRepository.deleteById(id);
        return String.format("redirect:/index?page=%d&keyword=%s", page, keyword);

    }

    @GetMapping("/")
    public String home( Long id){
        return "redirect:/index";
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Patient> getAllPatient(){
        return patientRepository.findAll();
    }
}
