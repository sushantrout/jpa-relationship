package com.tech.model.mapper;

import com.tech.model.dto.AddressDTO;
import com.tech.model.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper implements BaseMapper<Address, AddressDTO> {

    @Override
    public AddressDTO toDTO(Address address) {
        if (address == null) {
            return null;
        }

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        return addressDTO;
    }

    @Override
    public Address toEntity(AddressDTO addressDTO) {
        if (addressDTO == null) {
            return null;
        }

        Address address = new Address();
        address.setId(addressDTO.getId());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        return address;
    }
}
