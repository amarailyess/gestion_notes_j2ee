package test;


import java.util.List;

import dao.EtudiantDaoImpl;
import metier.entities.Etudiant;
public class TestDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EtudiantDaoImpl pdao= new EtudiantDaoImpl();
		Etudiant e1 = new Etudiant("Maryem" ,"115","Ingénieur", "Informatique", 2);
		Etudiant etud= pdao.save(e1);
		
		//List<Etudiant> etuds = pdao.etudiantsParFiliere("licence");
		 //for (Etudiant etd : etuds)
		     //System.out.println(etd);
		// List<Etudiant> etuds1 = pdao.etudiantsParFiliereNiveau("licence", 1);
		// for (Etudiant etd : etuds1)
		     //System.out.println(etd);
		
		List<Etudiant> etuds = pdao.listEtudiants();
		 for (Etudiant etd : etuds)
		     System.out.println(etd);

		Etudiant etudiant= pdao.getEtudiant(1);
		System.out.println(etudiant);
		etudiant.setNom("Mohamed");
		pdao.updateEtudiant(etudiant);
		System.out.println("after update " +etudiant);

	}

}
