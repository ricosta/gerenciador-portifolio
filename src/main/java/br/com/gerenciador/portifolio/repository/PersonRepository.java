package br.com.gerenciador.portifolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gerenciador.portifolio.repository.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
