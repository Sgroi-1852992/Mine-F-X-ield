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
import tommy.apllication.gridandbox.Casella;
import tommy.apllication.gridandbox.MatriceCampoMinato;

public class CampoMinatoApplication extends Application {

    private MatriceCampoMinato matriceCampoMinato;
    private int x= 10;
    private int y = 10;

    @Override
    public void start(Stage primaryStage) throws Exception{
        matriceCampoMinato = new MatriceCampoMinato(x, y,0 );

        Group grid = new Group();
        HBox hbox = new HBox();
        for(int x = 0; x<this.x; x++)
        {
            hbox.getChildren().add(new VBox(generateNodes(x)));
        }
        grid.getChildren().add(hbox);
        primaryStage.setScene(new Scene(grid));
        primaryStage.show();

        System.out.println(matriceCampoMinato.toString());
    }

    static int a = 0;
    public Node[] generateNodes(int x){
        Node[] list = new Node[this.y];
        for(int y = 0; y<this.y;y++)
        {
            Casella casella = new Casella(a++);
            list[y] = casella;
            matriceCampoMinato.setCasella(y,x,casella);
        }
        return list;
    }
}
