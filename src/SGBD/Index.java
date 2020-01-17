package SGBD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Brique de base:
 * représente un index secondaire d'un SGBD relationnel.
 */
public class Index {
    /**
     * Table liée à l'index
     */
    protected Table table;

    public Table getTable() {
        return table;
    }
    /**
     * Tableau des entrées de l'index.
     */
    private ArrayList<IndexEntry> tab;
    /**
     * Permet de vérifier la validité de l'index. Le hash de la table devient différent quand la table est modfiée.
     */
    private int LAST_VALID_HASH;
    /**
     * Nom de l'attribut sur lequel porte l'index.
     */
    private String ATTRIBUTE_NAME;

    /**
     * Renvoie une liste des entrées correspondants aux critère de recherche.
     *
     * @param s Critère de recherche. La donnée de l'entrée doit être égale pour être retournée.
     * @return Liste des accorences trouvées. null à défaut.
     */
    public List search(String s) {
        try {
            return tab.subList(indexOf(s), lastIndexOf(s) + 1);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Recherche les données dans la table d'entrées.
     *
     * @param s Critère de recherche.
     * @return index de la première occurence.
     */
    private int indexOf(String s) {
        for (int i = 0; i < tab.size(); i++) {
            if (tab.get(i).DATA.equals(s)) return i;
        }
        return -1;
    }

    /**
     * Recherche les données dans la table d'entrées.
     *
     * @param s Critère de recherche.
     * @return index de la dernière occurence.
     */
    private int lastIndexOf(String s) {
        for (int i = tab.size() - 1; i > 0; i--) {
            if (tab.get(i).DATA.equals(s)) return i;
        }
        return -1;
    }

    /**
     * Constructeur de base.
     *
     * @param t        Table liée à l'index
     * @param att_name Nom de l'attribut à indexer.
     */
    public Index(Table t, String att_name) {
        this.table = t;
        this.tab = new ArrayList<IndexEntry>();
        this.ATTRIBUTE_NAME = att_name;
        this.check_validity();
    }


    /**
     * Vérifie la validité de l'index. L'index est reconstruit si il n'est pas valide.
     */
    public void check_validity() {
        int REAL_H_CODE = this.table.hashCode();
        if (REAL_H_CODE != LAST_VALID_HASH) {
            this.build();
            this.LAST_VALID_HASH = REAL_H_CODE;
        }
    }

    /**
     * Indexation de toutes les entrées de la table liée. Classement par ordre alphabétique.
     */
    private void build() {
        this.tab.clear();
        for (Bloc B : table.getContent()) {
            for (Ligne L : B.getContent()) {
                for (Attribut A : L.getContent()) {
                    if (A.NAME.equals(this.ATTRIBUTE_NAME)) {
                        this.tab.add(new IndexEntry(A.DATA, B.getID(), L.ID));
                        break;
                    }
                }
            }
        }
        Collections.sort(this.tab);
    }
    
    public ArrayList<IndexEntry> getContent() {
        return this.tab;
    }
    
    public String getNAME(){
        return this.ATTRIBUTE_NAME;
    }
    
    public int size() {
        return this.tab.size();
    }
    
    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder("Index: " + ATTRIBUTE_NAME + "\n");
        for (IndexEntry IE : tab) {
            temp.append(IE.toString());
        }
        return temp.toString();
    }


}
