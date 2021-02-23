package br.com.zup.casadocodigo.validation;

import br.com.zup.casadocodigo.validation.interfaces.RequiredCondition;
import br.com.zup.casadocodigo.controller.request.NewCustomerRequest;

import javax.persistence.EntityManager;

public class RequiredIfCountryHasState implements RequiredCondition<NewCustomerRequest> {

    @Override
    public Boolean isRequired(EntityManager manager, NewCustomerRequest form) {
        return !manager.createQuery("from Estado e where e.pais.id = " + form.getCountryId())
                .getResultList()
                .isEmpty();
    }
}