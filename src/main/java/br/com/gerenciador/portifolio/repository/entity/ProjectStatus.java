package br.com.gerenciador.portifolio.repository.entity;

public enum ProjectStatus {
	UNDER_REVIEW("Em análise"), //
	ANALYSIS_PERFORMED("Análise realizada"), //
	ANALYSIS_APPROVED("Análise aprovada"), //
	STARTED("Iniciado"), //
	PLANNED("Planejado"), //
	UNDERWAY("Em andamento"), //
	CLOSED("Encerrado"), //
	CANCELED("Cancelado");

	String value;

	ProjectStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static ProjectStatus ofValue(String value) {
		for (ProjectStatus s : values()) {
			if (s.getValue().equalsIgnoreCase(value)) {
				return s;
			}
		}
		return null;
	}
}
