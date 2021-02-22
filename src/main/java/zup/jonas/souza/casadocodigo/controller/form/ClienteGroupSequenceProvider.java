package zup.jonas.souza.casadocodigo.controller.form;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;
import zup.jonas.souza.casadocodigo.validation.interfaces.CnpjGroup;
import zup.jonas.souza.casadocodigo.validation.interfaces.CpfGroup;

import java.util.ArrayList;
import java.util.List;

public class ClienteGroupSequenceProvider implements DefaultGroupSequenceProvider<NovoClienteForm> {

    @Override
    public List<Class<?>> getValidationGroups(NovoClienteForm cliente) {
        var groups = new ArrayList<Class<?>>();
        groups.add(NovoClienteForm.class);
        if (cliente != null && cliente.getDocumento() != null) {
            groups.add(cliente.getDocumento().length() > 11 ? CnpjGroup.class : CpfGroup.class);
        }
        return groups;
    }
}
