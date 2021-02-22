package zup.jonas.souza.casadocodigo.validation;

import zup.jonas.souza.casadocodigo.controller.form.NovoClienteForm;
import zup.jonas.souza.casadocodigo.validation.interfaces.RequiredCondition;

import javax.persistence.EntityManager;

public class RequiredIfPaisTemEstado implements RequiredCondition<NovoClienteForm> {

    @Override
    public Boolean isRequired(EntityManager manager, NovoClienteForm form) {
        return !manager.createQuery("from Estado e where e.pais.id = " + form.getPaisId())
                .getResultList()
                .isEmpty();
    }
}
