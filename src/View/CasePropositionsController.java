package View;

import Model.PropositionsCase;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOException;

public class CasePropositionsController extends Pane {

    @FXML
    public Button button;

    @FXML
    private Label nbChancesRest;

    private final PropositionsCase laCase;

    public CasePropositionsController(PropositionsCase laCase) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "CasePropositions.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        Image image = new Image(Main.class.getResource("TPPOODesign/CasePropositions.png").toExternalForm(),
                55, 64, true, true);
        BackgroundImage myBI = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        button.setBackground(new Background(myBI));
        this.laCase = laCase;

        nbChancesRest.setText(0+"");

        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
    }

    public PropositionsCase getLaCase() {
        return laCase;
    }

    private class MyEventHandler implements EventHandler<Event>
    {
        @Override
        public void handle(Event evt)
        {
            if(!((laCase.getCaractereDeLaCase()+"").equals(button.getText()))){
            LesCasesARemplirController.caseSelect=(Pane)((Control) evt.getSource()).getParent();
            ((LePenduController)(Main.PrincipalStage.getScene().getRoot())).clavier.getChildren().add(new Clavier4(laCase.getLesPropositions()));
            }
        }
    }
}
