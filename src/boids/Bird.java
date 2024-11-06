package boids;

import java.util.ArrayList;

/**
 * La classe boids.Bird représente un boids.Bird.
 */
public class Bird {
    // Paramètres de la simulation :
    private int hauteur;
    private int largeur;

    // Paramètres de vision des Birds :
    private double vision_distance = 50;
    private  double vision_angle = (5.0/6.0)*Math.PI;

    // Variables de déplacement des Birds :
    private Vector position;
    private Vector vitesse;
    private double maxv;

    // Paramètres des forces :
    private double coef_a = 0.5;
    private double coef_c = 0.72;
    private double dist_s = 25;
    private double coef_s = 0.50;
    private double coef_r = 10;

    public Bird(Vector position, Vector vitesse, int hauteur, int largeur, double maxv){
        /**
         * @param position est la position du boids.Bird "this".
         * @param vitesse est la vitesse du boids.Bird "this".
         * @param hauteur est la hauteur de la fenêtre de simulation.
         * @param largeur est la largeur de la fenêtre de simulation.
         * @param maxv est la vitesse maximale du boids.Bird "this" en norme.
         * @return Construit un objet de type boids.Bird.
         */
        this.position = position;
        this.vitesse = vitesse;
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.maxv = maxv;
    }

    public Vector getPosition(){
        /**
         * @return la position du boids.Bird "this".
         */
        return this.position;
    }

    public Vector getVitesse(){
        /**
         * @return la vitesse du boids.Bird "this".
         */
        return this.vitesse;
    }

    public void uptadeVitesse(Boids boids){
        /**
         * @param boids est l'ensemble des Birds de la simulation.
         * @return met à jour la vitesse du boids.Bird "this" en fonction des forces dépendantes
         * de la position des autres Birds.
         */
        Vector temp_sep = separation(boids);
        Vector temp_align = alignement(boids);
        Vector temp_coh = cohesion(boids);
        Vector temp_bp = boundPosition(boids);

        vitesse.add(temp_coh);
        vitesse.add(temp_sep);
        vitesse.add(temp_align);
        vitesse.add(temp_bp);

        this.limitVitesse();
    }

    public void updatePosition(){
        /**
         * @return met à jour la position du boids.Bird "this" en fonction de sa vitesse.
         */
        position.add(vitesse);

        //this.rebondBord();
    }

    public Vector cohesion(Boids boids){
        /**
         * @param boids est l'ensemble des Birds de la simulation.
         * @return la force cohésion du boids.Bird "this" en fonction de la position des autres Birds.
         */
        ArrayList<Bird> voisinage = boids.getVoisinage(this);
        int nbVoisins = voisinage.size()-1;
        System.out.println((nbVoisins));
        Vector cohesion = new Vector();
        if (nbVoisins > 0){
            for (int i = 0; i < nbVoisins; i++) {
                System.out.println(("before"));
                if (voisinage.get(i)!=this) {
                    System.out.println(("dnas le if"));
                    Vector pos_temp = new Vector(voisinage.get(i).getPosition().getX(), voisinage.get(i).getPosition().getY());
                    cohesion.add(pos_temp);
                }
            }
            cohesion.multScal( (double) 1.0 / (nbVoisins));

            cohesion.minus(position);
            cohesion.multScal(coef_c);
        }
        return cohesion;
    }

    public Vector alignement(Boids boids){
        /**
         * @param boids est l'ensemble des Birds de la simulation.
         * @return la force alignement du boids.Bird "this" en fonction de la vitesse des autres Birds.
         */
        ArrayList<Bird> voisinage = boids.getVoisinage(this);
        int nbVoisins = voisinage.size()-1;
        Vector align = new Vector();
        if (nbVoisins != 0) {
            for (int i = 0; i < nbVoisins; i++) {
                if (voisinage.get(i)!=this) {
                    Vector vit_temp = new Vector(voisinage.get(i).getVitesse().getX(), voisinage.get(i).getVitesse().getY());
                    align.add(vit_temp);
                }
            }
            align.multScal(1 / (nbVoisins));

            //align.add(this.getVitesse());

            align.add(this.vitesse);
            align.multScal(coef_a);
        }
        return align;
    }

    public Vector separation(Boids boids){
        /**
         * @param boids est l'ensemble des Birds de la simulation.
         * @return la force séparation du boids.Bird "this" en fonction de la position des autres Birds.
         */
        ArrayList<Bird> voisinage = boids.getVoisinage(this);
        int nbVoisins = voisinage.size()-1;
        Vector sep = new Vector();

        for (int i=0; i < nbVoisins; i++){
            if (voisinage.get(i)!=this) {
                Vector pos_temp = new Vector(voisinage.get(i).getPosition().getX(), voisinage.get(i).getPosition().getY());
                pos_temp.minus(position);
                if (pos_temp.norme() < dist_s) {
                    sep.minus(pos_temp);
                    sep.multScal(coef_s);
                }
            }
        }

        return sep;
    }

    public Vector boundPosition(Boids boids){
        /**
         * @param boids est l'ensemble des Birds de la simulation.
         * @return la force boundPosition du boids.Bird "this" en fonction de sa position.
         */
        Vector bP = new Vector();

        if (position.getX() < 0){
            bP.add(new Vector(coef_r,0));
        } else if (position.getX() > largeur){
            bP.add(new Vector(-coef_r,0));
        }
        if (position.getY() < 0){
            bP.add(new Vector(0,coef_r));
        } else if (position.getY() > hauteur){
            bP.add(new Vector(0,-coef_r));
        }

        return bP;
    }

    public void limitVitesse(){
        /**
         * @return limite la vitesse du boids.Bird "this" à maxv.
         */
        if (vitesse.norme() > maxv){
            vitesse.multScal(1/vitesse.norme());
            vitesse.multScal(maxv);
        }
    }

    public boolean isNeighbour(Bird voisin){
        /**
         * @param voisin est le boids.Bird dont on cherche à savoir s'il est dans le voisinage du boids.Bird "this".
         * @return true si le boids.Bird "voisin" est dans le voisinage du boids.Bird "this".
         */
        Vector pos_temp = new Vector(voisin.getPosition().getX(),voisin.getPosition().getY());
        Vector vit_temp = new Vector(voisin.getVitesse().getX(),voisin.getVitesse().getY());
        pos_temp.minus(this.getPosition());

        if (pos_temp.norme() <= vision_distance){
            double angleBird = this.getVitesse().getAngle();
            double angleVoisin = vit_temp.getAngle();
            if (angleVoisin < angleBird && angleBird - angleVoisin <= vision_angle){
                return true;
            } else if ( angleVoisin > angleBird && angleVoisin - angleBird <= vision_angle) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
