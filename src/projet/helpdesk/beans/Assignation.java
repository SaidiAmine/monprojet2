package projet.helpdesk.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Assignation {
	@Id
	private int id_assignation;
	private int id_tr;
	private int id_t;
	private int id_ticket;
	public int getId_assignation() {
		return id_assignation;
	}
	public void setId_assignation(int id_assignation) {
		this.id_assignation = id_assignation;
	}
	public int getId_tr() {
		return id_tr;
	}
	public void setId_tr(int id_tr) {
		this.id_tr = id_tr;
	}
	public int getId_t() {
		return id_t;
	}
	public void setId_t(int id_t) {
		this.id_t = id_t;
	}
	public int getId_ticket() {
		return id_ticket;
	}
	public void setId_ticket(int id_ticket) {
		this.id_ticket = id_ticket;
	}
}
