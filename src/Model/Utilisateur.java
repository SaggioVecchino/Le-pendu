package Model;

public class Utilisateur
{
    private String password ;
    private String userName;
    private int bestScore;

    public Utilisateur(String userName,String password) {
        this.password = password;
        this.userName = userName;
        this.bestScore=0;

    }

    public Utilisateur(String userName,String password,int bstScore) {
        this.password = password;
        this.userName = userName;
        this.bestScore=bstScore;

    }

    public String getUserName() {
        return userName;
    }

    public int getBestScore()
    {
        return bestScore;
    }

    public void setBestScore(int bestScore)
    {
        this.bestScore = bestScore;
    }
}
