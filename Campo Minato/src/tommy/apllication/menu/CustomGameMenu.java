package tommy.apllication.menu;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tommy.apllication.campominato.CampoMinatoApplication;
import tommy.apllication.menu.children.MenuButton;

public class CustomGameMenu extends Menu implements Playable, Backable{

    private CustomMenuButton rows = new CustomMenuButton("RIGHE");
    private CustomMenuButton column = new CustomMenuButton("COLONNE");
    private CustomMenuButton bombs = new CustomMenuButton("BOMBE");
    private CustomMenuButton play = new CustomMenuButton("PLAY");
    private CustomMenuButton none = new CustomMenuButton("");
    private CustomMenuButton back = new CustomMenuButton("BACK");
    Spinner<Integer> spinnerWidth;
    Spinner<Integer> spinnerHeight;
    Spinner<Integer> spinnerBombs;

    public CustomGameMenu(ImageView imageView, CampoMinatoApplication app) {
        super(imageView, app);
        setUpButtons();
        addItems(rows, column, bombs, play, none, back);
        addVboxToPane();
    }

    private void setUpButtons(){
        play.setOnMouseClicked(m ->startGame(spinnerBombs.getValue()));

        back.setOnMouseClicked(m -> back());

        none.setOpacity(0);

        for(CustomMenuButton c: new CustomMenuButton[]{rows, column, bombs, none})
        {
            c.setOnMouseClicked(m-> {});
            c.setOnMousePressed(m-> {});
        }
    }

    @Override
    protected void addVboxToPane() {
        HBox hBox = new HBox(20);

        spinnerWidth = buildSpinner(2, 25, 10, 75, (int) rows.getHeightMenuButton());
        spinnerHeight = buildSpinner(2, 25, 10, 75, (int) rows.getHeightMenuButton());
        spinnerBombs = buildSpinner(1, 624, 10, 75, (int) rows.getHeightMenuButton());

        setValueAtMaxPossible(spinnerHeight, spinnerHeight, spinnerWidth, spinnerBombs);
        setValueAtMaxPossible(spinnerWidth, spinnerHeight, spinnerWidth, spinnerBombs);
        setValueAtMaxPossible(spinnerBombs, spinnerHeight, spinnerWidth, spinnerBombs);

        VBox spinners = new VBox(10, spinnerWidth, spinnerHeight, spinnerBombs);
        spinners.setTranslateY(translateY);
        hBox.getChildren().addAll(this.vBox, spinners);
        getChildren().add(hBox);
    }

    private Spinner<Integer> buildSpinner(int i, int i1, int i2, int width, int height) {
        Spinner<Integer> spinner = new Spinner<>(i, i1, i2);
        spinner.setPrefSize(width, height);
        return spinner;
     }

     private void setValueAtMaxPossible(Spinner<Integer> spinner, Spinner<Integer> spinnerHeight, Spinner<Integer> spinnerWidth, Spinner<Integer> spinnerBombs){
        spinner.valueProperty().addListener((obs, oldValue, newValue)-> {
            if(spinnerBombs.getValue()>spinnerWidth.getValue()*spinnerHeight.getValue()-1)
                spinnerBombs.getValueFactory().setValue(spinnerWidth.getValue()*spinnerHeight.getValue()-1);
        });
    }

    @Override
    public void back() {app.setMainMenuGameScene();}

    @Override
    public void startGame(int bombs){
        CampoMinatoApplication.setRighe(spinnerHeight.getValue());
        CampoMinatoApplication.setColonne(spinnerWidth.getValue());
        CampoMinatoApplication.setBombs(spinnerBombs.getValue());
        app.initGame();
    }

    private class CustomMenuButton extends MenuButton{
        public CustomMenuButton(String text){
            super(text, 150, 30);
        }
    }

}
