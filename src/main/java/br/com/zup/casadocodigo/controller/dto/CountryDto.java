package br.com.zup.casadocodigo.controller.dto;

import br.com.zup.casadocodigo.model.Country;

public class CountryDto {
    private Integer id;

    private String name;

    public CountryDto(Country country) {
        this.id = country.getId();
        this.name = country.getName();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
