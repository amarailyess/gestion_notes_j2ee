package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.SingletonConnection;
import metier.entities.Etudiant;

public class EtudiantDaoImpl implements IEtudiantDao {

	@Override
	public Etudiant save(Etudiant etud) {
		 Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("INSERT INTO etudiant(nom,immatricule,filiere, specialite, niveau) VALUES(?,?,?,?,?)");
			ps.setString(1, etud.getNom());
			ps.setString(2, etud.getImmatricule());
			ps.setString(3, etud.getFiliere());
			ps.setString(4, etud.getSpecialite());
			ps.setInt(5, etud.getNiveau());
			ps.executeUpdate();
			
			
			PreparedStatement ps2= conn.prepareStatement
					("SELECT MAX(id) as MAX_ID FROM etudiant");
			ResultSet rs =ps2.executeQuery();
			if (rs.next()) {
				etud.setId(rs.getInt("MAX_ID"));
			}
			ps.close();
			ps2.close();
				 
					
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return etud;
	}

	@Override
	public List<Etudiant> etudiantsParFiliere(String nom_filiere) {
		
	      List<Etudiant> etuds= new ArrayList<Etudiant>();
	       Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from etudiant where filiere LIKE ?");
			ps.setString(1, nom_filiere);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Etudiant etud = new Etudiant();
				
				etud.setId(rs.getInt("id"));
				etud.setNom(rs.getString("nom"));
				etud.setImmatricule(rs.getString("immatricule"));
				etud.setFiliere(rs.getString("filiere"));
				etud.setSpecialite(rs.getString("specialite"));
				etud.setNiveau(rs.getInt("niveau"));
				etud.setMoyenne(rs.getFloat("moyenne"));
				etuds.add(etud);								
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			return etuds;
		   
	}
	
	@Override
	public List<Etudiant> etudiantsParSpecialite(String specialite) {
		// TODO Auto-generated method stub
		List<Etudiant> etuds= new ArrayList<Etudiant>();
	       Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from etudiant where specialite LIKE ?");
			ps.setString(1, specialite);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Etudiant etud = new Etudiant();
				
				etud.setId(rs.getInt("id"));
				etud.setNom(rs.getString("nom"));
				etud.setImmatricule(rs.getString("immatricule"));
				etud.setFiliere(rs.getString("filiere"));
				etud.setSpecialite(rs.getString("specialite"));
				etud.setNiveau(rs.getInt("niveau"));
				etud.setMoyenne(rs.getFloat("moyenne"));
				etuds.add(etud);								
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			return etuds;
	}
	
	@Override
	public List<Etudiant> etudiantsParNiveau(String niveau) {
		
	      List<Etudiant> etuds= new ArrayList<Etudiant>();
	       Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from etudiant where niveau LIKE ?");
			ps.setInt(1, Integer.parseInt(niveau));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Etudiant etud = new Etudiant();
				
				etud.setId(rs.getInt("id"));
				etud.setNom(rs.getString("nom"));
				etud.setImmatricule(rs.getString("immatricule"));
				etud.setFiliere(rs.getString("filiere"));
				etud.setSpecialite(rs.getString("specialite"));
				etud.setNiveau(rs.getInt("niveau"));
				etud.setMoyenne(rs.getFloat("moyenne"));
				etuds.add(etud);								
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			return etuds;
		   
	}
	
