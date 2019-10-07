package projet.helpdesk.beans;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="Tickets")
public class Ticket {
	@Column(name = "priorite")
	private int priorite;
	@Column(name = "etat")
	private int etat;
	@Column(name = "id_employe")
	private int id_employe;
	@Column(name = "id_technicien")
	private int id_technicien;
	@Column(name = "sujet")
	private String sujet;
	@Column(name = "description")
	private String description;
	@Column(name = "date_envoi")
	private Timestamp date_envoi;
	@Column(name = "date_fermeture")
	private Date date_fermeture;
	@Id
	@Column(name = "id_ticket")
	private int id_ticket; 
	
	public int getId_ticket() {
		return id_ticket;
	}
	public void setId_ticket(int id_ticket) {
		this.id_ticket = id_ticket;
	}
	public int getId_employe() {
		return id_employe;
	}
	public void setId_employe(int id_employe) {
		this.id_employe = id_employe;
	}
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}
	public int getId_technicien() {
		return id_technicien;
	}
	public void setId_technicien(int id_technicien) {
		this.id_technicien = id_technicien;
	}
	public String getSujet() {
		return sujet;
	}
	public void setSujet(String sujet) {
		this.sujet = sujet;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getDate_envoi() {
		return date_envoi;
	}
	public void setDate_envoi(Timestamp date_envoi) {
		this.date_envoi = date_envoi;
	}
	public int getPriorite() {
		return priorite;
	}
	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}
	public Date getDate_fermeture() {
		return date_fermeture;
	}
	public void setDate_fermeture(Date date_fermeture) {
		this.date_fermeture = date_fermeture;
	}
}
