package tommy.apllication.menu;

import javafx.event.EventType;
import javafx.scene.image.ImageView;
import tommy.apllication.campominato.CampoMinatoApplication;
import tommy.apllication.menu.children.MenuButton;

import java.awt.event.MouseEvent;

public class DifficultyMenu extends Menu {

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
        easy.setOnMouseClicked(m-> {
            CampoMinatoApplication.setBombs(Difficulty.EASY.getBombs());
            app.initGame();
        });
        intermediate.setOnMouseClicked(m-> {
            CampoMinatoApplication.setBombs(Difficulty.INTERMEDIATE.getBombs());
            app.initGame();
        });
        advanced.setOnMouseClicked(m-> {
            CampoMinatoApplication.setBombs(Difficulty.ADVANCED.getBombs());
            app.initGame();
        });
        expert.setOnMouseClicked(m-> {
            CampoMinatoApplication.setBombs(Difficulty.EXPERT.getBombs());
            app.initGame();
        });
        insane.setOnMouseClicked(m-> {
            CampoMinatoApplication.setBombs(Difficulty.INSANE.getBombs());
            app.initGame();
        });
        back.setOnMouseClicked(m-> app.setMainMenuGameScene());
    }

}
