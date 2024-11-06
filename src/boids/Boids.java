package boids;

import boids.Bird;

import java.util.ArrayList;

/**
 * La classe boids.Boids représente un ensemble de Birds.
 */
public class Boids {
    // Paramètres de la simulation :
    private int largeur;
    private int hauteur;

    // Variables de déplacement des Birds :
    private int nbBoids;
    private ArrayList<Bird> birds;
    private ArrayList<Bird> birdsInitial;

    // Paramètre de vitesse :
    private double maxv;

    /**
          * @param number_birds est le nombre de Birds dans l'objet boids.Boids.
     * @param largeur est la largeur de la fenêtre de simulation.
     * @param hauteur est la hauteur de la fenêtre de simulation.
     */
    public Boids(int number_birds, int largeur, int hauteur){
        
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.nbBoids = number_birds;
        this.maxv = 7;
        this.birds = new ArrayList<Bird>();
        this.birdsInitial = new ArrayList<Bird>();

        for (int i=0; i < nbBoids; i++){
            Vector position = new Vector(Math.random()*largeur,Math.random()*hauteur);
            Vector vitesse = new Vector(Math.random()*maxv - maxv/2,Math.random()*maxv - maxv/2);

            birds.add(new Bird(position, vitesse, hauteur, largeur, maxv));
            birdsInitial.add(new Bird(new Vector(position.getX(), position.getY()), new Vector(vitesse.getX(), vitesse.getY()), hauteur, largeur, maxv));
        }

    }

    /**
          * @param bird est le boids.Bird dont on cherche le voisinage.
     * @return la liste des Birds voisins du boids.Bird "bird".
     */
    public ArrayList<Bird> getVoisinage(Bird bird){
        
        ArrayList<Bird> voisinage = new ArrayList<Bird>();

        for (int i=0; i < nbBoids; i++){
            if (bird.isNeighbour(birds.get(i))){
                voisinage.add(birds.get(i));
            }
        }
        return voisinage;
    }

    /**
     * Fait avancer d'un pas la simulation.
     */
    public void nextTurn(){
        for (int i=0; i < nbBoids; i++){
            birds.get(i).uptadeVitesse(this);
            birds.get(i).updatePosition();
        }

    }

    /**
     * Remet la simulation à son état initial.
     */
    public void reInit(){
        for (int i=0; i < nbBoids; i++){
            birds.set(i, new Bird(new Vector(birdsInitial.get(i).getPosition().getX(), birdsInitial.get(i).getPosition().getY()),
                                    new Vector(birdsInitial.get(i).getVitesse().getX(), birdsInitial.get(i).getVitesse().getY()),
                                    hauteur, largeur, maxv));

        }
    }

    /**
     * Retourne le nombre de Birds dans l'objet boids.Boids.
     * 
     * @return le nombre de Birds dans l'objet boids.Boids.
     */
    public int getSize(){
        return birds.size();
    }

    /**
     * Retourne le boids.Bird d'indice i dans l'objet boids.Boids.
     * 
     * @param i est l'indice du boids.Bird que l'on souhaite récupérer.
     * @return le boids.Bird d'indice i dans l'objet boids.Boids.
     */
    public Bird get(int i){
        return birds.get(i);
    }
}