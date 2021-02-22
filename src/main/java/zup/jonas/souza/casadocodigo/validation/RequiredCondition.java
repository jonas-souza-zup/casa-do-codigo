package zup.jonas.souza.casadocodigo.validation;

import javax.persistence.EntityManager;

public interface RequiredCondition<T> {

    Boolean isRequired(EntityManager manager, T value);

}
