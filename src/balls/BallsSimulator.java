package balls;

import java.awt.*;
import gui.*;
import gui.Rectangle;

/**
 * La classe balls.BallsSimulator implémente l'interface Simulable, cela permettra ensuite d'utiliser
 * balls.BallsSimulator est un objet simulable (et donc représentable sur l'interface graphique) par GUISimulator.
 *
 */
public class BallsSimulator  implements Simulable {
    private Balls ballList;

    private GUISimulator gui;

    private Color color_balls;
    private int longueur;
    private int hauteur;

    //Liste des constructeurs :
    public BallsSimulator(GUISimulator gui,Color color_balls,int longueur, int hauteur){
        /**
         * @param gui est le GUISimulator sur lequel l'objet "Simulable" sera représenté.
         * @param color_balls est la couleur des balles sur l'interface
         * @param longueur est la longueur de la fenêtre de simulation.
         * @param hauteur est la hauteur de la fenêtre de simulation.
         * @return Construit un objet de type balls.BallsSimulator.
         * */
        this.ballList = new Balls();
        this.longueur = longueur;
        this.hauteur = hauteur;
        this.gui = gui;
        gui.setSimulable(this);
        this.color_balls = color_balls;

        ballList.addBall(new Point(0,100));
        ballList.addBall(new Point(100,100));
        ballList.addBall(new Point(200,100));

    }

    public void next() {
        /**
         * @return Fait avancer d'un pas la simulation.
         */
        if (this.ballList.get(0).x >= longueur || this.ballList.get(0).x + 5 <= 0){
            this.ballList.setBalls_speed_x(-this.ballList.getBalls_speed_x());
        } else if (this.ballList.get(0).y  >= hauteur || this.ballList.get(0).y +5 <= 0){
            this.ballList.setBalls_speed_y(-this.ballList.getBalls_speed_y());
        }
        this.ballList.translate(ballList.getBalls_speed_x(), ballList.getBalls_speed_y());
        draw();
    }

    public void restart() {
        /**
         * @return Remet la simulation à son état initial.
         */
        this.ballList.reInit();
        draw();
    }

    private void draw(){
        /**
         * @return Dessine la simulation sur l'interface graphique.
         */
        gui.reset();

        //gui.addGraphicalElement(new Rectangle(0,100,Color.WHITE, Color.BLACK,1000,1000));
        for (int i=0; i<ballList.getSize();i++) {
            Point temp = ballList.get(i);
            gui.addGraphicalElement(new Oval(temp.x+25,temp.y,Color.WHITE,this.color_balls,10,10 ));
        }
    }
}
