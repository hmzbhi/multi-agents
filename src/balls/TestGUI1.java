package balls;

import java.awt.Color;
import gui.GUISimulator;
import gui.Rectangle;
import gui.Triangle;


/**
 * Cette classe représente un testeur pour le simulateur de balles.
 */
public class TestGUI1 {
    public static void main(String[] args) {
        GUISimulator window = new GUISimulator(1000,1200,Color.WHITE);
        window.addGraphicalElement(new Rectangle((2*window.getWidth())/4,(2*window.getHeight())/5,Color.BLACK,Color.WHITE,window.getWidth()/2));
        for (int i=0; i<850;i+=10) {
            window.addGraphicalElement(new Rectangle(6+i,6,Color.GRAY,Color.CYAN,10));
            window.addGraphicalElement(new Triangle(400,400,Color.BLACK,Color.CYAN,8,6, Math.PI/2));
        }
    }
}
