package wireworldevolver;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class GUIView extends JFrame {
    private UIModel model;

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("Wireworld");
    private JMenuItem exitMenuItem;
    private JTextField filePathTf = new JTextField(20);
    private JButton loadButton = new JButton("Load");
    private JButton browseButton = new JButton("Browse");
    private JButton updateOnceButton = new JButton("One step");
    private JButton updateToggleButton = new JButton("Start/Stop");
    private JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 30, 15);
    private JPanel content;
    private JPanel board;
    private JPanel grid;
    private JScrollPane pane;

    public GUIView(UIModel model) {
        this.model = model;

        menuBar.add(menu);
        exitMenuItem = new JMenuItem("Exit");
        menu.add(exitMenuItem);
        this.setJMenuBar(menuBar);

        content = new JPanel(new BorderLayout());

        JPanel wigets1 = new JPanel(new GridLayout(0,3));
        JPanel file = new JPanel();
        file.setLayout(new BoxLayout(file, BoxLayout.LINE_AXIS));
        file.add(new JLabel("Path:"));
        file.add(filePathTf);
        wigets1.add(file);
        wigets1.add(updateOnceButton);
        wigets1.add(updateToggleButton);

        JPanel wigets2 = new JPanel(new GridLayout(0, 3));
        wigets2.add(loadButton);
        wigets2.add(browseButton);
        JPanel speed = new JPanel();
        speed.setLayout(new BoxLayout(speed, BoxLayout.LINE_AXIS));
        speed.add(new JLabel("Speed:"));
        speed.add(speedSlider);
        wigets2.add(speed);

        JPanel wigets = new JPanel();
        wigets.setLayout(new BoxLayout(wigets, BoxLayout.PAGE_AXIS));
        wigets.add(wigets1);
        wigets.add(wigets2);

        board = new JPanel(new BorderLayout());
        board.setPreferredSize(new Dimension(720, 400));

        content.add(wigets, BorderLayout.PAGE_END);
        content.add(board, BorderLayout.CENTER);

        this.setContentPane(content);
        this.pack();

        this.setTitle("WireworldEvolver");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void visualBoard() {
        int state;
        board.removeAll();
        grid = new JPanel(new GridLayout(model.getRows(),model.getColumns()));
        for (int row = 0; row < model.getRows(); row++) {
            for (int col = 0; col < model.getColumns(); col++) {
                MyJLabel label = new MyJLabel();
                model.getCellIn(row,col).addObserver(label);
                model.getCellIn(row,col).stateChanged();
                grid.add(label);
            }
        }
        pane = new JScrollPane(grid);
        board.add(pane, BorderLayout.CENTER);
        pane.validate();
        grid.validate();
        content.validate();
        this.validate();
    }

    public String getFilePathText() {
        return filePathTf.getText();
    }

    public void setFilePath(String filePath) {
        filePathTf.setText(filePath);
    }

    public void showError(String error) {
        filePathTf.setText(error);        
    }

    public void addMenuExitListener(ActionListener listener) {
        exitMenuItem.addActionListener(listener);
    }

    public void addLoadButtonListener(ActionListener listener) {
        loadButton.addActionListener(listener);
    }

    public void addBrowseButtonListener(ActionListener listener) {
        browseButton.addActionListener(listener);
    }

    public void addUpdateOnceButtonListener(ActionListener listener) {
        updateOnceButton.addActionListener(listener);
    }

    public void addUpdateToggleButtonListener(ActionListener listener) {
        updateToggleButton.addActionListener(listener);
    }

    public void addSpeedSliderListener(ChangeListener listener) {
        speedSlider.addChangeListener(listener);
    }
}
