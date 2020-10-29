/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoinelamoureux.jsftest1.ejbs;

import com.antoinelamoureux.jsftest1.entities.Themes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ThemesFacade extends Abstractfacade<Themes> {
    @PersistenceContext(unitName = "my_persistence_unit2")
    EntityManager em;


    public ThemesFacade() {
        super(Themes.class);
    }

    @Override
    protected EntityManager getEntityManager() {
       return em;
    }
    
    
}
