package com.backend.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.store.models.Address;
import com.backend.store.models.User;
import com.backend.store.repositories.AddressRepository;
import com.backend.store.repositories.UserRepository;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private final AddressRepository addressRepository;
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public Address create(Address dto, String userId) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new Error("User not found."));

            List<String> addresses = user.getAddresses();

            Address newAddress = new Address();
            newAddress.setProvince(dto.getProvince());
            newAddress.setDistrict(dto.getDistrict());
            newAddress.setWard(dto.getWard());
            newAddress.setSpecificAddress(dto.getSpecificAddress());
            newAddress.setPhone(dto.getPhone());
            newAddress.setReceiver(dto.getReceiver());

            Address result = addressRepository.save(newAddress);
            addresses.add(newAddress.getId());
            user.setAddresses(addresses);
            userRepository.save(user);

            return result;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return null;
        }
    }

    public Address update(String id, Address dto, String userId) {
        try {
            Address address = addressRepository.findById(id)
                    .orElseThrow(() -> new Error("Address not found"));

            if (dto.getDistrict() != null) {
                address.setDistrict(dto.getDistrict());
            }
            if (dto.getDistrict() != null) {
                address.setPhone(dto.getPhone());
            }
            if (dto.getDistrict() != null) {
                address.setProvince(dto.getProvince());
            }
            if (dto.getDistrict() != null) {
                address.setReceiver(dto.getReceiver());
            }
            if (dto.getDistrict() != null) {
                address.setSpecificAddress(dto.getSpecificAddress());
            }
            if (dto.getDistrict() != null) {
                address.setWard(dto.getWard());
            }

            Address updatedAddress = addressRepository.save(address);

            return updatedAddress;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return null;
        }
    }

    public void delete(String id, String userId) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new Error("User not found."));

            List<String> addresses = user.getAddresses();

            boolean isNotFound = true;
            for (String item : addresses) {
                if (item == id) {
                    addresses.remove(item);
                    isNotFound = false;
                }
            }
            if (isNotFound == false)
                throw new Error("Address not exists.");
            user.setAddresses(addresses);
            userRepository.save(user);

        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
