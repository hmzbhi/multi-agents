package cells;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;

/**
 * Cette classe représente un simulateur pour la simulation de l'immigration cellulaire.
 * Elle implémente l'interface Simulable.
 */
public class ImmigrationSimulator implements Simulable {
    private CellularImmigration automate;
    protected GUISimulator gui;
    protected Color first_color;
    protected int longsquare;

    /**
     * Construit un objet cells.ImmigrationSimulator.
     * 
     * @param gui Le objet GUISimulator utilisé pour la visualisation.
     * @param first_color La couleur de la première cellule.
     * @param number_state Le nombre d'états possibles pour les cellules.
     */
    public ImmigrationSimulator(GUISimulator gui, Color first_color, int number_state){
        this.gui = gui;
        gui.setSimulable(this);

        this.first_color = first_color;

        this.longsquare = 10;
        this.automate = new CellularImmigration((int)gui.getWidth()/10 - 2,(int) gui.getHeight()/10 - 9,number_state);
    }

    /**
     * Déplace la simulation vers le prochain tour.
     */
    public void next(){
        this.automate.nextTurn();
        draw();
    }

    /**
     * Redémarre la simulation en réinitialisant les cellules.
     */
    public void restart(){
        this.automate.reInit();
        draw();
    }

    /**
     * Convertit les coordonnées d'une cellule en coordonnées sur le plan.
     * 
     * @param i L'indice de la ligne de la cellule.
     * @param j L'indice de la colonne de la cellule.
     * @return Les coordonnées converties sous forme d'un objet Point.
     */
    public Point coordplan(int i,int j){
        return new Point(6+i*longsquare,6+j*longsquare);
    }

    /**
     * Dessine les cellules sur l'interface graphique.
     */
    public void draw(){
        gui.reset();

        for (int i=0; i<automate.sizeLig();i++){
            for (int j=0; j< automate.sizeCol(); j++){
                Point coord = coordplan(i,j);

                int state_k = automate.getCell(i,j).getState();
                Color currentColor = new Color(first_color.getRed(),first_color.getGreen(),first_color.getBlue());
                for (int c=0;c<state_k; c++){
                    currentColor = currentColor.darker();
                }

                gui.addGraphicalElement(new Rectangle(coord.x,coord.y,Color.GRAY,currentColor,longsquare));
            }
        }
    }
}
