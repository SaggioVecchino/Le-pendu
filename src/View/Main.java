package View;

import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage PrincipalStage;
    public static Jeu jeu;

    @Override
    public void start(Stage primaryStage) throws Exception{
        PrincipalStage=primaryStage;
        Parent root = (new FXMLLoader(getClass().getResource(
                "Login.fxml"))).load();
        primaryStage.setTitle("Login/Inscription");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.getScene().getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.show();
    }

    public static void main(String[] args) {
        jeu=new Jeu();
        launch(args);

    }
}
