package br.com.zup.casadocodigo.repository;

import br.com.zup.casadocodigo.model.Country;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CountryRepository extends PagingAndSortingRepository<Country, Integer> {

}
