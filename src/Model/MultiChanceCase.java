package Model;

public class MultiChanceCase extends Case implements Malus {

    public final static int chances = 2;
    private final static int nombrePoints = 1;
    public final static int valeurMalus = 2;

    private int nombreChancesConsomees;

    public MultiChanceCase(char caractereDeLaCase) {
        super(caractereDeLaCase);
        setNombreChancesConsomees(0);
    }


    public int getNombreChancesConsomees() {
        return nombreChancesConsomees;
    }


    public void setNombreChancesConsomees(int nombreChancesConsomees) {
        this.nombreChancesConsomees = nombreChancesConsomees;
    }

    public int consomerUneChance()//consommer une chance et donc incrémenter le nombre de chances consommées
    {
        setNombreChancesConsomees(getNombreChancesConsomees() + 1);
        return (getNombreChancesConsomees());
    }

    @Override
    public int getNombreDeTentativesRestantes() {
        return this.chances - getNombreChancesConsomees();
    }


    @Override
    public int getMalus() {
        return this.valeurMalus * getNombreChancesConsomees();
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

    @Override
    public boolean entrerCaractere(char caractere)//Entrer un caractère dans la case, retourne faux dans le
    //cas ou la case est à échec ("chances" fois erreurs), vrai sinon
    {
        setTraite(true);
        if (caractere == this.getCaractereDeLaCase()) {

            calculerScore();
            setCaseAVrai(true);
            return true;
        }
        //else
        if (getNombreChancesConsomees() < chances) {
            consomerUneChance();
            return true;
        }
        consomerUneChance();
        setEchecSurLaCase();
        return false;

    }


    public String toString() {
        String message = new String();
        message = "Le caractère de la case: " + getCaractereDeLaCase() + ", ";
        message += "le type de la case: MultiChance\n";

        return message;
    }
}
