package br.com.zup.casadocodigo.validation.interfaces;

import javax.persistence.EntityManager;

public interface RequiredIfCondition<T> {

    Boolean isRequired(EntityManager manager, T value);

}
