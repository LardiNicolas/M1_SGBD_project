package SGBD;

import java.util.ArrayList;

/**
 * Brique de base:
 * représente la mémoire cache lors de la gestion de la mémoire en base de donnée.
 * Pour l'algorithme du cache nous avions le choix entre LRU (Last Recently Used) et LFU (Last Frequently Used) et avons choisis LRU.
 */
public class Cache {
    //ATTENTION, Changement de Requete par Bloc (plus simple)

    /**
     * Taille du cache non modulable pour cette version
     */
    public static final int SIZE_MAX_CACHE = 7;
    /**
     * Requêtes mises en cache
     */
    private ArrayList<Bloc> bloc;

    /**
     * Constructeur par défaut.
     */
    public Cache() {
        this.bloc = new ArrayList<Bloc>();
    }

    /**
     * Méthode pour connaître la taille de notre cache.
     *
     * @return Taille du cache. (Nombres de requêtes stockées)
     */
    public int size() {
        return this.bloc.size();
    }

    /**
     * Retourne le contenu du cache.
     *
     * @return Contenu du cache.
     */
    public ArrayList<Bloc> getContent() {
        return this.bloc;
    }

    /**
     * FIXME
     * Retourne toutes les lignes contenues dans les blocs du cache.
     *
     * @return ArrayList de toutes les lignes du cache.
     */
    public ArrayList<Ligne> getLigne() {
        ArrayList<Ligne> L = new ArrayList<Ligne>();
        for (Bloc R : bloc) {
            L.addAll(R.getContent());
        }
        return L;
    }

    /**
     * Permet de déterminer si la taille du cache permet de stocker une requête en plus.
     *
     * @param x Requête à ajouter.
     * @return Booleen signifiant si le cache peut acceuillir le requête X.
     */
    public boolean espace_disponible(Bloc x) {
        int taille = 0;
        for (Bloc R : bloc) {
            taille += 1; //taille prends la taille totale de notre liste de requête déjà  accepté.
        }
        if (taille < SIZE_MAX_CACHE) { //On ajoute à la taille, la taille de la requête que nous voulons ajouter.
            return true;
        } else {
            return false;
        }
    }

    /**
     * Mise en cache d'une requête selon la méthode LRU. Méthode récursive qui
     * supprime la donnée cachée la plus ancienne si la taille du cache ne permet pas son simple ajout.
     * Les nouvelles entrées sont insérées à la fin de la liste et les plus anciennes sont supprimées au début de la liste (indice 0).
     *
     * @param R Requête à ajouter.
     */
    public void ajoutBloc(Bloc R) {
        if (espace_disponible(R)) {
            this.bloc.add(R);
        } else {
            this.bloc.remove(0);
            ajoutBloc(R);
        }
    }
    
    /**
     * Mise en cache d'une requête selon la méthode LRU. Méthode récursive qui
     * supprime la donnée cachée la plus ancienne si la taille du cache ne permet pas son simple ajout.
     * Les nouvelles entrées sont insérées à la fin de la liste et les plus anciennes sont supprimées au début de la liste (indice 0).
     *
     * @param i indice du tableau à supprimer.
     */
    public void removeBloc(int i) {
        this.bloc.remove(i);        
    }
    
    public void vider() {
        this.bloc.clear();
    }

    /**FIXME
     * @param R
     * @return
     */
    //Méthode pour savoir si toute les lignes du bloc sont déjà dans le cache.
    public boolean in_Cache(Bloc R) {
        boolean bool = false;
        for (Ligne L_bloc : R.getContent()) {
            for (Ligne L_cache : this.getLigne()) {
                if (L_cache == L_bloc) {
                    bool = true;
                } else {
                    return false;
                }
            }
        }
        return bool;
    }
    
    public int getSizeMax(){
        return this.SIZE_MAX_CACHE;
    }
    
    public String afficheCache(){
        String s = "Cache : ";
        for (Bloc R : bloc) {
            s += "[" + R.getID() + "]";
        }
        return s;
    }
    
    @Override
    public String toString() {
        String s = "Cache : \n";
        for (Bloc R : bloc) {
            s += R.toString();
        }
        s += "\nTaille max du cache : " + SIZE_MAX_CACHE;
        s += "\nTaille occupée du cache : " + this.size();
        return s;
    }


}
