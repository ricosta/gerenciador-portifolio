package br.com.gerenciador.portifolio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gerenciador.portifolio.exceptions.NotAllowedException;
import br.com.gerenciador.portifolio.repository.PersonRepository;
import br.com.gerenciador.portifolio.repository.ProjectsRepository;
import br.com.gerenciador.portifolio.repository.entity.Person;
import br.com.gerenciador.portifolio.repository.entity.Project;
import br.com.gerenciador.portifolio.service.ProjectsService;


@Service
public class ProjectsServiceImpl implements ProjectsService {
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private ProjectsRepository projectRepository;
	
	@Transactional
	public Project create(Project project) {
		return save(project);
	}

	private Project save(Project project) {
		for (Person member : project.getMembers()) {
			Optional<Person> person = personRepository.findById(member.getId());
			person.filter(p -> p.isEmployee()).orElseThrow(() -> new NotAllowedException("Person", "id", member.getId()));
		}
		return projectRepository.save(project);
	}

	@Transactional
	public Project update(Project project) {
		return save(project);
	}

	@Transactional
	public void remove(Long id) {
		if (projectRepository.existsById(id)) {
			projectRepository.deleteById(id);
		}
	}

	@Transactional(readOnly = true)
	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Project> findById(Long id) {
		return projectRepository.findById(id);
	}
}
