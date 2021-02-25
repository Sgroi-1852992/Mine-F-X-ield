package it.tommy.mine.fxield.application.menu;

import it.tommy.mine.fxield.application.campominato.CampoMinatoApplication;
import javafx.scene.image.ImageView;
import it.tommy.mine.fxield.application.menu.children.MenuButton;


public class MainMenu extends Menu {
    private MenuButton play = new MenuButton("PLAY");
    private MenuButton customPlay = new MenuButton("CUSTOM PLAY");
    private MenuButton settings = new MenuButton("HELP");
    private MenuButton exit = new MenuButton("EXIT");

    public MainMenu(ImageView imageView, CampoMinatoApplication app){
        super(imageView, app);
        addItems(play, customPlay, settings, exit);

        addVboxToPane();
        play.setOnMouseClicked(mouseEvent -> app.setDifficultyMenuScene());
        customPlay.setOnMouseClicked(mouseEvent -> app.setCustomMenuGameScene());
        exit.setOnMouseClicked(mouseEvent -> System.exit(0));
        settings.setOpacity(0);
    }


}
