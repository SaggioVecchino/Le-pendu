package View;

import Model.MultiChanceCase;
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

public class CaseMultiChancesController extends Pane {

    @FXML
    public Button button;

    @FXML
    private Label nbChancesRest;

    private final MultiChanceCase laCase;

    public CaseMultiChancesController(MultiChanceCase laCase) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "CaseMultiChances.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        Image image = new Image(Main.class.getResource("TPPOODesign/CaseMultiChances.png").toExternalForm(),
                55, 64, true, true);
        BackgroundImage myBI = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        button.setBackground(new Background(myBI));
        this.laCase = laCase;

        nbChancesRest.setText(laCase.getNombreDeTentativesRestantes()+"");

        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());


    }

    public MultiChanceCase getLaCase(){
        return laCase;
    }

    private class MyEventHandler implements EventHandler<Event>
    {
        @Override
        public void handle(Event evt)
        {
            if(!((laCase.getCaractereDeLaCase()+"").equals(button.getText()))){
            LesCasesARemplirController.caseSelect=(Pane)((Control) evt.getSource()).getParent();
            ((LePenduController)(Main.PrincipalStage.getScene().getRoot())).clavier.getChildren().add(new Clavier26());
            }
        }
    }

}
