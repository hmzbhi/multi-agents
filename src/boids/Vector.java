package boids;

/**
 * Cette classe représente un vecteur 2D.
 */
public class Vector {
    private double x;
    private double y;

    /**
     * Constructeur par défaut de la classe Vector.
     * Initialise les coordonnées x et y à 0.
     */
    public Vector(){
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constructeur de la classe Vector.
     * Initialise les coordonnées x et y avec les valeurs spécifiées.
     * 
     * @param x La coordonnée x du vecteur.
     * @param y La coordonnée y du vecteur.
     */
    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Ajoute un vecteur au vecteur courant.
     * 
     * @param vector Le vecteur à ajouter.
     */
    public void add(Vector vector){
        this.x += vector.getX();
        this.y += vector.getY();
    }

    /**
     * Retourne la coordonnée x du vecteur.
     * 
     * @return La coordonnée x du vecteur.
     */
    public double getX(){
        return this.x;
    }

    /**
     * Retourne la coordonnée y du vecteur.
     * 
     * @return La coordonnée y du vecteur.
     */
    public double getY(){
        return this.y;
    }

    /**
     * Multiplie le vecteur par un scalaire.
     * 
     * @param lambda Le scalaire à multiplier.
     */
    public void multScal(double lambda){
        this.x = this.x * lambda;
        this.y = this.y * lambda;
    }

    /**
     * Soustrait un vecteur au vecteur courant.
     * 
     * @param vector Le vecteur à soustraire.
     */
    public void minus(Vector vector){
        this.x -= vector.getX();
        this.y -= vector.getY();
    }

    /**
     * Calcule la norme du vecteur.
     * 
     * @return La norme du vecteur.
     */
    public double norme(){
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * Ajoute une valeur à la coordonnée x du vecteur.
     * 
     * @param lambda La valeur à ajouter.
     */
    public void addX(double lambda){
        this.x += lambda;
    }

    /**
     * Ajoute une valeur à la coordonnée y du vecteur.
     * 
     * @param lambda La valeur à ajouter.
     */
    public void addY(double lambda){
        this.y += lambda;
    }

    /**
     * Calcule l'angle du vecteur par rapport à l'axe x positif.
     * 
     * @return L'angle du vecteur en radians.
     */
    public double getAngle(){
        double angle;
        double x = this.x;
        double y = this.y;
        double norme = this.norme();

        if (y <= 0){
            if (x >= 0){
                angle = Math.acos(x / norme);
            } else {
                angle = Math.PI - Math.acos(-x / norme);
            }
        } else {
            if (x >= 0){
                angle = 2 * Math.PI - Math.acos(x / norme);
            } else {
                angle = Math.PI + Math.acos(-x / norme);
            }
        }
        return angle;
    }
}
