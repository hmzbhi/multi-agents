package cells;

/**
 * La classe CellularAutomate représente un automate cellulaire.
 */
public class CellularAutomate {
    protected Cell[][] current_matrix;

    protected Cell[][] next_matrix;

    protected Cell[][] init_matrix;

    protected int lig;

    protected int col;

    protected int number_states;

    /**
     * Construit un objet CellularAutomate.
     * 
     * @param lig Le nombre de lignes de la matrice.
     * @param col Le nombre de colonnes de la matrice.
     * @param number_state Le nombre d'états possibles pour chaque cellule.
     */
    public CellularAutomate(int lig, int col, int number_state){
        this.current_matrix = new Cell[lig][col];
        this.init_matrix = new Cell[lig][col];
        this.next_matrix = new Cell[lig][col];
        this.number_states = number_state;

        this.lig = lig;
        this.col = col;

        for (int i=0; i<lig; i++){
            for (int j=0; j<col; j++){

                this.current_matrix[i][j] = new Cell((int)(Math.random()*number_state));

                int state_k = getCell(i,j).getState();
                Cell temp = new Cell(state_k);
                init_matrix[i][j] = temp;
            }
        }
    }

    /**
     * Renvoie le nombre de lignes de la matrice.
     * 
     * @return Le nombre de lignes.
     */
    public int sizeLig(){
        return this.lig;
    }

    /**
     * Renvoie le nombre de colonnes de la matrice.
     * 
     * @return Le nombre de colonnes.
     */
    public int sizeCol(){
        return this.col;
    }

    /**
     * Renvoie la cellule à la position (i, j) dans la matrice courante.
     * 
     * @param i L'indice de ligne.
     * @param j L'indice de colonne.
     * @return La cellule à la position (i, j).
     */
    public Cell getCell(int i, int j){
        return this.current_matrix[i][j];
    }

    /**
     * Compte le nombre de voisins vivants d'une cellule à la position (i, j).
     * 
     * @param i L'indice de ligne.
     * @param j L'indice de colonne.
     * @return Le nombre de voisins vivants.
     */
    public int countNeighborAlive(int i, int j){
        int res = 0;

        for (int k=-1; k<2; k++){
            for (int l=-1; l<2; l++){
                if (!(k == 0 && l == 0)){
                    if (getCell((i+k+lig)%lig, (j+l+col)%col).getState() == 1){
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
        for (int i=0; i<lig; i++){
            for (int j=0; j<col; j++){
                if (getCell(i,j).getState() == 0){
                    if (countNeighborAlive(i,j) == 3){
                        next_matrix[i][j] = new Cell(1);
                    } else {
                        next_matrix[i][j] = new Cell(0);
                    }
                } else {
                    if (countNeighborAlive(i,j) == 3 || countNeighborAlive(i,j) == 2){
                        next_matrix[i][j] = new Cell(1);
                    } else {
                        next_matrix[i][j] = new Cell(0);
                    }
                }
            }
        }

        for (int i=0; i<lig; i++){
            for (int j=0; j<col; j++){
                if (next_matrix[i][j].getState() == 1){
                    Cell temp = new Cell(1);
                    current_matrix[i][j] = temp;
                } else {
                    Cell temp = new Cell(0);
                    current_matrix[i][j] = temp;
                }
            }
        }
    }

    /**
     * Réinitialise la matrice courante avec la matrice initiale.
     */
    public void reInit(){
        for (int i=0; i<lig; i++){
            for (int j=0; j<col; j++){
                int state_k = init_matrix[i][j].getState();
                Cell temp = new Cell(state_k);
                current_matrix[i][j] = temp;
            }
        }
    }
}
