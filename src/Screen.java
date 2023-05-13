import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.Flow;

public class Screen {
    private final int windowWidth = 800;
    private final int windowHeight = 600;
    private JPanel[][] organismsImages;
    private Coordinates boardSizes = new Coordinates(0,0);
    private JFrame frame;
    public Screen(int boardSizeX, int boardSizeY) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        organismsImages = new JPanel[boardSizeY][boardSizeX];
        boardSizes.setX(boardSizeX);
        boardSizes.setY(boardSizeY);
        frame = new JFrame("Wirtualny świat");
        frame.setSize(windowWidth, windowHeight);
        int imageWidth = windowWidth/boardSizeX;
        int imageHeight = (windowHeight - 100)/boardSizeY;
        JPanel board = new JPanel(new GridLayout(boardSizeY, boardSizeX));
        for (int y = 0; y < boardSizeY; y++) {
            for (int x = 0; x < boardSizeX; x++) {
                JPanel panel = new JPanel();
                panel.setSize(imageWidth, imageHeight);
                if ((y+x) % 2 == 0) {
                    panel.setBackground(Color.PINK);
                }
                organismsImages[y][x] = panel;
                board.add(panel);
            }
        }
        mainPanel.add(board);
        mainPanel.add((buttonPanel));
        frame.add(mainPanel);
    }
    public void show() {
        frame.setVisible(true);
    }
}
