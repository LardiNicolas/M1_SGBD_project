package SGBD;

import java.util.ArrayList;

/**
 * Brique de base:
 * Permet de rerésenter un bloc de mémoire physique d'un SGBD relationnel.
 */
public class Bloc {
    /**
     * Représente la taille de chaque bloc.
     */
    public static final int MAX_SIZE = 5;

    /**
     * Identifiant unique de chaque bloc.
     * Les blocs d'une même table ont un ID uniques mais il peut être le même que celui d'un bloc d'une autre table.
     */
    private int ID;
    /**
     * Représente les lignes (tuples) contenus dans la table.
     */
    private ArrayList<Ligne> tab;

    /**
     * Constructeur par défaut.
     *
     * @param id Identifiant du bloc. (Usuellement l'index de celui-ci dans le tableau de blocs de sa table)
     */
    public Bloc(int id) {
        this.tab = new ArrayList<Ligne>();
        this.ID = id;
    }

    /**
     * Getter sur l'identifiant du bloc.
     *
     * @return Identifiant du bloc.
     */
    public int getID() {
        return ID;
    }

    /**
     * Retourne le nombre de tuples stockés dans le bloc.
     *
     * @return Nombre de tuples stockés dans le bloc.
     */
    public int size() {
        return this.tab.size();
    }

    /**
     * Retourne les tuples stockés dans le bloc.
     *
     * @return ArrayList des tuples contenus dans ce bloc.
     */
    public ArrayList<Ligne> getContent() {
        return this.tab;
    }

    public int getBlockID(){
        return this.ID;
    }
    
    @Override
    public String toString() {
        String s = "\n\tBloc: " + this.ID + "\n";
        for (Ligne L : tab) {
            s += L.toString();
        }
        return s;
    }
}
