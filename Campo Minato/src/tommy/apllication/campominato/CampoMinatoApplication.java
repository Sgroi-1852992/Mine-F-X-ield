package tommy.apllication.campominato;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tommy.apllication.gridandbox.MatriceCampoMinato;

public class CampoMinatoApplication extends Application {

    private MatriceCampoMinato matriceCampoMinato;
    private int colonne= 10;
    private int righe = 12;
    private int bombs = 25;

    @Override
    public void start(Stage primaryStage) throws Exception{
        matriceCampoMinato = new MatriceCampoMinato(righe, colonne, bombs);

        Group grid = new Group();
        HBox hbox = new HBox();
        for(int x = 0; x<this.colonne; x++)
            hbox.getChildren().add(new VBox(generateNodes(x)));

//        //FOR TESTING PURPOSE {
//        Button apritutto = new Button("ApriTutto");
//        apritutto.addEventFilter(MouseEvent.MOUSE_CLICKED, (mouseEvent)->{
//            for(MatriceCampoMinato.Casella[] m: matriceCampoMinato.getMatriceCampoMinato())
//                for(MatriceCampoMinato.Casella c: m)
//                    c.setText(String.valueOf(c.getStatus()));
//        });
//        hbox.getChildren().add(0, apritutto);
//        //FOR TESTING PURPOSE }

        grid.getChildren().add(hbox);
        primaryStage.setScene(new Scene(grid));
        primaryStage.show();
        primaryStage.setTitle("Campo Minato");
        primaryStage.setResizable(false);
    }

    public Node[] generateNodes(int x){
        Node[] list = new Node[this.righe];
        for(int y = 0; y<this.righe;y++)
            list[y] =matriceCampoMinato.setCasella(x,y, 0);
        return list;
    }
}
