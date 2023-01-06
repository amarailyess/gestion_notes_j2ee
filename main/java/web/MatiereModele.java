package web;

import java.util.ArrayList;
import java.util.List;

import metier.entities.Matiere;

public class MatiereModele {
	private String code_matiere;
	private String nom_matiere;
	private String filiere;
	private String specialite;
	List<Matiere> matieres = new ArrayList<>();
	public String getCode_matiere() {
		return code_matiere;
	}
	public void setCode_matiere(String code_matiere) {
		this.code_matiere = code_matiere;
	}
	public String getNom_matiere() {
		return nom_matiere;
	}
	public void setNom_matiere(String nom_matiere) {
		this.nom_matiere = nom_matiere;
	}
	public String getFiliere() {
		return filiere;
	}
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	public List<Matiere> getMatieres() {
		return matieres;
	}
	public void setMatieres(List<Matiere> matieres) {
		this.matieres = matieres;
	}
	
}
