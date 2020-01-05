package Model;

public class Dessin {
    private int etat;
    private String sources[];

    public Dessin(String[] sources) {
        this.etat = 0;
        this.sources = sources;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String[] getSources() {
        return sources;
    }

    public void setSources(String[] sources) {
        this.sources = sources;
    }

    public int avancer() {
        setEtat(getEtat() + 1);
        return getEtat();
    }

    public String getImage() {
        try {
            return sources[etat];
        } catch (Exception e) {
            return "";
        }
    }

}
