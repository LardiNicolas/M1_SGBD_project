package SGBD;


import RandomString.RandomString;

import java.util.ArrayList;

/**
 * Brique de base:
 * Permet de représenter un tuple (Ligne) contenue dans une table d'un SGBD relationnel.
 */
public class Ligne {
    /**
     * Représente la liste des attributs de chaque tuple.
     */
    private ArrayList<Attribut> tab;
    protected int ID;

    /**
     * Constructeur par défaut.
     */
    public Ligne() {
        this.tab = new ArrayList<Attribut>();
    }

    /**
     * @param A Attribut à ajouter à la ligne.
     * Permet d'ajouter un attribut dans la ligne.
     */
    public void ajouterAttribut(Attribut A) {
        this.tab.add(A);
    }

    /**
     * Permet de générer des attributs correspondants au format de la table et avec des valeurs aléatoires.
     *
     * @param format Format de la table de laquelle dépend le tuple.
     */
    public void ajouterAttribut(TableFormat format) {
        for (String s : format.format)
            this.tab.add(new Attribut(s, RandomString.generate(1)));
    }

    public ArrayList<Attribut> getContent() {
        return this.tab;
    }
    
    public int getLineID(){
        return this.ID;
    }
    @Override
    public String toString() {
        String s = " \n\t\t Ligne:" + ID + "\n";
        for (Attribut A : tab) {
            s += A.toString();
        }
        return s;
    }
}
