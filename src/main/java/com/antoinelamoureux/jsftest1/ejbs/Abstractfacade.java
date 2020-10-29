/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoinelamoureux.jsftest1.ejbs;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;


/**
 *
 * @author 77011-53-17
 */
public abstract class Abstractfacade<T> {
    private Class<T> entityClass;
    
    // Constructor
    public Abstractfacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    // Les enfants doivent donner une définition de cette methode
    protected abstract EntityManager getEntityManager();
    
    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        
        return getEntityManager().createQuery(cq).getResultList();
    }
}
