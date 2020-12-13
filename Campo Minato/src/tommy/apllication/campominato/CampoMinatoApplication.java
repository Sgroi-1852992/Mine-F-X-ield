package tommy.apllication.campominato;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tommy.apllication.gridandbox.MatriceCampoMinato;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class CampoMinatoApplication extends Application {

    private MatriceCampoMinato matriceCampoMinato;
    private int colonne= 5;
    private int righe = 5;
    private int bombs = 5;

    @Override
    public void start(Stage primaryStage) throws Exception{
        matriceCampoMinato = new MatriceCampoMinato(colonne, righe, bombs);

        Group grid = new Group();
        HBox hbox = new HBox();
        for(int x = 0; x<this.colonne; x++)
            hbox.getChildren().add(new VBox(generateNodes(x)));

        //FOR TESTING PURPOSE {
        Button apritutto = new Button("ApriTutto");
        apritutto.addEventFilter(MouseEvent.MOUSE_CLICKED, (mouseEvent)->{
            for(MatriceCampoMinato.Casella[] m: matriceCampoMinato.getMatriceCampoMinato())
                for(MatriceCampoMinato.Casella c: m)
                    c.setText(String.valueOf(c.getStatus()));
        });
        hbox.getChildren().add(0, apritutto);
        //FOR TESTING PURPOSE }

        grid.getChildren().add(hbox);
        primaryStage.setScene(new Scene(grid));
        primaryStage.show();

        System.out.println(matriceCampoMinato.toString());
    }

    public Node[] generateNodes(int x){
        Node[] list = new Node[this.righe];
        for(int y = 0; y<this.righe;y++)
        {
            //Casella casella = new Casella(0);
            //System.out.println("X: "+x+ "; Y: "+y);
            list[y] =matriceCampoMinato.setCasella(x,y, 0);
            //((MatriceCampoMinato.Casella)list[y]).setText("X:"+String.valueOf(x)+"-Y:"+String.valueOf(y));
        }
        return list;
    }
}
