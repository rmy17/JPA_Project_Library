package dev.demo_JPA;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class App {

	public static void main(String[] args) {

		// Etape 1 - Créer une instance d'EntityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo-jpa");
		
		// Début d'une unité de travail
		EntityManager em1 = emf.createEntityManager();
		
		// création d'une requête
		TypedQuery<Livre> requete = em1.createQuery("select l from Livre l", Livre.class);
		
		List<Livre> listeLivres = requete.getResultList();
		
		listeLivres.forEach(unLivre -> {
			System.out.println(unLivre.getId() + " - "  + unLivre.getTitre() + " - " + unLivre.getAuteur());
		});
		
		//Faire maintenant une requête JPQL pour trouver un LIVRE en fonction de son TITRE
		TypedQuery<Livre> requestByTitle = em1.createQuery("select l from Livre l where TITRE = :reference",Livre.class);
		requestByTitle.setParameter("reference", "Germinal");
		Livre book = requestByTitle.getSingleResult();
		System.out.println(book);
		
		//Réaliser une requête qui permet d’extraire un emprunt et tous ses livres associés.
		TypedQuery<Emprunt> requestEmp = em1.createQuery("Select e from Emprunt e",Emprunt.class);
		List<Emprunt> listEmprunt = requestEmp.getResultList();
		listEmprunt.forEach(unEmprunt -> {
			System.out.println(unEmprunt.getID() + " - "  + unEmprunt.getLivres());
		});
		

		//Réaliser une requête qui permet d’extraire tous les emprunts d’un client donné.
		TypedQuery<Emprunt> requestEmp2 = em1.createQuery("Select e from Emprunt e, Client c WHERE c.NOM = 'Brigand' and e.client = c.ID",Emprunt.class);
		List<Emprunt> listEmpClient = requestEmp2.getResultList();
		listEmpClient.forEach(emp ->{
			System.out.println(emp.getID()+" - "+emp.getClient());
		});
		
		// Fin d'une unité de travail
		em1.close();
		
		emf.close();
		
		
		
	}
	
	
	/*
	public Livre findBookByTitle(String title){
		
		TypedQuery<Livre> requestByTitle = em1.createQuery("select l from Livre l where TITRE = :reference",Livre.class);
		requestByTitle.setParameter("reference", title);
		Livre book = requestByTitle.getSingleResult();
		return book;
	}
	
	public void addBook() {
		Livre livre1 = new Livre();
		livre1.setId(id);
		livre1.setAuteur(auteur);
		livre1.setTitre(titre);
		em1.persist(livre1);
		
	}
*/
}