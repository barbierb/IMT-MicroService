package fr.itspower.referenceproject.personnes.process;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import fr.itspower.referenceproject.livres.bdd.Livre;

@Entity
public class Personne  {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
    @Column(columnDefinition = "INT")
	private Long id;
	private String prenom;
    @Column(columnDefinition = "INT")
	private int age;
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private List<Livre> livres;
	
	public Personne() {
		super();
	}
	
	public Personne(String prenom, int age) {
		super();
		this.prenom = prenom;
		this.age = age;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Livre> getLivres() {
		return livres;
	}

	public void setLivres(List<Livre> livres) {
		this.livres = livres;
	}
}
