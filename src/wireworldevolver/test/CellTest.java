package wireworldevolver.test;
import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;
import org.junit.Before;
import org.junit.Test;

import wireworldevolver.Cell;

/**
 * Tests for Cell class.
 * @author Jani Rahkola
 * @version 0.1
 */
public class CellTest {
    private Cell empty;
    private Cell cond;
    private Cell head;
    private Cell tail;
    private Cell shouldBeEmpty;
    private Cell tmpEmpty;
    private Cell tmpHead;
    private Cell tmpTail;
    private Cell tmpCond;

    @Before public void setUp() {
        empty = new Cell(0);
        head = new Cell(1);
        tail = new Cell(2);
        cond = new Cell(3);
        shouldBeEmpty = new Cell(124);
        tmpEmpty = new Cell(0);
        tmpHead = new Cell(1);
        tmpTail = new Cell(2);
        tmpCond = new Cell(3);
    }

    /**
     * Two Cells should be equal when they have the same state.
     */
    @Test public void testEquals() {
        assertTrue(empty.equals(new Cell(0)));
        assertTrue(empty.equals(new Cell(4)));
        assertTrue(empty.equals(shouldBeEmpty));
        assertTrue(head.equals(new Cell(1)));
        assertTrue(tail.equals(new Cell(2)));
        assertTrue(cond.equals(new Cell(3)));
    }

    /**
     * Does getState return the state of the Cell?
     */
    @Test public void testGetState() {
        assertEquals(empty.getState(), 0);
        assertEquals(head.getState(), 1);
        assertEquals(tail.getState(), 2);
        assertEquals(cond.getState(), 3);
        assertTrue(empty.getState() == shouldBeEmpty.getState());
    }

    /**
     * Does setState set the state of the Cell to one give, or zero if
     * value given was not in range 0-3?
     */
    @Test public void testSetState() {
        tmpTail.setState(0);
        tmpHead.setState(4);
        assertTrue(empty.equals(tmpTail));
        assertTrue(empty.equals(tmpHead));
    }

    /**
     * Does Cell evolve according to Wireworld rules?
     */
    @Test public void testEvolve() {
        assertTrue(empty.equals(tmpEmpty.evolve()));
        assertTrue(tail.equals(tmpHead.evolve()));
        assertTrue(cond.equals(tmpTail.evolve()));
        tmpCond.checkHood();
        assertFalse(head.equals(tmpCond.evolve()));
        tmpCond = new Cell(3);
        assertTrue(head.equals(tmpCond.evolve()));
    }

    /**
     * Does checkHood make sure that conductor Cells evolve only if they
     * have the correct neighbourhood?
     */
    @Test public void testCheckHood() {
        Cell[] evolveHood = new Cell[8];
        evolveHood[0] = head;
        evolveHood[1] = head;
        Cell[] nonEvolveHood = new Cell[8];

        cond.setHood(evolveHood);
        assertTrue(cond.checkHood());
        cond.setHood(nonEvolveHood);
        assertFalse(cond.checkHood());
    }
    
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(CellTest.class);
    }

    public static void main(String args[]) {
        junit.textui.TestRunner.run(suite());
    }
}

