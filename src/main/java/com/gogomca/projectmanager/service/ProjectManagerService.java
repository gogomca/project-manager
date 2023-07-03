package com.gogomca.projectmanager.service;

import java.util.Optional;

import com.gogomca.projectmanager.model.Project;

public interface ProjectManagerService {

	Optional<Project> findById(Long id);
	
	Project save (Project project);
	
}
