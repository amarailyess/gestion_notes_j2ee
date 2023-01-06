package metier.entities;

public class Etudiant {
	private int id;
	private String nom;
	private String immatricule;
	private String filiere;
	private String specialite;
	private int niveau;
	private float moyenne;
	
	
public Etudiant(int id, String nom, String immatricule, String filiere, String specialite, int niveau, float moyenne) {
		super();
		this.id = id;
		this.nom = nom;
		this.immatricule = immatricule;
		this.filiere = filiere;
		this.specialite = specialite;
		this.niveau = niveau;
		this.moyenne = moyenne;
	}
public Etudiant(String nom, String immatricule, String filiere, String specialite, int niveau) {
		super();
		this.nom = nom;
		this.immatricule = immatricule;
		this.filiere = filiere;
		this.specialite = specialite;
		this.niveau = niveau;
	}
public Etudiant() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getImmatricule() {
		return immatricule;
	}
	public void setImmatricule(String immatricule) {
		this.immatricule = immatricule;
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
	public int getNiveau() {
		return niveau;
	}
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	
	public float getMoyenne() {
		return moyenne;
	}
	public void setMoyenne(float moyenne) {
		this.moyenne = moyenne;
	}
	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", nom=" + nom + ", immatricule=" + immatricule + ", filiere=" + filiere
				+ ", specialite=" + specialite + ", niveau=" + niveau + ", moyenne=" + moyenne + "]";
	}
}
