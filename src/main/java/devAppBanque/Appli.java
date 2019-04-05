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
				Client c = ajoutClient(sc);
				et.begin();
				em1.persist(c);
				et.commit();
				em1.close();

				break;
			case 2:
				Compte cp = ajoutCompte(sc);
				et.begin();
				em1.persist(cp);
				et.commit();
				em1.close();
				break;
			case 3:
				System.out.println("Choisir virement ou operation");
				String name = sc.nextLine().toLowerCase();
				if (name.equals("operation")) {
					Operation o = ajoutOperation(sc);
					et.begin();
					em1.persist(o);
					et.commit();
					em1.close();
					break;
				} else if (name.equals("virement")) {
					Virement v = ajoutVirement(sc);
					et.begin();
					em1.persist(v);
					et.commit();
					em1.close();
					break;
				}
			}

		}
	}

	private static Banque ajoutBanque(Scanner sc) {
		Banque b = new Banque();
		System.err.println("Donner un nom !");
		String nom = sc.nextLine();
		b.setName(nom);
		return b;
	}

	private static Client ajoutClient(Scanner sc) {
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
		c.setDateNessance(date);
		c.setAdresse(ajoutAdresse(sc));
		c.setBanque(ajoutBanque(sc));
		return c;
	}

	private static Compte ajoutCompte(Scanner sc) {
		Compte cp = new Compte();
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
		List<Client> clients = new ArrayList();
		while (cpt < reponse) {
			clients.add(ajoutClient(sc));
			cpt++;
		}
		cp.setClients(clients);
		return cp;
	}

	private static Operation ajoutOperation(Scanner sc) {
		Operation o = new Operation();
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

	private static Adresse ajoutAdresse(Scanner sc) {
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

	public static Virement ajoutVirement(Scanner sc) {
		Virement v = new Virement();
		System.out.println("Rentrer un nom de bénéficaire");
		String benef = sc.nextLine();
		v.setBeneficaire(benef);
		System.out.println("Donner une date sous forme aaaa-mm-jjThh-mm-ss");
		String d = sc.nextLine();
		LocalDateTime date = LocalDateTime.parse(d);
		v.setDate(date);
		System.out.println("Donner un montant");
		String mt = sc.nextLine();
		Double montant = Double.parseDouble(mt);
		v.setMontant(montant);
		System.out.println("Donner un motif");
		String motif = sc.nextLine();
		v.setMotif(motif);
		return v;
	}
}
