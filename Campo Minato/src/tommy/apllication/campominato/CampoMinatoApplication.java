package tommy.apllication.campominato;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tommy.apllication.gridandbox.MatriceCampoMinato;

public class CampoMinatoApplication extends Application {

    private MatriceCampoMinato matriceCampoMinato;
    private int colonne= 10;
    private int righe = 12;

    private int bombs = 5;
    private StackPane mainMenu;
	private Stage primaryStage;
	private Slider difficulty;


    @Override
    public void start(Stage primaryStage) throws Exception{
    	initMainMenu();
    	this.primaryStage = primaryStage;
        this.primaryStage.setScene(new Scene(mainMenu));
    	//this.primaryStage.show();
    	this.primaryStage.setTitle("Campo Minato");
    	this.primaryStage.setResizable(false);
		primaryStage.show();
    }


    /**
     * this code generates and runs the game
     * @return
     */
    public boolean initGame(boolean custom) {
    	if(!custom)
			switch((int)difficulty.getValue()) {
			case 1: bombs = 5; break;
			case 2: bombs = 9; break;
			case 3: bombs = 14; break;
			case 4: bombs = 20; break;
			default: bombs = 25; break;
			};
    	matriceCampoMinato = new MatriceCampoMinato(righe, colonne, bombs);

        Group grid = new Group();
        HBox hbox = new HBox();
        for(int x = 0; x<this.colonne; x++)
            hbox.getChildren().add(new VBox(generateNodes(x)));

//        //FOR TESTING PURPOSE {
//        Button apritutto = new Button("ApriTutto");
//        apritutto.addEventFilter(MouseEvent.MOUSE_CLICKED, (mouseEvent)->{
//            for(MatriceCampoMinato.Casella[] m: matriceCampoMinato.getMatriceCampoMinato())
//                for(MatriceCampoMinato.Casella c: m)
//                    c.setText(String.valueOf(c.getStatus()));
//        });
//        hbox.getChildren().add(0, apritutto);
//        //FOR TESTING PURPOSE }

        grid.getChildren().add(hbox);
        primaryStage.setScene(new Scene(grid));

    	
    	return true;
    }
    
    /**
     * this code generates the main menu
     */
    private void initMainMenu() {
    	mainMenu = new StackPane();
    	mainMenu.setMinSize(400, 350);
    	Button play = new Button("Play");
    	play.setOnAction(e -> initGame(false));
    	play.setTranslateY(20);
    	play.setScaleX(1.5);
    	play.setScaleY(1.5);
    	
    	
    	
    	difficulty = new Slider();
    	difficulty.setMax(5);
    	difficulty.setMaxWidth(100);
    	difficulty.setMaxHeight(20);
    	difficulty.setMin(1);
    	difficulty.setMajorTickUnit(1);
    	difficulty.setSnapToTicks(true);
    	difficulty.setMinorTickCount(1);
    	difficulty.setShowTickMarks(true);
    	difficulty.setShowTickLabels(true);
    	difficulty.setTranslateY(80);

		Button customGame = new Button("Custom");
		customGame.setOnAction(e->{
			primaryStage.setScene(new CustomGameMenu(primaryStage).buildCustomGameMenuScene(this));
		});
		customGame.setMinSize(75,25);
		customGame.setTranslateY(74);

    	Label l = new Label("Difficulty: ");
    	l.setTranslateY(77);
    	HBox diffBox = new HBox(l, difficulty, customGame);
    	diffBox.setMaxHeight(40);
    	diffBox.setMaxWidth(200);
    	
    	Label title = new Label("Campo Minato");
    	title.setFont(Font.font(55));
    	title.setTranslateY(-40);
    	mainMenu.getChildren().addAll(title,diffBox, play);
    	
    }

    public Node[] generateNodes(int x){
        Node[] list = new Node[this.righe];
        for(int y = 0; y<this.righe;y++)
            list[y] =matriceCampoMinato.setCasella(x,y, 0);
        return list;
    }
    


	public MatriceCampoMinato getMatriceCampoMinato() {
		return matriceCampoMinato;
	}


	public int getColonne() {
		return colonne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}

	public int getRighe() {
		return righe;
	}

	public void setRighe(int righe) {
		this.righe = righe;
	}

	public void setBombs(int bombs){
    	this.bombs = bombs;
	}

	public StackPane getMainMenu() {
		return mainMenu;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