	@Override
	public List<Etudiant> listEtudiants() {
	      List<Etudiant> etuds= new ArrayList<Etudiant>();
	       Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from etudiant");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Etudiant etud = new Etudiant();
				etud.setId(rs.getInt("id"));
				etud.setNom(rs.getString("nom"));
				etud.setImmatricule(rs.getString("immatricule"));
				etud.setFiliere(rs.getString("filiere"));
				etud.setSpecialite(rs.getString("specialite"));
				etud.setNiveau(rs.getInt("niveau"));
				etud.setMoyenne(rs.getFloat("moyenne"));
				etuds.add(etud);								
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			return etuds;
	}
	@Override
	public List<Etudiant> etudiantsParFiliereSpecialite(String nom_filiere, String specialite) {
		// TODO Auto-generated method stub
		if(specialite.equals("")) {
			if(nom_filiere.equals("")) {
			   List<Etudiant> etudiants = this.listEtudiants();
			   return etudiants;
			}else {
				List<Etudiant> etudiants = this.etudiantsParFiliere(nom_filiere);
				   return etudiants;
			}
		   }else if(nom_filiere.equals("")) {
			   List<Etudiant> etudiants = this.etudiantsParSpecialite(specialite);
			   return etudiants;
			}else {
				
	      List<Etudiant> etuds= new ArrayList<Etudiant>();
	       Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from etudiant where filiere LIKE ? and specialite LIKE ?");
			ps.setString(1, nom_filiere);
			ps.setString(2, specialite);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Etudiant etud = new Etudiant();
				etud.setId(rs.getInt("id"));
				etud.setNom(rs.getString("nom"));
				etud.setImmatricule(rs.getString("immatricule"));
				etud.setFiliere(rs.getString("filiere"));
				etud.setSpecialite(rs.getString("specialite"));
				etud.setNiveau(rs.getInt("niveau"));
				etud.setMoyenne(rs.getFloat("moyenne"));
				etuds.add(etud);								
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			return etuds;
		   }
	}
	
	@Override
	public List<Etudiant> etudiantsParSpecialiteNiveau(String specialite, String niveau) {
		// TODO Auto-generated method stub
		if(niveau.equals("")) {
			if(specialite.equals("")) {
			   List<Etudiant> etudiants = this.listEtudiants();
			   return etudiants;
			}else {
				List<Etudiant> etudiants = this.etudiantsParFiliere(specialite);
				   return etudiants;
			}
		   }else if(specialite.equals("")) {
			   List<Etudiant> etudiants = this.etudiantsParNiveau(niveau);
			   return etudiants;
			}else {
				
	      List<Etudiant> etuds= new ArrayList<Etudiant>();
	       Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from etudiant where specialite LIKE ? and niveau LIKE ?");
			ps.setString(1, specialite);
			ps.setInt(2, Integer.parseInt(niveau));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Etudiant etud = new Etudiant();
				etud.setId(rs.getInt("id"));
				etud.setNom(rs.getString("nom"));
				etud.setImmatricule(rs.getString("immatricule"));
				etud.setFiliere(rs.getString("filiere"));
				etud.setSpecialite(rs.getString("specialite"));
				etud.setNiveau(rs.getInt("niveau"));
				etud.setMoyenne(rs.getFloat("moyenne"));
				etuds.add(etud);								
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			return etuds;
		   }
	}
	
	@Override
	public List<Etudiant> etudiantsParFiliereNiveau(String nom_filiere, String niveau) {
		if(niveau.equals("")) {
			if(nom_filiere.equals("")) {
			   List<Etudiant> etudiants = this.listEtudiants();
			   return etudiants;
			}else {
				List<Etudiant> etudiants = this.etudiantsParFiliere(nom_filiere);
				   return etudiants;
			}
		   }else if(nom_filiere.equals("")) {
			   List<Etudiant> etudiants = this.etudiantsParNiveau(niveau);
			   return etudiants;
			}else {
				
	      List<Etudiant> etuds= new ArrayList<Etudiant>();
	       Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from etudiant where filiere LIKE ? and niveau LIKE ?");
			ps.setString(1, nom_filiere);
			ps.setInt(2, Integer.parseInt(niveau));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Etudiant etud = new Etudiant();
				etud.setId(rs.getInt("id"));
				etud.setNom(rs.getString("nom"));
				etud.setImmatricule(rs.getString("immatricule"));
				etud.setFiliere(rs.getString("filiere"));
				etud.setSpecialite(rs.getString("specialite"));
				etud.setNiveau(rs.getInt("niveau"));
				etud.setMoyenne(rs.getFloat("moyenne"));
				etuds.add(etud);								
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			return etuds;
		   }
	}
	
