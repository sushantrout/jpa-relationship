package com.tech.service;

import com.tech.model.dto.AddressDTO;
import com.tech.model.dto.StudentDTO;
import com.tech.model.entity.Address;
import com.tech.model.entity.Student;
import com.tech.model.mapper.AddressMapper;
import com.tech.model.mapper.StudentMapper;
import com.tech.repo.AddressRepository;
import com.tech.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private AddressMapper addressMapper;

    // Get all addresses
    public List<AddressDTO> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream()
                .map(addressMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get address by ID
    public AddressDTO getAddressById(int id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        return addressMapper.toDTO(address);
    }

    // Create a new address
    public AddressDTO createAddress(AddressDTO addressDTO) {
        Address address = addressMapper.toEntity(addressDTO);
        address = addressRepository.save(address);
        return addressMapper.toDTO(address);
    }

    // Update an existing address
    public AddressDTO updateAddress(int id, AddressDTO addressDTO) {
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        existingAddress.setCity(addressDTO.getCity());
        existingAddress.setState(addressDTO.getState());

        addressRepository.save(existingAddress);
        return addressMapper.toDTO(existingAddress);
    }

    // Delete address by ID
    @Transactional
    public void deleteAddress(int id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        Student student = address.getStudent();
        student.setAddress(null);
        addressRepository.deleteById(id);
    }

    public StudentDTO findStudentByAddressId(int addressId) {
       /* Student student = studentRepository.findByAddressId(addressId);
        return studentMapper.toDTO(student);*/
        return studentMapper.toDTO(addressRepository.findById(addressId).get().getStudent());
    }
}
