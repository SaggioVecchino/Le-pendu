package View;

import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;

import java.io.IOException;


public class LesCasesARemplirController extends Pane {

    @FXML
    public HBox hbox;

    public static Pane caseSelect;

    private LesCasesARemplirController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "LesCasesARemplir.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
}

    public LesCasesARemplirController(Mot mot) {
        this();
        for (Case laCase: mot.getCases()
             ) {
            AjouterAuHBox(laCase);
        }
    }

    private void AjouterAuHBox(Case laCase) {
    if(laCase instanceof MultiChanceCase)
        hbox.getChildren().add(new CaseMultiChancesController((MultiChanceCase)laCase));
    else if(laCase instanceof PropositionsCase)
        hbox.getChildren().add(new CasePropositionsController((PropositionsCase)laCase));
    else
        hbox.getChildren().add(new CaseZeroChanceController((ZeroChanceCase)laCase));
    }



}
