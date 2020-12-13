package tommy.apllication.gridandbox;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import tommy.apllication.exception.BombException;
import tommy.apllication.exception.WinException;

import java.util.*;

public class MatriceCampoMinato {
    private Casella[][] matriceCampoMinato;
    private int bombs, caselleScoperte;
    private boolean firstChoose;


    public MatriceCampoMinato(int righe, int colonne, int bombs){
        matriceCampoMinato = new Casella[righe][colonne];
        this.bombs=bombs;
        this.firstChoose=true;
    }

    public void scopriCasella(int colonna, int riga) throws BombException, WinException {

        if(firstChoose) generateBombs(riga, colonna);

        Casella c = matriceCampoMinato[riga][colonna];

        if(c.getStatus()==0)
        {
            c.setDisable(true);
            c.setText("");
            Casella[] caselle = getsurroundingBoxes(colonna, riga);
            for(Casella casella: caselle)
                if(casella!=null && !casella.isDisable())
                    scopriCasella(casella.getCoordinate().getX(), casella.getCoordinate().getY());
        }
        else if(c.getStatus()>0)
        {
            c.setText(String.valueOf(c.getStatus()));
            c.setDisable(true);
        }
        else throw new BombException(c);
        ++caselleScoperte;
        if(checkWin()) throw new WinException();
    }

    public boolean checkWin(){
        return caselleScoperte==(matriceCampoMinato[0].length* matriceCampoMinato.length - bombs);
    }

    public void generateBombs(int riga, int colonna){
        firstChoose=false;
        //keep track of free spaces
        LinkedList<Coordinate> freeCoordinates = new LinkedList<>();
            for (int column = 0; column < matriceCampoMinato[0].length; column++)
                for (int row = 0; row < matriceCampoMinato.length; row++)
                    if (column != colonna || row != riga) freeCoordinates.add(new Coordinate(row, column));

        Collections.shuffle(freeCoordinates);
        int bombPlaced = 0;
        while(true)
        {
            ArrayList<Coordinate> toRemove = new ArrayList<>();

            for(Coordinate coordinate: freeCoordinates)
            {
                if(Math.random()<=0.15)
                {
                    matriceCampoMinato[coordinate.getY()][coordinate.getX()].setStatus(-1);
                    for(Casella c: getsurroundingBoxes(coordinate.getX(), coordinate.getY()))
                        if(c!=null) c.incrementStatus();
                    toRemove.add(coordinate);
                    bombPlaced++;
                }
                if(bombPlaced>=bombs) break;
            }
            if(bombPlaced<bombs) freeCoordinates.removeAll(toRemove);
            else break;
        }
    }

    public Casella[] getsurroundingBoxes(int column, int riga){
        Casella[] caselle = new Casella[8];
        try //incrementa in alto a sx
        {
            caselle[0] = matriceCampoMinato[riga-1][column-1];
        }
        catch(IndexOutOfBoundsException i)
        {
            //guess we were at the border
            caselle[0] = null;
        }

        try //incrementa in alto
        {
            caselle[1] =matriceCampoMinato[riga-1][column];
        }
        catch(IndexOutOfBoundsException i)
        {
            //guess we were at the border
            caselle[1] = null;
        }

        try //incrementa in alto a dx
        {
            caselle[2] =matriceCampoMinato[riga-1][column+1];
        }
        catch(IndexOutOfBoundsException i)
        {
            //guess we were at the border
            caselle[2] = null;
        }
        try //incrementa a sx
        {
            caselle[3] =matriceCampoMinato[riga][column-1];
        }
        catch(IndexOutOfBoundsException i)
        {
            //guess we were at the border
            caselle[3] = null;
        }
        try //incrementa a dx
        {
            caselle[4] =matriceCampoMinato[riga][column+1];
        }
        catch(IndexOutOfBoundsException i)
        {
            //guess we were at the border
            caselle[4] = null;
        }

        try //incrementa in basso a sx
        {
            caselle[5] =matriceCampoMinato[riga+1][column-1];
        }
        catch(IndexOutOfBoundsException i)
        {
            //guess we were at the border
            caselle[5] = null;
        }

        try //incrementa in basso
        {
            caselle[6] =matriceCampoMinato[riga+1][column];
        }
        catch(IndexOutOfBoundsException i)
        {
            //guess we were at the border
            caselle[6] = null;
        }

        try //incrementa in basso a dx
        {
            caselle[7] =matriceCampoMinato[riga+1][column+1];
        }
        catch(IndexOutOfBoundsException i)
        {
            //guess we were at the border
            caselle[7] = null;
        }
        return caselle;
    }

    public Node setCasella(int colonna, int riga, int value){
        matriceCampoMinato[riga][colonna] = new Casella(riga, colonna, value);
        return matriceCampoMinato[riga][colonna];
    }

    public int getBombsNumber(){return bombs;}

    public Casella[][] getMatriceCampoMinato() {return matriceCampoMinato;}

    @Override
    public String toString(){
        String toReturn ="";
        for(Casella[] caselle: matriceCampoMinato) {
            for (Casella casella : caselle)
                if(casella.isDisable()) toReturn += casella.getStatus()+"; ";
                else toReturn+="@";
            toReturn+="\n";
        }
        return toReturn.substring(0, toReturn.length()-1);
    }

    public class Casella extends Button {
        private int status;
        private double size = 35;
        /**
         * X:Colonna
         * Y:Riga
         */
        private Coordinate coordinate;

        public Casella(int riga, int colonna, int status){
            this(status);
            coordinate = new Coordinate(riga,colonna);
        }

        public Casella(int status){
            this.status=status;

            addEventFilter(MouseEvent.MOUSE_CLICKED, (mouseEvent) -> {
                    switch (mouseEvent.getButton()) {
                        case PRIMARY: {
                            if(!getText().equals("?") && !getText().equals("F"))
                            {
                                setDisable(true);
                                try
                                {
                                    scopriCasella(coordinate.getX(), coordinate.getY());
                                }
                                catch (BombException e)
                                {
                                    for (Casella[] caselle : matriceCampoMinato)
                                        for (Casella cas : caselle)
                                            if (!cas.isDisable())
                                            {
                                                if (cas.getStatus() < 0) cas.setStyle("-fx-background-color: #CD5C5C;");
                                                else
                                                {
                                                    cas.setText(String.valueOf(cas.getStatus()));
                                                    cas.setStyle("-fx-background-color: #B9B7A7;");
                                                }
                                                cas.setDisable(true);
                                            }
                                }
                                catch (WinException w)
                                {
                                    for (Casella[] caselle : matriceCampoMinato)
                                        for (Casella cas : caselle)
                                            if(cas.getStatus()<0)
                                            {
                                                cas.setDisable(true);
                                                cas.setStyle("-fx-background-color: #7CFC00");
                                            }
                                }
                            }
                        }; break;

                        case SECONDARY: {
                            //System.out.println("X:"+this.getCoordinate().getX()+" Y:"+getCoordinate().getY());
                            switch (getText())
                            {
                                case "F": setText("?"); break;
                                case "?": setText(""); break;
                                default: setText("F"); break;
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

        public int getStatus(){return status;}
        public Coordinate getCoordinate(){return coordinate;}


        public void setStatus(int status){
            this.status=status;
            //if(status<0) setStyle("-fx-background-color: #CD5C5C;");
        }

        public void incrementStatus(){
            if(status>=0)
                status++;
        }

        }
    }


