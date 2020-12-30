package tommy.apllication.menu;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tommy.apllication.campominato.CampoMinatoApplication;

public abstract class Menu extends Pane {
    protected VBox vBox = new VBox(10);
    protected CampoMinatoApplication app;
<<<<<<< HEAD

    public Menu(ImageView imageView, CampoMinatoApplication app) {
        getChildren().add(imageView);
        vBox.setTranslateY(100);
        this.app = app;
    }

=======
    protected double translateY;

    public Menu(ImageView imageView, CampoMinatoApplication app) {
        this.translateY = 100;
        if(imageView!=null) getChildren().add(imageView);
        vBox.setTranslateY(translateY);
        this.app = app;
    }

    public Menu(CampoMinatoApplication app){this(null, app);}

>>>>>>> 0091c6b... replaced all the files with the Tom's repo and added the Game Over menu.
//    public Menu(ImageView imageView){
//        this(imageView, null);
//    }

    public void addItems(Node... nodes){
        vBox.getChildren().addAll(nodes);
        //getChildren().add(vBox);
    }

    protected void addVboxToPane(){getChildren().add(vBox);}
<<<<<<< HEAD
=======


>>>>>>> 0091c6b... replaced all the files with the Tom's repo and added the Game Over menu.
}
