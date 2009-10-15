package wireworldevolver.test;
import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;
import org.junit.Before;
import org.junit.Test;

import wireworldevolver.BoardFileManager;

/**
 * Tests for the BoardFileManager class.
 * @author Jani Rahkola
 * @version 0.1
 */
public class BoardFileManagerTest {

    private BoardFileManager filem;
    private BoardFileManager tmpFilem;

    @Before public void setUp() {
        filem = new BoardFileManager();
        tmpFilem = new BoardFileManager();
    }

    /**
     * Does getBoard return return a integer matrix that equals to the
     * contents of the inputfile?
     */
    @Test public void testGetBoard() {
        int[][] emptyExpected = {{0}};
        filem.setFilePath("emptyFile");
        try {
            assertEquals(filem.getBoard().length, 1);
            assertEquals(filem.getBoard(), emptyExpected);
        } catch(Exception e) {
            fail("Could not open file!");
        }

        int[][] singleOne = {{1}};
        filem.setFilePath("singleOneFile");
        try {
            assertEquals(filem.getBoard().length, 1);
            assertEquals(filem.getBoard(), singleOne);
        } catch(Exception e) {
            fail("Could not open file!");
        }

        int[][] rowOfZeros = new int[1][70];
        for (int i = 0; i < 70; i++) {
            rowOfZeros[0][i] = 0;
        }
        filem.setFilePath("lineOverflow");
        tmpFilem.setFilePath("fullLine");
        try {
            assertEquals(filem.getBoard().length, 1);
            assertEquals(filem.getBoard(), rowOfZeros);
            assertEquals(filem.getBoard(), tmpFilem.getBoard());
        } catch(Exception e) {
            fail("Could not open file!");
        }

        filem.setFilePath("sndLinePadding");
        tmpFilem.setFilePath("twoFullLines");
        try {
            assertEquals(filem.getBoard(), tmpFilem.getBoard());
        } catch(Exception e) {
            fail("Could not open file!");
        }
    }
    
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(BoardFileManagerTest.class);
    }

    public static void main(String args[]) {
        junit.textui.TestRunner.run(suite());
    }
}

