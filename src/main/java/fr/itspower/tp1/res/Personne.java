package fr.itspower.tp1.res;

import java.io.Serializable;

public class Personne implements Serializable {
	private static final long serialVersionUID = -8537962680206576813L;
	private String prenom;
	private int age;
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


	
}