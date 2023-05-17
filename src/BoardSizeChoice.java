import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class BoardSizeChoice implements ActionListener {
    private final int windowHeight = 300;
    private final int windowWidth = 400;
    private final int menuStartY = (windowHeight - 260) / 2;
    private final int spaceInMenu = 50;
    private World currentWorld;
    private JLabel title;
    private JButton button;
    private JSpinner xInput, yInput;
    private JDialog mainWindow;
    public BoardSizeChoice(World newWorld) {
        currentWorld = newWorld;
        show();
    }
    public void show() {
        mainWindow = new JDialog(mainWindow, Dialog.ModalityType.APPLICATION_MODAL);
        mainWindow.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        mainWindow.setResizable(false);
        title = new JLabel("Podaj rozmiar planszy", SwingConstants.CENTER);
        title.setBounds((windowWidth - 200) / 2, menuStartY, 200, 20);
        mainWindow.add(title);
        xInput = new JSpinner(new SpinnerNumberModel(20, 5, 50, 1));
        xInput.setBounds((windowWidth - 250) / 2, menuStartY + spaceInMenu, 100, 20);
        yInput = new JSpinner(new SpinnerNumberModel(20, 5, 50, 1));
        yInput.setBounds(((windowWidth - 250) / 2) + 150, menuStartY + spaceInMenu, 100, 20);
        button = new JButton("Ok");
        button.setBounds((windowWidth - 100) / 2, menuStartY + spaceInMenu * 2, 100, 50);
        button.addActionListener(this);
        mainWindow.add(xInput);
        mainWindow.add(yInput);
        mainWindow.add(button);
        mainWindow.setSize(windowWidth, windowHeight);
        mainWindow.setLayout(null);
        mainWindow.setVisible(true);
    }
    public void actionPerformed(ActionEvent event) {
        currentWorld.setBoardSizeX((int)xInput.getValue());
        currentWorld.setBoardSizeY((int)yInput.getValue());
        mainWindow.setVisible(false);
        mainWindow.dispose();
    }
}
