package metier.entities;

public class Matiere {
	private int id;
	private String code_matiere;
	private String nom_matiere;
	private String filiere;
	private String specialite;
	private int niveau;
	private float coef_ds;
	private float coef_tp;
	private float coef_examen;
	private float coef_matiere;
	
	public Matiere(int id, String code_matiere, String nom_matiere, String filiere, String specialite, int niveau, float coef_ds,
			float coef_tp, float coef_examen, float coef_matiere) {
		super();
		this.id = id;
		this.code_matiere = code_matiere;
		this.nom_matiere = nom_matiere;
		this.filiere = filiere;
		this.specialite = specialite;
		this.niveau = niveau;
		this.coef_ds = coef_ds;
		this.coef_tp = coef_tp;
		this.coef_examen = coef_examen;
		this.coef_matiere = coef_matiere;
	}
	public Matiere(String code_matiere, String nom_matiere, String filiere, String specialite, int niveau,
			float coef_ds, float coef_tp, float coef_examen, float coef_matiere) {
		super();
		this.code_matiere = code_matiere;
		this.nom_matiere = nom_matiere;
		this.filiere = filiere;
		this.specialite = specialite;
		this.niveau = niveau;
		this.coef_ds = coef_ds;
		this.coef_tp = coef_tp;
		this.coef_examen = coef_examen;
		this.coef_matiere = coef_matiere;
	}
	
	public Matiere() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public int getNiveau() {
		return niveau;
	}
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	public float getCoef_ds() {
		return coef_ds;
	}
	public void setCoef_ds(float coef_ds) {
		this.coef_ds = coef_ds;
	}
	public float getCoef_tp() {
		return coef_tp;
	}
	public void setCoef_tp(float coef_tp) {
		this.coef_tp = coef_tp;
	}
	public float getCoef_examen() {
		return coef_examen;
	}
	public void setCoef_examen(float coef_examen) {
		this.coef_examen = coef_examen;
	}
	
	public float getCoef_matiere() {
		return coef_matiere;
	}
	public void setCoef_matiere(float coef_matiere) {
		this.coef_matiere = coef_matiere;
	}
	@Override
	public String toString() {
		return "Matiere [id=" + id + ", code_matiere=" + code_matiere + ", nom_matiere=" + nom_matiere + ", filiere="
				+ filiere + ", specialite=" + specialite + ", niveau=" + niveau + ", coef_ds=" + coef_ds + ", coef_tp="
				+ coef_tp + ", coef_examen=" + coef_examen + ", coef_matiere=" + coef_matiere + "]";
	}
	
}
