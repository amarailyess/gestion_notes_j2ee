package dao;

import java.util.List;

import metier.entities.Admin;
import web.AppInfosModele;

public interface IAdminDao {
	public Admin save(Admin admin);
	public List<Admin> listAdmins();
	public List<Admin> adminParImmatricule(String immat_admin);
	public List<Admin> adminParNom(String nom_admin);
	public List<Admin> adminParImmatriculeNom(String immat_admin, String nom_admin) ;
	public List<Admin> adminParRole(String role);
	public AppInfosModele infos();
	public Admin getAdmin(int id);
	public Admin updateAdmin(Admin admin);
	public void deleteAdmin(int id);
}
