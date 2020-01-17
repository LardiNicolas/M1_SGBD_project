/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SGBD;

import java.util.ArrayList;

/**
 *
 * @author lardi
 */
public class Paire {

    private ArrayList<Ligne> tab;
    private int blocID1, lineID1, blocID2, lineID2;

    public Paire(int blocID1, int lineID1, int blocID2, int lineID2, Ligne L1, Ligne L2) {
        this.tab = new ArrayList<Ligne>();
        tab.add(L1);
        tab.add(L2);
        this.blocID1 = blocID1;
        this.lineID1 = lineID1;
        this.blocID2 = blocID2;
        this.lineID2 = lineID2;
    }
    
     public Paire(int blocID1, int lineID1) {
        this.tab = new ArrayList<Ligne>();
        this.blocID1 = blocID1;
        this.lineID1 = lineID1;
    }

    public ArrayList<Ligne> getContent() {
        return this.tab;
    }

    public int getBlocID1() {
        return this.blocID1;
    }

    public int getLineID1() {
        return this.lineID1;
    }

    public int getBlocID2() {
        return this.blocID2;
    }

    public int getLineID2() {
        return this.lineID2;
    }

    @Override
    public String toString() {
        String s = "\n\tInformation Ligne :\n";
        for (Ligne b : this.tab) {
            s += b.toString() + "\n";
            s += "\t\t Bloc T1 : " + this.getBlocID1() + "\n";
            s += " Ligne T1 : " + this.getLineID1() + "\n";
            s += " Bloc T2 : " + this.getBlocID2() + "\n";
            s += " Ligne T2 : " + this.getLineID2();
        }
        return s;
    }
}
