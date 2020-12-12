package tommy.apllication.gridandbox;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextAlignment;

public class Casella extends Button {
    private int status;
    private static double size = 35;

    public Casella(int status){
        super(String.valueOf(status));
        this.status=status;


        setMinSize(size, size);


        addEventFilter(MouseEvent.MOUSE_CLICKED, (mouseEvent)->{
                switch(mouseEvent.getButton())
                {
                    case PRIMARY: {

                    }; break;

                    case SECONDARY: {
                        if(getText().equals("F")) setText("?");
                        else if(getText().equals("?")) setText(String.valueOf(status));
                        else setText("F");
                    }; break;
                    default: break;
                }

                //setPrefSize(size,size);
        });

        setTextAlignment(TextAlignment.CENTER);
    }

    public int getStatus(){return status;}


}
