package zup.jonas.souza.casadocodigo.controller.form;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class ClienteGroupSequenceProvider implements DefaultGroupSequenceProvider<NovoClienteForm> {
    
    @Override
    public List<Class<?>> getValidationGroups(NovoClienteForm cliente) {
        var groups = new ArrayList<Class<?>>();
        groups.add(NovoClienteForm.class);
        if (cliente != null && cliente.getTipoPessoa() != null) {
            groups.add(cliente.getTipoPessoa().getGroup());
        }
        return groups;
    }
}
