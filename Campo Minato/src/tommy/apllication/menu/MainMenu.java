package tommy.apllication.menu;

import javafx.scene.image.ImageView;
import tommy.apllication.campominato.CampoMinatoApplication;
import tommy.apllication.menu.children.MenuButton;

public class MainMenu extends Menu {
    private MenuButton play = new MenuButton("PLAY");
    private MenuButton customPlay = new MenuButton("CUSTOM PLAY");
<<<<<<< HEAD
    private MenuButton settings = new MenuButton("SETTINGS");
=======
    private MenuButton settings = new MenuButton("HELP");
>>>>>>> 0091c6b... replaced all the files with the Tom's repo and added the Game Over menu.
    private MenuButton exit = new MenuButton("EXIT");

    public MainMenu(ImageView imageView, CampoMinatoApplication app){
        super(imageView, app);
        addItems(play, customPlay, settings, exit);
<<<<<<< HEAD
        //vBox.setTranslateX(50);


        addVboxToPane();
        play.setOnMouseClicked(mouseEvent -> app.setDifficultyMenuScene());
        exit.setOnMouseClicked(mouseEvent -> System.exit(0));
=======

        addVboxToPane();
        play.setOnMouseClicked(mouseEvent -> app.setDifficultyMenuScene());
        customPlay.setOnMouseClicked(mouseEvent -> app.setCustomMenuGameScene());
        exit.setOnMouseClicked(mouseEvent -> System.exit(0));
        settings.setOpacity(0);
>>>>>>> 0091c6b... replaced all the files with the Tom's repo and added the Game Over menu.
    }


}
