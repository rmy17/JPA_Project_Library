package devAppBanque;

import javax.persistence.Entity;

@Entity
public class Virement extends Operation {

	private String beneficaire;

	/**
	 * @return the beneficaire
	 */
	public String getBeneficaire() {
		return beneficaire;
	}

	/**
	 * @param beneficaire the beneficaire to set
	 */
	public void setBeneficaire(String beneficaire) {
		this.beneficaire = beneficaire;
	}
}
