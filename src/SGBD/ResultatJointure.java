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
public class ResultatJointure {

    /**
     * Tableau (ArrayList) représentant les tuples contenus dans la table.
     */
    private ArrayList<Paire> tab;

    protected String attribut;

    public String getAttribut() {
        return attribut;
    }

    /**
     * Constructeur par défaut de la table.
     *
     * @param tf Format des attributs de la table
     */
    public ResultatJointure() {
        this.tab = new ArrayList<Paire>();
    }

    public void ajouterLigneJointure(Ligne L1, Ligne L2, String attribut, int blocID1, int blocID2) {
        this.attribut = attribut;
        Paire newPaire = new Paire(blocID1, L1.getLineID(), blocID2, L2.getLineID(),L1,L2);
        this.getContent().add(newPaire);
    }

    /**
     * Accesseur aux tuples de la table.
     *
     * @return Arraylist 'tab' représentant l'ensemble des tuples de la table.
     */
    public ArrayList<Paire> getContent() {
        return this.tab;
    }

    public int valueSize() {
        int temp = 0;
        for (Paire b : tab) {
            for (Ligne l : b.getContent()) {
                temp += l.getContent().size();
            }
        }
        return temp;
    }

    @Override
    public String toString() {
        String s = "Resultat Jointure:\n";
        for (Paire b : this.tab) {
            s += b.toString();
        }
        return s;
    }
}
