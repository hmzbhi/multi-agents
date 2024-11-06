package boids;

import boids.Bird;
import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.Triangle;

import java.awt.*;

/**
 * Cette classe représente un simulateur pour la simulation des boids.Boids.
 * Elle implémente l'interface Simulable.
 */
public class BoidsSimulator implements Simulable {
    private Boids boids;
    private Color colors_boids;
    private GUISimulator gui;
    private int trans_largeur;
    private int trans_hauteur;

    /**
     * Construit un objet boids.BoidsSimulator.
     * 
     * @param gui Le objet GUISimulator utilisé pour la visualisation.
     * @param color_boids La couleur des boids.
     * @param number_birds Le nombre d'oiseaux dans la simulation.
     */
    public BoidsSimulator(GUISimulator gui, Color color_boids, int number_birds){
        this.gui = gui;
        gui.setSimulable(this);

        this.colors_boids = color_boids;
        this.trans_largeur = (2*this.gui.getWidth())/4;
        this.trans_hauteur = (2*this.gui.getHeight())/5;


        this.boids = new Boids(number_birds,gui.getWidth()/2,gui.getWidth()/2);
    }

    /**
     * Déplace la simulation vers le prochain tour.
     */
    public void next(){
        this.boids.nextTurn();
        draw();
    }

    /**
     * Redémarre la simulation en réinitialisant les boids.
     */
    public void restart(){
        this.boids.reInit();
        draw();
    }

    /**
     * Convertit les coordonnées d'un oiseau en coordonnées sur le plan.
     * 
     * @param bird L'objet oiseau.
     * @return Les coordonnées converties sous forme d'un objet Point.
     */
    private Point coordplan(Bird bird){
        return new Point((int)( trans_largeur/2+ bird.getPosition().getX()),(int)( trans_hauteur/4.5+ bird.getPosition().getY()));
    }

    /**
     * Dessine les boids sur l'interface graphique.
     */
    private void draw(){
        gui.reset();

        gui.addGraphicalElement(new Rectangle(trans_largeur,trans_hauteur,Color.BLACK,Color.WHITE,gui.getWidth()/2));
    
        for (int i=0; i<boids.getSize();i++){
            Bird temp = boids.get(i);
            Point coord = coordplan(temp);
            double angle;
            double vx =temp.getVitesse().getX();
            double vy =temp.getVitesse().getY();
            double norme = temp.getVitesse().norme();

            if (vy <= 0){
                if (vx >= 0){
                    angle = Math.acos(vx/norme);
                } else {
                    angle = Math.PI - Math.acos(-vx/norme);
                }
            } else {
                if (vx >= 0){
                    angle = 2*Math.PI - Math.acos(vx/norme);
                } else {
                    angle = Math.PI + Math.acos(-vx/norme);
                }
            }
            gui.addGraphicalElement( new Triangle(coord.x,coord.y,Color.BLACK,colors_boids,8,6,angle));
        }
    }
}
