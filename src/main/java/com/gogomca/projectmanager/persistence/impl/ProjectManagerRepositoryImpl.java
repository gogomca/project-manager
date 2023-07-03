package com.gogomca.projectmanager.persistence.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import org.springframework.stereotype.Repository;

import com.gogomca.projectmanager.model.Project;
import com.gogomca.projectmanager.persistence.ProjectManagerRepository;

@Repository
public class ProjectManagerRepositoryImpl implements ProjectManagerRepository {

	private List<Project> projects = new ArrayList<>();

	@Override
	public Optional<Project> findById(Long id) {
		return projects.stream().filter(p -> p.getId().equals(id)).findFirst();
	}

	@Override
	public Project save(Project project) {
		// Both saving and updating behaviour are included.
		var projectToBeReturned = new AtomicReference<Project>();
		findById(project.getId()).ifPresentOrElse(updateProject(project, projectToBeReturned),
				saveProject(project, projectToBeReturned));
		return projectToBeReturned.get();
	}

	private Runnable saveProject(Project project, AtomicReference<Project> projectToBeReturned) {
		return () -> {
			projects.add(project);
			projectToBeReturned.set(project);
		};
	}

	private Consumer<? super Project> updateProject(Project project, AtomicReference<Project> projectToBeReturned) {
		return p -> {
			projects.remove(p);
			Project newProject = new Project(project);
			projects.add(newProject);
			projectToBeReturned.set(newProject);
		};
	}
}
