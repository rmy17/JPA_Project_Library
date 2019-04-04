package dev.demo_JPA;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Client")
public class Client {

	@Id // obligatoire
	@Column(name = "ID")
	private int ID;
	@Column(name = "NOM")
	private String NOM;
	@Column(name = "PRENOM")
	private String PRENOM;
	
	@OneToMany(mappedBy="client")
	private List<Emprunt> emprunts;

	
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
	 * @return the nom
	 */
	public String getNom() {
		return NOM;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		NOM = nom;
	}
	/**
	 * @return the pRENOM
	 */
	public String getPRENOM() {
		return PRENOM;
	}
	/**
	 * @param pRENOM the pRENOM to set
	 */
	public void setPRENOM(String pRENOM) {
		PRENOM = pRENOM;
	}
	
}
