package com.gogomca.projectmanager.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gogomca.projectmanager.model.Project;
import com.gogomca.projectmanager.persistence.ProjectManagerRepository;
import com.gogomca.projectmanager.service.ProjectManagerService;

@ExtendWith(MockitoExtension.class)
class ProjectManagerServiceImplTest {

	@Mock
	private ProjectManagerRepository projectManagerRepository;

	private ProjectManagerService projectManagerService;

	@BeforeEach
	void setUp() {
		projectManagerService = new ProjectManagerServiceImpl(projectManagerRepository);
	}

	@Test
	void should_find_project_by_id_successfully() {
		// GIVEN
		var jeeId = Long.valueOf(37L);
		var expectedProject = new Project(jeeId, "JEE", LocalDate.now());

		when(projectManagerRepository.findById(jeeId)).thenReturn(Optional.of(expectedProject));

		// WHEN
		var project = projectManagerService.findById(jeeId);

		// THEN
		assertEquals(expectedProject, project.get());
	}

	@Test
	void should_save_project_by_id_successfully() {
		// GIVEN
		var jeeId = Long.valueOf(37L);
		var expectedProject = new Project(jeeId, "JEE", LocalDate.now());

		when(projectManagerRepository.save(expectedProject)).thenReturn(expectedProject);

		// WHEN
		var project = projectManagerService.save(expectedProject);

		// THEN
		assertEquals(expectedProject, project);
	}

}
