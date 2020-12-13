package tommy.apllication.gridandbox;

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
    public String toString(){return "X:"+colonna+"; Y:"+riga;}
}
