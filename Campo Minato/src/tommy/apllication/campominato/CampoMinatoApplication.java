package tommy.apllication.campominato;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
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
<<<<<<< HEAD
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javafx.stage.Stage;
import javafx.util.Duration;
import tommy.apllication.gridandbox.MatriceCampoMinato;

import tommy.apllication.menu.CustomGameMenu;
=======
import javafx.stage.Stage;
import javafx.util.Duration;
import tommy.apllication.gridandbox.MatriceCampoMinato;
import tommy.apllication.menu.*;

import java.io.IOException;
import java.io.InputStream;
>>>>>>> 0091c6b... replaced all the files with the Tom's repo and added the Game Over menu.

import tommy.apllication.menu.DifficultyMenu;
import tommy.apllication.menu.MainMenu;
import tommy.apllication.menu.Menu;

import java.io.IOException;
import java.io.InputStream;

public class CampoMinatoApplication extends Application {

    private MatriceCampoMinato matriceCampoMinato;
<<<<<<< HEAD
    private int colonne;
    private int righe;
    private Animations animClass;
    private static int bombs = 5;


    private StackPane mainMenu;
	  private Slider difficulty;
  	private Scene mainMenuGame;
	  private Scene difficultyMenu;
	  private Scene customMenuGame;
=======
    private static int colonne;
    private static int righe;
    private static int bombs;
    private int appWidth;
    private int appHeight;


	private Scene mainMenuGame;
	private Scene difficultyMenu;
	private Scene customGameMenu;

	private Stage primaryStage;
>>>>>>> 0091c6b... replaced all the files with the Tom's repo and added the Game Over menu.

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
<<<<<<< HEAD
    public boolean initGame(boolean custom) {
    	if(!custom) {
			switch((int)difficulty.getValue()) {
			case 1: bombs = 5; break;
			case 2: bombs = 9; break;
			case 3: bombs = 14; break;
			case 4: bombs = 20; break;
			default: bombs = 25; break;
			};
			colonne = 10;
			righe = 10;
    	}
    	matriceCampoMinato = new MatriceCampoMinato(righe, colonne, bombs ,this);


      Group grid = new Group();
      HBox hbox = new HBox();
      for(int x = 0; x<this.colonne; x++) hbox.getChildren().add(new VBox(generateNodes(x)));
=======
    public boolean initGame() {

    	matriceCampoMinato = new MatriceCampoMinato(righe, colonne, bombs, this);

        HBox hbox = new HBox();
        for(int x = 0; x<this.colonne; x++) hbox.getChildren().add(new VBox(generateNodes(x)));
>>>>>>> 0091c6b... replaced all the files with the Tom's repo and added the Game Over menu.

//        //FOR TESTING PURPOSE {
//        Button apritutto = new Button("ApriTutto");
//        apritutto.addEventFilter(MouseEvent.MOUSE_CLICKED, (mouseEvent)->{
//            for(MatriceCampoMinato.Casella[] m: matriceCampoMinato.getMatriceCampoMinato())
//                for(MatriceCampoMinato.Casella c: m)
//                    c.setText(String.valueOf(c.getStatus()));
//        });
//        hbox.getChildren().add(0, apritutto);
//        //FOR TESTING PURPOSE }

<<<<<<< HEAD
      grid.getChildren().add(hbox);
      primaryStage.setScene(new Scene(grid));
        
		  Button b = (Button)((Parent)hbox.getChildren().get(0)).getChildrenUnmodifiable().get(0);
		  double lato = b.getHeight();

		  primaryStage.setHeight(lato*righe);
		  primaryStage.setWidth(lato*colonne+14);
    	return true;
    }
    }
    
