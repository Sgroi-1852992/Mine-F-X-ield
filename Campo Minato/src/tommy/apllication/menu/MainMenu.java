package tommy.apllication.menu;

import javafx.scene.image.ImageView;
import tommy.apllication.campominato.CampoMinatoApplication;
import tommy.apllication.menu.children.MenuButton;

public class MainMenu extends Menu {
    private MenuButton play = new MenuButton("PLAY");
    private MenuButton customPlay = new MenuButton("CUSTOM PLAY");
    private MenuButton settings = new MenuButton("SETTINGS");
    private MenuButton exit = new MenuButton("EXIT");

    public MainMenu(ImageView imageView, CampoMinatoApplication app){
        super(imageView, app);
        addItems(play, customPlay, settings, exit);
        //vBox.setTranslateX(50);


        addVboxToPane();
        play.setOnMouseClicked(mouseEvent -> app.setDifficultyMenuScene());
        exit.setOnMouseClicked(mouseEvent -> System.exit(0));
    }


}
