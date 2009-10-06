package wireworldevolver;
import java.awt.Component;

/**
 * UIModel as in MVC. Acts as an interface between UIs and program
 * logic.
 * @author Jani Rahkola
 * @version 0.1
 */
public class UIModel {
   
   private CircuitBoard board;
   private BoardFileManager fileManager;
   private StateManager stateManager;

    /**
     * Creates UIModel.
     */
    public UIModel() {
        fileManager = new BoardFileManager();
        board = new CircuitBoard(fileManager);
        stateManager = new StateManager(board);
    }

    /**
     * Returns the state of a Cell in row, column.
     * @param row A board row number starting from 0.
     * @param column A board column number starting from 0.
     * @return The state of the Cell in row,column.
     */
    public int getStateIn(int row, int column) {
        return this.board.getCellIn(row, column).getState();
    }

    /**
     * Return the Cell in row, column.
     * @param row A board row number starting from 0.
     * @param column A board column number starting from 0.
     */
    public Cell getCellIn(int row, int column) {
        return this.board.getCellIn(row, column);
    }

    /**
     * Return the number of rows in the board.
     * @return The number of rows in the board.
     */
    public int getRows() {
        return this.board.getRows();
    }

    /**
     * Return the number of columns in the board.
     * @return The number of columns in the board.
     */
    public int getColumns() {
        return this.board.getColumns();
    }

    /**
     * Returns a Boolean based on wheter the board is populated with
     * Cells or not.
     *
     */
    public Boolean boardIsPopulated() {
        return this.board.isPopulated();
    }

    /**
     * 
     */
    public void loadFile(String filePath) throws Exception {
        board.populateFromFile(filePath);
    }

    /**
     * 
     */
    public void updateOnce() {
            stateManager.updateOnce();
    }
    
    /**
     * 
     */
    public void toggleContinuosUpdate() {
            stateManager.toggleContinuosUpdate();
    }

    /**
     * 
     */
    public void setSpeed(int speed) {
        stateManager.setSpeed(speed); 
    }
}
