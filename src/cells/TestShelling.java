package cells;

import cells.ShellingSimulator;
import gui.GUISimulator;
import java.awt.*;

/**
 * Cette classe représente un testeur pour le simulateur de cellules.
 */
public class TestShelling {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(800,500, Color.WHITE);
        ShellingSimulator Shelling = new ShellingSimulator(gui,Color.YELLOW,5,3);
    }
}