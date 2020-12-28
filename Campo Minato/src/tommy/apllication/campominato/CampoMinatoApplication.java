package tommy.apllication.campominato;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Mnemonic;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import tommy.apllication.gridandbox.MatriceCampoMinato;
import tommy.apllication.menu.CustomGameMenu;
import tommy.apllication.menu.DifficultyMenu;
import tommy.apllication.menu.MainMenu;
import tommy.apllication.menu.Menu;

import java.io.IOException;
import java.io.InputStream;

public class CampoMinatoApplication extends Application {

    private MatriceCampoMinato matriceCampoMinato;
    private static int colonne = 10;
    private static int righe = 12;
    private static int bombs;

    private StackPane mainMenu;
	private Slider difficulty;

	private Scene mainMenuGame;
	private Scene difficultyMenu;
	private Scene customGameMenu;

	private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
    	this.primaryStage = primaryStage;
    	this.primaryStage.setTitle("Campo Minato");

		Menu mainMenu = new MainMenu(new ImageView(setBackground()), this);
		this.mainMenuGame = new Scene(mainMenu);

		Menu difficultyMenu = new DifficultyMenu(new ImageView(setBackground()), this);
		this.difficultyMenu = new Scene(difficultyMenu);

		Menu customGameMenu = new CustomGameMenu(new ImageView(setBackground()), this);
		this.customGameMenu = new Scene(customGameMenu);

		primaryStage.setScene(this.mainMenuGame);
		animateMenu(mainMenu);
		//animateMenu(difficultyMenu);

		primaryStage.show();
		primaryStage.setHeight((int)mainMenu.getHeight());
		primaryStage.setResizable(false);


	}


    /**
     * this code generates and runs the game
     * @return
     */
    public boolean initGame() {
    	matriceCampoMinato = new MatriceCampoMinato(righe, colonne, bombs);

        Group grid = new Group();
        HBox hbox = new HBox();
        for(int x = 0; x<this.colonne; x++) hbox.getChildren().add(new VBox(generateNodes(x)));

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
		Button b = (Button)((Parent)hbox.getChildren().get(0)).getChildrenUnmodifiable().get(0);
		double lato = b.getHeight();

		primaryStage.setHeight(hbox.getHeight()+35.5);
		primaryStage.setWidth(hbox.getWidth()+14.2);
    	return true;
    }

    public Node[] generateNodes(int x){
        Node[] list = new Node[this.righe];
        for(int y = 0; y<this.righe;y++) list[y] =matriceCampoMinato.setCasella(x,y, 0);
        return list;
    }

    public Image setBackground(){
		try(InputStream is = getClass().getResourceAsStream("/tommy/resources/images/minefield.jpg");)
		{
			return new Image(is);
		}
		catch (IOException e)
		{
			return null;
		}
	}

	public void animateMenu(Menu menu){
    	Parent box = (Parent) menu.getChildren().stream().filter(n-> n instanceof VBox || n instanceof HBox).findFirst().get();
    	for(int i = 0; i<box.getChildrenUnmodifiable().size(); i++)
		{
			Node node = box.getChildrenUnmodifiable().get(i);
			TranslateTransition transition = new TranslateTransition(Duration.seconds(1 + i * 0.1), node);
			transition.setToX(50);
			transition.play();
		}
	}


	public MatriceCampoMinato getMatriceCampoMinato() {
		return matriceCampoMinato;
	}


	public static int getColonne() {
		return colonne;
	}

	public static void setColonne(int c) {
		colonne = c;
	}

	public static int getRighe() {
		return righe;
	}

	public static void setRighe(int r) {
		righe = r;
	}

	public static void setBombs(int b){
    	bombs = b;
	}

	public StackPane getMainMenu() {
		return mainMenu;
	}

	public void setMainMenuGameScene() {
		primaryStage.setScene(mainMenuGame);
		animateMenu((Menu) difficultyMenu.getRoot());
	}

//	public Scene getDifficultyMenu() {
//		return difficultyMenu;
//	}

	public void setDifficultyMenuScene() {
		primaryStage.setScene(difficultyMenu);
		animateMenu((Menu) difficultyMenu.getRoot());
	}

//	public Scene getCustomMenuGame() {
//		return customMenuGame;
//	}

	public void setCustomMenuGameScene() {
		primaryStage.setScene(customGameMenu);
		animateMenu((Menu) customGameMenu.getRoot());
	}

//	public Stage getPrimaryStage() {
//		return primaryStage;
//	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}





	public static void main(String[] args) {
		launch(args);
	}

//	public Scene getMainMenuGame() {
//		return mainMenuGame;
//	}

}
