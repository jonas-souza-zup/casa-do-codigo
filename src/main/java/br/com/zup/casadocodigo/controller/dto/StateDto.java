package br.com.zup.casadocodigo.controller.dto;

import br.com.zup.casadocodigo.model.State;

public class StateDto {
    private Long id;
    private String name;

    public StateDto(State state) {
        this.id = state.getId();
        this.name = state.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
