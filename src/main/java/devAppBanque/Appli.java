package devAppBanque;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Appli {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("banque");
		EntityManager em1 = emf.createEntityManager();
		EntityTransaction et = em1.getTransaction();
		boolean app = true;
		while (app) {
			System.out.println("Bonjour !");
			System.out.println("1. Ajouter un client.");
			System.out.println("2. Ajouter un compte. ");
			System.out.println("3. Ajouter une operation");
			System.out.println("Selectionner un  choix !");
			String r = sc.nextLine();
			int rep = Integer.parseInt(r);
			switch (rep) {
			case 1:
				et.begin();
				Client c = ajoutClient(sc, em1);
				em1.persist(c);
				et.commit();
				break;
			case 2:
				et.begin();
				Compte cp = ajoutCompte(sc, em1);
				em1.persist(cp);
				et.commit();
				break;
			case 3:
				System.out.println("Choisir virement ou operation");
				String name = sc.nextLine().toLowerCase();
				if (name.equals("operation")) {
					et.begin();
					Operation o = ajoutOperation(sc, em1);
					em1.persist(o);
					et.commit();
					break;
				} else if (name.equals("virement")) {
					et.begin();
					Virement v = ajoutVirement(sc, em1);
					em1.persist(v);
					et.commit();
					break;
				}
			}
		}
		em1.close();

	}

	private static Banque ajoutBanque(Scanner sc, EntityManager em1) {
		System.out.println("Ajout d'une banque");
		Banque b = new Banque();
		System.err.println("Donner un nom de banque!");
		String nom = sc.nextLine();
		b.setNom(nom);
		return b;
	}

	private static Client ajoutClient(Scanner sc, EntityManager em1) {
		List<Compte> comptes = new ArrayList<>();
		List<Banque> banques = new ArrayList<>();
		System.out.println("Configuration client");
		Client c = new Client();
		System.out.println("Donner un nom");
		String nom = sc.nextLine();
		c.setNom(nom);
		System.out.println("Donner un prenom");
		String prenom = sc.nextLine();
		c.setPrenom(prenom);
		System.out.println("Donner une date sous forme aaaa-mm-jj");
		String d = sc.nextLine();
		LocalDate date = LocalDate.of(Integer.parseInt(d.split("-")[0]), Integer.parseInt(d.split("-")[1]),
				Integer.parseInt(d.split("-")[2]));
		c.setDateNaissance(date);
		Adresse adresse = ajoutAdresse(sc, em1);
		c.setAdresse(adresse);
		Banque banque = ajoutBanque(sc, em1);
		c.setBanque(banque);
		System.out.println("Voulez vous ajouter un compte oui/non?");
		String rep = sc.nextLine();
		LivretA livA = new LivretA();
		AssuranceVie aVie = new AssuranceVie();
		if (rep.equals("oui")) {
			System.out.println("passe2");
			livA = ajoutLivretA(sc, em1);
			aVie = ajoutAssuranceVie(sc, em1);
			comptes.add(livA);
			comptes.add(aVie);
			em1.persist(livA);
			em1.persist(aVie);
			c.setComptes(comptes);
		}
		banques.add(banque);
		em1.persist(banques);
		return c;
	}

	private static Compte ajoutCompte(Scanner sc, EntityManager em1) {
		Compte cp = new LivretA();
		System.err.println("Donner un numero");
		String numero = sc.nextLine();
		cp.setNumero(numero);
		System.err.println("Donner un solde");
		String sd = sc.nextLine();
		Double solde = Double.parseDouble(sd);
		cp.setSolde(solde);
		System.out.println("Combien de clients voulez vous associer à ce compte ?");
		String rep = sc.nextLine();
		int reponse = Integer.parseInt(rep);
		int cpt = 0;
		List<Client> clients = new ArrayList<>();
		Client client = new Client();
		while (cpt < reponse) {
			client = ajoutClient(sc, em1);
			clients.add(client);
			cpt++;
		}
		cp.setClientbs(clients);
		em1.persist(clients);
		return cp;
	}

	private static Operation ajoutOperation(Scanner sc, EntityManager em1) {
		Virement o = new Virement();
		System.out.println("Donner une date sous forme aaaa-mm-jjThh-mm-ss");
		String d = sc.nextLine();
		LocalDateTime date = LocalDateTime.parse(d);
		o.setDate(date);
		System.out.println("Donner un montant");
		String mt = sc.nextLine();
		Double montant = Double.parseDouble(mt);
		o.setMontant(montant);
		System.out.println("Donner un motif");
		String motif = sc.nextLine();
		o.setMotif(motif);
		return o;

	}

	private static Adresse ajoutAdresse(Scanner sc, EntityManager em1) {
		System.out.println("Configuration de l'adresse client");
		Adresse a = new Adresse();
		System.out.println("Donner un numero");
		String n = sc.nextLine();
		int num = Integer.parseInt(n);
		a.setNumero(num);
		System.out.println("Donner un nom de rue");
		String nRue = sc.nextLine();
		a.setRue(nRue);
		System.out.println("Donner un code postal");
		String cP = sc.nextLine();
		int codeP = Integer.parseInt(cP);
		a.setCodePostal(codeP);
		System.out.println("Donner le nom d'une ville");
		String nomVille = sc.nextLine();
		a.setVille(nomVille);
		return a;
	}

	public static Virement ajoutVirement(Scanner sc, EntityManager em1) {
		Virement v = new Virement();
		System.out.println("Rentrer un nom de bénéficaire");
		String benef = sc.nextLine();
		v.setBeneficiaire(benef);
		v.setDate(LocalDateTime.now());
		System.out.println("Donner un montant");
		String mt = sc.nextLine();
		Double montant = Double.parseDouble(mt);
		v.setMontant(montant);
		System.out.println("Donner un motif");
		String motif = sc.nextLine();
		v.setMotif(motif);
		Compte comptes = ajoutCompte(sc, em1);
		v.setCompte(comptes);
		em1.persist(comptes);
		return v;
	}

	public static LivretA ajoutLivretA(Scanner sc, EntityManager em1) {
		LivretA livretA = new LivretA();
		System.out.println("Rentre un numero de compte");
		String numero = sc.nextLine();
		livretA.setNumero(numero);
		System.out.println("Donner un solde");
		String D = sc.nextLine();
		Double solde = Double.parseDouble(D);
		livretA.setSolde(solde);
		livretA.setTaux(10.00);
		return livretA;
	}

	public static AssuranceVie ajoutAssuranceVie(Scanner sc, EntityManager em1) {
		AssuranceVie assuranceVie = new AssuranceVie();
		System.out.println("Rentre un numero de compte");
		String numero = sc.nextLine();
		assuranceVie.setNumero(numero);
		System.out.println("Donner un solde");
		String D = sc.nextLine();
		Double solde = Double.parseDouble(D);
		assuranceVie.setSolde(solde);
		LocalDate dateFin = LocalDate.now();
		assuranceVie.setDateFin(dateFin);
		assuranceVie.setTaux(10.00);
		return assuranceVie;
	}

}
