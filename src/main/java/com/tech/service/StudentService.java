package com.tech.service;

import com.tech.model.dto.StudentDTO;
import com.tech.model.entity.Student;
import com.tech.model.mapper.StudentMapper;
import com.tech.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final MailService mailService;

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO getStudentById(int id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        return studentMapper.toDTO(student);
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO);
        student = studentRepository.save(student);
        mailService.sendmail(student.getEmail(), "Welcome", "Welcome to our platform");
        return studentMapper.toDTO(student);
    }

    public StudentDTO updateStudent(int id, StudentDTO studentDTO) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        
        // Update fields
        existingStudent.setName(studentDTO.getName());
        existingStudent.setEmail(studentDTO.getEmail());
        if (studentDTO.getAddress() != null) {
            existingStudent.setAddress(studentMapper.toEntity(studentDTO).getAddress());
        }
        
        studentRepository.save(existingStudent);
        return studentMapper.toDTO(existingStudent);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }
}
