package com.ppmt.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ppmt.domain.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {

	@Override
	Iterable<Project> findAllById(Iterable<Long> iterable);
}
