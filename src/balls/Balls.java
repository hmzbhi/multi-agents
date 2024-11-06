package balls;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe balls.Balls représente un ensemble de balles.
 * Elle permet de translater et repositionner à l'initial un ensemble de balles.
 */
class Balls {
    private java.util.List<Point> ballList;
    private List<Point> init_BallList;
    private int balls_speed_x;
    private int balls_speed_y;

    private int size;

    //Liste des constructeurs :
    public Balls(){
        /**
         * @return Construit un objet de type balls.Balls où les listes sont vides.
         */
        this.ballList = new ArrayList<>();
        this.init_BallList = new ArrayList<>();
        this.balls_speed_x = 10;
        this.balls_speed_y = 10;
        this.size = 0;
    }

    public void addBall(Point ball) {
        /**
         * @param ball est la balle qui sera ajoutée à l'objet balls.Balls sur lequel la méthode est utilisée.
         * @return Ajoute une ball à l'objet "this" de type balls.Balls.
         */
        this.ballList.add(ball);

        Point temp = new Point((int)ball.getX(),(int)ball.getY());//On viens faire une copie en profondeur pour indépendantiser ballList et init_BallList
        this.init_BallList.add(temp);

        this.size +=1;
    }

    //Liste des accesseurs/modificateurs :
    public int getSize() {
        /**
         * @return le nombre de balles dans le balls.Balls "this".
         */
        return this.size;
    }

    public int getBalls_speed_x() {
        /**
         * @return la vitesse selon l'axe des x des balles dans l'objet balls.Balls.
         */
        return balls_speed_x;
    }

    public int getBalls_speed_y() {
        /**
         * @return la vitesse selon l'axe des y des balles dans l'objet balls.Balls.
         */
        return balls_speed_y;
    }

    public void setBalls_speed_x(int balls_speed_x) {
        /**
         * @param balls_speed_x est l'entier qui sera la nouvelle vitesse selon l'axe des x des balles dans l'objet balls.Balls.
         * @return modifie la vitesse selon l'axe des x des balles dans l'objet balls.Balls.
         */
        this.balls_speed_x = balls_speed_x;
    }

    public void setBalls_speed_y(int balls_speed_y) {
        /**
         * @param balls_speed_y est l'entier qui sera la nouvelle vitesse selon l'axe des y des balles dans l'objet balls.Balls.
         * @return modifie la vitesse selon l'axe des y des balles dans l'objet balls.Balls.
         */
        this.balls_speed_y = balls_speed_y;
    }

    public Point get(int i) {
        /**
         * @param i représente l'indice du point que l'on veut renvoyer.
         * @throws renvoi une erreur si l'indice dépasse la taille de la liste de Points dans balls.Balls.
         * @return Le Point se trouvant à la ième position dans la liste de points de l'objet balls.Balls sur lequel la méthode est utilisée.
         */

        if(i >= this.size){
            throw new IllegalArgumentException("L'indice dépasse la taille de la liste.");
        }
        else{
        return ballList.get(i);
        }
    }

    //Liste des méthodes spécifiques :
    public void translate(int dx, int dy) {
        /**
         * @param dx est l'entier correspondant au déplacement selon l'axe des x appliqué aux balles dans l'objet de type balls.Balls "this".
         * @param dy est l'entier correspondant au déplacement selon l'axe des y appliqué aux balles dans l'objet de type balls.Balls "this".
         * @return déplace toutes les balles dans l'objet de type balls.Balls "this" selon les valeurs convenues en paramètre.
         */
        for (int i=0; i< ballList.size(); i++) {
            Point translated = new Point(0,0);
            translated.x = ballList.get(i).x;
            translated.y = ballList.get(i).y;
            translated.translate(dx,dy);

            ballList.set(i,translated);//On utilise une méthode de la classe Point pour changer le ième point de la liste en le nouveau point construit plus tôt.
        }
    }

    public void reInit() {
        /**
         * @return Replace toutes les balles contenues dans l'objet de type balls.Balls "this", à leurs position initiale.
         */
        for (int i=0; i< ballList.size(); i++) {
            ballList.set(i,init_BallList.get(i));
        }
    }

    public String toString() {
        /**
         * @return une chaîne de caractère représentant l'objet de type balls.Balls "this".
         */
        StringBuilder res  = new StringBuilder("[");

        int size = ballList.size();
        for (int i=0; i < size-1;i++){
            res.append("(").append(ballList.get(i).getX()).append(",").append(ballList.get(i).getY()).append(")").append(";");
        }

        res.append("(").append(ballList.get(size - 1).getX()).append(",").append(ballList.get(size - 1).getY()).append(")").append("]");
        return res.toString();
    }
}