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
	
	void fallAnimation(Node n ,int yStart, int yEnd, int frames, double tpf) {
		
        DoubleProperty position = new SimpleDoubleProperty(yStart);
        double gap = yEnd/frames;
        n.translateYProperty().bind(position);
        
        KeyFrame[] list = new KeyFrame[frames];
        while(frames > 0) {
        	frames--;
        	int i = frames;
        	list[frames] = new KeyFrame(Duration.seconds(tpf*frames), event -> position.setValue(yStart + i*gap));
        }

        Timeline fall = new Timeline(
            list
        );
        fall.play();
    }
	
	void moveAnimation(Node n, int xStart, int yStart,int xEnd, int yEnd, int frames, double tpf) {
		
        DoubleProperty xPosition = new SimpleDoubleProperty(xStart);
        DoubleProperty yPosition = new SimpleDoubleProperty(yStart);
        double gap = yEnd/frames;
        n.translateXProperty().bind(xPosition);
        n.translateYProperty().bind(yPosition);
        KeyFrame[] list = new KeyFrame[frames];
        while(frames > 0) {
        	frames--;
        	int i = frames;
        	list[frames] = new KeyFrame(Duration.seconds(tpf*frames), event -> {xPosition.setValue(xStart + i*gap); yPosition.setValue(yStart + i*gap);});
        }

        Timeline fall = new Timeline(
            list
        );
        fall.play();
    }
	
	
	void opacityAnimation(Node n, double opStart, double opEnd, int frames, double tpf) {
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
	
	
	public void easyPulseAnimation(Node n) {
		pulseAnimation(n, 1, 1.2, 10, 0.2, true);
	}
	
	public void pulseAnimation(Node n, double sStart, double sEnd, int frames, double tpf, boolean cycle) {
        DoubleProperty scale = new SimpleDoubleProperty(sStart);
        n.scaleXProperty().bind(scale);
        n.scaleYProperty().bind(scale);
        
        double passo = Math.abs((sStart - sEnd)/frames);
        
        KeyFrame[] l = new KeyFrame[frames];
        
        int i = 0;
        
        while(i < frames) {
        	int j = i;
        	l[i] = new KeyFrame(Duration.seconds(tpf*i), e -> scale.setValue(sStart + passo*j));
        	i++;
        }
        
        
        Timeline beat = new Timeline(l);
        if(cycle) {
        	beat.setAutoReverse(cycle);
        	beat.setCycleCount(Timeline.INDEFINITE);
        }
        beat.play();
    }

}