	@Override
	public List<Etudiant> etudiantsParFiliereSpecialiteNiveau(String nom_filiere, String specialite, String niveau) {
		// TODO Auto-generated method stub
		if(niveau.equals("")) {
			if(nom_filiere.equals("")) {
				if(specialite.equals("")) {
					List<Etudiant> etudiants = this.listEtudiants();
					   return etudiants;
				}else {
					List<Etudiant> etudiants = this.etudiantsParSpecialite(specialite);
					   return etudiants;
				}
			}else if(specialite.equals("")) {
					List<Etudiant> etudiants = this.etudiantsParFiliere(nom_filiere);
					   return etudiants;
				}else {
					List<Etudiant> etudiants = this.etudiantsParFiliereSpecialite(nom_filiere,specialite);
					   return etudiants;
				}
			}else if(nom_filiere.equals("")) {
				if(specialite.equals("")) {
					List<Etudiant> etudiants = this.etudiantsParNiveau(niveau);
					   return etudiants;
				}else {
					List<Etudiant> etudiants = this.etudiantsParSpecialiteNiveau(specialite, niveau);
					   return etudiants;
				}
			}else if(specialite.equals("")) {
					List<Etudiant> etudiants = this.etudiantsParFiliereSpecialite(nom_filiere, specialite);
					   return etudiants;
				}else {
					
				      List<Etudiant> etuds= new ArrayList<Etudiant>();
				       Connection conn=SingletonConnection.getConnection();
				       try {
						PreparedStatement ps= conn.prepareStatement("select * from etudiant where filiere LIKE ? and specialite LIKE ? and niveau LIKE ?");
						ps.setString(1, nom_filiere);
						ps.setString(2, specialite);
						ps.setInt(3, Integer.parseInt(niveau));
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							Etudiant etud = new Etudiant();
							etud.setId(rs.getInt("id"));
							etud.setNom(rs.getString("nom"));
							etud.setImmatricule(rs.getString("immatricule"));
							etud.setFiliere(rs.getString("filiere"));
							etud.setSpecialite(rs.getString("specialite"));
							etud.setNiveau(rs.getInt("niveau"));
							etud.setMoyenne(rs.getFloat("moyenne"));
							etuds.add(etud);								
						}
							
					} catch (SQLException e) {
						
						e.printStackTrace();
					}

						return etuds;
					   }
			
		  
//		return null;
	}

	@Override
	public Etudiant getEtudiant(int id) {
		   
		   Connection conn=SingletonConnection.getConnection();
		   Etudiant etud = new Etudiant();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from etudiant where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if  (rs.next()) {
				
				etud.setId(rs.getInt("id"));
				etud.setNom(rs.getString("nom"));
				etud.setImmatricule(rs.getString("immatricule"));
				etud.setFiliere(rs.getString("filiere"));
				etud.setSpecialite(rs.getString("specialite"));
				etud.setNiveau(rs.getInt("niveau"));
				etud.setMoyenne(rs.getFloat("moyenne"));
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return etud;
	}
	@Override
	public List<Etudiant> getEtudiantParImmatricule(String immatricule) {
		   if(immatricule.equals("")) {
			   List<Etudiant> etudiants = this.listEtudiants();
			   return etudiants;
		   }else {
		   List<Etudiant> etuds= new ArrayList<Etudiant>();
		   Connection conn=SingletonConnection.getConnection();
		   Etudiant etud = new Etudiant();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from etudiant where immatricule = ?");
			ps.setString(1, immatricule);
			ResultSet rs = ps.executeQuery();
			if  (rs.next()) {
				etud.setId(rs.getInt("id"));
				etud.setNom(rs.getString("nom"));
				etud.setImmatricule(rs.getString("immatricule"));
				etud.setFiliere(rs.getString("filiere"));
				etud.setSpecialite(rs.getString("specialite"));
				etud.setNiveau(rs.getInt("niveau"));
				etud.setMoyenne(rs.getFloat("moyenne"));
				etuds.add(etud);
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return etuds;
		   }
	}

	@Override
	public Etudiant updateEtudiant(Etudiant etud) {
		Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("UPDATE etudiant SET nom=?,immatricule=?, filiere=?, specialite=?, niveau=? WHERE id=?");
			ps.setString(1, etud.getNom());
			ps.setString(2, etud.getImmatricule());
			ps.setString(3, etud.getFiliere());
			ps.setString(4, etud.getSpecialite());
			ps.setInt(5, etud.getNiveau());
			ps.setInt(6, etud.getId());
			ps.executeUpdate();
			ps.close();
					
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return etud;
	}

	@Override
	public void deleteEtudiant(int id) {
		 Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("DELETE FROM etudiant WHERE id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	

	

	

	

}