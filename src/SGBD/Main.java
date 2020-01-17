package SGBD;

import RandomString.RandomString;

import java.util.Arrays;

/**
 * Classe principale
 */
public class Main {

    public static void main(String[] args) {
        //Table t1 = createTable(10, new TableFormat(Arrays.asList("Attr1", "Attr2", "Attr3")));
        //Table t2 = createTable(10, new TableFormat(Arrays.asList("Attr1", "Attr2", "Attr3")));

        //t1.createTable(1);
        //t2.createTable(5);

        //Jointure.Sort_Merg(t1, t2, "Attr2");
        // Jointure.Hashage(t1, t2, "Attr2");
        //Jointure.produitCartesien(t1, t2,"Attr1");
        //Jointure.keyLookup(t1,t2,i);
        /*
        Cache cache = new Cache();
        cache.ajoutBloc(t1.getContent().get(0));
        cache.ajoutBloc(t1.getContent().get(1));
        cache.ajoutBloc(t1.getContent().get(2));
        cache.ajoutBloc(t1.getContent().get(3));
        cache.ajoutBloc(t1.getContent().get(4));
        Blocs blocsDeT1 = new Blocs();
        for (int j = 0; j < cache.getSizeMax()-1; j++) {
            blocsDeT1.add(cache.getContent().get(j));
        }
        
        System.out.println();
        for(Bloc b : t1.getContent()){
            if(cache.espace_disponible(b)){
                cache.ajoutBloc(b);
                System.out.println("Ajout du bloc [" +b.getID()+"], Taille du cache après ajout : "+cache.size() + "\n");
            }else{ System.out.println("taille insuffisante"); }            
        }
        System.out.println(cache);
        cache.vider();
        System.out.println(cache);
         */
//        cache.ajoutBloc(b1);
        //System.out.println(""+cache.in_Cache(b));
        //String str1 = i.toString();
        //System.out.println(str1);
        //System.out.println(t1);
        //System.out.println(t2);
        //System.out.println(i.search("a"));
        
    }

    
}
