package wireworldevolver;
import java.util.*;
import java.io.*;
/**
 * Represents the file containing Wireworld board in the program logic.
 * Creates integer matrix representation of the file to those who need
 * it. Keeps the file open only for the duration of reading.
 * @author Jani Rahkola
 * @version 0.1
 */
public class BoardFileManager {

    private String filePath;
    private FileReader reader;

    /**
     * Constructor doing nothing.
     */
    public BoardFileManager() {
    }

    /**
     * Opens the file this BoardFileManager represents.
     * @throws FileNotFoundException
     */
    private void openFile() throws FileNotFoundException {
        reader = new FileReader(filePath);
    }

    /**
     * Closes the file this BoardFileManager represents.
     * @throws IOException
     */
    private void closeFile() throws IOException {
        reader.close();
    }

    /**
     * Sets what file this BoardFileManager represents.
     * @param filePath Absolute path to the file.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the path to the file this BoardFileManager represents.
     * @return Absolute path to the file.
     */
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * Reads one line from the file. Parses illegal characters to zeros.
     * @return Legal characters in a Integer Vector, not including end
     *         of line characters.
     */
     private Vector<Integer> readLine() throws IOException {
        Vector<Integer> chars = new Vector<Integer>();
        int c = reader.read();

        while (c != 10 || c != 13 ) {
            if (c == -1) {
                return null;
            }
            if (c == 49) {
                chars.add(Integer.valueOf(1));
            } else if (c == 50) {
                chars.add(Integer.valueOf(2));
            } else if (c == 51) {
                chars.add(Integer.valueOf(3));
            } else {
                chars.add(Integer.valueOf(0));
            }
            c = reader.read();
        }
        return chars;
     }

    /**
     * Makes and returns a integer matrix representing the file. Ignores
     * illegal characters and makes every line of the matrix the length
     * of the first line, padding shorter lines with zeroes. If first
     * line is longer than 70 characters it rest of the characters are
     * ignored.
     * @return Integer matrix representing the file.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public int[][] getBoard() throws IOException, FileNotFoundException {
        Vector<Vector<Integer>> chars = new Vector<Vector<Integer>>();
        int[][] board;
        int nOfRows;
        int nOfCols;
        Vector<Integer> line;

        openFile();
        while ((line = readLine()) != null) {
            chars.add(line);
        }
        closeFile();

        nOfRows = chars.size();
        nOfCols = chars.get(0).size();
        board = new int[nOfRows][nOfCols];
        for (int i = 0; i < nOfRows; i++) {
            for (int j = 0; j < nOfCols; j++) {
                try {
                    board[i][j] = chars.get(i).get(j).intValue();
                } catch (ArrayIndexOutOfBoundsException e) {
                    board[i][j] = 0;
                }
            }
        }
        return board;
    }
}