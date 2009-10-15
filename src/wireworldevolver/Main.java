package wireworldevolver;
import javax.swing.*;

/**
 * Entrypoint class to the application. Initializes the GUI.
 */
public class Main {
    public static void main(String[] args) {
        UIModel model = new UIModel();
        GUIView view = new GUIView(model);
        GUIController controller = new GUIController(view, model);

        view.setVisible(true);
    }
}
