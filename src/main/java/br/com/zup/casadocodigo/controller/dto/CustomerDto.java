package br.com.zup.casadocodigo.controller.dto;

import br.com.zup.casadocodigo.model.Customer;

public class CustomerDto {

    private Long id;

    private String email;

    private String name;

    public CustomerDto(Customer customer) {
        id = customer.getId();
        email = customer.getEmail();
        name = customer.getName();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
