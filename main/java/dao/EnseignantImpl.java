package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.SingletonConnection;
import metier.entities.Enseignant;

public class EnseignantImpl implements IEnseignantDao{

	@Override
	public Enseignant save(Enseignant ens) {
		// TODO Auto-generated method stub
		Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("INSERT INTO enseignant(immatricule, nom, departement, specialite, degree, num_tel, email) VALUES(?,?,?,?,?,?,?)");
			ps.setString(1, ens.getImmatricule());
			ps.setString(2, ens.getNom());
			ps.setString(3, ens.getDepartement());
			ps.setString(4, ens.getSpecialite());
			ps.setString(5, ens.getDegree());
			ps.setString(6, ens.getNum_tel());
			ps.setString(7, ens.getEmail());
			ps.executeUpdate();		
			PreparedStatement ps2= conn.prepareStatement
					("SELECT MAX(id) as MAX_ID FROM enseignant");
			ResultSet rs =ps2.executeQuery();
			if (rs.next()) {
				ens.setId(rs.getInt("MAX_ID"));
			}
			ps.close();
			ps2.close();		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return ens;
	}
	
	@Override
	public List<Enseignant> listEnseignants() {
		// TODO Auto-generated method stub
		List<Enseignant> enseignants= new ArrayList<Enseignant>();
	       Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from enseignant");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Enseignant ens = new Enseignant();
				ens.setId(rs.getInt("id"));
				ens.setImmatricule(rs.getString("immatricule"));
				ens.setNom(rs.getString("nom"));
				ens.setDepartement(rs.getString("departement"));
				ens.setSpecialite(rs.getString("specialite"));
				ens.setDegree(rs.getString("degree"));
				ens.setNum_tel(rs.getString("num_tel"));
				ens.setEmail(rs.getString("email"));
				enseignants.add(ens);								
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			return enseignants;
	}
	

