/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.dao;

import com.invbf.sistemagestionbonos.entitySGC.Casinos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author ideacentre
 */
public class CasinoDao {

    public CasinoDao() {
    }

    public static void create(Casinos casino) {

        casino.setNombre(casino.getNombre().toUpperCase());
        if (casino.getDireccion() != null) {
            casino.setDireccion(casino.getDireccion().toUpperCase());
        }
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("inversionesPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            em.persist(casino);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

        em.close();
        emf.close();
    }

    public static void edit(Casinos casino) {
        
        if (casino.getDireccion() != null) {
            casino.setDireccion(casino.getDireccion().toUpperCase());
        }
        casino.setNombre(casino.getNombre().toUpperCase());
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("inversionesPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            em.merge(casino);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

        em.close();
        emf.close();
    }

    public static void remove(Casinos casino) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("inversionesPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            em.remove(em.merge(casino));
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

        em.close();
        emf.close();
    }

    public static Casinos find(Integer id) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("inversionesPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Casinos casino = null;

        tx.begin();
        try {
            casino = em.find(Casinos.class, id);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

        em.close();
        emf.close();
        return casino;
    }

    public static List<Casinos> findAll() {
        
            System.out.println("Empezando la busqueda de casinos");
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("inversionesPU");
            System.out.println("Emf");
        EntityManager em = emf.createEntityManager();
            System.out.println("Em");
        EntityTransaction tx = em.getTransaction();
            System.out.println("tx");
        List<Casinos> lista = new ArrayList<Casinos>();

            System.out.println("yx begin");
        tx.begin();
        try {
            javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Casinos.class));
            lista = em.createQuery(cq).getResultList();
            System.out.println("lista lista");
            tx.commit();
            System.out.println("comit");
        } catch (Exception e) {
            System.out.println(e);
            tx.rollback();
        }

            System.out.println("terminada la busqueda de casinos");
        em.close();
        emf.close();
        return lista;
    }

    public static List<Casinos> findRange(int[] range) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("inversionesPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<Casinos> lista = new ArrayList<Casinos>();

        tx.begin();
        try {
            javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Casinos.class));
            javax.persistence.Query q = em.createQuery(cq);
            q.setMaxResults(range[1] - range[0]);
            q.setFirstResult(range[0]);
            lista = q.getResultList();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

        em.close();
        emf.close();
        return lista;
    }

    public static int count() {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("inversionesPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        int count = 0;

        tx.begin();
        try {
            javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            javax.persistence.criteria.Root<Casinos> rt = cq.from(Casinos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            javax.persistence.Query q = em.createQuery(cq);
            count = ((Long) q.getSingleResult()).intValue();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

        em.close();
        emf.close();
        return count;

    }

}
