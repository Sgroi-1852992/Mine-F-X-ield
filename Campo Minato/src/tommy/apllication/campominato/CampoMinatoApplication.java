package tommy.apllication.campominato;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tommy.apllication.gridandbox.MatriceCampoMinato;

public class CampoMinatoApplication extends Application {

    private MatriceCampoMinato matriceCampoMinato;
    private int x= 5;
    private int y = 5;
    private int bombs = 5;

    @Override
    public void start(Stage primaryStage) throws Exception{
        matriceCampoMinato = new MatriceCampoMinato(x, y, bombs);

        Group grid = new Group();
        HBox hbox = new HBox();
        for(int x = 0; x<this.x; x++) hbox.getChildren().add(new VBox(generateNodes(x)));

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
        Node[] list = new Node[this.y];
        for(int y = 0; y<this.y;y++)
        {
            //Casella casella = new Casella(0);
            list[y] =matriceCampoMinato.setCasella(y,x);
        }
        return list;
    }
}
