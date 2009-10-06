package tests;
import static org.junit.Assert.assertTrue;
import junit.framework.JUnit4TestAdapter;
import org.junit.Before;
import org.junit.Test;

import wireworldevolver.Cell;

public class CellTest {
    private Cell empty;
    private Cell cond;
    private Cell head;
    private Cell tail;

    @Before public void setUp() {
        empty = new Cell(0);
        head = new Cell(1);
        tail = new Cell(2);
        cond = new Cell(3);
    }
    
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(CellTest.class);
    }

    public static void main(String args[]) {
        junit.textui.TestRunner.run(suite());
    }
}
