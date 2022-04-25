package me.anass.gestionetudiantrestws.web;


import lombok.AllArgsConstructor;
import me.anass.gestionetudiantrestws.entity.Student;
import me.anass.gestionetudiantrestws.repository.StudentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
@CrossOrigin("*")
public class StudentController {

    final private StudentRepository studentRepository;

    @GetMapping
    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    @GetMapping("/pagination")
    public List<Student> findByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){

        return studentRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id){
        return studentRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Student save(@RequestBody Student student){
        student.setId(null);
        return studentRepository.save(student);
    }

    @PutMapping("/{id}")
    public Student save(@RequestBody Student student, @PathVariable Long id){
        student.setId(id);
        return studentRepository.save(student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        studentRepository.deleteById(id);
    }
}
