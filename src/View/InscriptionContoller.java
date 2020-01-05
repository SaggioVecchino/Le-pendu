package View;

import Model.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;

public class InscriptionContoller
{
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    Label errorConnexion;

    public void connexionClick() throws IOException {

            try {
                Main.jeu.setSession(new Session(Main.jeu.inscription(usernameField.getText(), passwordField.getText())));

            Parent root = new LePenduController();
            Main.PrincipalStage.setScene(new Scene(root, 800, 600));
            Main.PrincipalStage.setTitle("Le Pendu");
        }
        catch (Model.InscripException e)
        {
            if (e.isExisteDeja())
            errorConnexion.setText("Le nom d'utilisatuer existe déja");
            else errorConnexion.setText("Le nom d'utilisatuer doit commencer par une lettre");
        }
    }

    public void returnClick() throws IOException
    {
        Parent root=FXMLLoader.load(getClass().getResource("Login.fxml"));
        Main.PrincipalStage.setScene(new Scene(root, 500, 500)) ;
        Main.PrincipalStage.getScene().getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

    }

}






