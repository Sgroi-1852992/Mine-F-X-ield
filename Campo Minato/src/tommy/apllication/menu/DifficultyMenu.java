package tommy.apllication.menu;

import javafx.scene.image.ImageView;
import tommy.apllication.campominato.CampoMinatoApplication;
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
        easy.setOnMouseClicked(m-> Playable.startGame(app, Difficulty.EASY.getBombs()));
        intermediate.setOnMouseClicked(m-> Playable.startGame(app, Difficulty.INTERMEDIATE.getBombs()));
        advanced.setOnMouseClicked(m-> Playable.startGame(app, Difficulty.ADVANCED.getBombs()));
        expert.setOnMouseClicked(m-> Playable.startGame(app, Difficulty.EXPERT.getBombs()));
        insane.setOnMouseClicked(m-> Playable.startGame(app, Difficulty.INSANE.getBombs()));
        back.setOnMouseClicked(m-> back());
    }

    @Override
    public void back() {app.setMainMenuGameScene();}
}
