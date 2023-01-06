package web;

import java.util.ArrayList;
import java.util.List;


import metier.entities.Note;

public class NoteModele {

	private String code_matiere;
	private String nom_matiere;
	private String nom_etudiant;
	private String immat_etudiant;
	private String filiere;
	private String specialite;
	private String niveau;
	private float ds;
	private float tp;
	private float examen;
	private float moyenne;
	private float moyenne_generale;
	List<Note> notes = new ArrayList<>();
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
	
	public String getImmat_etudiant() {
		return immat_etudiant;
	}
	public void setImmat_etudiant(String immat_etudiant) {
		this.immat_etudiant = immat_etudiant;
	}
	public String getNom_etudiant() {
		return nom_etudiant;
	}
	public void setNom_etudiant(String nom_etudiant) {
		this.nom_etudiant = nom_etudiant;
	}
	
	public String getFiliere() {
		return filiere;
	}
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}
	public float getDs() {
		return ds;
	}
	public void setDs(float ds) {
		this.ds = ds;
	}
	public float getTp() {
		return tp;
	}
	public void setTp(float tp) {
		this.tp = tp;
	}
	public float getExamen() {
		return examen;
	}
	public void setExamen(float examen) {
		this.examen = examen;
	}
	public float getMoyenne() {
		return moyenne;
	}
	public void setMoyenne(float moyenne) {
		this.moyenne = moyenne;
	}
	
	public float getMoyenne_generale() {
		return moyenne_generale;
	}
	
	public void setMoyenne_generale(float moyenne_generale) {
		this.moyenne_generale = moyenne_generale;
	}
	
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	public List<Note> getNotes() {
		return notes;
	}
	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
	
	
	
}
