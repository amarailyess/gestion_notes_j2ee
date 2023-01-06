package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import metier.SingletonConnection;
import metier.entities.Etudiant;
import metier.entities.Matiere;
import metier.entities.Note;

public class NoteDaoImpl implements INoteDao {

	@Override
	public Note save(Note n) {
		// TODO Auto-generated method stub
		Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("INSERT INTO note(code_matiere, nom_matiere,immat_etudiant,nom_etudiant, ds, tp, examen, moyenne) VALUES(?,?,?,?,?,?,?,0)");
			ps.setString(1, n.getCode_matiere());
			ps.setString(2, n.getNom_matiere());
			ps.setString(3, n.getImmat_etudiant());
			ps.setString(4, n.getNom_etudiant());
			ps.setFloat(5, n.getDs());
			ps.setFloat(6, n.getTp());
			ps.setFloat(7, n.getExamen());
			ps.executeUpdate();
			PreparedStatement ps2= conn.prepareStatement
					("SELECT MAX(id) as MAX_ID FROM note");
			ResultSet rs =ps2.executeQuery();
			if (rs.next()) {
				n.setId(rs.getInt("MAX_ID"));
			}
			ps.close();
			ps2.close();
				 
					
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public List<Note> noteParEtudiant(String immat_etudiant) {
		// TODO Auto-generated method stub
		List<Note> notes= new ArrayList<Note>();
	       Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from note where immat_etudiant LIKE ? GROUP BY code_matiere");
			ps.setString(1, immat_etudiant);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Note note = new Note();
				
				note.setId(rs.getInt("id"));
				note.setCode_matiere(rs.getString("code_matiere"));
				note.setNom_matiere(rs.getString("nom_matiere"));
				note.setImmat_etudiant(rs.getString("immat_etudiant"));
				note.setNom_etudiant(rs.getString("nom_etudiant"));
				note.setDs(rs.getFloat("ds"));
				note.setTp(rs.getFloat("tp"));
				note.setExamen(rs.getFloat("examen"));
				note.setMoyenne(rs.getFloat("moyenne"));
				notes.add(note);								
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			return notes;
	}

	@Override
	public List<Note> noteParMatiere(String code_matiere) {
		// TODO Auto-generated method stub
		List<Note> notes= new ArrayList<Note>();
	       Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from note where code_matiere LIKE ? ");
			ps.setString(1, code_matiere);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Note note = new Note();
				note.setId(rs.getInt("id"));
				note.setCode_matiere(rs.getString("code_matiere"));
				note.setNom_matiere(rs.getString("nom_matiere"));
				note.setImmat_etudiant(rs.getString("immat_etudiant"));
				note.setNom_etudiant(rs.getString("nom_etudiant"));
				note.setDs(rs.getFloat("ds"));
				note.setTp(rs.getFloat("tp"));
				note.setExamen(rs.getFloat("examen"));
				note.setMoyenne(rs.getFloat("moyenne"));
				notes.add(note);								
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			return notes;
	}

	@Override
	public Note updateNote(Note n) {
		// TODO Auto-generated method stub
		Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("UPDATE note set code_matiere=?, nom_matiere=?,immat_etudiant=?, nom_etudiant=?, ds=?, tp=?, examen=?, moyenne=? where id=?");
			ps.setString(1, n.getCode_matiere());
			ps.setString(2, n.getNom_matiere());
			ps.setString(3, n.getImmat_etudiant());
			ps.setString(4, n.getNom_etudiant());
			ps.setFloat(5, n.getDs());
			ps.setFloat(6, n.getTp());
			ps.setFloat(7, n.getExamen());
			ps.setFloat(8, n.getMoyenne());
			ps.setInt(9, n.getId());
			ps.executeUpdate();
			ps.close();			 
					
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public void deleteNote(int id) {
		// TODO Auto-generated method stub
		 Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("DELETE FROM note WHERE id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Note> listNotes() {
		// TODO Auto-generated method stub
		List<Note> notes= new ArrayList<Note>();
	       Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from note");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Note note = new Note();
				note.setId(rs.getInt("id"));
				note.setCode_matiere(rs.getString("code_matiere"));
				note.setNom_matiere(rs.getString("nom_matiere"));
				note.setImmat_etudiant(rs.getString("immat_etudiant"));
				note.setNom_etudiant(rs.getString("nom_etudiant"));
				note.setDs(rs.getFloat("ds"));
				note.setTp(rs.getFloat("tp"));
				note.setExamen(rs.getFloat("examen"));
				note.setMoyenne(rs.getFloat("moyenne"));
				notes.add(note);								
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			return notes;
	}

	@Override
	public Note getNote(int id) {
		// TODO Auto-generated method stub
		Connection conn=SingletonConnection.getConnection();
		   Note note = new Note();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from note where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if  (rs.next()) {
				
				note.setId(rs.getInt("id"));
				note.setCode_matiere(rs.getString("code_matiere"));
				note.setNom_matiere(rs.getString("nom_matiere"));
				note.setImmat_etudiant(rs.getString("immat_etudiant"));
				note.setNom_etudiant(rs.getString("nom_etudiant"));
				note.setDs(rs.getFloat("ds"));
				note.setTp(rs.getFloat("tp"));
				note.setExamen(rs.getFloat("examen"));
				note.setMoyenne(rs.getFloat("moyenne"));
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return note;
	}

	@Override
	public List<Note> noteEtudiantParCodeMatiere(String immat_etudiant, String code_matiere) {
		// TODO Auto-generated method stub
		if(code_matiere.equals("")) {
			return this.noteParEtudiant(immat_etudiant);
		}else {
		List<Note> notes= new ArrayList<Note>();
	       Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from note where immat_etudiant LIKE ? and code_matiere LIKE ?");
			ps.setString(1, immat_etudiant);
			ps.setString(2, code_matiere);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Note note = new Note();
				note.setId(rs.getInt("id"));
				note.setCode_matiere(rs.getString("code_matiere"));
				note.setNom_matiere(rs.getString("nom_matiere"));
				note.setImmat_etudiant(rs.getString("immat_etudiant"));
				note.setNom_etudiant(rs.getString("nom_etudiant"));
				note.setDs(rs.getFloat("ds"));
				note.setTp(rs.getFloat("tp"));
				note.setExamen(rs.getFloat("examen"));
				note.setMoyenne(rs.getFloat("moyenne"));
				notes.add(note);								
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			return notes;
		}
	}

	@Override
	public List<Note> noteEtudiantParNomMatiere(String immat_etudiant, String nom_matiere) {
		// TODO Auto-generated method stub
		if(nom_matiere.equals("")) {
			return this.noteParEtudiant(immat_etudiant);
		}else {
		List<Note> notes= new ArrayList<Note>();
	       Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from note where immat_etudiant LIKE ? and nom_matiere LIKE ?");
			ps.setString(1, immat_etudiant);
			ps.setString(2, nom_matiere);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Note note = new Note();
				note.setId(rs.getInt("id"));
				note.setCode_matiere(rs.getString("code_matiere"));
				note.setNom_matiere(rs.getString("nom_matiere"));
				note.setImmat_etudiant(rs.getString("immat_etudiant"));
				note.setNom_etudiant(rs.getString("nom_etudiant"));
				note.setDs(rs.getFloat("ds"));
				note.setTp(rs.getFloat("tp"));
				note.setExamen(rs.getFloat("examen"));
				note.setMoyenne(rs.getFloat("moyenne"));
				notes.add(note);								
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			return notes;
		}
	}

	@Override
	public float calculerMoyenne(String immat_etudiant, List<Note> notes, float som_coef) {
		// TODO Auto-generated method stub
		float sum = 0;
		float coef_matiere=0;
		float moyenne_generale = 0;
		 Connection conn=SingletonConnection.getConnection();
		for (Note note: notes) {
			float moy_mat = note.getMoyenne();
			String code_matiere = note.getCode_matiere();
			try {
				PreparedStatement ps= conn.prepareStatement("select coef_matiere from matiere where code_matiere = ?");
				ps.setString(1, code_matiere);
				ResultSet rs = ps.executeQuery();
				if  (rs.next()) {
					coef_matiere = rs.getFloat("coef_matiere");
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			sum += moy_mat*coef_matiere;
			
		}
		float res = sum/som_coef;
		moyenne_generale =(float) ((float) Math.round(res * 100.0) / 100.0);
		try {
			PreparedStatement ps1= conn.prepareStatement("UPDATE etudiant set moyenne=? where immatricule=?");
			ps1.setFloat(1, moyenne_generale);
			ps1.setString(2, immat_etudiant);
			ps1.executeUpdate();
			ps1.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return moyenne_generale;
	}


	@Override
	public List<Note> noteParFiliere(String filiere) {
		// TODO Auto-generated method stub
		List<Note> notes = new ArrayList<Note>();
	       Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from etudiant where filiere LIKE ?");
			ps.setString(1, filiere);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String immat_etudiant = rs.getString("immatricule");
			    List<Note> notes_et = noteParEtudiant(immat_etudiant);	
			    for(Note note:notes_et) {
			    	notes.add(note);
			    }
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			return notes;
	}

	@Override
	public List<Note> noteParSpecialite(String specialite) {
		// TODO Auto-generated method stub
		List<Note> notes = new ArrayList<Note>();
	       Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from etudiant where specialite LIKE ?");
			ps.setString(1, specialite);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			    String immat_etudiant = rs.getString("immatricule");
			    List<Note> notes_et = noteParEtudiant(immat_etudiant);	
			    for(Note note:notes_et) {
			    	notes.add(note);
			    }
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			return notes;
	}

	@Override
	public List<Note> noteParNiveau(int niveau) {
		// TODO Auto-generated method stub
		List<Note> notes = new ArrayList<Note>();
	       Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from etudiant where niveau LIKE ?");
			ps.setInt(1, niveau);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			    String immat_etudiant = rs.getString("immatricule");
			    List<Note> notes_et = noteParEtudiant(immat_etudiant);	
			    for(Note note:notes_et) {
			    	notes.add(note);
			    }
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			return notes;
	}
	

	@Override
	public List<Note> noteParFiliereSpecialite(String filiere, String specialite) {
		// TODO Auto-generated method stub
		List<Note> notes = new ArrayList<Note>();
	       Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from etudiant where filiere LIKE ? and specialite LIKE ?");
			ps.setString(1, filiere);
			ps.setString(2, specialite);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			    String immat_etudiant = rs.getString("immatricule");
			    List<Note> notes_et = noteParEtudiant(immat_etudiant);	
			    for(Note note:notes_et) {
			    	notes.add(note);
			    }
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			return notes;
			
			
	}

	@Override
	public List<Note> noteParFiliereNiveau(String filiere, String niveau) {
		// TODO Auto-generated method stub
		List<Note> notes = new ArrayList<Note>();
	       Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from etudiant where filiere LIKE ? and niveau LIKE ?");
			ps.setString(1, filiere);
			ps.setInt(2, Integer.parseInt(niveau));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			    String immat_etudiant = rs.getString("immatricule");
			    List<Note> notes_et = noteParEtudiant(immat_etudiant);	
			    for(Note note:notes_et) {
			    	notes.add(note);
			    }
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			return notes;
			
	}

	@Override
	public List<Note> noteParSpecialiteNiveau(String specialite, String niveau) {
		// TODO Auto-generated method stub
		List<Note> notes = new ArrayList<Note>();
	       Connection conn=SingletonConnection.getConnection();
	       try {
			PreparedStatement ps= conn.prepareStatement("select * from etudiant where specialite LIKE ? niveau LIKE ?");
			ps.setString(1, specialite);
			ps.setInt(2, Integer.parseInt(niveau));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			    String immat_etudiant = rs.getString("immatricule");
			    List<Note> notes_et = noteParEtudiant(immat_etudiant);	
			    for(Note note:notes_et) {
			    	notes.add(note);
			    }
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			return notes;
	}
	
	@Override
	public List<Note> noteParFiliereSpecialiteNiveau(String filiere, String specialite, String niveau) {
		// TODO Auto-generated method stub
		if(niveau.equals("")) {
			if(filiere.equals("")) {
				if(specialite.equals("")) {
					List<Note> notes = this.listNotes();
					   return notes;
				}else {
					List<Note> notes = this.noteParSpecialite(specialite);
					   return notes;
				}
			}else if(specialite.equals("")) {
					List<Note> notes = this.noteParFiliere(filiere);
					   return notes;
				}else {
					List<Note> notes = this.noteParFiliereSpecialite(filiere,specialite);
					   return notes;
				}
			}else if(filiere.equals("")) {
				if(specialite.equals("")) {
					List<Note> notes = this.noteParNiveau(Integer.parseInt(niveau));
					   return notes;
				}else {
					List<Note> notes = this.noteParSpecialiteNiveau(specialite, niveau);
					   return notes;
				}
			}else{
					List<Note> notes = new ArrayList<Note>();
				       Connection conn=SingletonConnection.getConnection();
				       try {
						PreparedStatement ps= conn.prepareStatement("select * from etudiant where filiere LIKE ? specialite LIKE ? niveau LIKE ?");
						ps.setString(1, filiere);
						ps.setString(2, specialite);
						ps.setInt(3, Integer.parseInt(niveau));
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
						    String immat_etudiant = rs.getString("immatricule");
						    List<Note> notes_et = noteParEtudiant(immat_etudiant);	
						    for(Note note:notes_et) {
						    	notes.add(note);
						    }
						    
						}
							
					} catch (SQLException e) {
						
						e.printStackTrace();
					}

						return notes;
				}
	}
}
