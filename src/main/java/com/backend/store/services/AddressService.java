package com.backend.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.store.models.Address;
import com.backend.store.models.User;
import com.backend.store.repositories.AddressRepository;
import com.backend.store.repositories.UserRepository;


@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public Address create(Address dto, String userId) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

            Address newAddress = new Address();
            newAddress.setProvince(dto.getProvince());
            newAddress.setDistrict(dto.getDistrict());
            newAddress.setWard(dto.getWard());
            newAddress.setSpecificAddress(dto.getSpecificAddress());
            newAddress.setPhone(dto.getPhone());
            newAddress.setReceiver(dto.getReceiver());

            Address savedAddress = addressRepository.save(newAddress);

            userRepository.save(user);

            return savedAddress;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create address");
        }
    }

    public Address update(String id, Address dto, String userId) {
        try {
            Address address = addressRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Address not found"));

            address.setDistrict(dto.getDistrict());
            address.setPhone(dto.getPhone());
            address.setProvince(dto.getProvince());
            address.setReceiver(dto.getReceiver());
            address.setSpecificAddress(dto.getSpecificAddress());
            address.setWard(dto.getWard());

            Address updatedAddress = addressRepository.save(address);

            return updatedAddress;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update address");
        }
    }
}
