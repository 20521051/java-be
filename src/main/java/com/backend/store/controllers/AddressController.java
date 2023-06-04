// package com.backend.store.controllers;

// import com.backend.store.dto.address.AddressDTO;
// import com.backend.store.services.AddressService;
// import com.backend.store.utils.AuthenticationUtil;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.Authentication;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/addresses")
// public class AddressController {

// private final AddressService addressService;

// @Autowired
// public AddressController(AddressService addressService) {
// this.addressService = addressService;
// }

// @PostMapping
// public ResponseEntity<AddressDTO> createAddress(
// @RequestBody AddressDTO dto,
// Authentication authentication) {
// String userId = AuthenticationUtil.extractUserId(authentication);
// AddressDTO createdAddress = addressService.create(dto, userId);
// return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
// }

// @PutMapping("/{id}")
// public ResponseEntity<AddressDTO> updateAddress(
// @PathVariable("id") String id,
// @RequestBody AddressDTO dto,
// Authentication authentication) {
// String userId = AuthenticationUtil.extractUserId(authentication);
// AddressDTO updatedAddress = addressService.update(id, dto, userId);
// return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
// }

// @DeleteMapping("/{id}")
// public ResponseEntity<String> deleteAddress(
// @PathVariable("id") String id,
// Authentication authentication) {
// String userId = AuthenticationUtil.extractUserId(authentication);
// addressService.delete(id, userId);
// return new ResponseEntity<>("Address deleted", HttpStatus.OK);
// }
// }
