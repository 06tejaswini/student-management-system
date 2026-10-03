package com.example.student_management_system.controller;

import com.example.student_management_system.dto.StudentDto;
import com.example.student_management_system.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
//after this annotation our class can able to tale HTTPs  requests.
public class StudentController {
    private StudentService studentService;
//here we have injected studentservice dependecy in studentcontroller calss using constructed based dependecy injection
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //    Handler method to handle list students request
    @GetMapping("/students")
//    this is tohandle http get request with url /student
    public String listStudents(Model model){
        List<StudentDto> students= studentService.getAllStudents();
        model.addAttribute("students",students);
        return "students";
    }

//    Handler method to handle insertion new data
    @GetMapping("/students/new")
    public String newStudent(Model model){
//    student model object to store student form data
        StudentDto studentDto =new StudentDto();
        model.addAttribute("student", studentDto);
        return  "create_student";  // this is the student create view
    }

//    handler method to handle save student form submit request
    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") StudentDto student,
                              BindingResult result,
                              Model model){
        if(result.hasErrors()){
            model.addAttribute("student", student);
            return "create_student";
        }
        studentService.createStudent(student);
        return  "redirect:/students";
    }

//    Handler method to handle edit student request
    @GetMapping("/students/{studentId}/edit")
    public String editStudent(@PathVariable ("studentId") Long studentId, Model model){
        StudentDto student=studentService.getStudentById(studentId);
        model.addAttribute("student",student);
        return "edit_student"; //view name
    }

//    handler methodto handle edit student form submit request
    @PostMapping("/students/{studentId}")
    public String updateStudent(@PathVariable("studentId") Long studentId,
                                @Valid @ModelAttribute("student") StudentDto studentDto,
                                BindingResult result,
                                Model model){
        if(result.hasErrors()){
            model.addAttribute("student", studentDto);
            return "edit_student";
        }
        studentDto.setId(studentId);
        studentService.updateStudent(studentDto);
        return "redirect:/students";
    }

//    handler method to handle delete student request
    @GetMapping("/students/{studentId}/delete")
    public String deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
        return  "redirect:/students";
    }

//    Handler method to handle view student request
    @GetMapping("/students/{studentId}/view")
    public String viewStudent(@PathVariable("studentId") Long studentId,
                                Model model){
        StudentDto studentDto= studentService.getStudentById(studentId);
        model.addAttribute("student", studentDto);
        return "view_student";

    }
}
