package View;

import java.io.IOException;
import java.util.Iterator;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Clavier26 extends GridPane {
    @FXML
    Button A;
    @FXML
    Button B;
    @FXML
    Button C;
    @FXML
    Button D;
    @FXML
    Button E;
    @FXML
    Button F;
    @FXML
    Button G;
    @FXML
    Button H;
    @FXML
    Button I;
    @FXML
    Button J;
    @FXML
    Button K;
    @FXML
    Button L;
    @FXML
    Button M;
    @FXML
    Button N;
    @FXML
    Button O;
    @FXML
    Button P;
    @FXML
    Button Q;
    @FXML
    Button R;
    @FXML
    Button S;
    @FXML
    Button T;
    @FXML
    Button U;
    @FXML
    Button V;
    @FXML
    Button W;
    @FXML
    Button X;
    @FXML
    Button Y;
    @FXML
    Button Z;


    public Clavier26() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "Clavier26.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
            A.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            B.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            C.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            D.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            E.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            F.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            G.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            H.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            I.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            J.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            K.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            L.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            M.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            N.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            O.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            P.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            Q.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            R.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            S.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            T.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            U.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            V.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            W.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            X.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            Y.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            Z.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private class MyEventHandler implements EventHandler<Event> {
        @Override
        public void handle(Event evt) {
            ObservableList<Node> j = ((Control) evt.getSource()).getChildrenUnmodifiable();
            ObservableList<Node> j2 = ((StackPane) j.get(0)).getChildren();
            Model.Case caseSelected = null;
            if (LesCasesARemplirController.caseSelect instanceof CaseMultiChancesController) {
                caseSelected = ((CaseMultiChancesController) LesCasesARemplirController.caseSelect).getLaCase();
            }
            if (LesCasesARemplirController.caseSelect instanceof CasePropositionsController) {
                caseSelected = ((CasePropositionsController) LesCasesARemplirController.caseSelect).getLaCase();
            }
            if (LesCasesARemplirController.caseSelect instanceof CaseZeroChanceController) {
                caseSelected = ((CaseZeroChanceController) LesCasesARemplirController.caseSelect).getLaCase();
            }
            char caract = ((Label) j2.get(1)).getText().charAt(0);
            caseSelected.entrerCaractere(caract);
            ((Label) (LesCasesARemplirController.caseSelect.getChildren().get(1))).setText(caseSelected.getNombreDeTentativesRestantes() + "");
            if ((caseSelected.getCaractereDeLaCase() + "").equals(caract + ""))
                ((Button) (LesCasesARemplirController.caseSelect.getChildren().get(0))).setText(caract + "");
            if (Main.jeu.getSession().motCourant().echecSurLeMot() || Main.jeu.getSession().motCourant().motJuste())
                Corriger();
            ((Pane) ((Pane) Main.PrincipalStage.getScene().getRoot()).getChildren().get(5)).getChildren().clear();
        }
    }

    public static void Corriger() {
        ((Pane) Main.PrincipalStage.getScene().getRoot()).getChildren().get(13).setVisible(true);
        Node ob = ((Pane) Main.PrincipalStage.getScene().getRoot()).getChildren().get(4);
        Node ob1 = ((Pane) ((Pane) ob).getChildren().get(0)).getChildren().get(2);
        ObservableList<Node> obs = ((HBox) ob1).getChildren();
        Iterator<Node> iter = obs.iterator();
        while (iter.hasNext()) {
            Node node = iter.next();
            if (node instanceof CaseMultiChancesController) {
                ((CaseMultiChancesController) node).button.setText(((CaseMultiChancesController) node).getLaCase().getCaractereDeLaCase() + "");
            }
            if (node instanceof CasePropositionsController) {
                ((CasePropositionsController) node).button.setText(((CasePropositionsController) node).getLaCase().getCaractereDeLaCase() + "");
            }
            if (node instanceof CaseZeroChanceController) {
                ((CaseZeroChanceController) node).button.setText(((CaseZeroChanceController) node).getLaCase().getCaractereDeLaCase() + "");
            }

        }
    }
}

