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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import tommy.apllication.gridandbox.MatriceCampoMinato;
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
	private Scene customMenuGame;

	private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
    	this.primaryStage = primaryStage;
    	this.primaryStage.setTitle("Campo Minato");

		Menu mainMenu = new MainMenu(new ImageView(setBackground()), this);
		this.mainMenuGame = new Scene(mainMenu);

		Menu difficultyMenu = new DifficultyMenu(new ImageView(setBackground()), this);
		this.difficultyMenu = new Scene(difficultyMenu);

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
//    	if(!custom)
//			switch((int)difficulty.getValue()) {
//			case 1: bombs = 5; break;
//			case 2: bombs = 9; break;
//			case 3: bombs = 14; break;
//			case 4: bombs = 20; break;
//			default: bombs = 25; break;
//			};
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

		primaryStage.setHeight(lato*righe);
		primaryStage.setWidth(lato*colonne+14);
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
    	VBox vBox = (VBox) menu.getChildren().stream().filter(n-> n instanceof VBox).findFirst().get();
    	for(int i = 0; i<vBox.getChildrenUnmodifiable().size(); i++)
		{
			Node node = vBox.getChildrenUnmodifiable().get(i);
			TranslateTransition transition = new TranslateTransition(Duration.seconds(1 + i * 0.23), node);
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
		primaryStage.setScene(customMenuGame);
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
