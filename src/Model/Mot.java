package Model;

import java.util.Random;

public class Mot {
    public final static int tailleMin = 6;
    private final static int supCaseZeroChanceEtCasePropositions = 3;
    private final static int minPourSanctionner = 6;

    private IIndication typeIndication;
    private String indication;
    private String leMot;
    private Case[] cases;
    private int score;

    public Mot(String mot, String indication, IIndication typeIndication) {

        this.leMot = mot.toUpperCase();
        setIndication(indication);
        setTypeIndication(typeIndication);
        Random random = new Random();
        int nombreCaseZeroChanceEtPropositions = random.nextInt(supCaseZeroChanceEtCasePropositions + 1);
        int taille = mot.length();
        this.cases = new Case[taille];
        int type;
        int i = 0;
        while (i < taille) {
            this.cases[i] = new MultiChanceCase(this.leMot.charAt(i));
            i++;
        }
        i = nombreCaseZeroChanceEtPropositions;
        while (i > 0) {
            type = random.nextInt(2);
            int j = random.nextInt(this.leMot.length());
            switch (type) {
                case 0:
                    if (nombreCaseZeroChanceEtPropositions > 0) {
                        this.cases[j] = new ZeroChanceCase(this.leMot.charAt(j));
                        nombreCaseZeroChanceEtPropositions--;
                        i--;
                        break;//Pour des raisons de performance
                    }
                case 1:
                    if (nombreCaseZeroChanceEtPropositions > 0) {
                        this.cases[j] = new PropositionsCase(this.leMot.charAt(j));
                        nombreCaseZeroChanceEtPropositions--;
                        i--;
                        break;//Pour des raisons de performance
                    }
            }
        }
    }

    private int nombreCasesMultiChancesEtProposition() {
        int i = 0;
        for (Case uneCase : this.cases)
            if (uneCase instanceof PropositionsCase || uneCase instanceof MultiChanceCase)
                i++;
        return i;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int coeff()//Obtenir le coefficient suivant le type d'indication
    {
        if (this.getTypeIndication() == IIndication.Definition)
            return IIndication.coeffDefinition;
        if (this.getTypeIndication() == IIndication.Synonyme)
            return IIndication.coeffSynonyme;
        if (this.getTypeIndication() == IIndication.Antonyme)
            return IIndication.coeffAntonyme;
        //valeur par défaut
        return 0;
    }

    public void calculerScore()//ça permet de setter le score, après on utilise getScore
    {
        for (Case uneCase : this.cases) {//Calculer le score cumulé
            if (!uneCase.isTraite()) continue;
            setScore(getScore() + uneCase.getScore());
        }
        //Multiplié par le coeff de l'indication
        setScore(getScore() * coeff());

        if (nombreCasesMultiChancesEtProposition() >= minPourSanctionner)//La sanction est prise en compte
            for (Case uneCase : this.cases) {
                if (!uneCase.isTraite()) continue;
                if (uneCase instanceof Malus)
                    setScore(getScore() - ((Malus) uneCase).getMalus());
            }
    }

    public IIndication getTypeIndication() {
        return typeIndication;
    }

    public void setTypeIndication(IIndication typeIndication) {
        this.typeIndication = typeIndication;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String toString() {
        String message = new String();
        message = "Le mot: " + this.leMot + '\n';
        for (Case uneCase : this.cases
                ) {
            message += uneCase.toString();
        }
        return message;
    }

    public Case[] getCases() {
        return this.cases;
    }

    public boolean echecSurLeMot() {
        for (Case laCase : cases
                ) {
            if (laCase.getEchecSurLaCase()) return true;
        }
        return false;
    }

    //retourne vrai dans le cas où l'utilisateur a un succès sur le mot, faux sinon
    public boolean motJuste() {
        for (Case laCase : cases
                ) {
            if (!laCase.isCaseAVrai()) {
                return false;
            }
        }
        return true;
    }
}