package Model;

public class ZeroChanceCase extends Case {
    private final static int nombrePoints = 3;
    public ZeroChanceCase(char caractereDeLaCase) {
        super(caractereDeLaCase);
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
    public int getNombreDeTentativesRestantes() {
        return 0;
    }

    @Override
    public int getNombrePoints() {
        return nombrePoints;
    }

    @Override
    public boolean entrerCaractere(char caractere)//Entrer un caractère dans la case
    {
        setTraite(true);
        if (caractere == this.getCaractereDeLaCase()) {
            calculerScore();
            setCaseAVrai(true);
            return true;
        }
        //else
        setEchecSurLaCase();
        return false;
    }

    public String toString(){
        String message=new String();
        message="Le caractère de la case: " + getCaractereDeLaCase()+", ";
        message+="le type de la case: ZeroChance\n";

        return message;
    }
}
