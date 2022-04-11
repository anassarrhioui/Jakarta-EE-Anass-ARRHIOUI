package me.anass.gestionetudiant.controller;

import lombok.AllArgsConstructor;
import me.anass.gestionetudiant.entity.Student;
import me.anass.gestionetudiant.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class StudentController {

    final private StudentRepository studentRepository;

    @GetMapping("/")
    public String index(){
        return "layout";
    }

    @GetMapping("/index")
    public String patient(
            Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "keyword", defaultValue = "") String keyword
    ){
        Page<Student> studentPage = studentRepository.findByNomContainsOrderByIdDesc(keyword, PageRequest.of(page, size));
        if(studentPage.getTotalPages()-1 < page)
            return "redirect:/index?keyword="+keyword;
        model.addAttribute("students", studentPage.getContent());
        model.addAttribute("pagesNbr", new int[studentPage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "students";
    }

    //TODO : Transform Get to Delete with JavaScript
    @DeleteMapping("/delete")
    public String delete(
            @RequestParam Long id,
            @RequestParam(name = "keyword", defaultValue = "") String currentSearch,
            @RequestParam(name = "page", defaultValue = "0") int currentPage
    ){
        studentRepository.deleteById(id);
        return String.format("redirect:/index?page=%d&keyword=%s", currentPage, currentSearch);
    }

    @GetMapping("/formAdd")
    public String formStudent(Model model){
        model.addAttribute("student", new Student());
        return "addStudent";
    }

    @GetMapping("/formEdit")
    public String editStudent(Model model, @RequestParam(required = true, name = "id") Long id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent()){
            model.addAttribute("student", studentOptional.get());
            return "editStudent";
        }
        return "students";
    }

    @PostMapping("/addStudent")
    public String addStudent(@Valid Student student, Model model, BindingResult bindingResult){
        System.out.println("student = " + student);
        System.out.println("bindingResult = " + bindingResult);
        System.out.println("bindingResult.hasErrors() = " + bindingResult.hasErrors());
        if(bindingResult.hasErrors()){
            model.addAttribute("student", student);
            return "addStudent";
        }
        studentRepository.save(student);
        return "redirect:/index";
    }
}
