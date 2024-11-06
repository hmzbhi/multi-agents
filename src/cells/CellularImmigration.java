package cells;

/**
 * Cette classe représente une automatisation cellulaire pour l'immigration cellulaire.
 * Elle hérite de la classe CellularAutomate.
 */
public class CellularImmigration extends CellularAutomate{

    /**
     * Construit un objet CellularImmigration.
     * 
     * @param lig Le nombre de lignes de la grille.
     * @param col Le nombre de colonnes de la grille.
     * @param number_state Le nombre d'états possibles pour chaque cellule.
     */
    public CellularImmigration(int lig,int col,int number_state){
        super(lig, col,number_state);
    }

    /**
     * Compte le nombre de voisins ayant un état supérieur à l'état de la cellule à la position (i, j).
     * 
     * @param i La ligne de la cellule.
     * @param j La colonne de la cellule.
     * @return Le nombre de voisins ayant un état supérieur.
     */
    public int countNeighborBetter(int i,int j){
        int res =0;
        int state_k = getCell(i,j).getState();

        for (int k=-1;k<2;k++){
            for (int l=-1;l<2;l++){
                if (!(k == 0 && l == 0)){
                    if (getCell((i+k+lig)%lig,(j+l+col)%col).getState() == (state_k+1)%number_states){
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
                int stake_k = getCell(i,j).getState();
                if (countNeighborBetter(i,j) >=3){
                    next_matrix[i][j] = new Cell((stake_k+1)%number_states);
                } else {
                    next_matrix[i][j] = new Cell(stake_k);
                }
            }
        }
        /* On met à jour current dans next */
        for (int i=0;i<lig;i++){
            for (int j=0; j<col; j++){
                int stake_k = next_matrix[i][j].getState();
                Cell temp = new Cell(stake_k);
                current_matrix[i][j] = temp;
            }
        }
    }
}

