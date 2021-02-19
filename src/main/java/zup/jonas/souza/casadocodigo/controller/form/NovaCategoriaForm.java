package zup.jonas.souza.casadocodigo.controller.form;

import zup.jonas.souza.casadocodigo.model.Categoria;
import zup.jonas.souza.casadocodigo.repository.CategoriaRepository;
import zup.jonas.souza.casadocodigo.validation.annotation.Unique;

import javax.validation.constraints.NotBlank;

public class NovaCategoriaForm implements ModelForm<Categoria> {

    @NotBlank
    @Unique(name = "nome", repository = CategoriaRepository.class)
    private String nome;

    @Deprecated
    public NovaCategoriaForm() {
    }

    public String getNome() {
        return nome;
    }

    @Override
    public Categoria toModel() {
        return new Categoria(nome);
    }
}
