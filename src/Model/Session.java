package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;


public class Session {
    private final static int nbMot = 10;
    private int scoreCourant;
    private int indiceMotCourant;
    private int nbEchec;
    private Utilisateur userConnecte;
    private Mot[] mots;
    private Dessin dessin;

    public Session(Utilisateur us) {
        this.indiceMotCourant = 0;
        this.scoreCourant = 0;
        this.nbEchec = 0;
        this.userConnecte = us;
        String tmp = "TPPOODesign/";
        dessin = new Dessin(new String[]{tmp + "0.png", tmp + "1.png", tmp + "2.png", tmp + "3.png",
                tmp + "4.png", tmp + "5.png", tmp + "6.png"});
        this.mots = new Mot[nbMot];
        BufferedReader in2 = null;
        BufferedReader in = null;
        String ligne;
        try {
            in = new BufferedReader(new FileReader(new File("mots.poo")));
            int j = 0;
            ArrayList<Integer> moinsDe6 = new ArrayList<Integer>();
            //dans cette boucle on parcourt le fichier pour avoir le nombre de ligne
            // et enregistrer le numéro des lignes qui contiennent des mots de moins de 6 caractères
            // pour ne pas les générer par la suite
            while ((ligne = in.readLine()) != null) {
                StringTokenizer tok = new StringTokenizer(ligne, ";");
                int nb = tok.countTokens();
                String mot = "";

                for (int i = 0; i < nb; i++) {
                    mot = tok.nextToken();
                }
                if (mot.length() <= Mot.tailleMin) {
                    moinsDe6.add(j);
                }
                j++;
            }
            // On crée la liste qui contiendra les numéros des lignes du fichier
            // obtenu aléatoirement pour générer les mots
            ArrayList<Integer> lisrInt = new ArrayList<Integer>();
            int rand;
            Random rn = new Random();
            for (int k = 0; k < this.nbMot; k++) {
                do {
                    rand = rn.nextInt(j);
                } while (lisrInt.contains(rand) || moinsDe6.contains(rand));
                //on s’assure qu’il n y a pas de
                // doublant et on ne prend pas les mots de moins de 6 caractères

                lisrInt.add(rand);
            }
            in2 = new BufferedReader(new FileReader("mots.poo"));
            int cpt = 0, nbmot = 0;
            String contenu, type, mot;
            // Une boucle pour remplir le tableau des mots
            // en utilisant la liste qui contient les lignes des mots à récupérer
            while ((ligne = in2.readLine()) != null && nbmot < this.nbMot) {
                if (lisrInt.contains(cpt)) {
                    StringTokenizer tok = new StringTokenizer(ligne, ";");

                    type = tok.nextToken();
                    contenu = tok.nextToken();
                    mot = tok.nextToken();
                    this.mots[nbmot] = new Mot(mot, contenu, IIndication.getIndication(type));
                    nbmot++;
                }
                cpt++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
        }
    }

    public Dessin getDessin() {
        return dessin;
    }

    public void setDessin(Dessin dessin) {
        this.dessin = dessin;
    }

    public String toSring() {
        String str = new String("");
        for (Mot m : this.mots) {
            str = str + m.toString() + "\n";
        }
        return str;
    }

    public Mot getMot(int i) {
        return mots[i];
    }

    public Utilisateur getUserConnecte() {
        return userConnecte;
    }

    public int getScoreCourant() {
        return scoreCourant;
    }

    public void setScoreCourant(int scoreCourant) {
        this.scoreCourant = scoreCourant;
    }

    public int getIndiceMotCourant() {
        return indiceMotCourant;
    }

    public void setIndiceMotCourant(int indiceMotCourant) {
        this.indiceMotCourant = indiceMotCourant;
    }

    //retourne faux si fin du jeu, vrai sinon
    public boolean motSuivant() {
        mots[getIndiceMotCourant()].calculerScore();
        this.mettreAJourScores(motCourant().getScore());
        if(getMot(getIndiceMotCourant()).echecSurLeMot()){
            if(dessin.avancer()==6) return false;
        }
        if(this.indiceMotCourant==nbMot-1) return false;
        this.indiceMotCourant++;
        return true;
    }

    public Mot motCourant(){
        return getMot(getIndiceMotCourant());
    }

    //incrémenter le score courant de scoreARajouter et mettre à jour le best score
    //de l'utisateur connecté si nécessaire
    private int mettreAJourScores(int scoreARajouter){
        setScoreCourant(getScoreCourant()+scoreARajouter);
        if(scoreCourant>userConnecte.getBestScore())
            userConnecte.setBestScore(scoreCourant);
        return scoreCourant;
    }

}
