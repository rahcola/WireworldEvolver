package wireworldevolver;
import javax.swing.*;
import java.awt.*;
import java.util.Observer;
import java.util.Observable;

/**
 * JLabel that has one space as text, and changes its background color
 * based on the Observable that it observes.
 * @author Jani Rahkola
 * @version 0.1
 */
public class MyJLabel extends JLabel implements Observer{

    /**
     * Creates a opaque, 10 by 10 JLabel, that has one space character
     * as text.
     */
    public MyJLabel() {
        super(" ");
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(10,10));
    }

    /**
     * Changes the background color of this label based on the observed
     * <a href="Cell.html">Cell</a>.
     */
    public void update(Observable o, Object arg) {
        Cell a = (Cell)o;
        if (a.getState() == 0) {
            this.setBackground(Color.black);
        } else if (a.getState() == 1) {
            this.setBackground(Color.white);
        } else if (a.getState() == 2) {
            this.setBackground(Color.blue);
        } else {
            this.setBackground(Color.orange);
        }
    }
}
