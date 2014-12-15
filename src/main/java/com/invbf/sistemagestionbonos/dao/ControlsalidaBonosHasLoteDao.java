/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invbf.sistemagestionbonos.dao;

import com.invbf.sistemagestionbonos.entity.ControlsalidabonosHasLotesbonos;
import com.invbf.sistemagestionbonos.entity.ControlsalidabonosHasLotesbonosPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author ivan
 */
public class ControlsalidaBonosHasLoteDao {
     public ControlsalidaBonosHasLoteDao() {
    }

    public static void create(ControlsalidabonosHasLotesbonos cargo) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("gestionBonosPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            em.persist(cargo);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
        }

        em.close();
        emf.close();
    }

    public static void edit(ControlsalidabonosHasLotesbonos cargo) {

        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("gestionBonosPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            em.merge(cargo);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

        em.close();
        emf.close();
    }

    public static void remove(ControlsalidabonosHasLotesbonos cargo) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("gestionBonosPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            em.remove(em.merge(cargo));
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

        em.close();
        emf.close();
    }

    public static ControlsalidabonosHasLotesbonos find(ControlsalidabonosHasLotesbonosPK id) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("gestionBonosPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        ControlsalidabonosHasLotesbonos cargo = null;

        tx.begin();
        try {
            cargo = em.find(ControlsalidabonosHasLotesbonos.class, id);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

        em.close();
        emf.close();
        return cargo;
    }

    public static List<ControlsalidabonosHasLotesbonos> findAll() {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("gestionBonosPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<ControlsalidabonosHasLotesbonos> lista = new ArrayList<ControlsalidabonosHasLotesbonos>();

        tx.begin();
        try {
            javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ControlsalidabonosHasLotesbonos.class));
            lista = em.createQuery(cq).getResultList();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println(e);
        }

        em.close();
        emf.close();
        return lista;
    }

    public static List<ControlsalidabonosHasLotesbonos> findRange(int[] range) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("gestionBonosPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<ControlsalidabonosHasLotesbonos> lista = new ArrayList<ControlsalidabonosHasLotesbonos>();

        tx.begin();
        try {
            javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ControlsalidabonosHasLotesbonos.class));
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
                = Persistence.createEntityManagerFactory("gestionBonosPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        int count = 0;

        tx.begin();
        try {
            javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            javax.persistence.criteria.Root<ControlsalidabonosHasLotesbonos> rt = cq.from(ControlsalidabonosHasLotesbonos.class);
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
