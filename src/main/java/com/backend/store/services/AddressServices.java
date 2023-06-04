// package com.backend.store.services;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import com.backend.store.dto.address.AddressDTO;
// import com.backend.store.exception.AddressNotFoundException;
// import com.backend.store.exception.ChangeDefaultAddressException;
// import com.backend.store.exception.CreateAddressException;
// import com.backend.store.exception.DeleteAddressException;
// import com.backend.store.exception.UpdateAddressException;
// import com.backend.store.models.Address;
// import com.backend.store.models.User;
// import com.backend.store.repositories.AddressRepository;
// import com.backend.store.repositories.UserRepository;
// import com.backend.store.utils.AddressMapper;

// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collectors;

// @Service
// public class AddressService {

// private final AddressRepository addressRepository;
// private final UserRepository userRepository;

// @Autowired
// public AddressService(AddressRepository addressRepository, UserRepository
// userRepository) {
// this.addressRepository = addressRepository;
// this.userRepository = userRepository;
// }

// public AddressDTO create(AddressDTO dto, String userId) {
// try {
// User user = userRepository.findById(userId)
// .orElseThrow(() -> new RuntimeException("User not found"));

// boolean isDefault = user.getAddresses().isEmpty();

// Address newAddress = new Address();
// newAddress.setProvince(dto.getProvince());
// newAddress.setDistrict(dto.getDistrict());
// newAddress.setWard(dto.getWard());
// newAddress.setSpecificAddress(dto.getSpecificAddress());
// newAddress.setPhone(dto.getPhone());
// newAddress.setReceiver(dto.getReceiver());
// newAddress.setDefault(isDefault);
// newAddress.setDeleted(false);

// Address savedAddress = addressRepository.save(newAddress);

// user.getAddresses().add(savedAddress);
// userRepository.save(user);

// return AddressMapper.mapToAddressDTO(savedAddress);
// } catch (Exception e) {
// throw new CreateAddressException("Failed to create address");
// }
// }

// public AddressDTO update(String id, AddressDTO dto, String userId) {
// try {
// Address address = addressRepository.findById(id)
// .orElseThrow(() -> new AddressNotFoundException("Address not found"));

// if (address.isDefault() && !dto.isDefault()) {
// throw new ChangeDefaultAddressException("Cannot change default address");
// }

// if (dto.isDefault() && !address.isDefault()) {
// List<Address> addresses = addressRepository.findByUserId(userId);
// addresses.forEach(a -> a.setDefault(false));
// addressRepository.saveAll(addresses);
// }

// address.setDistrict(dto.getDistrict());
// address.setPhone(dto.getPhone());
// address.setProvince(dto.getProvince());
// address.setReceiver(dto.getReceiver());
// address.setSpecificAddress(dto.getSpecificAddress());
// address.setWard(dto.getWard());
// address.setDefault(dto.isDefault());

// Address updatedAddress = addressRepository.save(address);

// return AddressMapper.mapToAddressDTO(updatedAddress);
// } catch (Exception e) {
// throw new UpdateAddressException("Failed to update address");
// }
// }

// public void delete(String id, String userId) {
// try {
// Optional<Address> optionalAddress = addressRepository.findById(id);
// if (optionalAddress.isEmpty()) {
// throw new AddressNotFoundException("Address not found");
// }

// Address address = optionalAddress.get();
// address.setDeleted(true);
// addressRepository.save(address);

// User user = userRepository.findById(userId)
// .orElseThrow(() -> new RuntimeException("User not found"));
// user.getAddresses().remove(address);
// userRepository.save(user);
// } catch (Exception e) {
// throw new DeleteAddressException("Failed to delete address");
// }
// }

// public List<AddressDTO> getMany(List<String> ids) {
// List<Address> addresses = addressRepository.findByIdIn(ids);
// return addresses.stream()
// .map(AddressMapper::mapToAddressDTO)
// .collect(Collectors.toList());
// }
// }
