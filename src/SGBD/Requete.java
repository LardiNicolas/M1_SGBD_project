package SGBD;

import java.util.ArrayList;

/**
 * Représente le résultat d'une requête.
 */
public class Requete {

    /**
     * Liste des tuples retournés pas la requête
     */
    private ArrayList<Ligne> tab;

    /**
     * Constructeur par défaut.
     */
    public Requete() {
        this.tab = new ArrayList<Ligne>();
    }

    /**
     * Retourne l'identifiant unique de la requête.
     *
     * @return Identifiant de la requête.
     */
    public int getID() {
        return this.hashCode();
    }

    /**
     * Retoutne la taille de la requête.
     *
     * @return Nombre de tuples contenus dans la requête.
     */
    public int size() {
        return this.tab.size();
    }

    /**
     * Retourne le contenu de la requête.
     *
     * @return Lignes de la requête.
     */
    public ArrayList<Ligne> getContent() {
        return this.tab;
    }

    /**
     * Ajoute une ligne au résultat de la requête. Methode utilisée lors de la constructon de l'instance.
     *
     * @param l
     */
    public void add(Ligne l) {
        this.tab.add(l);
    }

    @Override
    public String toString() {
        String s = "\tRequete: " + this.getID() + "\n";
        for (Ligne L : tab) {
            s += L.toString();
        }
        return s;
    }
}
