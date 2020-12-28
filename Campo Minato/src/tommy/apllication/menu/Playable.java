package tommy.apllication.menu;

import tommy.apllication.campominato.CampoMinatoApplication;

public interface Playable {
    static void startGame(CampoMinatoApplication app, int difficulty){
        CampoMinatoApplication.setBombs(difficulty);
        app.initGame();
    }
}
