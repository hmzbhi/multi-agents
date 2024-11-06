package balls;

import java.awt.*;

public class TestBalls {
    public static void main(String[] args){

        Balls ballList = new Balls();

        Point ball1 = new Point(1,2);
        Point ball2 = new Point(3,4);
        Point ball3 = new Point(5,6);

        ballList.addBall(ball1);
        ballList.addBall(ball2);
        ballList.addBall(ball3);

        System.out.print(ballList.toString() + "\n");

        ballList.translate(1,3);
        System.out.print(ballList.toString() + "\n");

        ballList.translate(0,2);
        System.out.print(ballList.toString() + "\n");

        ballList.reInit();
        System.out.print(ballList.toString() + "\n");
    }
}