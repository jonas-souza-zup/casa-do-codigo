package zup.jonas.souza.casadocodigo.validation;

import javax.persistence.EntityManager;

public class RequiredIfPaisTemEstado implements RequiredCondition<Long> {

    @Override
    public Boolean isRequired(EntityManager manager, Long id) {
        return !manager.createQuery("select e from Estado where e.pais.id = " + id)
                .getResultList()
                .isEmpty();
    }
}
