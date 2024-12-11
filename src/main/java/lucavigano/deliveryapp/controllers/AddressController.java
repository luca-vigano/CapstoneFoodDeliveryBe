package lucavigano.deliveryapp.controllers;

import lucavigano.deliveryapp.entities.Address;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.repository.UserRepository;
import lucavigano.deliveryapp.service.AddressService;
import lucavigano.deliveryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Address createAddress(@RequestBody Address addressDTO, @AuthenticationPrincipal User currentUser) {

        Address address = new Address();
        address.setStreetAddress(addressDTO.getStreetAddress());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setPostalCode(addressDTO.getPostalCode());
        address.setCountry(addressDTO.getCountry());
        address.setUser(currentUser);


        Address savedAddress = addressService.saveAddress(address);
        currentUser.getAddress().add(savedAddress);
        userRepository.save(currentUser);

        return savedAddress;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Address> getAddresses(@AuthenticationPrincipal User currentUser) {
        return addressService.getAddressesByUser(currentUser);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Address getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id)
                .orElseThrow(() -> new IllegalArgumentException("Indirizzo non trovato con ID: " + id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }
}
