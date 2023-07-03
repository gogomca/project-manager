package com.gogomca.projectmanager.persistence;

import java.util.Optional;

import com.gogomca.projectmanager.model.Project;

public interface ProjectManagerRepository {

	Optional<Project> findById(Long id);
	
	Project save (Project project);
}