	@Override
	public List<Enseignant> enseignantParImmatricule(String immat_enseignant) {
		// TODO Auto-generated method stub
		if(immat_enseignant.equals("")) {
			   List<Enseignant> enseignants = this.listEnseignants();
			   return enseignants;
		   }else {
		   List<Enseignant> enseignants = new ArrayList<Enseignant>();
		   Connection conn=SingletonConnection.getConnection();
		   Enseignant ens = new Enseignant();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from enseignant where immatricule = ?");
			ps.setString(1, immat_enseignant);
			ResultSet rs = ps.executeQuery();
			while  (rs.next()) {
				ens.setId(rs.getInt("id"));
				ens.setImmatricule(rs.getString("immatricule"));
				ens.setNom(rs.getString("nom"));
				ens.setDepartement(rs.getString("departement"));
				ens.setSpecialite(rs.getString("specialite"));
				ens.setDegree(rs.getString("degree"));
				ens.setNum_tel(rs.getString("num_tel"));
				ens.setEmail(rs.getString("email"));
				enseignants.add(ens);	
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return enseignants;
		   }
	}

	@Override
	public List<Enseignant> enseignantParNom(String nom_enseignant) {
		// TODO Auto-generated method stub
		if(nom_enseignant.equals("")) {
			   List<Enseignant> enseignants = this.listEnseignants();
			   return enseignants;
		   }else {
		   List<Enseignant> enseignants = new ArrayList<Enseignant>();
		   Connection conn=SingletonConnection.getConnection();
		   Enseignant ens = new Enseignant();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from enseignant where nom = ?");
			ps.setString(1, nom_enseignant);
			ResultSet rs = ps.executeQuery();
			while  (rs.next()) {
				ens.setId(rs.getInt("id"));
				ens.setImmatricule(rs.getString("immatricule"));
				ens.setNom(rs.getString("nom"));
				ens.setDepartement(rs.getString("departement"));
				ens.setSpecialite(rs.getString("specialite"));
				ens.setDegree(rs.getString("degree"));
				ens.setNum_tel(rs.getString("num_tel"));
				ens.setEmail(rs.getString("email"));
				enseignants.add(ens);	
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return enseignants;
		   }
	}

	@Override
	public List<Enseignant> enseignantParImmatriculeNom(String immat_enseignant, String nom_enseignant) {
		// TODO Auto-generated method stub
		if(immat_enseignant.equals("")) {
			if(nom_enseignant.equals("")) {
				   List<Enseignant> enseignants = this.listEnseignants();
				   return enseignants;
			   }else {
				   List<Enseignant> enseignants = this.enseignantParNom(nom_enseignant);
				   return enseignants;
			   }
		}else if(nom_enseignant.equals("")) {
			List<Enseignant> enseignants = this.enseignantParImmatricule(immat_enseignant);
			   return enseignants;
		}
		else {
		   List<Enseignant> enseignants = new ArrayList<Enseignant>();
		   Connection conn=SingletonConnection.getConnection();
		   Enseignant ens = new Enseignant();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from enseignant where immatricule=? and  nom= ?");
			ps.setString(1, immat_enseignant);
			ps.setString(2, nom_enseignant);
			ResultSet rs = ps.executeQuery();
			while  (rs.next()) {
				ens.setId(rs.getInt("id"));
				ens.setImmatricule(rs.getString("immatricule"));
				ens.setNom(rs.getString("nom"));
				ens.setDepartement(rs.getString("departement"));
				ens.setSpecialite(rs.getString("specialite"));
				ens.setDegree(rs.getString("degree"));
				ens.setNum_tel(rs.getString("num_tel"));
				ens.setEmail(rs.getString("email"));
				enseignants.add(ens);	
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return enseignants;
		   }
	}

	@Override
	public List<Enseignant> enseignantParDepartement(String departement) {
		// TODO Auto-generated method stub
				if(departement.equals("")) {
					   List<Enseignant> enseignants = this.listEnseignants();
					   return enseignants;
				   }else {
				   List<Enseignant> enseignants = new ArrayList<Enseignant>();
				   Connection conn=SingletonConnection.getConnection();
				   Enseignant ens = new Enseignant();
			       try {
					PreparedStatement ps= conn.prepareStatement("select * from enseignant where departement= ?");
					ps.setString(1, departement);
					ResultSet rs = ps.executeQuery();
					while  (rs.next()) {
						ens.setId(rs.getInt("id"));
						ens.setImmatricule(rs.getString("immatricule"));
						ens.setNom(rs.getString("nom"));
						ens.setDepartement(rs.getString("departement"));
						ens.setSpecialite(rs.getString("specialite"));
						ens.setDegree(rs.getString("degree"));
						ens.setNum_tel(rs.getString("num_tel"));
						ens.setEmail(rs.getString("email"));
						enseignants.add(ens);	
					}
						
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
					return enseignants;
				   }
	}

	@Override
	public List<Enseignant> enseignantParSpecialite(String specialite) {
		// TODO Auto-generated method stub
		if(specialite.equals("")) {
			   List<Enseignant> enseignants = this.listEnseignants();
			   return enseignants;
		   }else {
		   List<Enseignant> enseignants = new ArrayList<Enseignant>();
		   Connection conn=SingletonConnection.getConnection();
		   Enseignant ens = new Enseignant();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from enseignant where specialite= ?");
			ps.setString(1, specialite);
			ResultSet rs = ps.executeQuery();
			while  (rs.next()) {
				ens.setId(rs.getInt("id"));
				ens.setImmatricule(rs.getString("immatricule"));
				ens.setNom(rs.getString("nom"));
				ens.setDepartement(rs.getString("departement"));
				ens.setSpecialite(rs.getString("specialite"));
				ens.setDegree(rs.getString("degree"));
				ens.setNum_tel(rs.getString("num_tel"));
				ens.setEmail(rs.getString("email"));
				enseignants.add(ens);	
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return enseignants;
		   }
	}

	@Override
	public List<Enseignant> enseignantParDepartementSpecialite(String departement, String specialite) {
		// TODO Auto-generated method stub
		if(departement.equals("")) {
			if(specialite.equals("")) {
				   List<Enseignant> enseignants = this.listEnseignants();
				   return enseignants;
			   }else {
				   List<Enseignant> enseignants = this.enseignantParSpecialite(specialite);
				   return enseignants;
			   }
		}else if(specialite.equals("")) {
			List<Enseignant> enseignants = this.enseignantParDepartement(departement);
			   return enseignants;
		}
		else {
		   List<Enseignant> enseignants = new ArrayList<Enseignant>();
		   Connection conn=SingletonConnection.getConnection();
		   Enseignant ens = new Enseignant();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from enseignant where departement=? and  specialite = ?");
			ps.setString(1, departement);
			ps.setString(2, specialite);
			ResultSet rs = ps.executeQuery();
			while  (rs.next()) {
				ens.setId(rs.getInt("id"));
				ens.setImmatricule(rs.getString("immatricule"));
				ens.setNom(rs.getString("nom"));
				ens.setDepartement(rs.getString("departement"));
				ens.setSpecialite(rs.getString("specialite"));
				ens.setDegree(rs.getString("degree"));
				ens.setNum_tel(rs.getString("num_tel"));
				ens.setEmail(rs.getString("email"));
				enseignants.add(ens);	
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return enseignants;
		   }
	}

	@Override
	public List<Enseignant> enseignantParDegree(String degree) {
		// TODO Auto-generated method stub
		if(degree.equals("")) {
			   List<Enseignant> enseignants = this.listEnseignants();
			   return enseignants;
		   }else {
		   List<Enseignant> enseignants = new ArrayList<Enseignant>();
		   Connection conn=SingletonConnection.getConnection();
		   Enseignant ens = new Enseignant();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from enseignant where degree = ?");
			ps.setString(1, degree);
			ResultSet rs = ps.executeQuery();
			while  (rs.next()) {
				ens.setId(rs.getInt("id"));
				ens.setImmatricule(rs.getString("immatricule"));
				ens.setNom(rs.getString("nom"));
				ens.setDepartement(rs.getString("departement"));
				ens.setSpecialite(rs.getString("specialite"));
				ens.setDegree(rs.getString("degree"));
				ens.setNum_tel(rs.getString("num_tel"));
				ens.setEmail(rs.getString("email"));
				enseignants.add(ens);	
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return enseignants;
		   }
	}

	@Override
	public Enseignant getEnseignant(int id) {
		// TODO Auto-generated method stub
		   Connection conn=SingletonConnection.getConnection();
		   Enseignant ens = new Enseignant();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from enseignant where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while  (rs.next()) {
				ens.setId(rs.getInt("id"));
				ens.setImmatricule(rs.getString("immatricule"));
				ens.setNom(rs.getString("nom"));
				ens.setDepartement(rs.getString("departement"));
				ens.setSpecialite(rs.getString("specialite"));
				ens.setDegree(rs.getString("degree"));
				ens.setNum_tel(rs.getString("num_tel"));
				ens.setEmail(rs.getString("email"));	
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return ens;
		   
	}

	@Override
	public Enseignant updateEnseignant(Enseignant ens) {
		// TODO Auto-generated method stub
		Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("UPDATE enseignant SET immatricule=?,nom=?, departement=?, specialite=?,degree=?,num_tel=?,email=? WHERE id=?");
			ps.setString(1, ens.getImmatricule());
			ps.setString(2, ens.getNom());
			ps.setString(3, ens.getDepartement());
			ps.setString(4, ens.getSpecialite());
			ps.setString(5, ens.getDegree());
			ps.setString(6, ens.getNum_tel());
			ps.setString(7, ens.getEmail());
			ps.setInt(8, ens.getId());
			ps.executeUpdate();
			ps.close();
					
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return ens;
	}

	@Override
	public void deleteEnseignant(int id) {
		// TODO Auto-generated method stub
		Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("DELETE FROM enseignant WHERE id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}



	

}
