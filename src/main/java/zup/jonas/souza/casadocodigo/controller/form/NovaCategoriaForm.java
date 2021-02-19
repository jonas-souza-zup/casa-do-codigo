package zup.jonas.souza.casadocodigo.controller.form;

import zup.jonas.souza.casadocodigo.model.Categoria;
import zup.jonas.souza.casadocodigo.validation.annotation.Unique;

import javax.validation.constraints.NotBlank;

public class NovaCategoriaForm {

    @NotBlank
    @Unique(modelClass = Categoria.class,field = "nome")
    private String nome;

    @Deprecated
    public NovaCategoriaForm() {
    }

    public String getNome() {
        return nome;
    }

    public Categoria toModel() {
        return new Categoria(nome);
    }
}
