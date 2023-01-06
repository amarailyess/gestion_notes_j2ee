package dao;
import java.util.List;

import metier.entities.Etudiant;
public interface IEtudiantDao {
	public Etudiant save(Etudiant p);
	public List<Etudiant> listEtudiants();
	public List<Etudiant> getEtudiantParImmatricule(String immatricule);
	public List<Etudiant> etudiantsParFiliere(String nom_filiere);
	public List<Etudiant> etudiantsParSpecialite(String specialite);
	public List<Etudiant> etudiantsParNiveau(String niveau);
	public List<Etudiant> etudiantsParFiliereNiveau(String nom_filiere, String niveau);
	public List<Etudiant> etudiantsParSpecialiteNiveau(String specialite, String niveau);
	public List<Etudiant> etudiantsParFiliereSpecialite(String nom_filiere, String specialite);
	public List<Etudiant> etudiantsParFiliereSpecialiteNiveau(String nom_filiere, String specialite, String niveau);
	public Etudiant getEtudiant(int id);
	public Etudiant updateEtudiant(Etudiant e);
	public void deleteEtudiant(int id);
}
