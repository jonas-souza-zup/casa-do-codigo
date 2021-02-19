package zup.jonas.souza.casadocodigo.controller.form;

import zup.jonas.souza.casadocodigo.model.Pais;
import zup.jonas.souza.casadocodigo.repository.PaisRepository;
import zup.jonas.souza.casadocodigo.validation.annotation.Unique;

import javax.validation.constraints.NotBlank;

public class NovoPaisForm {

    @NotBlank
    @Unique(name = "nome", repository = PaisRepository.class)
    private String nome;

    @Deprecated
    public NovoPaisForm() {
    }

    public String getNome() {
        return nome;
    }

    public Pais toModel() {
        return new Pais(nome);
    }
}
