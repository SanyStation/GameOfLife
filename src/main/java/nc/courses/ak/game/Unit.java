package nc.courses.ak.game;

/**
 * The class {@code Unit} represents one cell that have two conditions: alive or
 * dead.
 * 
 * @author Alexander Kovriga
 */
public class Unit {
    
    private boolean alive = false;
    private boolean willAlive = false;
    
    /**
     * Sets value of the cell condition.
     * 
     * @param alive value of the cell condition
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    
    /**
     * Returns value of the cell condition.
     * 
     * @return value of the cell condition
     */
    public boolean isAlive() {
        return alive;
    }
    
    /**
     * Sets value of the cell condition in next generation.
     * 
     * @param willAlive value of the cell condition in next generation
     */
    public void setWillAlive(boolean willAlive) {
        this.willAlive = willAlive;
    }
    
    /**
     * Returns value of the cell condition in next generation.
     * 
     * @return value of the cell condition in next generation
     */
    public boolean willAlive() {
        return willAlive;
    }
}
