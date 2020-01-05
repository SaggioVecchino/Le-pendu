package View;

import Model.AuthentifException;
import Model.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    Label errorConnexion;

    public void connexionClick() throws IOException {

        try {
            Main.jeu.setSession(new Session(Main.jeu.authentification(usernameField.getText(), passwordField.getText())));
            Parent root = new LePenduController();
            Main.PrincipalStage.setScene(new Scene(root, 800, 600));
            Main.PrincipalStage.setTitle("Le Pendu");
        } catch (AuthentifException e) {
            errorConnexion.setText("Erreur de connexion, votre mot de passe ou nom d'utilisateur est incorrect !");
        }
    }

    public void inscriptionClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Inscription.fxml"));
        Main.PrincipalStage.setScene(new Scene(root, 500, 500));
        Main.PrincipalStage.getScene().getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
    }
}