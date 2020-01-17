/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SGBD;

import java.util.*;

/**
 *
 * @author lardi
 */
public class Jointure {

    public static ResultatJointure keyLookup(Table t1, Table t2, Index ix1) {
        //on considere que l'index est sur t1
        int cout = 0;

        ResultatJointure resultat = new ResultatJointure();

        System.out.println("test keylookup:\n");
        /*
        PSEUDO CODE
1       jointure_key_lookup(relation R1, relation R2, critère C) {
2             pour chaque ligne lr1 de R1
3             pour chaque ligne lr2 de R2 vérifiant C (balayage par index)
4             nouvelle_ligne(concat(lr1, lr2))
5             finpour
6             finpour
7             }
         */
        int nbBloc1Lu = 0;
        int nbBloc2Lu = 0;
        int nbLigne1Lu = 0;
        int nbLigne2Lu = 0;
        int tempBloc = -1;
        int tempLine = -1;
        int k = 0;

        for (IndexEntry index : ix1.getContent()) {
            if (index.getBlockID() != tempBloc) {
                nbBloc1Lu++;
                if (index.getLineID() != tempLine) {
                    nbLigne1Lu++;
                }
            } //On fait cela pour compter le nombre de bloc et de ligne parcouru dans la table 1 par l'index
            tempBloc = index.getBlockID();
            tempLine = index.getLineID();
            //System.out.println("ligne lu de B1:\n" + lr1);
            for (Bloc b2 : t2.getContent()) {
                //System.out.println("bloc lu de T2:\n" + b2);
                nbBloc2Lu++;
                for (Ligne lr2 : b2.getContent()) {
                    nbLigne2Lu++;
                    for (Attribut at2 : lr2.getContent()) {
                        if (index.getDATA().equals(at2.getDATA()) && ix1.getNAME().equals(at2.getNAME())) {
                            cout++;
                            resultat.ajouterLigneJointure(
                                    t1.getContent().get(index.getBlockID()).getContent().get(index.getLineID()),
                                    t2.getContent().get(b2.getBlockID()).getContent().get(lr2.getLineID()),
                                    ix1.getNAME(), index.getBlockID(), b2.getBlockID());
                            k++;
                        }
                    }
                }
            }
        }

        System.out.println("nbBloc1Lu = " + nbBloc1Lu);
        System.out.println("nbLigne1Lu = " + nbLigne1Lu);
        System.out.println("nbBloc2Lu = " + nbBloc2Lu);
        System.out.println("nbLigne2Lu = " + nbLigne2Lu);

        System.out.println(resultat);
        System.out.println("cout = " + cout);

        //cout estimé en cours = Br + Tr * (Ts/Ib)
        int coutEstime = t2.blocsSize() + t1.linesSize() * (t2.linesSize() / ix1.getContent().size());
        System.out.println("cout estimé = " + coutEstime);

        int[] couts = new int[2];
        couts[0] = cout;
        couts[1] = coutEstime;
        return resultat;
    }

