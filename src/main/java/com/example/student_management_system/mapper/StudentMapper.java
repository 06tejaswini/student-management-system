package com.example.student_management_system.mapper;

import com.example.student_management_system.dto.StudentDto;
import com.example.student_management_system.entity.Student;

//we will use mapper class for common logic so if we want to covert the student  dto sudent jpaentity or viseversa it will help.
public class StudentMapper {

    //it will convert the jpaentity to dto
    public static StudentDto mapToStudentDto (Student student) {
        StudentDto studentDto= new StudentDto( student.getId(), student.getFirstName(), student.getLastName(), student.getEmail() );
        return studentDto;
    }
   // it will convert the dto to jpaentity
    public static Student mapToStudent(StudentDto studentDto){
        Student student= new Student(studentDto.getId(), studentDto.getFirstName(), studentDto.getLastName(), studentDto.getEmail());
        return student;
    }
}
