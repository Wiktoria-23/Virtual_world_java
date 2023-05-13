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
    private Coordinates boardSizes = new Coordinates(0,0);
    private JFrame frame = new JFrame("Wirtualny świat");
    public Screen(int boardSizeX, int boardSizeY, Controller newController) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controller = newController;
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton turnButton = new JButton("Nowa tura");
        JPanel board = new JPanel(new GridLayout(boardSizeY, boardSizeX));
        turnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                controller.getWorld().performRound();
                updateBoard(board, boardSizeX, boardSizeY);
                board.revalidate();
                board.repaint();
            }
        });
        buttonPanel.add(turnButton);
        organismsImages = new JPanel[boardSizeY][boardSizeX];
        boardSizes.setX(boardSizeX);
        boardSizes.setY(boardSizeY);
        frame.setSize(windowWidth, windowHeight);
        updateBoard(board, boardSizeX, boardSizeY);
        mainPanel.add(board, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);
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
        frame.setVisible(true);
    }
}
