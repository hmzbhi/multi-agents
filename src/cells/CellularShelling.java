package cells;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CellularShelling extends CellularImmigration{
    private int seuil;
    private Queue<Point> vacants;

    private ArrayList<Point> vacants_init;


    /**
     * Constructeur de la classe cells.CellularShelling.
     *
     * @param lig Le nombre de lignes de la matrice.
     * @param col Le nombre de colonnes de la matrice.
     * @param number_state Le nombre d'états possibles pour une cellule.
     * @param seuil Le seuil pour la migration des familles.
     */
    public CellularShelling(int lig, int col, int number_state, int seuil) {
        super(lig, col, number_state);
        this.seuil = seuil;

        /*initialisation de la liste des vacants */
        this.vacants = new LinkedList<Point>();
        this.vacants_init = new ArrayList<Point>();

        for (int i = 0; i < lig; i++) {
            for (int j = 0; j < col; j++) {
                if (getCell(i, j).getState() == 0) {
                    vacants.add(new Point(i, j));
                    vacants_init.add(new Point(i, j));
                }
            }
        }
    }

    /**
     * Compte le nombre de voisins meilleurs pour une cellule donnée.
     *
     * @param i L'indice de ligne de la cellule.
     * @param j L'indice de colonne de la cellule.
     * @return Le nombre de voisins meilleurs.
     */
    public int countNeighborBetter(int i,int j){
        int res =0;
        int state_k = getCell(i,j).getState();

        for (int k=-1;k<2;k++){
            for (int l=-1;l<2;l++){
                if (!(k == 0 && l == 0)){
                    int state_neighbor = getCell((i+k+lig)%lig,(j+l+col)%col).getState();
                    if (state_neighbor != state_k && state_neighbor != 0 ){
                        res += 1;
                    }
                }
            }
        }

        return res;
    }

    /**
     * Passe au tour suivant de la simulation.
     */
    public void nextTurn(){
        /* Dans un premier temps, on calcul t+1 dans next */

        for (int i=0;i<lig;i++){
            for (int j=0; j<col; j++){
                int state_k = getCell(i,j).getState();
                if ( state_k != 0) {
                    if (countNeighborBetter(i, j) > seuil) {
                        /* La famille part de chez elle */
                        current_matrix[i][j] = new Cell(0);

                        /* La famille migre vers une autre place vacante */
                        Point coord = vacants.remove();
                        current_matrix[coord.x][coord.y] = new Cell(state_k);

                        /* La famille est parti, donc elle laisse sa place vacante */
                        vacants.add(new Point(i, j));
                    }
                }
            }
        }
    }

    /**
     * Réinitialise la simulation en remettant les cellules à leur état initial.
     */
    public void reInit(){
        for (int i=0; i<lig; i++){
            for (int j=0;j<col;j++){


                int state_k = init_matrix[i][j].getState();
                Cell temp = new Cell(state_k);
                current_matrix[i][j] = temp;
            }
        }

        while (!vacants.isEmpty()){
            vacants.remove();
        }
        for (int i=0;i<vacants_init.size();i++){
            Point temp = new Point(vacants_init.get(i).x,vacants_init.get(i).y);
            vacants.add(temp);
        }

    }
}
