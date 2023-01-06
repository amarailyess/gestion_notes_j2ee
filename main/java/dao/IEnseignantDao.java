package dao;

import java.util.List;

import metier.entities.Enseignant;

public interface IEnseignantDao {
	public Enseignant save(Enseignant n);
	public List<Enseignant> listEnseignants();
	public List<Enseignant> enseignantParImmatricule(String immat_enseignant);
	public List<Enseignant> enseignantParNom(String nom_enseignant);
	public List<Enseignant> enseignantParImmatriculeNom(String immat_enseignant, String nom_enseignant) ;
	public List<Enseignant> enseignantParDepartement(String departement);
	public List<Enseignant> enseignantParSpecialite(String specialite);
	public List<Enseignant> enseignantParDepartementSpecialite(String departement, String specialite);
	public List<Enseignant> enseignantParDegree(String degree);
	public Enseignant getEnseignant(int id);
	public Enseignant updateEnseignant(Enseignant ens);
	public void deleteEnseignant(int id);
}
