package projet.helpdesk.beans;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name="ReponsesTickets")
public class ReponseTicket {
	
	private int id_ticket;
	@Id
	private int id_reponse;
	private int id_employe;
	private int valide;
	private String texte;
	private String type;
	private Timestamp date_post;
	public int getId_ticket() {
		return id_ticket;
	}
	public void setId_ticket(int id_ticket) {
		this.id_ticket = id_ticket;
	}
	public int getId_reponse() {
		return id_reponse;
	}
	public void setId_reponse(int id_reponse) {
		this.id_reponse = id_reponse;
	}
	public int getId_employe() {
		return id_employe;
	}
	public void setId_employe(int id_employe) {
		this.id_employe = id_employe;
	}
	public String getTexte() {
		return texte;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Timestamp getDate_post() {
		return date_post;
	}
	public void setDate_post(Timestamp date_post) {
		this.date_post = date_post;
	}
	public int getValide() {
		return valide;
	}
	public void setValide(int valide) {
		this.valide = valide;
	}
	
}
