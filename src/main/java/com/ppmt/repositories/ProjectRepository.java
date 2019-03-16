package com.ppmt.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ppmt.domain.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {

	Project findByProjectIdentifier(String projectId);
	
	@Override
	Iterable<Project>findAll();
}
