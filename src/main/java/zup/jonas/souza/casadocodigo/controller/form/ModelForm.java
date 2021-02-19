package zup.jonas.souza.casadocodigo.controller.form;

public interface ModelForm<T> {

    T toModel(Object... params);

}
