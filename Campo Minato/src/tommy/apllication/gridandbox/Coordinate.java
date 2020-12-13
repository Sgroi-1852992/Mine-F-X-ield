package tommy.apllication.gridandbox;

public class Coordinate {
    private int riga;
    private int colonna;

    public Coordinate(int riga, int colonna){
        this.riga=riga;
        this.colonna=colonna;
    }


    public int getX() {
        return riga;
    }

    public void setX(int riga) {
        this.riga = riga;
    }

    public int getY() {
        return colonna;
    }

    public void setY(int y) {
        this.colonna = colonna;
    }

    @Override
    public String toString(){return "X:"+riga+"; Y:"+colonna;}
}