    /**
     * this code generates the main menu
     */
    private void initMainMenu() {
    	mainMenu = new StackPane();
    	mainMenu.setMinSize(400, 350);
    	mainMenu = new MainMenu(new ImageView(setBackground()), this);
    	Image bomb = new Image("bomb_main.png");
    	ImageView ivBomb = new ImageView(bomb);
    	ivBomb.setOpacity(0.7);
    	ivBomb.setScaleX(0.7);
    	ivBomb.setScaleY(0.7);
    	animClass.pulseAnimation(ivBomb, 0, 1, 50, 0.05, false);
    	
      ///////////////TommyCode. IDK what do you want to do with this
      Menu difficultyMenu = new DifficultyMenu(new ImageView(setBackground()), this);
		  this.difficultyMenu = new Scene(difficultyMenu);
      ///////////////End of tommyCode
//    	Image redStar = new Image("stella_gialla.png");
//    	ImageView ivRedStar = new ImageView(redStar);
//    	ivRedStar.setScaleX(0.7);
//    	ivRedStar.setScaleY(0.7);
//    	animClass.moveAnimation(ivRedStar, 0, 0, 300, -600, 200, 0.06);
    	animClass.starShooting(mainMenu, 0, 0);
    	

    	Button play = new Button("Play");
    	play.setOnAction(e -> initGame(false));
    	play.setTranslateY(20);
    	play.setScaleX(1.5);
    	play.setScaleY(1.5);
    	animClass.opacityAnimation(play, 0.0, 1, 70, 0.04);
    	
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
=======
		hbox.setAlignment(Pos.CENTER);
		Group grid = new Group(hbox);
		Scene matrix = new Scene(grid);

		matrix.setOnKeyPressed(new EventHandler<KeyEvent>() {
			double height = primaryStage.getHeight();
			double width = primaryStage.getWidth();
			@Override
			public void handle(KeyEvent keyEvent) {
				if(keyEvent.getCode() == KeyCode.ESCAPE || keyEvent.getCode() == KeyCode.ENTER)
				{
					primaryStage.setHeight(height);
					primaryStage.setWidth(width);
					setMainMenuGameScene();
				}
			}
>>>>>>> 0091c6b... replaced all the files with the Tom's repo and added the Game Over menu.
		});
		primaryStage.setScene(matrix);
		MatriceCampoMinato.Casella b = (MatriceCampoMinato.Casella) ((Parent)hbox.getChildren().get(0)).getChildrenUnmodifiable().get(0);
		double size = b.getSize();
//		primaryStage.setHeight(size*righe+righe*2);
		primaryStage.setHeight(size*righe+44);
//		primaryStage.setWidth(size*colonne+colonne*2);
		primaryStage.setWidth(size*colonne+20);
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
<<<<<<< HEAD
			return new Image(is);
=======
			Image i = new Image(is);
			appWidth = (int) i.getWidth();
			appHeight = (int) i.getHeight();
			return i;
>>>>>>> 0091c6b... replaced all the files with the Tom's repo and added the Game Over menu.
		}
		catch (IOException e)
		{
			return null;
		}
	}

	public void animateMenu(Menu menu){
<<<<<<< HEAD
    	VBox vBox = (VBox) menu.getChildren().stream().filter(n-> n instanceof VBox).findFirst().get();
    	for(int i = 0; i<vBox.getChildrenUnmodifiable().size(); i++)
		{
			Node node = vBox.getChildrenUnmodifiable().get(i);
			TranslateTransition transition = new TranslateTransition(Duration.seconds(1 + i * 0.23), node);
=======
    	Parent box = (Parent) menu.getChildren().stream().filter(n-> n instanceof VBox || n instanceof HBox).findFirst().get();
    	for(int i = 0; i<box.getChildrenUnmodifiable().size(); i++)
		{
			Node node = box.getChildrenUnmodifiable().get(i);
			TranslateTransition transition = new TranslateTransition(Duration.seconds(1 + i * 0.1), node);
>>>>>>> 0091c6b... replaced all the files with the Tom's repo and added the Game Over menu.
			transition.setToX(50);
			transition.play();
		}
	}
<<<<<<< HEAD
=======

	public static void resetValuesToDefault(){
    	colonne = 10;
    	righe = 12;
	}
>>>>>>> 0091c6b... replaced all the files with the Tom's repo and added the Game Over menu.

	public MatriceCampoMinato getMatriceCampoMinato() {
		return matriceCampoMinato;
	}

	public Scene getActualScene(){return primaryStage.getScene();}

	public Stage getStage(){return primaryStage;}

<<<<<<< HEAD

	public int getColonne() {
=======
	public static int getBombs(){return bombs;}

	public static int getColonne() {
>>>>>>> 0091c6b... replaced all the files with the Tom's repo and added the Game Over menu.
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
		primaryStage.setScene(mainMenuGame);
		primaryStage.setWidth(appWidth);
		primaryStage.setHeight(appHeight);
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

<<<<<<< HEAD
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
		primaryStage.setScene(customMenuGame);
	}

//	public Stage getPrimaryStage() {
//		return primaryStage;
//	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

=======
>>>>>>> 0091c6b... replaced all the files with the Tom's repo and added the Game Over menu.




	public static void main(String[] args) {
		launch(args);
	}

<<<<<<< HEAD
=======


>>>>>>> 0091c6b... replaced all the files with the Tom's repo and added the Game Over menu.
//	public Scene getMainMenuGame() {
//		return mainMenuGame;
//	}

}
