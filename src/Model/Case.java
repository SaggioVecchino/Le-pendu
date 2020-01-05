package Model;

public abstract class Case {

    private char caractereDeLaCase;
    private int score;
    private boolean echecSurLaCase;//vrai si il y'a un échec, faux sinon
    private boolean traite;//Si la case est traitée est à vrai, faux sinon
    private boolean caseAVrai;

    public char getCaractereDeLaCase() {
        return caractereDeLaCase;
    }

    public void setCaractereDeLaCase(char caractere) {
        this.caractereDeLaCase = caractere;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Case(char caractereDeLaCase){
        setCaractereDeLaCase(caractereDeLaCase);
        setScore(0);//initialement le score est à 0
        setEchecSurLaCase(false);//initialement il n'y a pas d'échecs sur la case
        setTraite(false);//initialement la case n'est pas traitée
        setCaseAVrai(false);
    }

    public abstract void calculerScore();

    public abstract int getNombrePoints();

    public boolean getEchecSurLaCase() {
        return this.echecSurLaCase;
    }//vrai si il y'a un échec, faux sinon

    private void setEchecSurLaCase(boolean etat) {
        this.echecSurLaCase = etat;
    }

    public void setEchecSurLaCase(){setEchecSurLaCase(true);}

    public abstract boolean entrerCaractere(char caractere);//retourne false au cas où case échouée

    public boolean isTraite() {
        return traite;
    }

    public void setTraite(boolean traite) {
        this.traite = traite;
    }

    public abstract int getNombreDeTentativesRestantes();

    public boolean isCaseAVrai() {
        return caseAVrai;
    }

    public void setCaseAVrai(boolean caseAVrai) {
        this.caseAVrai = caseAVrai;
    }

}