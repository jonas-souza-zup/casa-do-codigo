package br.com.zup.casadocodigo.controller.dto;

import br.com.zup.casadocodigo.model.Category;

public class CategoryDto {
    private Long id;

    private String name;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
