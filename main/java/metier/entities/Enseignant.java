package metier.entities;

public class Enseignant {
	private int id;
	private String immatricule;
	private String nom;
	private String departement;
	private String specialite;
	private String degree;
	private String num_tel;
	private String email;
	public Enseignant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Enseignant(int id, String immatricule, String nom, String departement, String specialite, String degree,
			String num_tel, String email) {
		super();
		this.id = id;
		this.immatricule = immatricule;
		this.nom = nom;
		this.departement = departement;
		this.specialite = specialite;
		this.degree = degree;
		this.num_tel = num_tel;
		this.email = email;
	}
	public Enseignant(String immatricule, String nom, String departement, String specialite, String degree,
			String num_tel, String email) {
		super();
		this.immatricule = immatricule;
		this.nom = nom;
		this.departement = departement;
		this.specialite = specialite;
		this.degree = degree;
		this.num_tel = num_tel;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImmatricule() {
		return immatricule;
	}
	public void setImmatricule(String immatricule) {
		this.immatricule = immatricule;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getNum_tel() {
		return num_tel;
	}
	public void setNum_tel(String num_tel) {
		this.num_tel = num_tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Enseignant [id=" + id + ", immatricule=" + immatricule + ", nom=" + nom + ", departement=" + departement
				+ ", specialite=" + specialite + ", degree=" + degree + ", num_tel=" + num_tel + ", email=" + email
				+ "]";
	}
	
}
