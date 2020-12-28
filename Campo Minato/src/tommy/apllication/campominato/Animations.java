package tommy.apllication.campominato;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public class Animations {
	
	void fallAnimation(Node n ,int yStart, int yEnd, int frames, double tpf) {
		
        moveAnimation(n, (int) n.getLayoutX(), yStart, (int) n.getLayoutX(), yEnd, frames, tpf);
    }
	
	void moveAnimation(Node n, int xStart, int yStart,int xEnd, int yEnd, int frames, double tpf) {
		repeatedMoveAnimation(n, xStart, yStart, xEnd, yEnd, frames, tpf, 1, false);
        
    }
	
	void repeatedMoveAnimation(Node n, int xStart, int yStart,int xEnd, int yEnd, int frames, double tpf, int repeat, boolean reverse) {
		DoubleProperty xPosition = new SimpleDoubleProperty(xStart);
        DoubleProperty yPosition = new SimpleDoubleProperty(yStart);
        double ygap = yEnd/frames;
        double xgap = xEnd/frames;
        n.translateXProperty().bind(xPosition);
        n.translateYProperty().bind(yPosition);
        KeyFrame[] list = new KeyFrame[frames];
        while(frames > 0) {
        	frames--;
        	int i = frames;
        	list[frames] = new KeyFrame(Duration.seconds(tpf*frames), event -> {xPosition.setValue(xStart + i*xgap); yPosition.setValue(yStart + i*ygap);});
        }

        Timeline fall = new Timeline(
            list
        );
        fall.setAutoReverse(reverse);
        fall.setCycleCount(repeat);	//use Timeline.INDEFINITE for a loop
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
	
	public void starShooting(Pane p, int x, int y) {
    	ImageView[] l = new ImageView[30];
		for(int i = 0; i < l.length; i++) {
			ImageView iv;
			switch((int) (Math.random()*4)) {
				case 0: iv = new ImageView(new Image("stella_gialla.png")); iv.setScaleX(0.7); iv.setScaleY(0.7); break;
				case 1: iv = new ImageView(new Image("stella_rossa.png")); iv.setScaleX(0.7); iv.setScaleY(0.7); break;
				case 2: iv = new ImageView(new Image("nuvola.png")); iv.setScaleX(0.7); iv.setScaleY(0.7); break;
				default: iv = new ImageView(new Image("bomb.png")); iv.setScaleX(0.18); iv.setScaleY(0.18); break;
			}
			double angle = Math.random()*360;
			
			double xEnd = 600 * Math.sin(angle);
			double yEnd = 600 * Math.cos(angle);
			System.out.println(angle + "    " + xEnd + "   " + yEnd);
			repeatedMoveAnimation(iv, x, y, (int) xEnd, (int) yEnd, 150 +  (int) (100 * Math.random()), 0.03, Timeline.INDEFINITE, false);
			p.getChildren().add(iv);
		}
	}

}
