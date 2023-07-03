package com.gogomca.projectmanager.model;

import java.time.LocalDate;
import java.util.Objects;

// TODO: Include Lombok.
public class Project {
	private Long id;
	private String name;
	private LocalDate creationDate;
	
	public Project(Long id, String name, LocalDate creationDate) {
		super();
		this.id = id;
		this.name = name;
		this.creationDate = creationDate;
	}

	public Project(Project project) {
		this(project.getId(), project.getName(), project.getCreationDate());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(creationDate, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		return Objects.equals(creationDate, other.creationDate) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", creationDate=" + creationDate + "]";
	}

}
