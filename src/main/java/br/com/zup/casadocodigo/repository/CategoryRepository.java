package br.com.zup.casadocodigo.repository;

import br.com.zup.casadocodigo.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

}
