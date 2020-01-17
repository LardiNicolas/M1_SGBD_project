/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SGBD;

import java.util.ArrayList;

/**
 *
 * @author nl435089
 */
public class Partition {

    private String hashcode;

    private ArrayList<Paire> tab;

    public Partition(String hashcode){
        this.tab = new ArrayList<Paire>();
        this.hashcode = hashcode;
    }
    
    public String getHashcode() {
        return this.hashcode;
    }
    
    public int size() {
        return this.tab.size();
    }
    
    public ArrayList<Paire> getContent() {
        return this.tab;
    }
    
    @Override
    public String toString() {
        String s = "\n\tPartition: " + this.hashcode + "\n";
        for (Paire L : tab) {
            s += L.toString();
        }
        return s;
    }
    
}
