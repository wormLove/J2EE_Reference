/*
 * NameHandler.java
 *
 * Created on October 28, 2006, 9:41 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.me.hello;

import java.io.Serializable;

/**
 *
 * @author sang
 */
public class NameHandler implements Serializable{
    
    private String name;
      
    /** Creates a new instance of NameHandler */
    public NameHandler() {
        setName(null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
