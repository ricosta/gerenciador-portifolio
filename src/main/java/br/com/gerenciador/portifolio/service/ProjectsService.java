package br.com.gerenciador.portifolio.service;

import java.util.List;
import java.util.Optional;

import br.com.gerenciador.portifolio.repository.entity.Project;


public interface ProjectsService {
	Project create(Project project);

	Project update(Project project);

	void remove(Long id);

	List<Project> findAll();

	Optional<Project> findById(Long id);
}
