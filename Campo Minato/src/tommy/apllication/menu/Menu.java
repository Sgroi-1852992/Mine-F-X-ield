package tommy.apllication.menu;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tommy.apllication.campominato.CampoMinatoApplication;

public abstract class Menu extends Pane {
    protected VBox vBox = new VBox(10);
    protected CampoMinatoApplication app;
    protected double translateY;

    public Menu(ImageView imageView, CampoMinatoApplication app) {
        this.translateY = 100;
        getChildren().add(imageView);
        vBox.setTranslateY(translateY);
        this.app = app;
    }

//    public Menu(ImageView imageView){
//        this(imageView, null);
//    }

    public void addItems(Node... nodes){
        vBox.getChildren().addAll(nodes);
        //getChildren().add(vBox);
    }

    protected void addVboxToPane(){getChildren().add(vBox);}


}
