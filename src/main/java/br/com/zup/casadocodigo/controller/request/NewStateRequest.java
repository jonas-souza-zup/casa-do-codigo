package br.com.zup.casadocodigo.controller.request;

import br.com.zup.casadocodigo.exception.NotFoundException;
import br.com.zup.casadocodigo.model.Country;
import br.com.zup.casadocodigo.model.State;
import br.com.zup.casadocodigo.repository.CountryRepository;
import br.com.zup.casadocodigo.validation.annotation.Exists;
import br.com.zup.casadocodigo.validation.annotation.FieldAlias;
import br.com.zup.casadocodigo.validation.annotation.UniqueValues;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@UniqueValues(modelClass = State.class, fields = {"name", "countryId"})
public class NewStateRequest {

    @NotBlank
    private String name;

    @NotNull
    @Exists(field = "id", modelClass = Country.class)
    @FieldAlias("country.id")
    private Integer countryId;

    public NewStateRequest(String name, Integer countryId) {
        this.name = name;
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public State toModel(CountryRepository countryRepository) {
        var country = countryRepository.findById(countryId).orElseThrow(() -> new NotFoundException(countryId));
        return new State(name, country);
    }
}
