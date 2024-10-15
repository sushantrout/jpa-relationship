package com.tech.model.mapper;

import com.tech.model.dto.AddressDTO;
import com.tech.model.dto.StudentDTO;
import com.tech.model.entity.Address;
import com.tech.model.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper implements BaseMapper<Student, StudentDTO> {

    public StudentDTO toDTO(Student student) {
        if (student == null) {
            return null;
        }
        
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());

        // Map Address entity to AddressDTO
        if (student.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setId(student.getAddress().getId());
            addressDTO.setCity(student.getAddress().getCity());
            addressDTO.setState(student.getAddress().getState());
            studentDTO.setAddress(addressDTO);
        }

        return studentDTO;
    }

    public Student toEntity(StudentDTO studentDTO) {
        if (studentDTO == null) {
            return null;
        }
        
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());

        // Map AddressDTO to Address entity
        if (studentDTO.getAddress() != null) {
            AddressDTO addressDTO = studentDTO.getAddress();
            student.setAddress(new Address());
            student.getAddress().setId(addressDTO.getId());
            student.getAddress().setCity(addressDTO.getCity());
            student.getAddress().setState(addressDTO.getState());
        }

        return student;
    }
}
