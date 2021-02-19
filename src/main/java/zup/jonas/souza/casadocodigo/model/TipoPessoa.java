package zup.jonas.souza.casadocodigo.model;

import zup.jonas.souza.casadocodigo.validation.interfaces.CnpjGroup;
import zup.jonas.souza.casadocodigo.validation.interfaces.CpfGroup;

public enum TipoPessoa {

    FISICA(CpfGroup.class),
    JURIDICA(CnpjGroup .class);

    private final Class<?> group;

    private TipoPessoa(Class<?> group) {
        this.group = group;
    }

    public Class<?> getGroup() {
        return group;
    }
}
