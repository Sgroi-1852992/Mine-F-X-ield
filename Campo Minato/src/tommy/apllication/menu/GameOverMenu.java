package tommy.apllication.menu;

import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tommy.apllication.campominato.CampoMinatoApplication;

/**
 * this class will pop up a game over menu with two options:
 * 	1) restart the game
 * 	2) go to main menu
 * @author Andrea
 *
 */
public class GameOverMenu {
	private Stage gameOverStage;
	
	public void buildGameOverMenu(boolean won, CampoMinatoApplication app) {
		gameOverStage = new Stage(StageStyle.TRANSPARENT);
		gameOverStage.initModality(Modality.APPLICATION_MODAL);
		gameOverStage.setResizable(false);
		gameOverStage.setWidth(app.getStage().getScene().getWindow().getWidth());
		gameOverStage.setHeight(app.getStage().getScene().getWindow().getHeight());
		gameOverStage.setTitle("Game Over");
		gameOverStage.setX(app.getStage().getX());
		gameOverStage.setY(app.getStage().getY());
		gameOverStage.setOpacity(0.7);
		
		StackPane mainPanel = new StackPane();
		
		Label gameOverLabel = new Label(won ? "You won!":"Game Over");
		gameOverLabel.setFont(Font.font("Verdana" , FontWeight.EXTRA_BOLD,30));
		gameOverLabel.setTranslateY(-40);
		ScaleTransition st = new ScaleTransition(Duration.seconds(1), gameOverLabel);
		st.setByX(1.1);
		st.setToX(1.2);
		st.setByY(1.1);
		st.setToY(1.2);
		st.setCycleCount(Timeline.INDEFINITE);
		st.setAutoReverse(true);
		st.play();
		
		Button playAgain = new Button("Play Again");
		playAgain.setScaleX(1.5);
		playAgain.setScaleY(1.5);
		playAgain.setTranslateY(80);
		playAgain.setOnAction(e -> {
			app.initGame();
			gameOverStage.close();
		});
		
		Button gotoMainMenu = new Button("Go to Main Menu");
		gotoMainMenu.setScaleX(1.5);
		gotoMainMenu.setScaleY(1.5);
		gotoMainMenu.setTranslateY(30);
		gotoMainMenu.setOnAction(e -> {
			app.setMainMenuGameScene();
			gameOverStage.close();
		});
		
		StackPane wrapper = new StackPane(gameOverLabel, playAgain, gotoMainMenu);
		wrapper.setScaleX(0.5); 
		wrapper.setScaleY(0.5); 
		mainPanel.getChildren().addAll(wrapper);
		Scene s = new Scene(mainPanel);
		s.setFill(Color.TRANSPARENT);
		gameOverStage.setScene(s);
		
		gameOverStage.show();
	}
	
	

}
