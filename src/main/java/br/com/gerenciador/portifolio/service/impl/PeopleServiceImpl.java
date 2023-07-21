package br.com.gerenciador.portifolio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gerenciador.portifolio.repository.PersonRepository;
import br.com.gerenciador.portifolio.repository.entity.Person;
import br.com.gerenciador.portifolio.service.PeopleService;

@Service
public class PeopleServiceImpl implements PeopleService {
	@Autowired
	private PersonRepository personRepository;
	
	public PeopleServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Transactional(readOnly = true)
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Person> findById(Long id) {
		return personRepository.findById(id);
	}
}
