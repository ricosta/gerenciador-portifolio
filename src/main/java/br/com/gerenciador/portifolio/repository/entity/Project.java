package br.com.gerenciador.portifolio.repository.entity;

import static br.com.gerenciador.portifolio.repository.entity.ProjectStatus.CLOSED;
import static br.com.gerenciador.portifolio.repository.entity.ProjectStatus.STARTED;
import static br.com.gerenciador.portifolio.repository.entity.ProjectStatus.UNDERWAY;
import static br.com.gerenciador.portifolio.repository.entity.ProjectStatus.UNDER_REVIEW;
import static br.com.gerenciador.portifolio.repository.entity.Risk.HIGH;
import static br.com.gerenciador.portifolio.repository.entity.Risk.LOW;
import static br.com.gerenciador.portifolio.repository.entity.Risk.MID;
import static java.time.LocalDate.now;
import static java.time.ZoneId.systemDefault;
import static javax.persistence.TemporalType.DATE;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.gerenciador.portifolio.repository.entity.converter.ProjectStatusConverter;
import br.com.gerenciador.portifolio.repository.entity.converter.RiskConverter;

@Entity
@Table(name = "projeto")
public class Project extends Base implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "nome")
	@NotEmpty
	@Size(max = 200)
	private String name;

	@Column(name = "data_inicio")
	@Temporal(DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date start;

	@Column(name = "data_previsao_fim")
	@Temporal(DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date scheduledEnd;

	@Column(name = "data_fim")
	@Temporal(DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date end;

	@Column(name = "descricao")
	@Size(max = 5000)
	private String description;

	@Column(name = "status")
	@Convert(converter = ProjectStatusConverter.class)
	private ProjectStatus status;

	@Column(name = "orcamento")
	private BigDecimal budget;

	@Column(name = "risco")
	@Convert(converter = RiskConverter.class)
	private Risk risk;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "idgerente")
	private Person manager;

	@ManyToMany
	@JoinTable(name = "membros", joinColumns = @JoinColumn(name = "idprojeto"), inverseJoinColumns = @JoinColumn(name = "idpessoa"))
	private Set<Person> members;

	public Project() {
		this.members = new HashSet<>();
	}

	public String getName() {
		return name;
	}

	public Date getStart() {
		return start;
	}

	public Date getScheduledEnd() {
		return scheduledEnd;
	}

	public Date getEnd() {
		if (end != null) {
			return end;
		}
		return scheduledEnd;
	}

	public String getDescription() {
		return description;
	}

	public ProjectStatus getStatus() {
		if (ObjectUtils.isEmpty(status)) {
			return UNDER_REVIEW;
		}
		return status;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public Risk getRisk() {
		if (ObjectUtils.isEmpty(risk)) {
			return LOW;
		}
		return risk;
	}

	public Person getManager() {
		return manager;
	}

	public Set<Person> getMembers() {
		return Collections.unmodifiableSet(members);
	}

	public void changeName(String name) {
		this.name = name;
	}

	public void schedule(Date start, Date end) {
		this.start = start;
		this.scheduledEnd = end;
	}

	public void finish() {
		this.end = Date.from(now().atStartOfDay().atZone(systemDefault()).toInstant());
	}

	public void changeDescription(String description) {
		this.description = description;
	}

	public void changeStatus(ProjectStatus status) {
		this.status = status;
	}

	public void changeBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public void lowRisk() {
		this.risk = LOW;
	}

	public void midRisk() {
		this.risk = MID;
	}

	public void highRisk() {
		this.risk = HIGH;
	}

	public void changeManager(Person manager) {
		if (!manager.isEmployee()) {
			this.manager = manager;
		}
	}

	public boolean isAllowedRemove() {
		if (STARTED.equals(this.status) || UNDERWAY.equals(this.status) || CLOSED.equals(this.status)) {
			return false;
		}
		return true;
	}

	public void addEmployee(Person employee) {
		if (employee.isEmployee()) {
			this.members.add(employee);
		}
	}
}
