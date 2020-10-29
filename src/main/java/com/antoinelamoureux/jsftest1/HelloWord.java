/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoinelamoureux.jsftest1;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author 77011-53-17
 */
@Named(value = "yo")
@RequestScoped
public class HelloWord implements Serializable {
    private String message;

    public HelloWord() {
        System.out.println("Constructeur HelloWorld");
        message = "Hey !";
    }
    
    public String goTo() {
        return "page1";
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }   
}
