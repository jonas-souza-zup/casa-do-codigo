package zup.jonas.souza.casadocodigo.controller.form;

import zup.jonas.souza.casadocodigo.model.Autor;
import zup.jonas.souza.casadocodigo.repository.AutorRepository;
import zup.jonas.souza.casadocodigo.validation.annotation.Unique;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovoAutorForm {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    @Unique(name = "email", repository = AutorRepository.class)
    private String email;

    @NotBlank
    @Size(max = 400)
    private String descricao;

    public NovoAutorForm(String nome, String email, String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDescricao() {
        return descricao;
    }

    public Autor toModel() {
        return new Autor(nome, email, descricao);
    }
}
