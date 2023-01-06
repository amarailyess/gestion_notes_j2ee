package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import dao.IEtudiantDao;
import dao.IMatiereDao;
import dao.INoteDao;
import dao.MatiereDaoImpl;
import dao.NoteDaoImpl;
import dao.AdminDaoImpl;
import dao.EnseignantImpl;
import dao.EtudiantDaoImpl;
import dao.IAdminDao;
import dao.IEnseignantDao;
import metier.entities.Admin;
import metier.entities.Enseignant;
import metier.entities.Etudiant;
import metier.entities.Matiere;
import metier.entities.Note;

@WebServlet (name="cs",urlPatterns= {"/controleur","*.do"})
public class ControleurServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IEtudiantDao iEtudiant;
	INoteDao iNote;
	IMatiereDao iMatiere;
	IEnseignantDao iEnseignant;
	IAdminDao iAdmin;
	 @Override
	public void init() throws ServletException {
		 iEtudiant = new EtudiantDaoImpl();
		 iNote = new NoteDaoImpl();
		 iMatiere = new MatiereDaoImpl();
		 iEnseignant =  new EnseignantImpl();
		 iAdmin = new AdminDaoImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			             HttpServletResponse response) 
			            throws ServletException, IOException {
		String path=request.getServletPath();
			
		//request.getRequestDispatcher("etudiants.jsp").forward(request,response);
		if (path.equals("/index.do")  )
		{
			AppInfosModele model = iAdmin.infos();
			request.setAttribute("model", model);	
			request.getRequestDispatcher("public_html/index.jsp").forward(request,response);
		}
		else if (path.equals("/etudiants.do"))
		{
			EtudiantModele model= new EtudiantModele();
			List<Etudiant> etudiants = iEtudiant.listEtudiants();
			model.setEtudiants(etudiants);
			request.setAttribute("model", model);	
			request.getRequestDispatcher("public_html/etudiant/etudiants.jsp").forward(request,response);
			
		}
		else if (path.equals("/chercher_etudiant_filiere_niveau.do"))
		{
			String filiere=request.getParameter("filiere");
			String niveau = request.getParameter("niveau");
			EtudiantModele model= new EtudiantModele();
			model.setFiliere(filiere);
			List<Etudiant> etds = iEtudiant.etudiantsParFiliereNiveau(filiere,niveau);
			model.setEtudiants(etds);
			request.setAttribute("model", model);
			request.getRequestDispatcher("public_html/etudiant/etudiants.jsp").forward(request,response);
		}
		else if (path.equals("/chercher_etudiant_immatricule.do"))
		{
			String immatricule=request.getParameter("immatricule");
			EtudiantModele model= new EtudiantModele();
			model.setImmatricule(immatricule);
			List<Etudiant> etds = iEtudiant.getEtudiantParImmatricule(immatricule);
			model.setEtudiants(etds);
			request.setAttribute("model", model);
			request.getRequestDispatcher("public_html/etudiant/etudiants.jsp").forward(request,response);
		}
		else if (path.equals("/saisie_etudiant.do")  )
		{
			request.getRequestDispatcher("public_html/etudiant/saisieEtudiant.jsp").forward(request,response);
		}
		else if (path.equals("/sauv_etudiant.do")  && request.getMethod().equals("POST"))
		{
		    String nom=request.getParameter("nom");
			String immatricule = request.getParameter("immatricule");
			String filiere = request.getParameter("filiere");
			String specialite = request.getParameter("specialite");
			int niveau = Integer.parseInt(request.getParameter("niveau"));
			Etudiant et = iEtudiant.save(new Etudiant(nom,immatricule,filiere, specialite,niveau));
			request.setAttribute("etudiant", et);
			request.getRequestDispatcher("public_html/etudiant/confirmationSaisieEtudiant.jsp").forward(request,response);
			
		}
		else if (path.equals("/supprimer_etudiant.do"))
		{
		    int id= Integer.parseInt(request.getParameter("id"));
		    iEtudiant.deleteEtudiant(id);
		    response.sendRedirect("index.do");
					
			//request.getRequestDispatcher("confirmation.jsp").forward(request,response);
		}
		else if (path.equals("/editer_etudiant.do")  )
		{
			int id= Integer.parseInt(request.getParameter("id"));
			Etudiant et = iEtudiant.getEtudiant(id);
		    request.setAttribute("etudiant", et);
			request.getRequestDispatcher("public_html/etudiant/editerEtudiant.jsp").forward(request,response);
		}
		else if (path.equals("/modifier_etudiant.do")  )
		{
			int id= Integer.parseInt(request.getParameter("id"));
			String nom=request.getParameter("nom");
			String immatricule = request.getParameter("immatricule");
			String filiere = request.getParameter("filiere");
			String specialite = request.getParameter("specialite");
			int niveau = Integer.parseInt(request.getParameter("niveau"));
			 Etudiant et = new Etudiant();
			 et.setId(id);
			 et.setNom(nom);
			 et.setImmatricule(immatricule);
			 et.setFiliere(filiere);
			 et.setSpecialite(specialite);
			 et.setNiveau(niveau);
			 iEtudiant.updateEtudiant(et);
			 request.setAttribute("etudiant", et);
			 request.getRequestDispatcher("public_html/etudiant/confirmationSaisieEtudiant.jsp").forward(request,response);
			 
		}else if(path.equals("/notes_etudiant.do") ) {
			String immat_etudiant =request.getParameter("immatricule");
			List<Etudiant> et = iEtudiant.getEtudiantParImmatricule(immat_etudiant);
			NoteModele model= new NoteModele();
			List<Note> notes = iNote.noteParEtudiant(immat_etudiant);
			model.setNotes(notes);
			model.setImmat_etudiant(immat_etudiant);
			model.setNom_etudiant(et.get(0).getNom());
			model.setFiliere(et.get(0).getFiliere());
			model.setMoyenne_generale(et.get(0).getMoyenne());
			request.setAttribute("model", model);
			request.getRequestDispatcher("public_html/note/notes_etudiant.jsp").forward(request,response);
			
		}else if(path.equals("/editer_note.do")) {
			int id= Integer.parseInt(request.getParameter("id"));
			String code_matiere =  request.getParameter("code_matiere");
			Note note = iNote.getNote(id);
			List<Matiere> matieres = iMatiere.getMatiereParCodeMatiere(code_matiere);
		    request.setAttribute("note", note);
		    request.setAttribute("matiere", matieres.get(0));
			request.getRequestDispatcher("public_html/note/editerNote.jsp").forward(request,response);
		}
		else if (path.equals("/modifier_note.do")  )
		{
			int id= Integer.parseInt(request.getParameter("id"));
			String immat_etudiant =request.getParameter("immat_etudiant");
			String nom_etudiant = request.getParameter("nom_etudiant");
			String code_matiere = request.getParameter("code_matiere");
			String nom_matiere = request.getParameter("nom_matiere");
			float ds = Float.parseFloat(request.getParameter("ds"));
			float tp = Float.parseFloat(request.getParameter("tp"));
			float examen = Float.parseFloat(request.getParameter("examen"));
			float moyenne = Float.parseFloat(request.getParameter("moyenne"));
			Note note = new Note();
			note.setId(id);
			note.setCode_matiere(code_matiere);
			note.setNom_matiere(nom_matiere);
			note.setImmat_etudiant(immat_etudiant);
			note.setNom_etudiant(nom_etudiant);
			note.setDs(ds);
			note.setTp(tp);
			note.setExamen(examen);
			note.setMoyenne(moyenne);
			 iNote.updateNote(note);
			
			List<Etudiant> et = iEtudiant.getEtudiantParImmatricule(immat_etudiant);
			List<Note> notes = iNote.noteParEtudiant(immat_etudiant);
			Float som_coef = iMatiere.getSomCoef(et.get(0).getFiliere(), et.get(0).getSpecialite(), et.get(0).getNiveau());
			Float moyenne_generale = iNote.calculerMoyenne(immat_etudiant, notes, som_coef);
			 
			request.setAttribute("note", note);
			 request.getRequestDispatcher("public_html/note/confirmationSaisieNote.jsp").forward(request,response);
			 
		}else if(path.equals("/calculerMoyenne.do")) {
			String immat_etudiant =request.getParameter("immat_etudiant");
			List<Etudiant> et = iEtudiant.getEtudiantParImmatricule(immat_etudiant);
			NoteModele model= new NoteModele();
			List<Note> notes = iNote.noteParEtudiant(immat_etudiant);
			Float som_coef = iMatiere.getSomCoef(et.get(0).getFiliere(), et.get(0).getSpecialite(), et.get(0).getNiveau());
			Float moyenne_generale = iNote.calculerMoyenne(immat_etudiant, notes, som_coef);
			List<Etudiant> et1 = iEtudiant.getEtudiantParImmatricule(immat_etudiant);
			model.setNotes(notes);
			model.setImmat_etudiant(immat_etudiant);
			model.setNom_etudiant(et1.get(0).getNom());
			model.setFiliere(et1.get(0).getFiliere());
			model.setMoyenne_generale(et1.get(0).getMoyenne());
			request.setAttribute("model", model);
			request.getRequestDispatcher("public_html/note/notes_etudiant.jsp").forward(request,response);
		}
		else if (path.equals("/chercher_note_codeMatiere.do"))
		{
			String code_matiere=request.getParameter("code_matiere");
			String immat_etudiant=request.getParameter("immat_etudiant");
			List<Etudiant> et = iEtudiant.getEtudiantParImmatricule(immat_etudiant);
			NoteModele model= new NoteModele();
			model.setCode_matiere(code_matiere);
			List<Note> notes = iNote.noteEtudiantParCodeMatiere(immat_etudiant, code_matiere) ;
			List<Etudiant> et1 = iEtudiant.getEtudiantParImmatricule(immat_etudiant);
			model.setNotes(notes);
			model.setImmat_etudiant(immat_etudiant);
			model.setNom_etudiant(et.get(0).getNom());
			model.setMoyenne_generale(et1.get(0).getMoyenne());
			request.setAttribute("model", model);
			request.getRequestDispatcher("public_html/note/notes_etudiant.jsp").forward(request,response);
			
		}else if (path.equals("/chercher_note_nomMatiere.do"))
		{
			String nom_matiere=request.getParameter("nom_matiere");
			String immat_etudiant=request.getParameter("immat_etudiant");
			List<Etudiant> et = iEtudiant.getEtudiantParImmatricule(immat_etudiant);
			NoteModele model= new NoteModele();
			model.setNom_matiere(nom_matiere);
			List<Note> notes = iNote.noteEtudiantParNomMatiere(immat_etudiant, nom_matiere) ;
			List<Etudiant> et1 = iEtudiant.getEtudiantParImmatricule(immat_etudiant);
			model.setNotes(notes);
			model.setImmat_etudiant(immat_etudiant);
			model.setNom_etudiant(et.get(0).getNom());
			model.setMoyenne_generale(et1.get(0).getMoyenne());
			request.setAttribute("model", model);
			request.getRequestDispatcher("public_html/note/notes_etudiant.jsp").forward(request,response);
			
		}else if (path.equals("/saisie_note.do")  )
		{
			String immat_etudiant = request.getParameter("immat_etudiant");
			String nom_etudiant = request.getParameter("nom_etudiant");
			request.setAttribute("immat_etudiant", immat_etudiant);
			request.setAttribute("nom_etudiant", nom_etudiant);
			request.getRequestDispatcher("public_html/note/saisieNote.jsp").forward(request,response);
			
		}else if (path.equals("/sauv_notes.do")  && request.getMethod().equals("POST"))
		{
		    String nom_matiere=request.getParameter("nom_matiere");
		    String code_matiere=request.getParameter("code_matiere");
			String immat_etudiant = request.getParameter("immat_etudiant");
			String nom_etudiant = request.getParameter("nom_etudiant");
			Float ds = Float.parseFloat(request.getParameter("ds"));
			Float tp = Float.parseFloat(request.getParameter("tp"));
			Float examen = Float.parseFloat(request.getParameter("examen"));
			Note note = iNote.save(new Note(code_matiere, nom_matiere, immat_etudiant,nom_etudiant, ds, tp, examen));
			request.setAttribute("note", note);
			request.getRequestDispatcher("public_html/note/confirmationSaisieNote.jsp").forward(request,response);
			
		}else if (path.equals("/supprimer_note_etudiant.do"))
		{
		    int id= Integer.parseInt(request.getParameter("id"));
		    String immat_etudiant = request.getParameter("immat_etudiant");
		    iNote.deleteNote(id);
		    response.sendRedirect("notes_etudiant.do?immatricule="+immat_etudiant);
					
			//request.getRequestDispatcher("confirmation.jsp").forward(request,response);
		}else if (path.equals("/matiere.do"))
		{
			MatiereModele model= new MatiereModele();
			List<Matiere> matieres = iMatiere.listMatieres();
			model.setMatieres(matieres);
			request.setAttribute("model", model);	
			request.getRequestDispatcher("public_html/matiere/matiere.jsp").forward(request,response);
			
		}
		else if (path.equals("/editer_matiere.do")  )
		{
			int id= Integer.parseInt(request.getParameter("id"));
			Matiere m = iMatiere.getMatiere(id);
		    request.setAttribute("matiere", m);
			request.getRequestDispatcher("public_html/matiere/editerMatiere.jsp").forward(request,response);
			
		}else if (path.equals("/supprimer_matiere.do"))
		{
		    String code_matiere = request.getParameter("code_matiere");
		    iMatiere.deleteMatiere(code_matiere);
		    response.sendRedirect("matiere.do");
					
			//request.getRequestDispatcher("confirmation.jsp").forward(request,response);
		}else if (path.equals("/chercher_matiere_code_nom.do"))
		{
			String code_matiere =request.getParameter("code_matiere");
			String nom_matiere = request.getParameter("nom_matiere");
			MatiereModele model= new MatiereModele();
			model.setCode_matiere(code_matiere);
			model.setNom_matiere(nom_matiere);
			List<Matiere> matieres = iMatiere.getMatiereParCodeNomMatiere(code_matiere,nom_matiere);
			model.setMatieres(matieres);
			request.setAttribute("model", model);
			request.getRequestDispatcher("public_html/matiere/matiere.jsp").forward(request,response);
			
		}else if (path.equals("/chercher_matiere_filiere_specialite.do"))
		{
			String filiere =request.getParameter("filiere");
			String specialite = request.getParameter("specialite");
			MatiereModele model= new MatiereModele();
			model.setFiliere(filiere);
			model.setSpecialite(specialite);
			List<Matiere> matieres = iMatiere.matieresParFiliereSpecialite(filiere, specialite);
			model.setMatieres(matieres);
			request.setAttribute("model", model);
			request.getRequestDispatcher("public_html/matiere/matiere.jsp").forward(request,response);
		}else if (path.equals("/saisie_matiere.do")  )
		{
			request.getRequestDispatcher("public_html/matiere/saisieMatiere.jsp").forward(request,response);
			
		}else if (path.equals("/sauv_matiere.do")  && request.getMethod().equals("POST"))
		{
		    String nom_matiere=request.getParameter("nom_matiere");
		    String code_matiere=request.getParameter("code_matiere");
			String filiere = request.getParameter("filiere");
			String specialite = request.getParameter("specialite");
			int niveau = Integer.parseInt(request.getParameter("niveau"));
			Float coef_ds = Float.parseFloat(request.getParameter("coef_ds"));
			Float coef_tp = Float.parseFloat(request.getParameter("coef_tp"));
			Float coef_examen = Float.parseFloat(request.getParameter("coef_examen"));
			Float coef_matiere = Float.parseFloat(request.getParameter("coef_matiere"));
			Matiere matiere = iMatiere.save(new Matiere(code_matiere, nom_matiere, filiere, specialite, niveau,coef_ds, coef_tp, coef_examen, coef_matiere));
			request.setAttribute("matiere", matiere);
			request.getRequestDispatcher("public_html/matiere/confirmationSaisieMatiere.jsp").forward(request,response);
			
		}else if (path.equals("/modifier_matiere.do")  )
		{
			int id= Integer.parseInt(request.getParameter("id"));
			String nom_matiere=request.getParameter("nom_matiere");
		    String code_matiere=request.getParameter("code_matiere");
			String filiere = request.getParameter("filiere");
			String specialite = request.getParameter("specialite");
			int niveau = Integer.parseInt(request.getParameter("niveau"));
			Float coef_ds = Float.parseFloat(request.getParameter("coef_ds"));
			Float coef_tp = Float.parseFloat(request.getParameter("coef_tp"));
			Float coef_examen = Float.parseFloat(request.getParameter("coef_examen"));
			Float coef_matiere = Float.parseFloat(request.getParameter("coef_matiere"));
			Matiere matiere = iMatiere.updateMatiere(new Matiere(id,code_matiere, nom_matiere, filiere, specialite, niveau,coef_ds, coef_tp, coef_examen, coef_matiere));
			request.setAttribute("matiere", matiere);
			request.getRequestDispatcher("public_html/matiere/confirmationSaisieMatiere.jsp").forward(request,response);
			 
		}else if (path.equals("/enseignant.do"))
		{
			EnseignantModele model= new EnseignantModele();
			List<Enseignant> enseignants = iEnseignant.listEnseignants();
			model.setEnseignants(enseignants);
			request.setAttribute("model", model);	
			request.getRequestDispatcher("public_html/enseignant/enseignant.jsp").forward(request,response);
			
		}else if (path.equals("/saisie_enseignant.do")  )
		{
			request.getRequestDispatcher("public_html/enseignant/saisieEnseignant.jsp").forward(request,response);
			
		}
		else if (path.equals("/editer_enseignant.do")  )
		{
			int id= Integer.parseInt(request.getParameter("id"));
			Enseignant ens = iEnseignant.getEnseignant(id);
		    request.setAttribute("enseignant", ens);
			request.getRequestDispatcher("public_html/enseignant/editerEnseignant.jsp").forward(request,response);
			
		}else if (path.equals("/supprimer_enseignant.do"))
		{
		    int id= Integer.parseInt(request.getParameter("id"));
		    iEnseignant.deleteEnseignant(id);
		    response.sendRedirect("enseignant.do");
					
			//request.getRequestDispatcher("confirmation.jsp").forward(request,response);
		}else if (path.equals("/chercher_enseignant_immatricule_nom.do"))
		{
			String immatricule =request.getParameter("immatricule");
			String nom = request.getParameter("nom");
			EnseignantModele model= new EnseignantModele();
			model.setImmatricule(immatricule);
			model.setNom(nom);
			List<Enseignant> enseignant = iEnseignant.enseignantParImmatriculeNom(immatricule, nom);
			model.setEnseignants(enseignant);
			request.setAttribute("model", model);
			request.getRequestDispatcher("public_html/enseignant/enseignant.jsp").forward(request,response);
			
		}else if (path.equals("/chercher_enseignant_departement_specialite.do"))
		{
			String departement =request.getParameter("departement");
			String specialite = request.getParameter("specialite");
			EnseignantModele model= new EnseignantModele();
			model.setDepartement(departement);
			model.setSpecialite(specialite);
			List<Enseignant> enseignant = iEnseignant.enseignantParDepartementSpecialite(departement, specialite);
			model.setEnseignants(enseignant);
			request.setAttribute("model", model);
			request.getRequestDispatcher("public_html/enseignant/enseignant.jsp").forward(request,response);
			
		}else if (path.equals("/sauv_enseignant.do")  && request.getMethod().equals("POST"))
		{
		    String immatricule=request.getParameter("immatricule");
		    String nom=request.getParameter("nom");
			String departement = request.getParameter("departement");
			String specialite = request.getParameter("specialite");
			String degree = request.getParameter("degree");
			String num_tel = request.getParameter("num_tel");
			String email = request.getParameter("email");
			Enseignant enseignant = iEnseignant.save(new Enseignant(immatricule, nom, departement, specialite,degree, num_tel, email));
			request.setAttribute("enseignant", enseignant);
			request.getRequestDispatcher("public_html/enseignant/confirmationSaisieEnseignant.jsp").forward(request,response);
			
		}else if (path.equals("/modifier_enseignant.do")  )
		{
			int id= Integer.parseInt(request.getParameter("id"));
			String immatricule=request.getParameter("immatricule");
		    String nom=request.getParameter("nom");
			String departement = request.getParameter("departement");
			String specialite = request.getParameter("specialite");
			String degree = request.getParameter("degree");
			String num_tel = request.getParameter("num_tel");
			String email = request.getParameter("email");
			Enseignant enseignant = iEnseignant.updateEnseignant(new Enseignant(id,immatricule, nom, departement, specialite,degree, num_tel, email));
			request.setAttribute("enseignant", enseignant);
			request.getRequestDispatcher("public_html/enseignant/confirmationSaisieEnseignant.jsp").forward(request,response);
			 
		}
		else if (path.equals("/moyennes.do"))
		{
			EtudiantModele model= new EtudiantModele();
			List<Etudiant> etudiants = iEtudiant.listEtudiants();
			model.setEtudiants(etudiants);
			request.setAttribute("model", model);	
			request.getRequestDispatcher("public_html/note/moyennes.jsp").forward(request,response);
			
		}else if (path.equals("/chercher_moyenne_immatricule.do"))
		{
			String immatricule=request.getParameter("immatricule");
			EtudiantModele model= new EtudiantModele();
			model.setImmatricule(immatricule);
			List<Etudiant> etds = iEtudiant.getEtudiantParImmatricule(immatricule);
			model.setEtudiants(etds);
			request.setAttribute("model", model);
			request.getRequestDispatcher("public_html/note/moyennes.jsp").forward(request,response);
		}else if (path.equals("/chercher_moyenne_filiere_niveau.do"))
		{
			String filiere = request.getParameter("filiere");
			String specialite = request.getParameter("specialite");
			String niveau = request.getParameter("niveau");
			EtudiantModele model= new EtudiantModele();
			model.setFiliere(filiere);
			model.setSpecialite(specialite);
			List<Etudiant> etds = iEtudiant.etudiantsParFiliereSpecialiteNiveau(filiere,specialite,niveau);
			model.setEtudiants(etds);
			request.setAttribute("model", model);
			request.getRequestDispatcher("public_html/note/moyennes.jsp").forward(request,response);
		}else if (path.equals("/notes.do"))
		{
			NoteModele model= new NoteModele();
			List<Note> notes = iNote.listNotes();
			model.setNotes(notes);
			request.setAttribute("model", model);	
			request.getRequestDispatcher("public_html/note/notes.jsp").forward(request,response);
			
		}else if (path.equals("/note_code_matiere.do"))
		{
			String code_matiere = request.getParameter("code_matiere");
			List<Note> notes = iNote.noteParMatiere(code_matiere);
			NoteModele model= new NoteModele();
			model.setCode_matiere(code_matiere);
			model.setNotes(notes);
			request.setAttribute("model", model);	
			request.getRequestDispatcher("public_html/note/notes.jsp").forward(request,response);
			
		}
		else if (path.equals("/chercher_notes_filiere_specialite_niveau.do"))
		{
			String filiere = request.getParameter("filiere");
			String specialite = request.getParameter("specialite");
			String niveau = request.getParameter("niveau");
			List<Note> notes = iNote.noteParFiliereSpecialiteNiveau(filiere,specialite,niveau);
			NoteModele model= new NoteModele();
			model.setFiliere(filiere);
			model.setSpecialite(specialite);
			model.setNiveau(niveau);
			model.setNotes(notes);
			request.setAttribute("model", model);	
			request.getRequestDispatcher("public_html/note/notes.jsp").forward(request,response);
			
		}else if (path.equals("/admins.do"))
		{
			String immat_admin = request.getParameter("immatricule");
			String nom = request.getParameter("nom");
			AdminModele model= new AdminModele();
			List<Admin> admins = iAdmin.listAdmins();
			model.setImmatricule(immat_admin);
			model.setNom(nom);
			model.setAdmins(admins);
			request.setAttribute("model", model);	
			request.getRequestDispatcher("public_html/admin/admins.jsp").forward(request,response);
			
		}else if (path.equals("/saisie_admin.do"))
		{
			request.getRequestDispatcher("public_html/admin/saisieAdmin.jsp").forward(request,response);
		}
		else if (path.equals("/sauv_admin.do")  && request.getMethod().equals("POST"))
		{
		    String immatricule=request.getParameter("immatricule");
		    String nom=request.getParameter("nom");
			String role = request.getParameter("role");
			String num_tel = request.getParameter("num_tel");
			String email = request.getParameter("email");
			Admin admin = iAdmin.save(new Admin(immatricule, nom,role , num_tel, email));
			request.setAttribute("admin", admin);
			request.getRequestDispatcher("public_html/admin/confirmationSaisieAdmin.jsp").forward(request,response);
			
		}else if (path.equals("/supprimer_admin.do"))
		{
		    int id= Integer.parseInt(request.getParameter("id"));
		    iAdmin.deleteAdmin(id);
		    response.sendRedirect("admins.do");
					
			//request.getRequestDispatcher("confirmation.jsp").forward(request,response);
		}else if (path.equals("/editer_admin.do")  )
		{
			int id= Integer.parseInt(request.getParameter("id"));
			Admin admin = iAdmin.getAdmin(id);
		    request.setAttribute("admin", admin);
			request.getRequestDispatcher("public_html/admin/editerAdmin.jsp").forward(request,response);
			
		}else if (path.equals("/modifier_admin.do")  )
		{
			int id= Integer.parseInt(request.getParameter("id"));
			String immatricule=request.getParameter("immatricule");
		    String nom=request.getParameter("nom");
			String role = request.getParameter("role");
			String num_tel = request.getParameter("num_tel");
			String email = request.getParameter("email");
			Admin admin = iAdmin.updateAdmin(new Admin(id,immatricule, nom, role, num_tel, email));
			request.setAttribute("admin", admin);
			request.getRequestDispatcher("public_html/admin/confirmationSaisieAdmin.jsp").forward(request,response);
		}
		else
		{
			response.sendError(Response.SC_NOT_FOUND);		
		}	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, 
						  HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request,response);
	}
}