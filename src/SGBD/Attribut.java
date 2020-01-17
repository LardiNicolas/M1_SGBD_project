package SGBD;

import RandomString.RandomString;

/**
 * Brique de base:
 * Représente un attribut d'un tuple contenu dans une table d'un SGBD relationnel.
 */
public class Attribut implements Comparable {
    /**
     * Valeur de l'attribut.
     */
    protected String DATA;
    /**
     * Nom de l'attribut. Correspond au champ contenu dans le TableFormat de la table contenant cet attribut.
     */
    protected String NAME;

    /**
     * @deprecated Constructeur par défaut. Génération automatique aléatoire.
     */
    public Attribut() {
        this.DATA = RandomString.generate(10);
        this.NAME = RandomString.generate(3);
    }

    /**
     * Constructeur permettant de construire un attribut en spécifiant le nom et la valeur de celui-ci.
     *
     * @param N Nom de l'attribut.
     * @param D Valeur de l'attribut.
     */
    public Attribut(String N, String D) {
        this.DATA = D;
        this.NAME = N;
    }

    @Override
    public String toString() {
        return "\t\t\t Attribut: " + this.NAME + " " + this.DATA ;

    }
    
    public String getDATA(){
        return this.DATA;
    }
    
    public String getNAME(){
        return this.NAME;
    }
    
    @Override
    public int compareTo(Object o) {
        if (o instanceof Attribut)
            return this.DATA.compareTo(((Attribut) o).DATA);
        return 0;
    }
}
