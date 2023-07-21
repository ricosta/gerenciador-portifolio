package br.com.gerenciador.portifolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.gerenciador.portifolio.exceptions.NotFoundException;
import br.com.gerenciador.portifolio.repository.entity.Person;
import br.com.gerenciador.portifolio.service.PeopleService;


@RestController
@RequestMapping("/people")
public class PeopleController {
	@Autowired
	private PeopleService peopleService;
	
	public PeopleController(PeopleService peopleService) {
		this.peopleService = peopleService;
	}

	@GetMapping
	@ResponseBody
	@Transactional(readOnly = true)
	public List<Person> findAll() {
		return peopleService.findAll();
	}

	@GetMapping("/{id}")
	@Transactional(readOnly=true)
	public Person findById(@PathVariable(value = "id") Long id) {
		return peopleService.findById(id).orElseThrow(() -> new NotFoundException("Person", "id", id));
	}
}
