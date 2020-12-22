package tommy.apllication.campominato;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public class Animations {
	
	void fallAnimation(Region n ,int yStart, int yEnd, int frames, double tpf) {
		
        DoubleProperty position = new SimpleDoubleProperty(yStart);
        double gap = yEnd/frames;
        n.translateYProperty().bind(position);
        
        KeyFrame[] list = new KeyFrame[frames];
        while(frames > 0) {
        	frames--;
        	int i = frames;
        	list[frames] = new KeyFrame(Duration.seconds(tpf*frames), event -> position.setValue(yStart + i*gap));
        }

        Timeline beat = new Timeline(
            list
        );
        beat.play();
    }
	
	void opacityAnimation(Region n, double opStart, double opEnd, int frames, double tpf) {
		DoubleProperty position = new SimpleDoubleProperty(opStart);
        double gap = opEnd/frames;
        n.opacityProperty().bind(position);
        
        KeyFrame[] list = new KeyFrame[frames];
        while(frames > 0) {
        	frames--;
        	int i = frames;
        	list[frames] = new KeyFrame(Duration.seconds(tpf*frames), event -> position.setValue(opStart + i*gap));
        }

        Timeline beat = new Timeline(
            list
        );
        beat.play();
	}

}
