package dev.demo_JPA;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "emprunt")
public class Emprunt {
	
	@Id // obligatoire
	@Column(name = "ID")
	private int ID;
	@Column(name = "DATE_DEBUT")
	
	private Date DATE_DEBUT;
	@Column(name = "DATE_FIN")
	private Date DATE_FIN;
	@Column(name = "DELAI")
	private int DELAI;
	
	@ManyToMany(mappedBy="emprunts")
	private List<Livre> livres;
	
	@ManyToOne
	@JoinColumn(name="ID_CLIENT",referencedColumnName="ID",insertable=false,updatable=false)
	private Client client;
	
	
	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * @return the dATE_DEBUT
	 */
	public Date getDATE_DEBUT() {
		return DATE_DEBUT;
	}
	/**
	 * @param dATE_DEBUT the dATE_DEBUT to set
	 */
	public void setDATE_DEBUT(Date dATE_DEBUT) {
		DATE_DEBUT = dATE_DEBUT;
	}
	/**
	 * @return the dATE_FIN
	 */
	public Date getDATE_FIN() {
		return DATE_FIN;
	}
	/**
	 * @param dATE_FIN the dATE_FIN to set
	 */
	public void setDATE_FIN(Date dATE_FIN) {
		DATE_FIN = dATE_FIN;
	}
	/**
	 * @return the dELAI
	 */
	public int getDELAI() {
		return DELAI;
	}
	/**
	 * @param dELAI the dELAI to set
	 */
	public void setDELAI(int dELAI) {
		DELAI = dELAI;
	}
	/**
	 * @return the livres
	 */
	public List<Livre> getLivres() {
		return livres;
	}
	/**
	 * @param livres the livres to set
	 */
	public void setLivres(List<Livre> livres) {
		this.livres = livres;
	}
	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}
	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

}
