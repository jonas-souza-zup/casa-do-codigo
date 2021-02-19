package zup.jonas.souza.casadocodigo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import zup.jonas.souza.casadocodigo.model.Pais;

import java.util.List;

public interface PaisRepository extends PagingAndSortingRepository<Pais, Integer> {

    List<Pais> findByNome(String nome);
}
