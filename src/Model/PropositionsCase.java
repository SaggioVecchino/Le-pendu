package Model;

import java.util.Random;

public class PropositionsCase extends ZeroChanceCase implements Malus {
    private final static int nombrePoints = 2;
    public final static int nombrePropositions = 4;
    public final static int valeurMalus = 1;

    private int nombreChancesConsomees;
    private char[] lesPropositions;

    public PropositionsCase(char caractereDeLaCase) {
        super(caractereDeLaCase);
        setNombreChancesConsomees(0);

        // Dans toute la suite
        // On génère quatre (4) caractères dans lesPropositions tous différents deux à deux
        // dont l'un est certainement caractereDeLaCase

        lesPropositions = new char[nombrePropositions];
        Random random = new Random();
        lesPropositions[0] = (char) (random.nextInt(26)+65); //Avoir un premier caractère aléatoire
        boolean exist = false;
        char randomCaractere;
        for (int i = 1; i < nombrePropositions; i++) {//Remplir le tableau de nombreDePropositions caractères distincts deux à deux
            do {//Eviter les doublons
                exist = false;
                randomCaractere = (char) (random.nextInt(26)+65);
                for (int j = 0; j < i; j++) {
                    if (lesPropositions[j] == randomCaractere) {
                        exist = true;
                        break;
                    }
                }
            } while (exist == true);
            lesPropositions[i] = randomCaractere;
        }

        for (int i = 0; i < nombrePropositions; i++) {//Verifier si caractereDeLaCase est dans le tableau
            if (lesPropositions[i] == caractereDeLaCase) {
                exist = true;
                break;
            }
        }
        //Si il n'est pas dans le tableau il prend une case du tabeau d'un indice aléatoire
        if (!exist) lesPropositions[random.nextInt(nombrePropositions)] = caractereDeLaCase;
    }

    public int getNombreChancesConsomees() {
        return nombreChancesConsomees;
    }

    public void setNombreChancesConsomees(int nombreChancesConsomees) {
        this.nombreChancesConsomees = nombreChancesConsomees;
    }

    public char[] getLesPropositions() {
        return lesPropositions;
    }

    @Override
    public int getMalus() {
        if (getEchecSurLaCase()) return this.valeurMalus;
        return 0;
    }

    @Override
    public void calculerScore()//ça permet de setter le score, après on utilise getScore
    {
        if (!isTraite()) return;
        if (!getEchecSurLaCase())
            setScore(this.getNombrePoints());
        //else score est à 0 par défaut
        return;
    }

    @Override
    public int getNombrePoints() {
        return nombrePoints;
    }

    public String toString(){
        String message=new String();
        message="Le caractère de la case: " + getCaractereDeLaCase()+", ";

        message+="Les caractères proposés: ";
        for (char carac: lesPropositions
                ) {
            message+=carac+" ,";
        }
        message+="le type de la case: PropositionsChance\n";

        return message;
    }
}
