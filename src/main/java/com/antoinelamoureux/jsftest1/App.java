/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoinelamoureux.jsftest1;

import com.antoinelamoureux.jsftest1.entities.Themes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 77011-53-17
 */

@WebServlet(name = "App", urlPatterns = {"/app"})
public class App extends HttpServlet {
    @PersistenceContext(unitName = "my_persistence_unit2")
    EntityManager em2;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("EM 2 ==> " + em2);
        
        // On instancie la fabrique
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");

        // Instanciation d'un EntityManager
        EntityManager em = emf.createEntityManager();

        try {
            // On démarre nous mème les transactins
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            
            // Liste thèmes depuis une NamedQuery
            // Requete JPQL avec NamedQuery
            Query allThemes = em.createNamedQuery("Themes.findAll");
            
            // Un container
            List<Themes> listeThemes = allThemes.getResultList();
            
            System.out.println("Liste thèmes" + listeThemes);
            listeThemes.forEach(theme -> System.out.println("Theme ==>" + theme));
            
            // Avec une query
            Query allThemesQuery = em.createQuery("SELECT t FROM Themes t");
            List<Themes> listeThemes2 = allThemesQuery.getResultList();
            
            System.out.println("Liste thèmes" + listeThemes2);
            listeThemes2.forEach(theme -> System.out.println("Theme ==>" + theme));
            
            // Avec une CriteriaQuery
            Class<Themes> entityClass = Themes.class;
            
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Class.forName("com.antoinelamoureux.jsftest1.entities.Themes")));
            cq.select(cq.from(entityClass));

            List<Themes> listeThemes3 = em.createQuery(cq).getResultList();
            System.out.println("Liste thèmes" + listeThemes3);
            listeThemes3.forEach(theme -> System.out.println("Theme ==>" + theme));
            
            // Find by ID
            Themes themeToBeFound = new Themes();
            
            //Query themeToBeFoundQuery = em.createNamedQuery("Themes.findByIdTheme");
            //themeToBeFoundQuery.setParameter("idTheme", 25);
            //themeToBeFound = (Themes) themeToBeFoundQuery.getSingleResult();
            
            //themeToBeFound = (Themes) em.createNamedQuery("Themes.findByIdTheme").setParameter("idTheme", 25).getSingleResult();
            
            themeToBeFound = em.find(entityClass, 25);
            System.out.println("Thème à trouver: " + themeToBeFound);
            
            // CREATE
            
            Themes brandNewThema = new Themes();
            brandNewThema.setLibelle("Brand New Thema");
            
            // On ajoute au repository
            em.persist(brandNewThema);
            
            allThemesQuery = em.createQuery("SELECT t FROM Themes t");
            List<Themes> listeThemes4 = allThemesQuery.getResultList();
            
            listeThemes4.forEach(theme -> System.out.println("Theme ==>" + theme));
            
            transaction.commit();
            // Transaction terminée
            
            transaction.begin();
            
            System.out.println("Modification");
            brandNewThema.setLibelle("NEW LIBELLE");
            
            Themes tt = em.find(entityClass, 42);
            tt.setLibelle("fsffdvffff");
            em.merge(brandNewThema);
            transaction.commit();
            
            transaction.begin();
            listeThemes4.forEach(theme -> System.out.println("Theme ==>" + theme));
            
            System.out.println("DELETE");
            
            em.remove(em.merge(themeToBeFound));
            
            transaction.commit();
            
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        request.getRequestDispatcher("index.xhtml").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
