package com.example.student_management_system.service.impl;

import com.example.student_management_system.dto.StudentDto;
import com.example.student_management_system.entity.Student;
import com.example.student_management_system.mapper.StudentMapper;
import com.example.student_management_system.repository.StudentRepository;
import com.example.student_management_system.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
//    now we have injected studentrepository in a studentserviceimpl
    private StudentRepository studentRepository;
//    this is the dependency injection
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDto> getAllStudents() {
//        this will student jpa  entity
        List<Student> students= studentRepository.findAll();
//        this will convert list of student jpa enties to list of student dto
        List<StudentDto> studentDtos= students.stream()
                .map((student)-> StudentMapper.mapToStudentDto(student))
                .collect(Collectors.toList());
        return studentDtos;
    }

    @Override
    public void createStudent(StudentDto studentDto) {
//        inorder to save student details in database table, then we should have a student jpa entity
//        so we need to convert studentDto object to student jpa entity
        Student student= StudentMapper.mapToStudent(studentDto);
        studentRepository.save(student);
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        StudentDto studentDto= StudentMapper.mapToStudentDto(student);
        return studentDto;
    }

    @Override
    public void updateStudent(StudentDto studentDto) {
        studentRepository.save(StudentMapper.mapToStudent(studentDto));
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }
}
