package SGBD;

import java.util.ArrayList;
import java.util.List;

/**
 * Format d'une table d'un SGBD relationnel.
 */
public class TableFormat {
    /**
     * Tableau contenant les noms des attributs des tuples d'une table.
     */
    protected ArrayList<String> format;

    public ArrayList<String> getFormat() {
        return format;
    }

    /**
     * Constructeur de base.
     *
     * @param a Tableau des noms des attributs de la table.
     */
    public TableFormat(List<String> a) {
        this.format = new ArrayList<String>(a);
    }

}
