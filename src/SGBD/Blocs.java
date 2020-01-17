package SGBD;

import java.util.ArrayList;

/**
 * Représente le résultat d'une requête.
 */
public class Blocs {

    /**
     * Liste des tuples retournés pas la requête
     */
    private ArrayList<Bloc> tab;

    /**
     * Constructeur par défaut.
     */
    public Blocs() {
        this.tab = new ArrayList<Bloc>();
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
     * @return Blocs de la requête.
     */
    public ArrayList<Bloc> getContent() {
        return this.tab;
    }

    /**
     * Ajoute une ligne au résultat de la requête. Methode utilisée lors de la constructon de l'instance.
     *
     * @param l
     */
    public void add(Bloc l) {
        this.tab.add(l);
    }

    @Override
    public String toString() {
        String s = "\tRequete: " + this.getID() + "\n";
        for (Bloc L : tab) {
            s += L.toString();
        }
        return s;
    }
}
