package dao;

import java.util.List;
import metier.entities.Matiere;

public interface IMatiereDao {
	public Matiere save(Matiere m);
	public List<Matiere> listMatieres();
	public List<Matiere> getMatiereParCodeMatiere(String code_matiere);
	public List<Matiere> getMatiereParNomMatiere(String nom_matiere);
	public List<Matiere> getMatiereParCodeNomMatiere(String code_matiere, String nom_matiere);
	public List<Matiere> matieresParFiliere(String filiere);
	public List<Matiere> matieresParSpecialite(String specialite);
	public List<Matiere> matieresParFiliereSpecialite(String filiere, String specialite);
	public List<Matiere> matieresParFiliereSpecialiteNiveau(String filiere, String specialite, int niveau);
	public Matiere getMatiere(int id);
	public Float getSomCoef(String filiere, String specialite, int niveau);
	public Matiere updateMatiere(Matiere m);
	public void deleteMatiere(String code_matiere);

}
