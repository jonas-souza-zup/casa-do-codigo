package zup.jonas.souza.casadocodigo.controller.dto;

import zup.jonas.souza.casadocodigo.model.Cliente;

public class ClienteDto {

    private Long id;

    private String email;

    private String nome;

    public ClienteDto(Cliente cliente) {
        id = cliente.getId();
        email = cliente.getEmail();
        nome = cliente.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }
}
