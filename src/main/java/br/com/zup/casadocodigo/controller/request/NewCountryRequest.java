package br.com.zup.casadocodigo.controller.request;

import br.com.zup.casadocodigo.validation.annotation.Unique;
import br.com.zup.casadocodigo.model.Country;

import javax.validation.constraints.NotBlank;

public class NewCountryRequest {

    @NotBlank
    @Unique(field = "name", modelClass = Country.class)
    private String name;

    @Deprecated
    public NewCountryRequest() {
    }

    public String getName() {
        return name;
    }

    public Country toModel() {
        return new Country(name);
    }
}
