package com.gogomca.projectmanager.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gogomca.projectmanager.model.Project;
import com.gogomca.projectmanager.persistence.ProjectManagerRepository;
import com.gogomca.projectmanager.service.ProjectManagerService;

@Service
public class ProjectManagerServiceImpl implements ProjectManagerService {
	
	private final ProjectManagerRepository projectManagerRepository;	

	public ProjectManagerServiceImpl(ProjectManagerRepository projectManagerRepository) {
		super();
		this.projectManagerRepository = projectManagerRepository;
	}

	@Override
	public Optional<Project> findById(Long id) {
		return projectManagerRepository.findById(id);
	}

	@Override
	public Project save(Project project) {
		return projectManagerRepository.save(project);
	}

}
