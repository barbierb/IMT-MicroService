package fr.projetimt.repositories;

import org.springframework.data.repository.CrudRepository;

import fr.projetimt.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
}