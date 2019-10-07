package projet.helpdesk.beans;

import javax.persistence.*;

@Entity
@Table(name="faq")
public class FAQ {

	@Id
	private int id_qr;
	private int id_technicien;
	private String question;
	private String reponse;
	
	public int getId_qr() {
		return id_qr;
	}
	public void setId_qr(int id_qr) {
		this.id_qr = id_qr;
	}
	public int getId_technicien() {
		return id_technicien;
	}
	public void setId_technicien(int id_technicien) {
		this.id_technicien = id_technicien;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getReponse() {
		return reponse;
	}
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	
}
