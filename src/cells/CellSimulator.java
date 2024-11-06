package cells;

import java.awt.*;
import gui.*;
import gui.Rectangle;

/**
 * Cette classe représente un simulateur pour la simulation de l'automate cellulaire.
 * Elle implémente l'interface Simulable.
 */
public class CellSimulator implements Simulable {
    private CellularAutomate automate;

    protected GUISimulator gui;
    protected Color color_alive;

    protected int longsquare;

    /**
     * Construit un objet cells.CellSimulator.
     *
     * @param gui L'objet GUISimulator utilisé pour la visualisation.
     * @param color_alive La couleur des cellules vivantes.
     */
    public CellSimulator(GUISimulator gui, Color color_alive){
        this.gui = gui;
        gui.setSimulable(this);

        this.color_alive = color_alive;

        this.longsquare = 10;
        this.automate = new CellularAutomate((int)gui.getWidth()/10 - 2,(int) gui.getHeight()/10 - 9,2);
    }

    /**
     * Déplace la simulation vers le prochain tour.
     */
    public void next(){
        this.automate.nextTurn();
        draw();
    }

    /**
     * Redémarre la simulation en réinitialisant l'automate.
     */
    public void restart(){
        this.automate.reInit();
        draw();
    }

    /**
     * Convertit les coordonnées d'une cellule en coordonnées sur le plan.
     *
     * @param i L'indice de ligne de la cellule.
     * @param j L'indice de colonne de la cellule.
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
                if (automate.getCell(i,j).getState() == 1){
                    /* Cellule vivante ! */
                    gui.addGraphicalElement(new Rectangle(coord.x,coord.y,Color.GRAY,this.color_alive,longsquare));
                } else {
                    /* Cellule morte */
                    gui.addGraphicalElement(new Rectangle(coord.x,coord.y,Color.GRAY,Color.WHITE,longsquare));
                }
            }
        }
    }
}
