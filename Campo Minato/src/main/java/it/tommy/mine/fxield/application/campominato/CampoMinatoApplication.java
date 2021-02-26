package it.tommy.mine.fxield.application.campominato;

import it.tommy.mine.fxield.application.gridandbox.MatriceCampoMinato;
import it.tommy.mine.fxield.application.menu.CustomGameMenu;
import it.tommy.mine.fxield.application.menu.DifficultyMenu;
import it.tommy.mine.fxield.application.menu.MainMenu;
import it.tommy.mine.fxield.application.menu.Menu;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;

public class CampoMinatoApplication extends Application {

    private MatriceCampoMinato matriceCampoMinato;
    private static int colonne;
    private static int righe;
    private static int bombs;


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

		primaryStage.setMaxWidth(800);
		primaryStage.setMaxHeight(800);

		resetValuesToDefault();
	}


    /**
     * this code generates and runs the game
     * @return
     */
    public boolean initGame() {

    	matriceCampoMinato = new MatriceCampoMinato(righe, colonne, bombs);

        HBox hbox = new HBox();
        for(int x = 0; x<this.colonne; x++) hbox.getChildren().add(new VBox(generateNodes(x)));

		hbox.setAlignment(Pos.CENTER);
		Group grid = new Group(hbox);
		Scene matrix = new Scene(grid);
		Stage gameStage = new Stage();

		matrix.setOnKeyPressed(new EventHandler<KeyEvent>() {
//			double height = primaryStage.getHeight();
//			double width = primaryStage.getWidth();
			@Override
			public void handle(KeyEvent keyEvent) {
				if(keyEvent.getCode() == KeyCode.ESCAPE || keyEvent.getCode() == KeyCode.ENTER)
				{
//					primaryStage.setHeight(height);
//					primaryStage.setWidth(width);
					setMainMenuGameScene();
				}
			}
		});
		gameStage.setScene(matrix);
		MatriceCampoMinato.Casella b = (MatriceCampoMinato.Casella) ((Parent)hbox.getChildren().get(0)).getChildrenUnmodifiable().get(0);
		double size = b.getSize();
//		primaryStage.setHeight(size*righe+righe*2);
		gameStage.setHeight(size*righe+44);
//		primaryStage.setWidth(size*colonne+colonne*2);
		gameStage.setWidth(size*colonne+20);

		gameStage.setOnCloseRequest(e->primaryStage.show());

		gameStage.setTitle("Matrix "+matriceCampoMinato.getMatriceCampoMinato().length+"x"+matriceCampoMinato.getMatriceCampoMinato()[0].length+" - bombs: "+matriceCampoMinato.getBombsNumber());
		gameStage.setResizable(false);
		gameStage.show();
		primaryStage.hide();
    	return true;
    }

    public Node[] generateNodes(int x){
        Node[] list = new Node[this.righe];
        for(int y = 0; y<this.righe;y++) list[y] =matriceCampoMinato.setCasella(x,y, 0);
        return list;
    }

    public Image setBackground(){

		try(InputStream is = getClass().getResourceAsStream("/minefield.jpg");)
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

	public static void resetValuesToDefault(){
    	colonne = 10;
    	righe = 12;
	}

	public MatriceCampoMinato getMatriceCampoMinato() {
		return matriceCampoMinato;
	}

	public Scene getActualScene(){return primaryStage.getScene();}

	public Stage getStage(){return primaryStage;}

	public static int getBombs(){return bombs;}

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

	public void setMainMenuGameScene() {
		Menu mainMenu = new MainMenu(new ImageView(setBackground()), this);
		this.mainMenuGame = new Scene(mainMenu);

		primaryStage.setScene(mainMenuGame);
		animateMenu(mainMenu);
	}

//	public Scene getDifficultyMenu() {
//		return difficultyMenu;
//	}

	public void setDifficultyMenuScene() {
		Menu difficultyMenu = new DifficultyMenu(new ImageView(setBackground()), this);
		this.difficultyMenu = new Scene(difficultyMenu);

		primaryStage.setScene(this.difficultyMenu);
		animateMenu(difficultyMenu);
	}

//	public Scene getCustomMenuGame() {
//		return customMenuGame;
//	}

	public void setCustomMenuGameScene() {
		Menu customGameMenu = new CustomGameMenu(new ImageView(setBackground()), this);
		this.customGameMenu = new Scene(customGameMenu);

		primaryStage.setScene(this.customGameMenu);
		animateMenu(customGameMenu);
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
