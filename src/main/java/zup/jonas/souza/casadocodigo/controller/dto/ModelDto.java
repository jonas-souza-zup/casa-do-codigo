package zup.jonas.souza.casadocodigo.controller.dto;

public interface ModelDto<T> {
    ModelDto fromModel(T model);
}
