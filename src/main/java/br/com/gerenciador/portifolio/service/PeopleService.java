package br.com.gerenciador.portifolio.service;

import java.util.List;
import java.util.Optional;

import br.com.gerenciador.portifolio.repository.entity.Person;

public interface PeopleService {
	List<Person> findAll();

	Optional<Person> findById(Long id);
}
