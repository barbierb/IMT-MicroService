package fr.projetimt.repositories;

import org.springframework.data.repository.CrudRepository;

import fr.projetimt.entities.User;

public interface UserRepository extends CrudRepository<User, Long> { }