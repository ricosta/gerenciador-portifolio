package br.com.gerenciador.portifolio.repository.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.gerenciador.portifolio.repository.entity.ProjectStatus;

@Converter
public class ProjectStatusConverter implements AttributeConverter<ProjectStatus, String> {
	@Override
	public String convertToDatabaseColumn(ProjectStatus status) {
		if (status != null) {
			return status.getValue();
		}
		return null;
	}

	@Override
	public ProjectStatus convertToEntityAttribute(String value) {
		if (value == null) {
			return null;
		}
		return ProjectStatus.ofValue(value);
	}
}
