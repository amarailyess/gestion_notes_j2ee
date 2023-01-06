package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.SingletonConnection;
import metier.entities.Etudiant;
import metier.entities.Matiere;
import metier.entities.Note;
import dao.NoteDaoImpl;
public class MatiereDaoImpl implements IMatiereDao{

	@Override
	public Matiere save(Matiere m) {
		// TODO Auto-generated method stub
		 Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("INSERT INTO matiere(code_matiere,nom_matiere,filiere, specialite, niveau,coef_ds, coef_tp,  coef_examen, coef_matiere) VALUES(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, m.getCode_matiere());
			ps.setString(2, m.getNom_matiere());
			ps.setString(3, m.getFiliere());
			ps.setString(4, m.getSpecialite());
			ps.setInt(5, m.getNiveau());
			ps.setFloat(6, m.getCoef_ds());
			ps.setFloat(7, m.getCoef_tp());
			ps.setFloat(8, m.getCoef_examen());
			ps.setFloat(9, m.getCoef_matiere());
			ps.executeUpdate();
			
			
			PreparedStatement ps2= conn.prepareStatement
					("SELECT MAX(id) as MAX_ID FROM matiere");
			ResultSet rs =ps2.executeQuery();
			if (rs.next()) {
				m.setId(rs.getInt("MAX_ID"));
			}
			
			PreparedStatement ps3 = conn.prepareStatement("select * from etudiant where filiere LIKE ? and specialite LIKE ? and niveau LIKE ?");
			ps3.setString(1, m.getFiliere());
			ps3.setString(2, m.getSpecialite());
			ps3.setInt(3, m.getNiveau());
			ResultSet rs_etud = ps3.executeQuery();
			while (rs_etud.next()) {
				INoteDao iNote = new NoteDaoImpl();
				String code_matiere = m.getCode_matiere();
				String nom_matiere = m.getNom_matiere();
				String immatricule = rs_etud.getString("immatricule");
				String nom = rs_etud.getString("nom");
				float ds = 0;
				float tp = 0;
				float examen = 0;
				Note note = iNote.save(new Note(code_matiere, nom_matiere, immatricule, nom, 0, 0, 0));								
			}
			
			
			
			PreparedStatement ps4= conn.prepareStatement("select * from etudiant where filiere LIKE ? and specialite LIKE ? and niveau LIKE ?");
			ps4.setString(1, m.getFiliere());
			ps4.setString(2, m.getSpecialite());
			ps4.setInt(3, m.getNiveau());
			ResultSet rs3 = ps4.executeQuery();
			while (rs3.next()) {
				String immat_etudiant = rs3.getString("immatricule");
				IEtudiantDao iEtudiant = new EtudiantDaoImpl();
				INoteDao iNote = new NoteDaoImpl();
				List<Etudiant> et = iEtudiant.getEtudiantParImmatricule(immat_etudiant);
				List<Note> notes = iNote.noteParEtudiant(immat_etudiant);
				Float som_coef = this.getSomCoef(et.get(0).getFiliere(), et.get(0).getSpecialite(), et.get(0).getNiveau());
				Float moyenne_generale = iNote.calculerMoyenne(immat_etudiant, notes, som_coef);
			}
			
			
			ps.close();
			ps2.close();
			ps3.close();
			ps4.close();
				 
					
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public List<Matiere> listMatieres() {
		// TODO Auto-generated method stub
		 List<Matiere> matieres= new ArrayList<Matiere>();
	       Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from matiere");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Matiere m = new Matiere();
				m.setId(rs.getInt("id"));
				m.setCode_matiere(rs.getString("code_matiere"));
				m.setNom_matiere(rs.getString("nom_matiere"));
				m.setFiliere(rs.getString("filiere"));
				m.setSpecialite(rs.getString("specialite"));
				m.setNiveau(rs.getInt("niveau"));
				m.setCoef_ds(rs.getFloat("coef_ds"));
				m.setCoef_tp(rs.getFloat("coef_tp"));
				m.setCoef_examen(rs.getFloat("coef_examen"));
				m.setCoef_matiere(rs.getFloat("coef_matiere"));
				matieres.add(m);								
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			return matieres;
	}

	@Override
	public List<Matiere> getMatiereParCodeMatiere(String code_matiere) {
		// TODO Auto-generated method stub
		if(code_matiere.equals("")) {
			   List<Matiere> matieres = this.listMatieres();
			   return matieres;
		   }else {
		   List<Matiere> matieres = new ArrayList<Matiere>();
		   Connection conn=SingletonConnection.getConnection();
		   Matiere m = new Matiere();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from matiere where code_matiere = ?");
			ps.setString(1, code_matiere);
			ResultSet rs = ps.executeQuery();
			while  (rs.next()) {
				m.setId(rs.getInt("id"));
				m.setCode_matiere(rs.getString("code_matiere"));
				m.setNom_matiere(rs.getString("nom_matiere"));
				m.setFiliere(rs.getString("filiere"));
				m.setSpecialite(rs.getString("specialite"));
				m.setNiveau(rs.getInt("niveau"));
				m.setCoef_ds(rs.getFloat("coef_ds"));
				m.setCoef_tp(rs.getFloat("coef_tp"));
				m.setCoef_examen(rs.getFloat("coef_examen"));
				m.setCoef_matiere(rs.getFloat("coef_matiere"));
				matieres.add(m);	
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return matieres;
		   }
	}

	@Override
	public List<Matiere> getMatiereParNomMatiere(String nom_matiere) {
		// TODO Auto-generated method stub
		if(nom_matiere.equals("")) {
			   List<Matiere> matieres = this.listMatieres();
			   return matieres;
		   }else {
		   List<Matiere> matieres = new ArrayList<Matiere>();
		   Connection conn=SingletonConnection.getConnection();
		   Matiere m = new Matiere();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from matiere where nom_matiere = ?");
			ps.setString(1, nom_matiere);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				m.setId(rs.getInt("id"));
				m.setCode_matiere(rs.getString("code_matiere"));
				m.setNom_matiere(rs.getString("nom_matiere"));
				m.setFiliere(rs.getString("filiere"));
				m.setSpecialite(rs.getString("specialite"));
				m.setNiveau(rs.getInt("niveau"));
				m.setCoef_ds(rs.getFloat("coef_ds"));
				m.setCoef_tp(rs.getFloat("coef_tp"));
				m.setCoef_examen(rs.getFloat("coef_examen"));
				m.setCoef_matiere(rs.getFloat("coef_matiere"));
				matieres.add(m);	
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return matieres;
		   }
	}

	@Override
	public List<Matiere> matieresParFiliere(String filiere) {
		// TODO Auto-generated method stub
		if(filiere.equals("")) {
			   List<Matiere> matieres = this.listMatieres();
			   return matieres;
		   }else {
		   List<Matiere> matieres = new ArrayList<Matiere>();
		   Connection conn=SingletonConnection.getConnection();
		   
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from matiere where filiere = ?");
			ps.setString(1, filiere);
			ResultSet rs = ps.executeQuery();
			while  (rs.next()) {
				Matiere m = new Matiere();
				m.setId(rs.getInt("id"));
				m.setCode_matiere(rs.getString("code_matiere"));
				m.setNom_matiere(rs.getString("nom_matiere"));
				m.setFiliere(rs.getString("filiere"));
				m.setSpecialite(rs.getString("specialite"));
				m.setNiveau(rs.getInt("niveau"));
				m.setCoef_ds(rs.getFloat("coef_ds"));
				m.setCoef_tp(rs.getFloat("coef_tp"));
				m.setCoef_examen(rs.getFloat("coef_examen"));
				m.setCoef_matiere(rs.getFloat("coef_matiere"));
				matieres.add(m);	
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return matieres;
		   }
	}

	@Override
	public List<Matiere> matieresParSpecialite(String specialite) {
		// TODO Auto-generated method stub
		
		   List<Matiere> matieres = new ArrayList<Matiere>();
		   Connection conn=SingletonConnection.getConnection();
		   
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from matiere where specialite = ?");
			ps.setString(1, specialite);
			ResultSet rs = ps.executeQuery();
			while  (rs.next()) {
				Matiere m = new Matiere();
				m.setId(rs.getInt("id"));
				m.setCode_matiere(rs.getString("code_matiere"));
				m.setNom_matiere(rs.getString("nom_matiere"));
				m.setFiliere(rs.getString("filiere"));
				m.setSpecialite(rs.getString("specialite"));
				m.setNiveau(rs.getInt("niveau"));
				m.setCoef_ds(rs.getFloat("coef_ds"));
				m.setCoef_tp(rs.getFloat("coef_tp"));
				m.setCoef_examen(rs.getFloat("coef_examen"));
				m.setCoef_matiere(rs.getFloat("coef_matiere"));
				matieres.add(m);	
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return matieres;
		   
	}
	@Override
	public List<Matiere> getMatiereParCodeNomMatiere(String code_matiere, String nom_matiere) {
		// TODO Auto-generated method stub
		if(code_matiere.equals("")) {
			if(nom_matiere.equals("")) {
			   List<Matiere> matieres = this.listMatieres();
			   return matieres;
			}else {
				List<Matiere> matieres = this.getMatiereParNomMatiere(nom_matiere);
				   return matieres;
			}
		   }else if(nom_matiere.equals("")) {
			   List<Matiere> matieres = this.getMatiereParCodeMatiere(code_matiere);
			   return matieres;
			}else {
		   List<Matiere> matieres = new ArrayList<Matiere>();
		   Connection conn=SingletonConnection.getConnection();
		   Matiere m = new Matiere();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from matiere where code_matiere=? and nom_matiere = ?");
			ps.setString(1, code_matiere);
			ps.setString(2, nom_matiere);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				m.setId(rs.getInt("id"));
				m.setCode_matiere(rs.getString("code_matiere"));
				m.setNom_matiere(rs.getString("nom_matiere"));
				m.setFiliere(rs.getString("filiere"));
				m.setSpecialite(rs.getString("specialite"));
				m.setNiveau(rs.getInt("niveau"));
				m.setCoef_ds(rs.getFloat("coef_ds"));
				m.setCoef_tp(rs.getFloat("coef_tp"));
				m.setCoef_examen(rs.getFloat("coef_examen"));
				m.setCoef_matiere(rs.getFloat("coef_matiere"));
				matieres.add(m);	
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return matieres;
		   }
	}
	@Override
	public List<Matiere> matieresParFiliereSpecialite(String filiere, String specialite) {
		// TODO Auto-generated method stub
		if(filiere.equals("")) {
			if(specialite.equals("")) {
			   List<Matiere> matieres = this.listMatieres();
			   return matieres;
			}else {
				List<Matiere> matieres = this.matieresParSpecialite(specialite);
				   return matieres;
			}
		   }else if(specialite.equals("")) {
			   List<Matiere> matieres = this.matieresParFiliere(filiere);
			   return matieres;
			}else {
		   List<Matiere> matieres = new ArrayList<Matiere>();
		   Connection conn=SingletonConnection.getConnection();
		   
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from matiere where filiere=? and specialite = ?");
			ps.setString(1, filiere);
			ps.setString(2, specialite);
			ResultSet rs = ps.executeQuery();
			Matiere m = new Matiere();
			while (rs.next()) {
				m.setId(rs.getInt("id"));
				m.setCode_matiere(rs.getString("code_matiere"));
				m.setNom_matiere(rs.getString("nom_matiere"));
				m.setFiliere(rs.getString("filiere"));
				m.setSpecialite(rs.getString("specialite"));
				m.setNiveau(rs.getInt("niveau"));
				m.setCoef_ds(rs.getFloat("coef_ds"));
				m.setCoef_tp(rs.getFloat("coef_tp"));
				m.setCoef_examen(rs.getFloat("coef_examen"));
				m.setCoef_matiere(rs.getFloat("coef_matiere"));
				matieres.add(m);	
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return matieres;
		   }
	}

	@Override
	public Matiere getMatiere(int id) {
		// TODO Auto-generated method stub
		Connection conn=SingletonConnection.getConnection();
		Matiere m = new Matiere();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from matiere where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while  (rs.next()) {
				m.setId(rs.getInt("id"));
				m.setCode_matiere(rs.getString("code_matiere"));
				m.setNom_matiere(rs.getString("nom_matiere"));
				m.setFiliere(rs.getString("filiere"));
				m.setSpecialite(rs.getString("specialite"));
				m.setNiveau(rs.getInt("niveau"));
				m.setCoef_ds(rs.getFloat("coef_ds"));
				m.setCoef_tp(rs.getFloat("coef_tp"));
				m.setCoef_examen(rs.getFloat("coef_examen"));
				m.setCoef_matiere(rs.getFloat("coef_matiere"));
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return m;
	}
	
	@Override
	public Float getSomCoef(String filiere, String specialite, int niveau) {
		// TODO Auto-generated method stub
		Connection conn=SingletonConnection.getConnection();
		Float som_coef=(float) 0;
	       try {
			PreparedStatement ps= conn.prepareStatement("select SUM(coef_matiere) as som_coef from matiere where filiere= ? and specialite=? and niveau=?");
			ps.setString(1, filiere);
			ps.setString(2, specialite);
			ps.setInt(3, niveau);
			ResultSet rs = ps.executeQuery();
			while  (rs.next()) {
				som_coef = rs.getFloat("som_coef");
			}	
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return som_coef;
	}

	@Override
	public Matiere updateMatiere(Matiere m) {
		// TODO Auto-generated method stub
		Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("UPDATE matiere SET code_matiere=?,nom_matiere=?, filiere=?, specialite=?, niveau=?,coef_ds=?,coef_tp=?,coef_examen=?,coef_matiere=? WHERE id=?");
			ps.setString(1, m.getCode_matiere());
			ps.setString(2, m.getNom_matiere());
			ps.setString(3, m.getFiliere());
			ps.setString(4, m.getSpecialite());
			ps.setInt(5, m.getNiveau());
			ps.setFloat(6, m.getCoef_ds());
			ps.setFloat(7, m.getCoef_tp());
			ps.setFloat(8, m.getCoef_examen());
			ps.setFloat(9, m.getCoef_matiere());
			ps.setInt(10, m.getId());
			ps.executeUpdate();
			
			PreparedStatement ps1= conn.prepareStatement("UPDATE note SET moyenne=ds*?+tp*?+examen*? WHERE code_matiere=?");
			ps1.setFloat(1, m.getCoef_ds());
			ps1.setFloat(2, m.getCoef_tp());
			ps1.setFloat(3, m.getCoef_examen());
			ps1.setString(4, m.getCode_matiere());
			ps1.executeUpdate();
			
			PreparedStatement ps2= conn.prepareStatement("select * from etudiant where filiere LIKE ? and specialite LIKE ? and niveau LIKE ?");
			ps2.setString(1, m.getFiliere());
			ps2.setString(2, m.getSpecialite());
			ps2.setInt(3, m.getNiveau());
			ResultSet rs = ps2.executeQuery();
			while (rs.next()) {
				String immat_etudiant = rs.getString("immatricule");
				IEtudiantDao iEtudiant = new EtudiantDaoImpl();
				INoteDao iNote = new NoteDaoImpl();
				List<Etudiant> et = iEtudiant.getEtudiantParImmatricule(immat_etudiant);
				List<Note> notes = iNote.noteParEtudiant(immat_etudiant);
				Float som_coef = this.getSomCoef(et.get(0).getFiliere(), et.get(0).getSpecialite(), et.get(0).getNiveau());
				Float moyenne_generale = iNote.calculerMoyenne(immat_etudiant, notes, som_coef);
			}

			ps.close();
			ps1.close();
			ps2.close();
			
			
					
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public void deleteMatiere(String code_matiere) {
		// TODO Auto-generated method stub
		Connection conn=SingletonConnection.getConnection();
		String filiere = "";
		String specialite = "";
		String immat_etudiant;
		int niveau = 0;
	       try {
			PreparedStatement ps= conn.prepareStatement("DELETE FROM matiere WHERE code_matiere = ?");
			ps.setString(1,code_matiere);
			ps.executeUpdate();
			
			PreparedStatement ps1= conn.prepareStatement("DELETE FROM note WHERE code_matiere = ?");
			ps1.setString(1,code_matiere);
			ps1.executeUpdate();
						
			ps.close();
			ps1.close();
			
			PreparedStatement ps2= conn.prepareStatement("select * from matiere where code_matiere = ?");
			ps2.setString(1, code_matiere);
			ResultSet rs = ps2.executeQuery();
			while  (rs.next()) {
				filiere = rs.getString("filiere");
				specialite = rs.getString("specialite");
				niveau = rs.getInt("niveau");
			}
			Float som_coef = getSomCoef(filiere, specialite,niveau);
			
			
			
			
			PreparedStatement ps3= conn.prepareStatement("select immatricule from etudiant where filiere = ? and specialite=? and niveau=?");
			ps3.setString(1, filiere);
			ps3.setString(2, specialite);
			ps3.setInt(3, niveau);
			ResultSet rs2 = ps3.executeQuery();
			while  (rs2.next()) {
				immat_etudiant = rs.getString("immatricule");
				NoteDaoImpl iNote =  new NoteDaoImpl();
				List<Note> notes = iNote.noteParEtudiant(immat_etudiant);
				Float moyenne_generale = iNote.calculerMoyenne(immat_etudiant, notes, som_coef);
			}
			ps2.close();
			ps3.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Matiere> matieresParFiliereSpecialiteNiveau(String filiere, String specialite, int niveau) {
		// TODO Auto-generated method stub
		List<Matiere> matieres = new ArrayList<Matiere>();
		   Connection conn=SingletonConnection.getConnection();
		try {
			PreparedStatement ps= conn.prepareStatement("select * from matiere where filiere=? and specialite = ? and niveau=?");
			ps.setString(1, filiere);
			ps.setString(2, specialite);
			ps.setInt(3, niveau);
			ResultSet rs = ps.executeQuery();
			Matiere m = new Matiere();
			while (rs.next()) {
				m.setId(rs.getInt("id"));
				m.setCode_matiere(rs.getString("code_matiere"));
				m.setNom_matiere(rs.getString("nom_matiere"));
				m.setFiliere(rs.getString("filiere"));
				m.setSpecialite(rs.getString("specialite"));
				m.setNiveau(rs.getInt("niveau"));
				m.setCoef_ds(rs.getFloat("coef_ds"));
				m.setCoef_tp(rs.getFloat("coef_tp"));
				m.setCoef_examen(rs.getFloat("coef_examen"));
				m.setCoef_matiere(rs.getFloat("coef_matiere"));
				matieres.add(m);	
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return matieres;
	}

	

}
