package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.SingletonConnection;
import metier.entities.Admin;
import web.AppInfosModele;

public class AdminDaoImpl implements IAdminDao{

	@Override
	public Admin save(Admin admin) {
		// TODO Auto-generated method stub
		Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("INSERT INTO admin(immatricule, nom, num_tel, email, role) VALUES(?,?,?,?,?)");
			ps.setString(1, admin.getImmatricule());
			ps.setString(2, admin.getNom());
			ps.setString(3, admin.getRole());
			ps.setString(4, admin.getNum_tel());
			ps.setString(5, admin.getEmail());
			ps.executeUpdate();		
			PreparedStatement ps2= conn.prepareStatement
					("SELECT MAX(id) as MAX_ID FROM admin");
			ResultSet rs =ps2.executeQuery();
			if (rs.next()) {
				admin.setId(rs.getInt("MAX_ID"));
			}
			ps.close();
			ps2.close();		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return admin;
	}

	@Override
	public List<Admin> listAdmins() {
		// TODO Auto-generated method stub
		List<Admin> admins = new ArrayList<Admin>();
	       Connection conn = SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from admin");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Admin admin = new Admin();
				admin.setId(rs.getInt("id"));
				admin.setImmatricule(rs.getString("immatricule"));
				admin.setNom(rs.getString("nom"));
				admin.setRole(rs.getString("role"));
				admin.setNum_tel(rs.getString("num_tel"));
				admin.setEmail(rs.getString("email"));
				admins.add(admin);								
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return admins;
	}

