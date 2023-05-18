import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class BoardSizeChoice implements ActionListener {
    private final World currentWorld;
    private JSpinner xInput, yInput;
    private JDialog mainWindow;
    public BoardSizeChoice(World newWorld) {
        currentWorld = newWorld;
        show();
    }
    public void show() {
        final int windowHeight = 300;
        final int windowWidth = 400;
        final int menuStartY = (windowHeight - 260) / 2;
        final int spaceInMenu = 50;
        mainWindow = new JDialog(mainWindow, Dialog.ModalityType.APPLICATION_MODAL);
        mainWindow.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        mainWindow.setResizable(false);
        JLabel title = new JLabel("Podaj rozmiar planszy", SwingConstants.CENTER);
        title.setBounds((windowWidth - 200) / 2, menuStartY, 200, 20);
        mainWindow.add(title);
        xInput = new JSpinner(new SpinnerNumberModel(20, 5, 50, 1));
        xInput.setBounds((windowWidth - 250) / 2, menuStartY + spaceInMenu, 100, 20);
        yInput = new JSpinner(new SpinnerNumberModel(20, 5, 50, 1));
        yInput.setBounds(((windowWidth - 250) / 2) + 150, menuStartY + spaceInMenu, 100, 20);
        JButton button = new JButton("Ok");
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
