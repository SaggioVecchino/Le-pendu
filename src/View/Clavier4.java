package View;

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import javax.swing.*;

public class Clavier4 extends GridPane
{
    @FXML
    Button A1;
    @FXML
    Button B1;
    @FXML
    Button C1;
    @FXML
    Button D1;
   @FXML
    Label lA;
    @FXML
    Label lB;
    @FXML
    Label lC;
    @FXML
    Label lD;

    public Clavier4( char [] lesprops)
    {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "Clavier4.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
            lA.setText(lesprops[0]+"");
            lB.setText(lesprops[1]+"");
            lC.setText(lesprops[2]+"");
            lD.setText(lesprops[3]+"");
            A1.addEventHandler(MouseEvent.MOUSE_CLICKED, new Clavier4.MyEventHandler());
            B1.addEventHandler(MouseEvent.MOUSE_CLICKED, new Clavier4.MyEventHandler());
            C1.addEventHandler(MouseEvent.MOUSE_CLICKED, new Clavier4.MyEventHandler());
            D1.addEventHandler(MouseEvent.MOUSE_CLICKED, new Clavier4.MyEventHandler());
        }
        catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
    }

    private class MyEventHandler implements EventHandler<Event>
    {
        @Override
        public void handle(Event evt)
        {
            ObservableList<Node> j= ((Control) evt.getSource()).getChildrenUnmodifiable();
            ObservableList<Node> j2=  ((StackPane)j.get(0)).getChildren();
            Model.Case caseSelected=null;

            if ( LesCasesARemplirController.caseSelect instanceof CaseMultiChancesController)
            {
                caseSelected=((CaseMultiChancesController)LesCasesARemplirController.caseSelect).getLaCase();
            }
            if ( LesCasesARemplirController.caseSelect instanceof CasePropositionsController)
            {
                caseSelected=((CasePropositionsController)LesCasesARemplirController.caseSelect).getLaCase();
            }
            if ( LesCasesARemplirController.caseSelect instanceof CaseZeroChanceController)
            {
                caseSelected=((CaseZeroChanceController)LesCasesARemplirController.caseSelect).getLaCase();
            }
            char caract=((Label)j2.get(1)).getText().charAt(0);
            caseSelected.entrerCaractere(caract);
            if((caseSelected.getCaractereDeLaCase()+"").equals(caract+""))
                ((Button) (LesCasesARemplirController.caseSelect.getChildren().get(0))).setText(caract+"");
            if (Main.jeu.getSession().motCourant().echecSurLeMot() || Main.jeu.getSession().motCourant().motJuste())
            {
                View.Clavier26.Corriger();
            }
            ((Pane)((Pane)Main.PrincipalStage.getScene().getRoot()).getChildren().get(5)).getChildren().clear();
        }
    }
}
