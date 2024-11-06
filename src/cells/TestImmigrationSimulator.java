package cells;

import gui.GUISimulator;
import java.awt.*;

/**
 * Cette classe représente un testeur pour le simulateur de cellules.
 */
public class TestImmigrationSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(700,500, Color.WHITE);
        ImmigrationSimulator Immigration = new ImmigrationSimulator(gui,Color.YELLOW,4);
    }
}
