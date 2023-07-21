package br.com.gerenciador.portifolio.repository.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.gerenciador.portifolio.repository.entity.Risk;

@Converter
public class RiskConverter implements AttributeConverter<Risk, String> {
	@Override
	public String convertToDatabaseColumn(Risk status) {
		if (status != null) {
			return status.getValue();
		}
		return null;
	}

	@Override
	public Risk convertToEntityAttribute(String value) {
		if (value == null) {
			return null;
		}
		return Risk.ofValue(value);
	}
}
