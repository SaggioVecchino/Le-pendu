package View;

import Model.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FinSessionController extends Pane implements Initializable
{
    @FXML
    private Label LesScores;
    @FXML
    private Label ScoreCourantFin;
    @FXML
    private Label MeilleurScoreFin;
    @FXML
    private Button saveButton;
    @FXML
    private ScrollPane scrol;
    @FXML
    private ImageView ImageFin;
    public void Replay()
    {
        Main.jeu.setSession(new Session(Main.jeu.getSession().getUserConnecte()));
        Parent root = new LePenduController();
        Main.PrincipalStage.setScene(new Scene(root, 800, 600));
    }

    public void SaveScore()
    {
        Main.jeu.sauvegardeScore(Main.jeu.getSession().getUserConnecte(),Main.jeu.getSession().getScoreCourant());
        saveButton.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        MeilleurScoreFin.setText(Main.jeu.getSession().getUserConnecte().getBestScore()+"");
        ScoreCourantFin.setText(Main.jeu.getSession().getScoreCourant()+"");
        if ( Main.jeu.getSession().getDessin().getEtat()==6) {
            Image image = new Image(Main.class.getResource("TPPOODesign/6.png").toExternalForm(),
                    465, 416, true, true);
            ImageFin.setImage(image);
        }
        else
        {
            Image image = new Image(Main.class.getResource("TPPOODesign/congrat1.png").toExternalForm(),
                    465, 416, true, true);
            ImageFin.setImage(image);
        }
    }

    public void afficheScore()
    {
        scrol.setVisible(true);
        MeilleurScoreFin.setVisible(true);
        LesScores.setText(Main.jeu.parcourirScores(Main.jeu.getSession().getUserConnecte()));
    }

    public void Logout() throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("Login.fxml"));
        Main.PrincipalStage.setTitle("Login/Inscription");
        Main.PrincipalStage.setScene(new Scene(root, 500, 500));
        Main.PrincipalStage.getScene().getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

    }
}