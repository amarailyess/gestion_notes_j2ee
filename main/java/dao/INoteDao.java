package dao;

import java.util.List;

import metier.entities.Note;

public interface INoteDao {
	public Note save(Note n);
	public List<Note> listNotes();
	public List<Note> noteParEtudiant(String immat_etudiant);
	public List<Note> noteParMatiere(String code_matiere);
	public List<Note> noteEtudiantParCodeMatiere(String immat_etudiant,String code_matiere);
	public List<Note> noteEtudiantParNomMatiere(String immat_etudiant,String nom_matiere);
	public List<Note> noteParFiliere(String filiere);
	public List<Note> noteParSpecialite(String specialite);
	public List<Note> noteParNiveau(int niveau);
	public List<Note> noteParFiliereSpecialite(String filiere, String specialite);
	public List<Note> noteParFiliereNiveau(String filiere, String niveau);
	public List<Note> noteParSpecialiteNiveau(String specialite, String niveau);
	public List<Note> noteParFiliereSpecialiteNiveau(String filiere,String specialite,String niveau);
	public float calculerMoyenne(String immat_etudiant,List<Note> notes, float som_coef);
	public Note getNote(int id);
	public Note updateNote(Note n);
	public void deleteNote(int id);
	
}
