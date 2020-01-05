package Model;

import java.sql.*;


public class Jeu {
    private Session session;


    // une méthode pour sauvegarder l’utilisateur et son nouveau score dans la base de donné
// tout en modifiant son meilleur score si nécessaire
    public boolean sauvegardeScore(Utilisateur user, int score) {
        PreparedStatement psp = null;
        Statement st = null;
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
            st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT  * FROM users WHERE username = '" + user.getUserName() + "' ;");
            rs.next();
            int id = rs.getInt("iduser");
            int best = rs.getInt("BestScore");//user.getBestScore();
            if (best < score) {
                st.executeUpdate("UPDATE users set bestscore = " + score + " where iduser= " + id + ";");
                psp = c.prepareStatement("INSERT INTO scores (iduser,score) VALUES (?,?);");
                psp.setInt(1, id);
                psp.setInt(2, score);
                psp.executeUpdate();
                psp.close();
                //  st.executeUpdate("INSERT INTO scores (iduser,score) VALUES ("+id+","+score+");") ;
            } else {
                st.executeUpdate("INSERT INTO scores (iduser,score) VALUES (" + id + "," + score + ");");
            }
            rs.close();
            st.close();
            c.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return false;
        } catch (ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return false;
        }
    }

    //Une méthode qui affiche tous les scores enregistrés
    // ainsi que l’identifiant du joueur associer à chaque score
    public String parcourirScores(Utilisateur user) {
        PreparedStatement psp = null;
        Statement st = null;
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
            st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT  * FROM users WHERE username = '" + user.getUserName() + "' ;");
            rs.next();
            int idUser = rs.getInt("iduser");
            rs = st.executeQuery("SELECT * FROM scores WHERE iduser = " + idUser + ";");
            String str = user.getUserName() + ":";
            while (rs.next()) {
                str = str + "\n " + rs.getInt("score");
            }
            rs.close();
            st.close();
            c.close();
            return str;
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return null;

        } catch (ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return null;
        }
    }

    //Une méthode qui permet d’effectuer l’authentification d’un utilisateur
    // ayant son nom d’utilisateur et mot de passe en entrer
    public Utilisateur authentification(String userName, String pwd) throws AuthentifException {
        Statement st = null;
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
            st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users;");
            while (rs.next()) {
                String name = rs.getString("username");
                String password = rs.getString("password");
                if (pwd.equals(password) && userName.equals(name)) {
                    int bstScore = rs.getInt("BestScore");
                    rs.close();
                    st.close();
                    c.close();
                    return new Utilisateur(userName, password, bstScore);
                }
            }
            rs.close();
            st.close();
            c.close();
            throw new AuthentifException();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return null;
        }
    }

    //Une méthode qui retourne 1 si le nom d’utilisateur existe,
    // 2 si nom d’utilisateur ne commence pas par une lettre
    // 0 si il est valide
    public int pseudoValide(String user) {
        if (user.equals(""))
            return 2;
        else if (!(((user.charAt(0) >= 65) && (user.charAt(0) <= 90)) || ((user.charAt(0) >= 97) && (user.charAt(0) <= 122))))
            return 2;

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users;");

            while (rs.next()) {
                String name = rs.getString("username");
                if (user.equals(name)) {
                    rs.close();
                    stmt.close();
                    c.close();
                    return 1;
                }
            }
            rs.close();
            stmt.close();
            c.close();
            return 0;
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return 2;
        } catch (ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return 2;
        }
    }

    //Une méthode qui retourne une référence vers le nouveau utilisateur inscrit
    // si l’inscription est possible, null sinon
    public Utilisateur inscription(String userName, String pwd) throws InscripException {
        int pseudoValide = pseudoValide(userName);
        if (pseudoValide == 0) {
            Connection c = null;
            PreparedStatement stmt = null;
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
                String sql = "INSERT INTO users (username,password,bestscore) " +
                        "VALUES (?, ?, 0 );";
                stmt = c.prepareStatement(sql);
                stmt.setString(1, userName);
                stmt.setString(2, pwd);

                stmt.executeUpdate();

                stmt.close();
                c.close();
                return new Utilisateur(userName, pwd);

            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
                return null;
            } catch (ClassNotFoundException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
                return null;
            }
        } else {
            if (pseudoValide == 1)
                throw new InscripException(true);
            else throw new InscripException(false);
        }
    }

    public Session getSession() {
        return this.session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
