package zup.jonas.souza.casadocodigo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import zup.jonas.souza.casadocodigo.model.Autor;

import java.util.List;

public interface AutorRepository extends PagingAndSortingRepository<Autor, Long> {

    List<Autor> findByEmail(String email);
}
