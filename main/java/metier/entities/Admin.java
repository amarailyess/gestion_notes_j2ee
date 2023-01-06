package metier.entities;

public class Admin {
	private int id;
	private String immatricule;
	private String nom;
	private String num_tel;
	private String email;
	private String role;
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(int id, String immatricule, String nom, String role,
			String num_tel, String email) {
		super();
		this.id = id;
		this.immatricule = immatricule;
		this.nom = nom;
		this.role = role;
		this.num_tel = num_tel;
		this.email = email;
	}
	public Admin(String immatricule, String nom, String role,
			String num_tel, String email) {
		super();
		this.immatricule = immatricule;
		this.nom = nom;
		this.role = role;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", immatricule=" + immatricule + ", nom=" + nom + ", num_tel=" + num_tel + ", email="
				+ email + ", role=" + role + "]";
	}
	
	
}
