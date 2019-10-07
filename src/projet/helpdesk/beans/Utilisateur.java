package projet.helpdesk.beans;

import java.sql.Timestamp;
import javax.persistence.*;
@Entity
@Table(name = "Utilisateur")
public class Utilisateur {
	private int tech;
	@Column(name = "nom")
	private String nom;
	@Column(name = "prenom")
    private String prenom;
	@Column(name = "email")
    private String email;
	@Column(name = "departement")
    private String dept;
	@Column(name = "poste")
    private String poste;
	@Column(name = "agence")
    private String agence;
	@Column(name = "mdp")
    private String mdp;
	@Column(name = "type")
    private String type;
	@Column(name = "date_inscr")
    private Timestamp date_inscr;
	
	@Id
	//@GeneratedValue( strategy = GenerationType.AUTO, generator ="DEMO_USERS_SEQ" )
	@Column(name = "id_emp")
    private int idemp;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getPoste() {
		return poste;
	}
	public void setPoste(String poste) {
		this.poste = poste;
	}
	public String getAgence() {
		return agence;
	}
	public void setAgence(String agence) {
		this.agence = agence;
	}
	public int getIdemp() {
		return idemp;
	}
	public void setIdemp(int id) {
		this.idemp = id;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Timestamp getDate_inscr() {
		return date_inscr;
	}
	public void setDate_inscr(Timestamp date_inscr) {
		this.date_inscr = date_inscr;
	}
	public int getTech() {
		return tech;
	}
	public void setTech(int tech) {
		this.tech = tech;
	}
    
}
