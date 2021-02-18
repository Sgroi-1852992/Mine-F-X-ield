package it.tommy.mine.fxield.application.gridandbox;

import java.util.Objects;

public class Coordinate {
    private int riga;
    private int colonna;

    public Coordinate(int riga, int colonna){
        this.riga=riga;
        this.colonna=colonna;
    }


    public int getX() {
        return colonna;
    }

    public void setX(int colonna) {
        this.colonna = colonna;
    }

    public int getY() {
        return riga;
    }

    public void setY(int riga) {
        this.riga = riga;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return riga == that.riga && colonna == that.colonna;
    }

    @Override
    public int hashCode() {
        return Objects.hash(riga, colonna);
    }

    @Override
    public String toString(){return "X:"+colonna+"; Y:"+riga;}
}
