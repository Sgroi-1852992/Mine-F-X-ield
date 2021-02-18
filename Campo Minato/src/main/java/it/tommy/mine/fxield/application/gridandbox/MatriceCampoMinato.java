package it.tommy.mine.fxield.application.gridandbox;

import it.tommy.mine.fxield.application.campominato.CampoMinatoApplication;
import it.tommy.mine.fxield.application.exception.BombException;
import it.tommy.mine.fxield.application.exception.WinException;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.io.InputStream;
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

    public void scopriCasella(Casella casella) throws BombException, WinException {
        int colonna = casella.getCoordinate().getX();
        int riga = casella.getCoordinate().getY();

        Casella c = matriceCampoMinato[riga][colonna];
        if(firstChoose) generateBombs(c);

        if(c.getStatus()==0)
        {
            c.setDisable(true);
            c.setText("");
            Casella[] caselle = getsurroundingBoxes(casella);
            for(Casella casellaNext: caselle)
                if(casellaNext!=null && !casellaNext.isDisable())
                    scopriCasella(casellaNext);
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
        return caselleScoperte==(CampoMinatoApplication.getColonne()*CampoMinatoApplication.getRighe() - bombs);
    }

    public void generateBombs(Casella init){
        firstChoose=false;

        //keep track of free spaces
        LinkedList<Casella> freeBoxes = new LinkedList<>();
        Arrays.stream(matriceCampoMinato).forEach(caselle->Arrays.stream(caselle).forEach(casella-> {if(!init.equals(casella)) freeBoxes.add(casella);}));

        int bombPlaced = 0;
        while(bombPlaced<bombs)
        {
            int toRemove = (int)(freeBoxes.size() * Math.random());
            Casella casella = freeBoxes.get(toRemove);
            casella.setStatus(-1);
            freeBoxes.remove(toRemove);
            incrementSurroundingBoxes(casella);
            bombPlaced++;
        }

    }

    public void incrementSurroundingBoxes(Casella casella){
        for(Casella c: getsurroundingBoxes(casella))
            if(c!=null) c.incrementStatus();
    }

    public Casella[] getsurroundingBoxes(Casella casella){
        int column = casella.getCoordinate().getX();
        int riga = casella.getCoordinate().getY();

        Casella[] caselle = new Casella[8];
            // ttt
            // tXt
            // ttt
            int[] points = new int[] {
                    -1, -1,
                    -1, 0,
                    -1, 1,
                    0, -1,
                    0, 1,
                    1, -1,
                    1, 0,
                    1, 1
            };

            for (int i = 0, x = 0; i < points.length; i++, x++)
            {
                int dx = points[i];
                int dy = points[++i];

                int newX = column + dx;
                int newY = riga + dy;

                if (newX >= 0 && newX < matriceCampoMinato[0].length
                        && newY >= 0 && newY < matriceCampoMinato.length)
                    caselle[x] = matriceCampoMinato[newY][newX];
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

    private enum Status{
        VOID, FLAG, QUESTION_MARKER;
    }
    public class Casella extends Button {
        private int status;
        private double size = 25;
        private final static String BOMB_URL = "/bomb_icon.png";
        private final static String FLAG_URL = "/flag_icon.png";

        private ImageView image;

        private Status statusCasella = Status.VOID;


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
            setMaxSize(size, size);
            setTextAlignment(TextAlignment.CENTER);
            setFont(Font.font(null, FontWeight.BOLD, 12));

            addEventFilter(MouseEvent.MOUSE_CLICKED, (mouseEvent) -> {
                    switch (mouseEvent.getButton()) {
                        case PRIMARY: {
                            if(!(getStatusCasella()==Status.QUESTION_MARKER) && !(getStatusCasella() == Status.FLAG))
                            {
                                setDisable(true);
                                try
                                {
                                    scopriCasella(this);
                                }
                                catch (BombException e)
                                {

                                    fillImage(this, BOMB_URL);
                                    for (Casella[] caselle : matriceCampoMinato)
                                        for (Casella cas : caselle)
                                            if (!cas.isDisable())
                                            {
                                                if (cas.getStatus() < 0)
                                                {
                                                    cas.setStyle("-fx-background-color: #CD5C5C;");
                                                    fillImage(cas, BOMB_URL);
                                                }
                                                else
                                                {
                                                    if(cas.getStatus()>0) cas.setText(String.valueOf(cas.getStatus()));
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
                                                fillImage(cas, BOMB_URL);
                                            }
                                }
                            }
                        }; break;

                        case SECONDARY: {
                            //System.out.println("X:"+this.getCoordinate().getX()+" Y:"+getCoordinate().getY());
                            switch (getStatusCasella())
                            {
                                case FLAG:
                                {
                                    setGraphic(null);
                                    setText("?");
                                    setStatusCasella(Status.QUESTION_MARKER);
                                } break;
                                case QUESTION_MARKER:
                                {
                                    setText("");
                                    setStatusCasella(Status.VOID);
                                } break;
                                default:
                                {
                                    setText("");
                                    fillImage(this, FLAG_URL);
                                    setStatusCasella(Status.FLAG);
                                } break;
                            }
                        }; break;
                        default: break;
                    }
                });
            setStyle("-fx-background-radius: 0");
            setTextFill(Color.BLACK);
//            double max = Math.max(matriceCampoMinato.length, matriceCampoMinato[0].length);
//            double newSize = size*max<=CampoMinatoApplication.getMaxWidthHeight()?
//                            size: CampoMinatoApplication.getMaxWidthHeight()/max;
//            System.out.println(newSize);
            setMinSize(size, size);
            setFocusTraversable(false);
            setTextAlignment(TextAlignment.CENTER);

        }

        public void fillImage(Casella cas, String url){
            //Image bomb = new Image(getClass().getResourceAsStream("/tommy/resources/images/bomb.jpg"));
            try(InputStream is = getClass().getResourceAsStream(url);)
            {
                ImageView image = new ImageView(new Image(is));
                image.setFitHeight(size);
                image.setFitWidth(size);
                cas.setGraphic(image);
                this.image = image;
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
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

        public double getSize() {
            return size;
        }

        @Override
        public boolean equals(Object o){
             if (this == o) return true;
             if (o == null || getClass() != o.getClass()) return false;
             Casella that = (Casella) o;
             return getCoordinate().equals(((Casella) o).getCoordinate()) && that.getStatus()==getStatus();
         }

        @Override
        public int hashCode() {
            return Objects.hash(status, size, coordinate);
        }

        public Status getStatusCasella() {
            return statusCasella;
        }

        public void setStatusCasella(Status status){
            this.statusCasella = status;
        }
    }

    }


