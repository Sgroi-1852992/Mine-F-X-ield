package it.tommy.mine.fxield.application.menu;

import it.tommy.mine.fxield.application.campominato.CampoMinatoApplication;
import javafx.scene.image.ImageView;
import tommy.apllication.menu.children.MenuButton;

public class DifficultyMenu extends Menu implements Playable, Backable{

    private enum Difficulty{

        EASY(5), INTERMEDIATE(10), ADVANCED(15), EXPERT(20), INSANE(25);
        private int bombs;
        Difficulty(int bombs) {
            this.bombs=bombs;
        }

        public int getBombs() {return bombs;}
    }

    private MenuButton easy = new MenuButton("EASY");
    private MenuButton intermediate = new MenuButton("INTERMEDIATE");
    private MenuButton advanced = new MenuButton("ADVANCED");
    private MenuButton expert = new MenuButton("EXPERT");
    private MenuButton insane = new MenuButton("INSANE");
    private MenuButton voidButton = new MenuButton("");
    private MenuButton back = new MenuButton("BACK");

    public DifficultyMenu(ImageView imageView, CampoMinatoApplication app){
        super(imageView, app);
        setUpButtons();
        voidButton.setOpacity(0);
        addItems(easy, intermediate, advanced, expert, insane, voidButton, back);
        addVboxToPane();
    }

    protected void setUpButtons(){
        easy.setOnMouseClicked(m-> startGame(Difficulty.EASY.getBombs()));
        intermediate.setOnMouseClicked(m-> startGame(Difficulty.INTERMEDIATE.getBombs()));
        advanced.setOnMouseClicked(m-> startGame(Difficulty.ADVANCED.getBombs()));
        expert.setOnMouseClicked(m-> startGame(Difficulty.EXPERT.getBombs()));
        insane.setOnMouseClicked(m-> startGame(Difficulty.INSANE.getBombs()));
        back.setOnMouseClicked(m-> back());
    }

    @Override
    public void back() {app.setMainMenuGameScene();}

    @Override
    public void startGame(int bombs) {
        CampoMinatoApplication.setBombs(bombs);
        CampoMinatoApplication.resetValuesToDefault();
        app.initGame();
    }
}
