package wireworldevolver;
import java.util.*;
import java.awt.*;

/**
 * Managesd the updating of CircuitBoard to new generations. Evolves
 * every cell in the CirucuitBoard either generation at a time, or
 * continuosly at a rate set.
 * @author Jani Rahkola
 * @version 0.1
 */
public class StateManager {

    private long updatesPerSecond;
    private Boolean isContinuoslyUpdating;
    private CircuitBoard board;
    private Timer stateTimer;
    private TimerTask task;

    /**
     * Creates a StateManager managing the CircuitBoard given. Continuos
     * updating speed is set to 15 updates per second.
     * @param board The CircuitBoard that is to be managed.
     */
    public StateManager(CircuitBoard board) {
        this.board = board;
        this.updatesPerSecond = 15;
        this.isContinuoslyUpdating = false;
        this.stateTimer = new Timer(true);
    }

    /**
     * Set the continuos updating speed to one given.
     * @param speed New updating speed give as times per second.
     */
    public void setSpeed(int speed) {
        updatesPerSecond = (long)(speed);
        if (isContinuoslyUpdating) {
            this.task.cancel();
            isContinuoslyUpdating = false;
            toggleContinuosUpdate();
        }
    }
    
    /**
     * Updates the board the new generation, evolving all the cells
     * once.
     */
    public void updateOnce() {
        if (!isContinuoslyUpdating) {
            stateTimer.schedule(new Update(), new Date());
        }
    }
    
    /**
     * Stops continuos update. If not continuosly updating, does
     * nothing.
     */
     public void stopContinuosUpdate() {
        if (isContinuoslyUpdating) {
            this.task.cancel();
            isContinuoslyUpdating = false;
        }
     }

    /**
     * Toggles contiuos updating. If not updating continuosly, creates a
     * Timer object to handel the updating in a separate process and
     * starts updating. If updating continuosly, calls
     * the stopContinuosUpdate.
     */
    public void toggleContinuosUpdate() {
        if (isContinuoslyUpdating) {
            stopContinuosUpdate();
        } else {
            long period = 1000 / updatesPerSecond;
            this.task = new Update();
            stateTimer.schedule(this.task, new Date(), period);
            isContinuoslyUpdating = true;
        }
    }

    /**
     * TimerTask that evolves every cell in the board.
     */
    class Update extends TimerTask {
        public void run() {
            for (int row = 0; row < board.getRows(); row++) {
                for (int col = 0; col < board.getColumns(); col++) {
                    board.getCellIn(row, col).evolve();
                }
            }
            for (int row = 0; row < board.getRows(); row++) {
                for (int col = 0; col < board.getColumns(); col++) {
                    board.getCellIn(row, col).checkHood();
                }
            }
        }
    }
}
