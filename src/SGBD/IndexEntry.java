package SGBD;

/**
 * Entrée dans un index secondaire.
 */
public class IndexEntry implements Comparable {
    /**
     * Représente la valeur de l'attribut.
     */
    protected String DATA;
    /**
     * L'id du bloc et de la ligne qui contiennent la donnée.
     */
    int BLOCK_ID, LINE_ID;

    /**
     * Constructeur de base.
     *
     * @param data Donnée de l'entrée.
     * @param bid  Indice du bloc contenant la donnée dans la table.
     * @param lid  Indice de la ligne contenant la donnée dans le bloc.
     */
    public IndexEntry(String data, int bid, int lid) {
        this.DATA = data;
        this.BLOCK_ID = bid;
        this.LINE_ID = lid;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof IndexEntry)
            return this.DATA.compareTo(((IndexEntry) o).DATA);
        return 0;
    }
    
    public String getDATA() {
        return this.DATA;
    }
    
    public int getBlockID() {
        return this.BLOCK_ID;
    }
    
    public int getLineID(){
        return this.LINE_ID;
    }

    @Override
    public String toString() {
        return "Entréé: \tDATA: " + DATA + "\t Bloc: " + BLOCK_ID + " \t Ligne: " + LINE_ID + "\n";
    }
}
