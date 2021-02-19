package zup.jonas.souza.casadocodigo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import zup.jonas.souza.casadocodigo.model.Categoria;

import java.util.List;

public interface CategoriaRepository extends PagingAndSortingRepository<Categoria, Long> {

    List<Categoria> findByNome(String name);
}
