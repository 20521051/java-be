package com.backend.store.controllers;

import com.backend.store.models.Address;
import com.backend.store.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public Address createAddress(@RequestBody Address request, @PathVariable String userId) {
        // String userId = AuthenticationUtil.extractUserId(authentication);
        // AddressDTO createdAddress = addressService.create(dto, userId);
        return AddressService.create(request, userId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(
            @PathVariable("id") String id,
            @RequestBody AddressDTO dto,
            Authentication authentication) {
        String userId = AuthenticationUtil.extractUserId(authentication);
        AddressDTO updatedAddress = addressService.update(id, dto, userId);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(
            @PathVariable("id") String id,
            Authentication authentication) {
        String userId = AuthenticationUtil.extractUserId(authentication);
        addressService.delete(id, userId);
        return new ResponseEntity<>("Address deleted", HttpStatus.OK);
    }
}
