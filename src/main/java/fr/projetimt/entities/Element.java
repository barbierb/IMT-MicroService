package fr.projetimt.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity
public class Element {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	@NonNull
	private String title;
	
	@Enumerated(EnumType.ORDINAL)
    private Eisenhower eisenhower;

	private Long dateStart;
	
	@NonNull
	private Long dateEnd;
	
	private String description;
	
	private Boolean isDone;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getDateStart() {
		return dateStart;
	}

	public void setDateStart(Long dateStart) {
		this.dateStart = dateStart;
	}

	public Long getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Long dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsDone() {
		return isDone;
	}

	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}

	public Long getId() {
		return id;
	}
	
	public Eisenhower getEisenhower() {
		return eisenhower;
	}

	public void setEisenhower(Eisenhower eisenhower) {
		this.eisenhower = eisenhower;
	}
	
}
