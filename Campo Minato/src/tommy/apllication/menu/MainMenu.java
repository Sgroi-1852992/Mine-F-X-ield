package tommy.apllication.menu;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tommy.apllication.menu.children.MenuButton;

public class MainMenu extends Pane {
    private MenuButton play = new MenuButton("PLAY");
    private MenuButton customPlay = new MenuButton("CUSTOM PLAY");
    private MenuButton settings = new MenuButton("SETTINGS");
    private MenuButton exit = new MenuButton("EXIT");

    public MainMenu(ImageView imageView){
        super();
        getChildren().add(imageView);

        VBox vBox = new VBox(10);
        vBox.getChildren().add(play);
        vBox.getChildren().add(customPlay);
        vBox.getChildren().add(settings);
        vBox.getChildren().add(exit);
        vBox.setTranslateX(50);
        vBox.setTranslateY(100);

        getChildren().add(vBox);

        exit.setOnMouseClicked(mouseEvent -> System.exit(0));
    }


}
