package com.gogomca.projectmanager.persistence.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.gogomca.projectmanager.model.Project;
import com.gogomca.projectmanager.persistence.ProjectManagerRepository;

class ProjectManagerRepositoryImplTest {

	private ProjectManagerRepository projectManagerRepository = new ProjectManagerRepositoryImpl();

	@Test
	void should_find_project_by_id_successfully() {
		// GIVEN
		var jeeId = Long.valueOf(37L);
		ReflectionTestUtils.setField(projectManagerRepository, "projects",
				List.of(new Project(Long.valueOf(12L), "Spring", LocalDate.now()),
						new Project(Long.valueOf(jeeId), "JEE", LocalDate.now()),
						new Project(Long.valueOf(44L), "Angula", LocalDate.now())));

		// WHEN
		var project = projectManagerRepository.findById(jeeId);

		// THEN
		assertNotNull(project);
		assertEquals(jeeId, project.orElseThrow().getId());
	}

	@Test
	void shoul_not_find_project_by_id() {
		// GIVEN
		var jeeId = Long.valueOf(37L);
		ReflectionTestUtils.setField(projectManagerRepository, "projects",
				List.of(new Project(Long.valueOf(12L), "Spring", LocalDate.now()),
						new Project(Long.valueOf(jeeId), "JEE", LocalDate.now()),
						new Project(Long.valueOf(44L), "Angula", LocalDate.now())));

		// WHEN
		var project = projectManagerRepository.findById(Long.MAX_VALUE);

		// THEN
		assertNotNull(project);
		assertFalse(project.isPresent());
	}

	@Test
	void should_save_project_because_does_not_exist_previously() {
		// GIVEN
		var jeeId = Long.valueOf(37L);
		var expectedProject = new Project(Long.valueOf(jeeId), "JEE", LocalDate.now());

		// WHEN
		var project = projectManagerRepository.save(expectedProject);

		// THEN
		var projects = (List<Project>) ReflectionTestUtils.getField(projectManagerRepository, "projects");
		assertTrue(projects.contains(project));
		assertEquals(expectedProject, project);
	}

	@Test
	void should_update_project_because_exists_previously() {
		// GIVEN
		var jeeId = Long.valueOf(37L);
		var expectedProject = new Project(Long.valueOf(jeeId), "Maven", LocalDate.now());

		ReflectionTestUtils.setField(projectManagerRepository, "projects",
				new ArrayList<>(Arrays.asList(new Project(Long.valueOf(12L), "Spring", LocalDate.now()),
						new Project(Long.valueOf(jeeId), "JEE", LocalDate.now()),
						new Project(Long.valueOf(44L), "Angula", LocalDate.now()))));

		// WHEN
		var project = projectManagerRepository.save(expectedProject);

		// THEN
		var projects = (List<Project>) ReflectionTestUtils.getField(projectManagerRepository, "projects");
		assertTrue(projects.contains(project));
		assertEquals(expectedProject, project);
	}

}
