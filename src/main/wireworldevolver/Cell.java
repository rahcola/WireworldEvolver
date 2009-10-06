package wireworldevolver;
import java.util.Observable;
/**
 * Represents a single Wireworld cell. Knows about it's neighbours and
 * if it's state will change when it evolves. Also an observable, so
 * observers will be notified if state has changed.
 * @author Jani Rahkola
 * @version 0.1
 */
public class Cell extends Observable{
    private int state;
    private Cell[] neighbourhood;
    private Boolean willEvolve;

    /**
     * Creates a cell, and sets the state to one given.
     * @param state The state of the newly created cell.
     */
    public Cell(int state) {
        super();
        if (state == 0 || state == 1 || state == 2 || state ==3) {
            this.state = state;
        } else {
            this.state = 0;
        }
        willEvolve = true;
    }
    /**
     * Returns the state of the cell.
     * @return The state of the cell, 0, 1, 2 or 3.
     */
    public int getState() {
        return this.state;
    }
    /**
     * Sets the neighbourhood of the cell.
     * @param neighbourhood The neighbouring cells listed from
     *                      left to right and top to bottom.
     */
    public void setHood(Cell[] neighbourhood) {
        this.neighbourhood = neighbourhood;
    }
    /**
     * Evolves the cell to next generation.
     */
    public void evolve() {
        if (state == 0) {
            state = 0;
        } else if (state == 1) {
            state = 2;
            this.stateChanged();
        } else if (state == 2) {
            state = 3;
            this.stateChanged();
        } else if (state == 3) {
            if (willEvolve) {
                state = 1;
                this.stateChanged();
            } else {
                state = 3;
            }
        }
    }
    /**
     * Notifies registered observers about a change in state.
     */
    public void stateChanged() {
        this.setChanged();
        this.notifyObservers();
    }
    /**
     * Checks if the neighbourhood allows conductor (state == 3) to
     * evolve.
     */
    public void checkHood() {
        int heads = 0;
        for (Cell i : this.neighbourhood) {
            if (i != null && i.getState() == 1) {
                    heads++;
            }
        }
        this.willEvolve = (heads == 1 || heads == 2);
    }
}
