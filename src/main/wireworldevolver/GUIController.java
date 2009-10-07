package wireworldevolver;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import java.io.File;

/**
 * GUI controller as in MVC.
 * @author Jani Rahkola
 * @version 0.1
 */
public class GUIController {
    
    private GUIView view;
    private UIModel model;

    /**
     * Creates a Controller.
     * @param view GUI View as in MVC
     * @param model UI Model as in MVC
     */
    public GUIController(GUIView view, UIModel model) {
        this.view = view;
        this.model = model;
        view.addMenuExitListener(new MenuExitListener());
        view.addLoadButtonListener(new LoadButtonListener());
        view.addBrowseButtonListener(new BrowseButtonListener());
        view.addUpdateOnceButtonListener(new UpdateOnceButtonListener());
        view.addUpdateToggleButtonListener(new UpdateToggleButtonListener());
        view.addSpeedSliderListener(new SpeedSliderListener());
    }

    /**
     * Specifies what happens when user pushes Exit in menu.
     */
    protected class MenuExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    /**
     * Specifies what happens when user pushes Load button.
     */
    protected class LoadButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                model.loadFile(view.getFilePathText()); 
                model.stopContinuosUpdate();
                view.visualBoard();
            } catch (Exception ex) {
                view.showError("Unable to open file.");
                System.out.println("" + ex);
            }
        }
    }

    /**
     * Specifies what happens when user pushes Browse button.
     */
    protected class BrowseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser();
            int returnval = fc.showOpenDialog(null);

            if (returnval == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                view.setFilePath(file.getAbsolutePath());
            }
        }
    }

    /**
     * Specifies what happens when user pushes One step button.
     */
    protected class UpdateOnceButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (model.boardIsPopulated()) {
                model.updateOnce();
            }
        }
    }

    /**
     * Specifies what happens when user pushes Start/Stop button.
     */
    protected class UpdateToggleButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (model.boardIsPopulated()) {
                model.toggleContinuosUpdate();
            }
        }
    }

    /**
     * Specifies what happens when user wiggles the slider.
     */
    protected class SpeedSliderListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider)e.getSource();  
            model.setSpeed((int)(source.getValue()));
        }
    }
}
