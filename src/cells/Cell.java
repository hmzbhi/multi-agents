package cells;

/**
 * La classe Cell représente une cellule avec un état.
 */
public class Cell {
    private int state;

    /**
     * Construit un objet Cell avec l'état spécifié.
     * 
     * @param state l'état de la cellule
     */
    public Cell(int state){
        this.state = state;
    }

    /**
     * Renvoie l'état de la cellule.
     * 
     * @return the state of the cell
     */
    public int getState(){
        return this.state;
    }

    /**
     * Remplace l'état de la cellule par celui spécifié.
     * 
     * @return a string representation of the cell
     */
    public String toString(){
        return " La cellule se trouve dans l'état " + getState();
    }
}
