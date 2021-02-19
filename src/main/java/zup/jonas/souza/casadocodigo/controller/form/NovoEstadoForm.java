package zup.jonas.souza.casadocodigo.controller.form;

import zup.jonas.souza.casadocodigo.exception.NotFoundException;
import zup.jonas.souza.casadocodigo.model.Estado;
import zup.jonas.souza.casadocodigo.model.Pais;
import zup.jonas.souza.casadocodigo.repository.PaisRepository;
import zup.jonas.souza.casadocodigo.validation.annotation.Exists;
import zup.jonas.souza.casadocodigo.validation.annotation.FieldAlias;
import zup.jonas.souza.casadocodigo.validation.annotation.UniqueValues;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@UniqueValues(domainClass = Estado.class, fields = {"nome", "paisId"})
public class NovoEstadoForm {

    @NotBlank
    private String nome;

    @NotNull
    @Exists(modelClass = Pais.class, field = "id")
    @FieldAlias("pais.id")
    private Integer paisId;

    public NovoEstadoForm(String nome, Integer paisId) {
        this.nome = nome;
        this.paisId = paisId;
    }

    public String getNome() {
        return nome;
    }

    public Integer getPaisId() {
        return paisId;
    }

    public Estado toModel(PaisRepository paisRepository) {
        var pais = paisRepository.findById(paisId).orElseThrow(() -> new NotFoundException(paisId));
        return new Estado(nome, pais);
    }
}
