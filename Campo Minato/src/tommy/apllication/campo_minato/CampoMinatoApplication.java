package tommy.apllication.campo_minato;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CampoMinatoApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group grid = new Group();
        HBox hbox = new HBox();
        for(int x = 0; x<10; x++)
            hbox.getChildren().add(new VBox(generateNodes()));
        grid.getChildren().add(hbox);
        primaryStage.setScene(new Scene(grid));
        primaryStage.show();
    }

    public Node[] generateNodes(){
        Node[] list = new Node[10];
        for(int x = 0; x<10;x++)
        {
            Button button = new Button("0");
            button.setLayoutX(10);
            button.setLayoutY(10);
            list[x] = button;
        }
        return list;
    }
}
