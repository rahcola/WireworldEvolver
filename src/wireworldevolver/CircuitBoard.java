package wireworldevolver;
/**
 * Representation of the board that <a href="Cell.html">Cells</a>
 * live in based on given BoardFileManager. Is used to select cells,
 * and gives cells the knowledge of their neighbouring cells.
 * Interacts with BoardFileManager to populate the board from file.
 * @author Jani Rahkola
 * @version 0.1
 */
public class CircuitBoard {
    
    private Cell[][] board;
    private int rows;
    private int columns;
    private BoardFileManager file;
    private Boolean isPopulated;
    /**
     * Creates a board that is based on <code>file</code>
     * @param file A BoardFileManager representing the file that the
     *             board is based on.
     */
    public CircuitBoard(BoardFileManager file) {
        this.file = file;
        this.isPopulated = false;
    }
    /**
     * Returns the Cell in row, col.
     * @param row A row number of this board, starting from 0.
     * @param col A column number of this board, starting from 0.
     * @return The Cell in row, col position in board or null if Cell
     *         does not exist.
     */
    public Cell getCellIn(int row, int col) {
        if ((row >= 0 && row <= this.rows)
             && (col >= 0 && col <= this.columns)) {
            return board[row+1][col+1];
        } else {
            return null;
        }
    }
    /**
     * Returns the number of rows in this board.
     * @return The number of rows in this board.
     */
    public int getRows() {
        return rows;
    }
    /**
     * Returns the number of columns in this board.
     * @return The number of columns in this board.
     */
    public int getColumns() {
        return columns;
    }
    /**
     * Tells wheter this board is already populated with Cells or not.
     * @return A Boolean telling if this board is populated or not.
     */
    public Boolean isPopulated() {
        return isPopulated;
    }
    /**
     * Initializes the internal board and populates it with Cells based
     * on a file denoted by filePath.
     * @param filePath Full path to the file that this board will be
     *                 based on.
     * @throws
     */
    public void populateFromFile(String filePath) throws Exception {
        file.setFilePath(filePath);
        int[][] intboard = file.getBoard();
        this.rows = intboard.length;
        this.columns = intboard[0].length;
        this.board = new Cell[rows+2][columns+2];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                this.board[row+1][col+1] = new Cell(intboard[row][col]);
            }
        }
        setHoods();
        this.isPopulated = true;
   }
   /**
    * Sets the neighbourhood of every Cell in this board.
    */
    private void setHoods() {
        for (int row = 1; row <= rows; row++) {
            for (int col =1; col <= columns; col++) {
                Cell[] hood = {board[row-1][col-1], board[row-1][col],
                               board[row-1][col+1], board[row][col-1],
                               board[row][col+1], board[row+1][col-1],
                               board[row+1][col], board[row+1][col+1]};
                board[row][col].setHood(hood);
                board[row][col].checkHood();
            }
        }
   }
}
