package tommy.apllication.gridandbox;

public class MatriceCampoMinato {
    private Casella[][] matriceCampoMinato;
    private int bombs;

    public MatriceCampoMinato(int x, int y, int bombs){
        matriceCampoMinato = new Casella[x][y];
        this.bombs=bombs;
    }

    public boolean scopriCasella(int x, int y){
        //TODO
        return false;
    }

    public void setCasella(int x, int y, Casella casella){
        matriceCampoMinato[x][y] = casella;
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
}
