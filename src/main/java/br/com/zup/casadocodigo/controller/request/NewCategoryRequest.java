package br.com.zup.casadocodigo.controller.request;

import br.com.zup.casadocodigo.validation.annotation.Unique;
import br.com.zup.casadocodigo.model.Category;

import javax.validation.constraints.NotBlank;

public class NewCategoryRequest {

    @NotBlank
    @Unique(modelClass = Category.class,field = "name")
    private String name;

    @Deprecated
    public NewCategoryRequest() {
    }

    public String getName() {
        return name;
    }

    public Category toModel() {
        return new Category(name);
    }
}
