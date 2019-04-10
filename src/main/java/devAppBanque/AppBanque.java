package devAppBanque;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class AppBanque {

	public static Banque createBank(String name) {
		Banque banque = new Banque();
		banque.setNom(name);
		return banque;
	}

	public static Adresse createAdress(int codePostal, int numRue, String libelleRue, String ville) {
		Adresse adresse = new Adresse();
		adresse.setCodePostal(codePostal);
		adresse.setNumero(numRue);
		adresse.setRue(libelleRue);
		adresse.setVille(ville);
		return adresse;
	}

	public static Client createClient(String nom, String prenom, LocalDate dateNaissance, Banque banque,
			Adresse adresse) {
		Client client = new Client();
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setDateNaissance(dateNaissance);
		client.setBanque(banque);
		client.setAdresse(adresse);
		return client;
	}

	public static AssuranceVie createAssuranceVie(String identifiantCompte, double solde, double taux,
			LocalDate dateFin) {
		AssuranceVie compte = new AssuranceVie();
		compte.setNumero(identifiantCompte);
		compte.setSolde(solde);
		compte.setTaux(taux);
		compte.setDateFin(dateFin);
		return compte;
	}

	public static LivretA createLivretA(String identifiantCompte, double solde, double taux) {
		LivretA compte = new LivretA();
		compte.setNumero(identifiantCompte);
		compte.setSolde(solde);
		compte.setTaux(taux);
		return compte;
	}

	public static Virement createVirement(Compte compte, LocalDateTime date, double montant, String motif,
			String beneficiaire) {
		Virement operation = new Virement();
		operation.setCompte(compte);
		operation.setDate(date);
		operation.setMontant(montant);
		operation.setMotif(motif);
		operation.setBeneficiaire(beneficiaire);
		return operation;
	}

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("banque");

		// Création de banques test
		Banque banque = createBank("Ma super banque");
		Banque banque2 = createBank("Banque à Picsou");
		Banque banque3 = createBank("Banque Love Money");

		// Création de comptes test
		AssuranceVie compte = createAssuranceVie("1c", 1000.0, 10.0, LocalDate.now());
		AssuranceVie compte1 = createAssuranceVie("2c", 1000.0, 10.0, LocalDate.now());
		AssuranceVie compte2 = createAssuranceVie("3c", 1000.0, 10.0, LocalDate.now());
		LivretA compte3 = createLivretA("4c", 1000.0, 5.0);

		// Création des opérations de test
		Virement op1 = createVirement(compte1, LocalDateTime.now(), 100.0, "salaire", "Bob Lennon");
		Virement op2 = createVirement(compte2, LocalDateTime.now(), 100.0, "cadeau", "Bob Lemon");
		Virement op3 = createVirement(compte, LocalDateTime.now(), 100.0, "cadeau", "Bob Limon");

		// Création d'adresses de test
		Adresse adresse1 = createAdress(75000, 2, "Rue bidon", "Paris");
		Adresse adresse2 = createAdress(31000, 2, "Rue inconnue", "Toulouse");
		Adresse adresse3 = createAdress(44000, 2, "Rue connue", "Nantes");

		// Création de clients test
		Client client1 = createClient("Lanvin", "Gérard", LocalDate.now(), banque, adresse1);
		Client client2 = createClient("Bidon", "Bob", LocalDate.now(), banque2, adresse2);
		Client client3 = createClient("Bidon", "Martha", LocalDate.now(), banque2, adresse2);
		Client client4 = createClient("Kent", "Clark", LocalDate.now(), banque3, adresse3);

		// Jonction entre les comptes et leur client
		List<Compte> compteClient1 = new ArrayList<>();
		compteClient1.add(compte);
		client1.setComptes(compteClient1);

		List<Compte> compteClient2 = new ArrayList<>();
		compteClient2.add(compte1);
		client2.setComptes(compteClient2);
		client3.setComptes(compteClient2);

		List<Compte> compteClient3 = new ArrayList<>();
		compteClient3.add(compte3);
		client4.setComptes(compteClient3);

		// Jonction entre les opérations et les comptes
		List<Operation> listOpe1 = new ArrayList<>();
		listOpe1.add(op1);
		compte.setOperations(listOpe1);

		List<Operation> listOpe2 = new ArrayList<>();
		listOpe2.add(op2);
		compte.setOperations(listOpe2);

		List<Operation> listOpe3 = new ArrayList<>();
		listOpe3.add(op3);
		compte.setOperations(listOpe3);

		// Rcupération des objets sous forme de listes

		List<Banque> listBanque = new ArrayList<>();
		listBanque.add(banque);
		listBanque.add(banque2);
		listBanque.add(banque3);

		List<Client> listClients = new ArrayList<>();
		listClients.add(client1);
		listClients.add(client2);
		listClients.add(client3);
		listClients.add(client4);

		List<Compte> listCompte = new ArrayList<>();
		listCompte.add(compte);
		listCompte.add(compte1);
		listCompte.add(compte2);

		List<Operation> listOperation = new ArrayList<>();
		listOperation.add(op1);
		listOperation.add(op2);
		listOperation.add(op3);

		// Création de l'entity manager
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		// Pour insérer les données
		tx.begin();
		for (Banque b : listBanque) {
			em.persist(b);
		}
		tx.commit();

		tx.begin();
		for (Compte co : listCompte) {
			em.persist(co);
		}
		tx.commit();

		tx.begin();
		for (Operation op : listOperation) {
			em.persist(op);
		}
		tx.commit();

		tx.begin();
		for (Client c : listClients) {
			em.persist(c);
		}
		tx.commit();

		em.close();
		emf.close();
	}

}