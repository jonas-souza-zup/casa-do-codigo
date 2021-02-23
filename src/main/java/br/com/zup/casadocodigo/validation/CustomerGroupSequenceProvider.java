package br.com.zup.casadocodigo.validation;

import br.com.zup.casadocodigo.controller.request.NewCustomerRequest;
import br.com.zup.casadocodigo.validation.interfaces.CnpjGroup;
import br.com.zup.casadocodigo.validation.interfaces.CpfGroup;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class CustomerGroupSequenceProvider implements DefaultGroupSequenceProvider<NewCustomerRequest> {

    @Override
    public List<Class<?>> getValidationGroups(NewCustomerRequest cliente) {
        var groups = new ArrayList<Class<?>>();
        groups.add(NewCustomerRequest.class);
        if (cliente != null && cliente.getDocument() != null) {
            groups.add(cliente.getDocument().length() > 11 ? CnpjGroup.class : CpfGroup.class);
        }
        return groups;
    }
}
