package devAppBanque;

import javax.persistence.Entity;

@Entity

public class LivretA extends Compte {

	private Double taux;

	/**
	 * @return the taux
	 */
	public Double getTaux() {
		return taux;
	}

	/**
	 * @param taux the taux to set
	 */
	public void setTaux(Double taux) {
		this.taux = taux;
	}

}
