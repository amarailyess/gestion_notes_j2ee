package metier.entities;

public class Note {
	private int id;
	private String code_matiere;
	private String nom_matiere;
	private String immat_etudiant;
	private String nom_etudiant;
	private float ds;
	private float tp;
	private float examen;
	private float moyenne;
	
	public Note() {
		super();
	}
	public Note(String code_matiere, String nom_matiere, String immat_etudiant, String nom_etudiant, float ds, float tp,
			float examen) {
		super();
		this.code_matiere = code_matiere;
		this.nom_matiere = nom_matiere;
		this.immat_etudiant = immat_etudiant;
		this.nom_etudiant = nom_etudiant;
		this.ds = ds;
		this.tp = tp;
		this.examen = examen;
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
	@Override
	public String toString() {
		return "Note [id=" + id + ", code_matiere=" + code_matiere + ", nom_matiere=" + nom_matiere
				+ ", immat_etudiant=" + immat_etudiant + ", nom_etudiant=" + nom_etudiant + ", ds=" + ds + ", tp=" + tp
				+ ", examen=" + examen + ", moyenne=" + moyenne + "]";
	}
	
	
}
