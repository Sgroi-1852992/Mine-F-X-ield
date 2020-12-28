package tommy.apllication.menu;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import tommy.apllication.campominato.CampoMinatoApplication;

public class CustomGameMenu {
    private Stage primaryStage;

    public CustomGameMenu(Stage primaryStage){
        this.primaryStage=primaryStage;
    }

    public Scene buildCustomGameMenuScene(CampoMinatoApplication app){

        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
        Label width = buildLabel("Colonne:", font);
        Label height = buildLabel("Righe:", font);
        Label bombs = buildLabel("Bombe:", font);

        int maxLength = Math.max(Math.max(width.getText().length(), height.getText().length()), bombs.getText().length());

        Spinner<Integer> spinnerWidth = buildSpinner(2, 100, 10, 75, 25);
        Spinner<Integer> spinnerHeight = buildSpinner(2, 100, 10, 75, 25);
        Spinner<Integer> spinnerBombs = buildSpinner(2, 100, 10, 75, 25);

        setValueAtMaxPossible(spinnerHeight, spinnerHeight, spinnerWidth, spinnerBombs);
        setValueAtMaxPossible(spinnerWidth, spinnerHeight, spinnerWidth, spinnerBombs);
        setValueAtMaxPossible(spinnerBombs, spinnerHeight, spinnerWidth, spinnerBombs);

        Button play = new Button("Play");

        play.setScaleX(1.5);
        play.setScaleY(1.5);
        play.setTranslateX(-20);
        play.setTranslateY(20);
        play.setOnAction(e->{
            app.setBombs(spinnerBombs.getValue());
            app.setColonne(spinnerWidth.getValue());
            app.setRighe(spinnerHeight.getValue());

            app.initGame(true);
        });

        Button back = new Button("Back");
        back.setScaleX(1.5);
        back.setScaleY(1.5);
        back.setTranslateX(20);
        back.setTranslateY(20);
        back.setOnAction(e->primaryStage.setScene(app.getMainMenu().getScene()));


        HBox hWidth = new HBox(width, spinnerWidth);
        hWidth.setSpacing(5);
        HBox hHeight = new HBox(height, spinnerHeight);
        hHeight.setSpacing(20);
        HBox hBombs = new HBox(bombs, spinnerBombs);
        hBombs.setSpacing(12);
        VBox vbox = new VBox(5, hWidth, hHeight, hBombs, new HBox(5, play, back));

        vbox.getChildren().forEach(child->{if(child instanceof HBox) ((HBox) child).setAlignment(Pos.CENTER);});
        vbox.setAlignment(Pos.CENTER);
        StackPane s = new StackPane(vbox);
        s.setMinSize(app.getMainMenu().getWidth(),app.getMainMenu().getHeight());
        Scene scene = new Scene(s);
        return scene;
    }

    private Spinner<Integer> buildSpinner(int i, int i1, int i2, int width, int height) {
        Spinner<Integer> spinner = new Spinner<>(i, i1, i2);
        spinner.setPrefSize(width, height);
        spinner.setEditable(true);
        return spinner;
    }

    private void setValueAtMaxPossible(Spinner<Integer> spinner, Spinner<Integer> spinnerHeight, Spinner<Integer> spinnerWidth, Spinner<Integer> spinnerBombs){
        spinner.valueProperty().addListener((obs, oldValue, newValue)-> {
            if(spinnerBombs.getValue()>spinnerWidth.getValue()*spinnerHeight.getValue()-1)
                spinnerBombs.getValueFactory().setValue(spinnerWidth.getValue()*spinnerHeight.getValue()-1);
        });
    }

    private Label buildLabel(String text, Font font){
        Label label = new Label(text);
        label.setFont(font);
        return label;
    }

    //public void show(){customGameMenu.show();}

}
