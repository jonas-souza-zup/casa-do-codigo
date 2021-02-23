package br.com.zup.casadocodigo.controller;

import br.com.zup.casadocodigo.controller.dto.CustomerDto;
import br.com.zup.casadocodigo.controller.request.NewCustomerRequest;
import br.com.zup.casadocodigo.repository.CountryRepository;
import br.com.zup.casadocodigo.repository.CustomerRepository;
import br.com.zup.casadocodigo.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<CustomerDto> save(@RequestBody @Valid NewCustomerRequest req, UriComponentsBuilder uriBuilder) {
        var customer = customerRepository.save(req.toModel(countryRepository, stateRepository));
        URI uri = uriBuilder.path("/customers/{id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(uri).body(new CustomerDto(customer));
    }

    @GetMapping
    public Page<CustomerDto> list(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pagination) {
        return customerRepository.findAll(pagination).map(CustomerDto::new);
    }

}
