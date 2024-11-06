package balls;

import balls.BallsSimulator;
import gui.GUISimulator;
import java.awt.*;

/**
 * Cette classe représente un testeur pour le simulateur de balles.
 */
public class TestBallsSimulator {
    public static void main(String[] args) {
        int longueur = 600;
        int hauteur = 600;
        GUISimulator gui = new GUISimulator(600,600, Color.BLACK);
        BallsSimulator ballsSimu = new BallsSimulator(gui,Color.YELLOW,longueur,hauteur);
    }
}
