import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.Flow;

public class Screen {
    private Controller controller;
    private final int windowWidth = 800;
    private final int windowHeight = 600;
    private JPanel[][] organismsImages;
    private JPanel board;
    private Coordinates boardSizes = new Coordinates(0,0);
    private JFrame mainFrame = new JFrame("Wirtualny świat");
    public Screen(int boardSizeX, int boardSizeY, Controller newController) {
        controller = newController;
        mainFrame.addKeyListener(new KeyboardListener(controller));
        mainFrame.setFocusable(true);
        mainFrame.setFocusTraversalKeysEnabled(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton turnButton = new JButton("Nowa tura");
        board = new JPanel(new GridLayout(boardSizeY, boardSizeX));
        turnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                controller.newRound();
                updateMainFrame();
            }
        });
        buttonPanel.add(turnButton);
        organismsImages = new JPanel[boardSizeY][boardSizeX];
        boardSizes.setX(boardSizeX);
        boardSizes.setY(boardSizeY);
        mainFrame.setSize(windowWidth, windowHeight);
        updateBoard(board, boardSizeX, boardSizeY);
        mainPanel.add(board, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainFrame.add(mainPanel);
    }
    public void updateMainFrame() {
        updateBoard(board, boardSizes.getX(), boardSizes.getY());
        board.revalidate();
        board.repaint();
        getMainFrame().requestFocus();
    }
    public JFrame getMainFrame() {
        return mainFrame;
    }
    public void updateBoard(JPanel board, int boardSizeX, int boardSizeY) {
        board.removeAll();
        int imageWidth = windowWidth/boardSizeX;
        int imageHeight = (windowHeight)/boardSizeY;
        for (int y = 0; y < boardSizeY; y++) {
            for (int x = 0; x < boardSizeX; x++) {
                JPanel panel = new JPanel();
                panel.setSize(imageWidth, imageHeight);
                if (controller.getWorld().checkFieldXY(x, y)) {
                    panel.setBackground(controller.getWorld().getOrganismFromXY(x, y).getColor());
                }
                else {
                    panel.setBackground(Color.white);
                }
                organismsImages[y][x] = panel;
                board.add(panel);
            }
        }
    }
    public void show() {
        mainFrame.setVisible(true);
    }
}