    public static ResultatJointure produitCartesien(Table t1, Table t2, String attribut) {
        int cout = 0;

        ResultatJointure resultat = new ResultatJointure();

        System.out.println("test produitCartesien :\n");
        Cache cache = new Cache();
        //La taille de notre cache est setup dans la classe Cache.
        int tailleMaxCache = cache.getSizeMax();
        int indiceBlocsT1 = 0; //Pour la lecture de blocs
        int k = 0;

        while (indiceBlocsT1 != t1.getContent().size()) {
            //mise en cache des blocs de t1
            cache.vider();
            System.out.println("Vide du cache -> " + cache.afficheCache());
            int indiceCache = 0;
            for (Bloc b1 : t1.getContent()) {
                if (b1.getBlockID() >= indiceBlocsT1 && indiceCache < tailleMaxCache - 1) { //-1 car on souhaite mettre en dernière valeur les blocs de T2
                    cache.ajoutBloc(b1);
                    indiceCache++;
                    cout++; //car on lit un bloc de T1
                    System.out.println("Ajout bloc T1 dans le " + cache.afficheCache());
                }
            }
            indiceBlocsT1 += indiceCache;

            for (Bloc b2 : t2.getContent()) {
                // On prendra pour exemple : pour un cache = [0,1,2,3,4,5] les blocs de T1 seront aux indices [0,1,2,3,4] et les blocs de T2 dans l'indice [5]
                //Cependant il se peu que T1 est moins de 5 valeurs, donc on prends en compte l'indice du cache suivant les valeurs de T1. Soit T1 n'ayant que 2 valeurs, alors le cache sera
                // de la manière suivant : cache = [0,1,2] avec [0,1] les blocs de T1 et [2] un bloc de T2.
                cout++;

                cache.ajoutBloc(b2);

                System.out.println("Ajout Bloc T2 dans le " + cache.afficheCache());
                Blocs blocsDeT1 = new Blocs();
                for (int i = 0; i < indiceCache; i++) {
                    blocsDeT1.add(cache.getContent().get(i));
                }
                Bloc blocDeT2 = cache.getContent().get(indiceCache);

                for (Bloc b1 : blocsDeT1.getContent()) {

                    for (Ligne l1 : b1.getContent()) {
                        for (Ligne l2 : blocDeT2.getContent()) {
                            for (Attribut at1 : l1.getContent()) {
                                for (Attribut at2 : l2.getContent()) {
                                    if (at1.getDATA().equals(at2.getDATA()) && at1.getNAME().equals(at2.getNAME()) && at1.getNAME().equals(attribut)) {

                                        resultat.ajouterLigneJointure(
                                                t1.getContent().get(b1.getBlockID()).getContent().get(l1.getLineID()),
                                                t2.getContent().get(b2.getBlockID()).getContent().get(l2.getLineID()),
                                                attribut, b1.getBlockID(), b2.getBlockID());

                                        k++;
                                    }
                                }
                            }
                        }
                    }
                }
                cache.removeBloc(indiceCache);//On supprime à chaque fois le bloc de T2 avant de le remplacer par un nouveau bloc de T2
                System.out.println("REMOVE bloc T2 -> " + cache.afficheCache());
            }
            indiceCache = 0;
        }

        System.out.println(resultat);

        System.out.println("cout = " + cout);

        //cout estimé en cours = (Br1/m-1) * Br2 + Br1
        System.out.println("taille blocs t1 = " + t1.blocsSize());
        System.out.println("taille blocs t2 = " + t2.blocsSize());
        System.out.println("M-1 = " + (cache.getSizeMax() - 1));
        float coutEstime = (((float) t1.blocsSize() / ((float) cache.getSizeMax() - 1)) * (float) t2.blocsSize()) + (float) t1.blocsSize();
        System.out.println("cout estimé arrondi à l'inférieur = " + (int) coutEstime);
        System.out.println("taille final de la jointure : " + k);
        int[] couts = new int[2];
        couts[0] = cout;
        couts[1] = (int) coutEstime;
        return resultat;
    }

    public static ResultatJointure Hashage(Table t1, Table t2, String attribut) {
        /*
        PSEUDO CODE
            partitionner_h(R1, A) -> (R11, ..., R1n)
            partitionner_h(R2, A) -> (R21, ..., R2n)
            pour chaque i = 1 à n
                lire tous les blocs de R1i en mémoire centrale
                pour chaque bloc b de R2i
                    pour chaque ligne lR2 dans b
                        pour chaque ligne lR1 dans R1i
                            nouvelle_ligne(concat(lR1,lR2))
                        finpour
                    finpour
                finpour
            finpour
            }
            partitionner_h(relation R, attribut A) {
                pour chaque ligne lR de R
                    i <- h(R(lR).A)
                ajouter lR au bloc de Ri
            finpour
            }
         */

        int cout = 0;

        ResultatJointure resultat = new ResultatJointure();

        ArrayList<Partition> partition1 = new ArrayList();
        ArrayList<Partition> partition2 = new ArrayList();

        /*Partitionnement de t1*/
        for (Bloc b1 : t1.getContent()) {
            cout++;
            for (Ligne l1 : b1.getContent()) {
                for (Attribut a1 : l1.getContent()) {
                    if (a1.getNAME().equals(attribut)) {
                        if (partition1.size() == 0) {
                            Paire pa = new Paire(b1.getBlockID(), l1.getLineID());
                            pa.getContent().add(l1);
                            partition1.add(new Partition(a1.getDATA()));
                            partition1.get(partition1.size() - 1).getContent().add(pa);
                        } else {
                            boolean test = true;
                            for (Partition p : partition1) {
                                if (p.getHashcode().equals(a1.getDATA())) {
                                    Paire pa = new Paire(b1.getBlockID(), l1.getLineID());
                                    pa.getContent().add(l1);
                                    p.getContent().add(pa);
                                    test = false;
                                }
                            }
                            if (test) {
                                partition1.add(new Partition(a1.getDATA()));
                                Paire pa = new Paire(b1.getBlockID(), l1.getLineID());
                                pa.getContent().add(l1);
                                partition1.get(partition1.size() - 1).getContent().add(pa);
                            }
                        }
                    }
                }
            }
        }

        /*Partitionnement de t2*/
        for (Bloc b2 : t2.getContent()) {
            cout++;
            for (Ligne l2 : b2.getContent()) {
                for (Attribut a2 : l2.getContent()) {
                    if (a2.getNAME().equals(attribut)) {
                        if (partition1.size() == 0) {
                            Paire pa = new Paire(b2.getBlockID(), l2.getLineID());
                            pa.getContent().add(l2);
                            partition2.add(new Partition(a2.getDATA()));
                            partition2.get(partition2.size() - 1).getContent().add(pa);
                        } else {
                            boolean test = true;
                            for (Partition p : partition1) {
                                if (p.getHashcode().equals(a2.getDATA())) {
                                    Paire pa = new Paire(b2.getBlockID(), l2.getLineID());
                                    pa.getContent().add(l2);
                                    p.getContent().add(pa);
                                    test = false;
                                }
                            }
                            if (test) {
                                partition2.add(new Partition(a2.getDATA()));
                                Paire pa = new Paire(b2.getBlockID(), l2.getLineID());
                                pa.getContent().add(l2);
                                partition2.get(partition2.size() - 1).getContent().add(pa);
                            }
                        }
                    }
                }
            }
        }

        /*Jointure*/
        for (Bloc b1 : t1.getContent()) {
            cout++;
            for (Bloc b2 : t2.getContent()) {
                for (Ligne l1 : b1.getContent()) {
                    for (Ligne l2 : b2.getContent()) {
                        for (Attribut a1 : l1.getContent()) {
                            for (Attribut a2 : l2.getContent()) {
                                if (a1.getDATA().equals(a2.getDATA()) && a1.getNAME().equals(a2.getNAME()) && a1.getNAME().equals(attribut)) {
                                    resultat.ajouterLigneJointure(
                                            t1.getContent().get(b1.getBlockID()).getContent().get(l1.getLineID()),
                                            t2.getContent().get(b2.getBlockID()).getContent().get(l2.getLineID()),
                                            attribut, b1.getBlockID(), b2.getBlockID());
                                }
                            }
                        }
                    }
                }
            }
        }


        
        
        double cout_estime = 3 * t1.blocsSize() + 3 * t2.blocsSize() ;
        System.out.println("cout de notre résultat:" + (int) cout);
        System.out.println("cout estimé :" + (int) cout_estime);
        
        return resultat;
        
    }

