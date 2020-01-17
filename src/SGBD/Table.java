package SGBD;

import java.util.ArrayList;

/**
 * Brique de base: Permet de représenter de manière simplifiée une table
 * contenue dans un SGBD relationnel.
 */
public class Table {

    /**
     * Tableau (ArrayList) représentant les tuples contenus dans la table.
     */
    private ArrayList<Bloc> tab;
    /**
     * Format de la table. Contient la liste des attributs des tuples de la
     * table.
     */
    private TableFormat format;

    public TableFormat getFormat() {
        return format;
    }

    /**
     * Constructeur par défaut de la table.
     *
     * @param tf Format des attributs de la table
     */
    public Table(TableFormat tf) {
        this.tab = new ArrayList<Bloc>();
        this.format = tf;
    }

    /**
     * Création automatique des lignes à insérer dans la table en fonction du
     * TableFormat.
     *
     * @param nbL Nombre de lignes à insérer
     */
    public void createTable(int nbL) {
        for (int i = 0; i < nbL; i++) {
            Ligne l = new Ligne();
            l.ajouterAttribut(format);
            this.ajouterLigne(l);
        }
    }

    public TableFormat gteFormat() {
        return this.format;
    }

    /**
     * Permet d'ajouter une ligne à la table en l'insérant dans un bloc.
     *
     * @param L Ligne à ajouter à la Table
     */
    public void ajouterLigne(Ligne L) {
        for (Bloc B : tab) {
            if (B.size() < Bloc.MAX_SIZE) {
                L.ID = B.size();
                B.getContent().add(L);
                return;
            }
        }
        this.tab.add(new Bloc(tab.size()));
        this.tab.get(this.tab.size() - 1).getContent().add(L);
    }

    /**
     * Accesseur aux tuples de la table.
     *
     * @return Arraylist 'tab' représentant l'ensemble des tuples de la table.
     */
    public ArrayList<Bloc> getContent() {
        return this.tab;
    }

    public int valueSize() {
        int temp = 0;
        for (Bloc b : tab) {
            for (Ligne l : b.getContent()) {
                temp += l.getContent().size();
            }
        }
        return temp;
    }

    public int linesSize() {
        int temp = 0;
        for (Bloc b : tab) {
            temp += b.size();
        }
        return temp;
    }

    public int blocsSize() {
        return this.getContent().size();
    }

    @Override
    public String toString() {
        String s = "Table:\n";
        for (Bloc b : this.tab) {
            s += b.toString();
        }
        return s;
    }

    /**
     * Permet de générer un identifiant unique à chaque table par hashage.
     *
     * @return HashCode unique par table.
     */
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

}
