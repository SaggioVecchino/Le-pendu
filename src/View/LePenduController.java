package View;

import Model.IIndication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;

public class LePenduController extends Pane {


    @FXML
    private ImageView dessin;

    @FXML
    public Pane lesCasesARemplir;

    @FXML
    public Pane clavier;

    @FXML
    private Label typeIndication;

    @FXML
    private Label indication;

    @FXML
    private Label ScoreCourant;

    @FXML
    private Label MeilleurScore;

    @FXML
    private Pane fond;

    @FXML
    private Label NumMot;

    @FXML
    private Button Continuer;

    public Button getContinuer() {
        return Continuer;
    }

    public LePenduController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "LePendu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
       // fond.setBackground(new Image(Main.class.getResource("TPPOODesign/TPPOO.png")));
        Image image = new Image(Main.class.getResource(Main.jeu.getSession().getDessin().getImage()).toExternalForm(),
                250, 200, true, true);
        dessin.setImage(image);
        lesCasesARemplir.getChildren().add(new LesCasesARemplirController(Main.jeu.getSession().motCourant()));
        typeIndication.setText(IIndication.indicationString(Main.jeu.getSession().motCourant().getTypeIndication()));
        indication.setText(Main.jeu.getSession().motCourant().getIndication());
        ScoreCourant.setText(Main.jeu.getSession().getScoreCourant()+"");
        MeilleurScore.setText(Main.jeu.getSession().getUserConnecte().getBestScore()+"");
        NumMot.setText((Main.jeu.getSession().getIndiceMotCourant()+1)+"");
        getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

    }

    public void Continuer()
    {
        if (Main.jeu.getSession().motSuivant())
        {
            Parent root = new LePenduController();
            Main.PrincipalStage.setScene(new Scene(root, 800, 600));
        }
        else
            {
            try
            {
                Parent root = FXMLLoader.load(getClass().getResource("FinSession.fxml"));
                Main.PrincipalStage.setScene(new Scene(root, 800, 600));
                Main.PrincipalStage.getScene().getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }


    }


}
