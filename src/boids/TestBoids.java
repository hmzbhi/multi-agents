package boids;

import boids.BoidsSimulator;
import gui.GUISimulator;
import java.awt.*;

/**
 * Cette classe représente un test pour le simulateur pour la simulation des boids.Boids.
 * Elle implémente l'interface Simulable.
 */
public class TestBoids {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(800,600, Color.WHITE);
        BoidsSimulator BoidsSimu = new BoidsSimulator(gui,Color.RED,100);
    }
}