package SGBD;

import java.util.ArrayList;

/**
 * @deprecated Classe abstraite utile pour les classes stockant des données.
 * Non utilisée car elle implique des casts dans toutes les classes et rend le code illisible.
 */
public abstract class Container {
    /**
     * Tableau de stockage.
     */
    protected ArrayList tab;
    /**
     * Identifiant du stockage.
     */
    protected int ID;

    /**
     * @return Contenu du tableau de stockage.
     */
    public ArrayList getContent() {
        return this.tab;
    }

    /**
     * Retourne la taille du stockage.
     *
     * @return Taille du tableau de stockage.
     */
    public int size() {
        return this.tab.size();
    }
}
