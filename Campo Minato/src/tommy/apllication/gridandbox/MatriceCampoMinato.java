package tommy.apllication.gridandbox;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class MatriceCampoMinato {
    private Casella[][] matriceCampoMinato;
    private int bombs;
    private boolean firstChoose;

    public MatriceCampoMinato(int x, int y, int bombs){
        matriceCampoMinato = new Casella[x][y];
        this.bombs=bombs;
        this.firstChoose=true;
    }

    public boolean scopriCasella(int x, int y){
        //TODO
        if(firstChoose) generateBombs(x, y);

        Casella c = matriceCampoMinato[y][x];
        c.setText(String.valueOf(c.getStatus()));
        return false;
    }

    public void generateBombs(int x, int y){
        firstChoose=false;
        //keep track of free spaces
        LinkedList<Coordinate> freeCoordinates = new LinkedList<>();
            for (int column = 0; column < matriceCampoMinato[0].length; column++)
                for (int row = 0; row < matriceCampoMinato.length; row++)
                    if (column != x || row != y) freeCoordinates.add(new Coordinate(column, row));

        int bombPlaced = 0;
        while(bombPlaced<bombs)
        {
            ArrayList<Coordinate> toRemove = new ArrayList<>();
            for(Coordinate coordinate: freeCoordinates)
            {
                if(Math.random()<=0.15)
                {
                    matriceCampoMinato[coordinate.getY()][coordinate.getX()].setStatus(-1);
                    incrementaCricostanti(coordinate.getX(), coordinate.getY());
                    toRemove.add(coordinate);
                    bombPlaced++;
                }
            }
            freeCoordinates.removeAll(toRemove);
        }
    }

    public void incrementaCricostanti(int column, int riga){
        try //incrementa in alto a sx
        {
            matriceCampoMinato[riga-1][column-1].incrementStatus();
        }
        catch(IndexOutOfBoundsException i)
        {
            //guess we were at the border
        }

        try //incrementa in alto
        {
            matriceCampoMinato[riga-1][column].incrementStatus();
        }
        catch(IndexOutOfBoundsException i)
        {
            //guess we were at the border
        }

        try //incrementa in alto a dx
        {
            matriceCampoMinato[riga-1][column+1].incrementStatus();
        }
        catch(IndexOutOfBoundsException i)
        {
            //guess we were at the border
        }
        try //incrementa a sx
        {
            matriceCampoMinato[riga][column-1].incrementStatus();
        }
        catch(IndexOutOfBoundsException i)
        {
            //guess we were at the border
        }
        try //incrementa a dx
        {
            matriceCampoMinato[riga][column+1].incrementStatus();
        }
        catch(IndexOutOfBoundsException i)
        {
            //guess we were at the border
        }

        try //incrementa in basso a sx
        {
            matriceCampoMinato[riga+1][column-1].incrementStatus();
        }
        catch(IndexOutOfBoundsException i)
        {
            //guess we were at the border
        }

        try //incrementa in basso
        {
            matriceCampoMinato[riga+1][column].incrementStatus();
        }
        catch(IndexOutOfBoundsException i)
        {
            //guess we were at the border
        }

        try //incrementa in basso a dx
        {
            matriceCampoMinato[riga+1][column+1].incrementStatus();
        }
        catch(IndexOutOfBoundsException i)
        {
            //guess we were at the border
        }
    }

    public Node setCasella(int x, int y){
        matriceCampoMinato[x][y] = new Casella(0);
        return matriceCampoMinato[x][y];
    }

    public int getBombsNumber(){return bombs;}

    public Casella[][] getMatriceCampoMinato() {
        return matriceCampoMinato;
    }

    @Override
    public String toString(){
        String toReturn ="";
        for(Casella[] caselle: matriceCampoMinato) {
            for (Casella casella : caselle)
                toReturn += casella.getText();
            toReturn+="\n";
        }
        return toReturn.substring(0, toReturn.length()-1);
    }

    public class Casella extends Button {
        private int status;
        private double size = 35;
        private boolean shown;
        private boolean canBeShown = true;


        public Casella(int status){
            this.status=status;

            addEventFilter(MouseEvent.MOUSE_CLICKED, (mouseEvent) -> {
                if (!shown)
                    switch (mouseEvent.getButton()) {
                        case PRIMARY: {
                            if (canBeShown) {
                                //setText(String.valueOf(status));
                                shown = true;
                                canBeShown = true;
                                setDisable(true);
                                //TROVA LA CASELLA
                                for(int y = 0; y<matriceCampoMinato.length; y++)
                                    for(int x = 0; x<matriceCampoMinato[y].length; x++)
                                        if(matriceCampoMinato[y][x].equals(this)){
                                            System.out.println(matriceCampoMinato[y][x]+", "+"Trovata! y:"+y+"; x:"+x);
                                            scopriCasella(x,y);
                                        }
                            }

                        }; break;

                        case SECONDARY: {
                            if (getText().equals("F")) {
                                setText("?");
                                canBeShown = false;
                            } else if (getText().equals("")) {
                                setText("F");
                                canBeShown = false;
                            } else {
                                setText("");
                                canBeShown = true;
                            }
                        }; break;
                        default: break;
                    }
                });
            setStyle("-fx-background-radius: 0");
            setTextFill(Color.BLACK);
            setMinSize(size, size);
            setFocusTraversable(false);
            setTextAlignment(TextAlignment.CENTER);

        }
//        public void trovaCasella(Casella casella){
//            for(int y = 0; y<matriceCampoMinato.length; y++)
//                for(int x = 0; x<matriceCampoMinato[y].length; x++)
//                    if(matriceCampoMinato[y][x].equals(this)){
//                        System.out.println(matriceCampoMinato[y][x]+", "+"Trovata! y:"+y+"; x:"+x);
//                    }
//        }
        public int getStatus(){return status;}

        public void setStatus(int status){
            this.status=status;
            if(status<0) setStyle("-fx-background-color: #CD5C5C;");
        }

        public void incrementStatus(){if(status>=0) status++;}

        }
    }