	@Override
	public List<Admin> adminParImmatricule(String immat_admin) {
		// TODO Auto-generated method stub
		if(immat_admin.equals("")) {
			   List<Admin> admins = this.listAdmins();
			   return admins;
		   }else {
		   List<Admin> admins = new ArrayList<Admin>();
		   Connection conn = SingletonConnection.getConnection();
		   Admin admin = new Admin();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from admin where immatricule LIKE ?");
			ps.setString(1, immat_admin);
			ResultSet rs = ps.executeQuery();
			while  (rs.next()) {
				admin.setId(rs.getInt("id"));
				admin.setImmatricule(rs.getString("immatricule"));
				admin.setNom(rs.getString("nom"));
				admin.setRole(rs.getString("role"));
				admin.setNum_tel(rs.getString("num_tel"));
				admin.setEmail(rs.getString("email"));
				admins.add(admin);	
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return admins;
			
		   }
	}

	@Override
	public List<Admin> adminParNom(String nom_admin) {
		// TODO Auto-generated method stub
		if(nom_admin.equals("")) {
			   List<Admin> admins = this.listAdmins();
			   return admins;
		   }else {
		   List<Admin> admins = new ArrayList<Admin>();
		   Connection conn = SingletonConnection.getConnection();
		   Admin admin = new Admin();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from admin where nom LIKE ?");
			ps.setString(1, nom_admin);
			ResultSet rs = ps.executeQuery();
			while  (rs.next()) {
				admin.setId(rs.getInt("id"));
				admin.setImmatricule(rs.getString("immatricule"));
				admin.setNom(rs.getString("nom"));
				admin.setRole(rs.getString("role"));
				admin.setNum_tel(rs.getString("num_tel"));
				admin.setEmail(rs.getString("email"));
				admins.add(admin);	
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return admins;
			
		   }
	}

	@Override
	public List<Admin> adminParImmatriculeNom(String immat_admin, String nom_admin) {
		// TODO Auto-generated method stub
		List<Admin> admins = new ArrayList<Admin>();
		Connection conn = SingletonConnection.getConnection();
		Admin admin = new Admin();
		try {
			
			PreparedStatement ps= conn.prepareStatement("select * from admin where immatricule LIKE ? and nom LIKE ?");
			ps.setString(1, immat_admin);
			ps.setString(2, nom_admin);
			ResultSet rs = ps.executeQuery();
			while  (rs.next()) {
				admin.setId(rs.getInt("id"));
				admin.setImmatricule(rs.getString("immatricule"));
				admin.setNom(rs.getString("nom"));
				admin.setRole(rs.getString("role"));
				admin.setNum_tel(rs.getString("num_tel"));
				admin.setEmail(rs.getString("email"));
				admins.add(admin);
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return admins;
	}

	@Override
	public List<Admin> adminParRole(String role) {
		// TODO Auto-generated method stub
		List<Admin> admins = new ArrayList<Admin>();
		Connection conn = SingletonConnection.getConnection();
		Admin admin = new Admin();
		try {
			
			PreparedStatement ps= conn.prepareStatement("select * from admin where role LIKE ?");
			ps.setString(1, role);
			ResultSet rs = ps.executeQuery();
			while  (rs.next()) {
				admin.setId(rs.getInt("id"));
				admin.setImmatricule(rs.getString("immatricule"));
				admin.setNom(rs.getString("nom"));
				admin.setRole(rs.getString("role"));
				admin.setNum_tel(rs.getString("num_tel"));
				admin.setEmail(rs.getString("email"));
				admins.add(admin);
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return admins;
	}

	@Override
	public Admin getAdmin(int id) {
		// TODO Auto-generated method stub
		Connection conn = SingletonConnection.getConnection();
		Admin admin = new Admin();
		try {
			PreparedStatement ps= conn.prepareStatement("select * from admin where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while  (rs.next()) {
				admin.setId(rs.getInt("id"));
				admin.setImmatricule(rs.getString("immatricule"));
				admin.setNom(rs.getString("nom"));
				admin.setRole(rs.getString("role"));
				admin.setNum_tel(rs.getString("num_tel"));
				admin.setEmail(rs.getString("email"));
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return admin;
	}

	@Override
	public Admin updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("UPDATE admin SET immatricule=?,nom=?,num_tel=?,email=?,role=? WHERE id=?");
			ps.setString(1, admin.getImmatricule());
			ps.setString(2, admin.getNom());
			ps.setString(3, admin.getNum_tel());
			ps.setString(4, admin.getEmail());
			ps.setString(5, admin.getRole());
			ps.setInt(6, admin.getId());
			ps.executeUpdate();
			ps.close();
					
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return admin;
	}

	@Override
	public void deleteAdmin(int id) {
		// TODO Auto-generated method stub
		Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("DELETE FROM admin WHERE id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public AppInfosModele infos() {
		// TODO Auto-generated method stub
		Connection conn=SingletonConnection.getConnection();
		AppInfosModele infos = new AppInfosModele();
		try {
		PreparedStatement ps1= conn.prepareStatement("SELECT count(id) as nb_admin FROM admin");
		ResultSet rs1 =ps1.executeQuery();
		if (rs1.next()) {
			infos.setNb_admins(rs1.getInt("nb_admin"));
		}
		ps1.close();
		
		PreparedStatement ps2= conn.prepareStatement("SELECT count(id) as nb_etudiant FROM etudiant");
		ResultSet rs2 =ps2.executeQuery();
		if (rs2.next()) {
			infos.setNb_etudiants(rs2.getInt("nb_etudiant"));
		}
		ps2.close();
		
		PreparedStatement ps3= conn.prepareStatement("SELECT count(id) as nb_enseignant FROM enseignant");
		ResultSet rs3 =ps3.executeQuery();
		if (rs3.next()) {
			infos.setNb_enseignants(rs3.getInt("nb_enseignant"));
		}
		ps3.close();
		
		PreparedStatement ps4= conn.prepareStatement("SELECT count(id) as nb_matiere FROM matiere");
		ResultSet rs4 =ps4.executeQuery();
		if (rs4.next()) {
			infos.setNb_matieres(rs4.getInt("nb_matiere"));
		}
		ps4.close();
		
		PreparedStatement ps5= conn.prepareStatement("SELECT count(id) as nb_note FROM note");
		ResultSet rs5 =ps5.executeQuery();
		if (rs5.next()) {
			infos.setNb_notes(rs5.getInt("nb_note"));
		}
		ps5.close();
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return infos;
	}

	

}
