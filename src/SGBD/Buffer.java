package SGBD;

import java.util.ArrayList;

/**
 * @deprecated Cette classe à été crée mais n'est pas utilisée car nous estimons qu'elle n'apporte rien au projet
 * et qu'elle n'est pas indispensable pour répondre au sujet de celui-ci.
 */
public class Buffer {
    /**
     * Représente les blocs en attente d'être écris sur le disque.
     */
    private ArrayList<Bloc> buff;

    /**
     * Constructeur par défaut.
     */
    public Buffer() {
        this.buff = new ArrayList<Bloc>();
    }

    /**
     * Retourne le nombre de blocs contenus dans ce buffer.
     *
     * @return Nombre de blocs contenus dans le buffer
     */
    public int size() {
        return this.buff.size();
    }

    /**
     * Retourne le contenus du buffer.
     *
     * @return ArrayList des blocs de ce buffer.
     */
    public ArrayList<Bloc> getContent() {
        return this.buff;
    }

    /**
     * Méthode vidant le buffer, simulant l'écriture sur disque en brut des données stockées en mémoire tampon.
     */
    public void clear_Buffer() {
        this.buff.clear();
    }

    @Override
    public String toString() {
        String s = "\tBuffer : \n";
        for (Bloc B : buff) {
            s += B.toString();
        }
        return s;
    }
}
