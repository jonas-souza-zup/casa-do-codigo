package br.com.zup.casadocodigo.validation;

import br.com.zup.casadocodigo.validation.interfaces.RequiredCondition;
import br.com.zup.casadocodigo.controller.request.NewCustomerRequest;

import javax.persistence.EntityManager;

public class RequiredIfCountryHasState implements RequiredCondition<NewCustomerRequest> {

    @Override
    public Boolean isRequired(EntityManager manager, NewCustomerRequest request) {
        var resultList =  manager
                .createQuery("from State s where s.country.id = " + request.getCountryId())
                .getResultList();
        return !resultList.isEmpty();
    }
}