    public static ResultatJointure Sort_Merg(Table t1, Table t2, String attribut) {
        /*
        PEUDO CODE
            trier R1 sur A
            trier R2 sur A
            posR1 = 0
            posR2 = 0
            tantque posR1 = taille(R1)-1 ou posR2 = taille(R2)-1
                si R1[lig:posR1, col:A] = R2[lig:posR2, col:A] alors
                    nouvelle_ligne(concat(R1[lig:posR1],R2[lig:posR2]))
                    posR2 = posR2 + 1
                sinon si R1[lig:posR1, col:A] > R2[lig:posR2, col:A] alors
                    posR2 = posR2 + 1
                sinon
                    posR1 = posR1 + 1
                fin si
            fin tantque
         */
        double cout = 0;

        TableFormat tf = new TableFormat(Arrays.asList("Attr1", "Attr2", "Attr3"));
        ResultatJointure resultat = new ResultatJointure();
        /*Création des index sur t1 et t2 pour trié la table*/
        Index i1 = new Index(t1, attribut);
        Index i2 = new Index(t2, attribut);

        int posR1 = 0;
        int posR2 = 0;
        int indiceBloc = 0;
        int indiceLigne = 0;

        while (posR1 < i1.size() && posR2 < i2.size()) {
            for (IndexEntry ix1 : i1.getContent()) {
                for (IndexEntry ix2 : i2.getContent()) {
                    if (ix1.getDATA().equals(ix2.getDATA())) {
                        //resultat.ajouterLigne(t1.getContent().get(ix1.getBlockID()).getContent().get(ix1.getLineID()));
                        resultat.ajouterLigneJointure(
                                t1.getContent().get(ix1.getBlockID()).getContent().get(ix1.getLineID()),
                                t2.getContent().get(ix2.getBlockID()).getContent().get(ix2.getLineID()),
                                attribut, ix1.getBlockID(), ix2.getBlockID());

                        //resultat.ajouterLigne(t2.getContent().get(ix2.getBlockID()).getContent().get(ix2.getLineID()));
                        System.out.println("valeur a1 : " + ix1.getDATA() + " sur " + attribut);
                        System.out.println("valeur a2 : " + ix2.getDATA());
                        System.out.println("Ajout de la ligne de t1 : " + t1.getContent().get(ix1.getBlockID()).getContent().get(ix1.getLineID()));
                        posR2++;
                        cout++;
                    } else if (posR1 > posR2) {
                        posR2++;
                    } else {
                        posR1++;
                    }
                }
            }
        }

        double cout_estime = 2 * t1.blocsSize() * Math.log(t1.blocsSize()) + 2 * t2.blocsSize() * Math.log(t2.blocsSize()) + t1.blocsSize() + t2.blocsSize();
        cout = cout + 2 * t1.blocsSize() * Math.log(t1.blocsSize()) + 2 * t2.blocsSize() * Math.log(t2.blocsSize());
        System.out.println("cout de notre résultat:" + (int) cout);
        System.out.println("cout estimé :" + (int) cout_estime);
        System.out.println(resultat);
        return resultat;
    }

    

}
