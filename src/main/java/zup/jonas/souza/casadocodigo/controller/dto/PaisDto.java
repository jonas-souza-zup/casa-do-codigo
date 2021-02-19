package zup.jonas.souza.casadocodigo.controller.dto;

import zup.jonas.souza.casadocodigo.model.Pais;

public class PaisDto {
    private Integer id;

    private String nome;

    public PaisDto(Pais pais) {
        this.id = pais.getId();
        this.nome = pais.getNome();
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
