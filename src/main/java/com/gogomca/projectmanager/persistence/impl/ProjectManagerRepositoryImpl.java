package com.gogomca.projectmanager.persistence.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.gogomca.projectmanager.model.Project;
import com.gogomca.projectmanager.persistence.ProjectManagerRepository;

@Repository
public class ProjectManagerRepositoryImpl implements ProjectManagerRepository {

	private List<Project> projects = new ArrayList<>();

	@Override
	public Optional<Project> findById(Long id) {
		return projects.stream().filter(p -> p.getId().equals(id)).findFirst(); // TODO: improve lambda.
	}

	@Override
	public Project save(Project project) {
		// Both saving and updating behaviour are included.
		// TODO: use functional approach
		Project existingProject = findById(project.getId()).orElse(null);
		if (existingProject == null) {
			projects.add(project);
			return project;
		} else {
			projects.remove(existingProject);
			Project newProject = new Project(project);
			projects.add(newProject);
			return project;
		}
	}
}
