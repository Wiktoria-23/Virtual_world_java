import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.Flow;

public class Screen {
    private Controller controller;
    private final int windowWidth = 800;
    private final int windowHeight = 600;
    private JPanel[][] organismsImages;
    private JPanel board, mainPanel, logPanel, buttonPanel;
    private JFrame mainFrame = new JFrame("Wirtualny świat");
    public Screen(int boardSizeX, int boardSizeY, Controller newController) {
        controller = newController;
        mainFrame.addKeyListener(new KeyboardListener(controller));
        mainFrame.setFocusable(true);
        mainFrame.setFocusTraversalKeysEnabled(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel(new BorderLayout());
        logPanel = new JPanel(new GridLayout(boardSizeY, 1));
        logPanel.setPreferredSize(new Dimension(350, windowHeight));
        JLabel newText = new JLabel("Stworzono wszystkie organizmy");
        logPanel.add(newText);
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton turnButton = new JButton("Nowa tura");
        board = new JPanel(new GridLayout(boardSizeY, boardSizeX));
        turnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                controller.newRound();
                updateMainFrame();
            }
        });
        JButton saveButton = new JButton("Zapisz");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.save();
            }
        });
        JButton loadButton = new JButton("Wczytaj");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.load();
            }
        });
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(turnButton);
        organismsImages = new JPanel[boardSizeY][boardSizeX];
        mainFrame.setSize(windowWidth, windowHeight);
        updateBoard(boardSizeX, boardSizeY);
        mainPanel.add(logPanel, BorderLayout.EAST);
        mainPanel.add(board, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainFrame.add(mainPanel);
    }
    public void resetOrganismImages(int boardX, int boardY) {
        organismsImages = new JPanel[boardY][boardX];
    }
    public void updateLogs() {
        logPanel.removeAll();
        ArrayList<String> allEvents = controller.getWorld().getAllEventsInfo();
        for (int i = 0; i < controller.getWorld().getBoardSizeY(); i++) {
            if (i >= controller.getWorld().getAllEventsInfo().size()) {
                break;
            }
            String event = allEvents.get(i);
            JLabel log = new JLabel(event);
            logPanel.add(log);
        }
        logPanel.revalidate();
        logPanel.repaint();
    }
    public void resetBoard(int boardY, int boardX){
        mainPanel.remove(board);
        board = new JPanel(new GridLayout(boardY, boardX));
        mainPanel.add(board);
        mainPanel.revalidate();
    }
    public void updateMainFrame() {
        updateBoard(controller.getWorld().getBoardSizeX(), controller.getWorld().getBoardSizeY());
        board.revalidate();
        board.repaint();
        getMainFrame().requestFocus();
    }
    public void getFilenameInput() {
        int windowWidth = 250;
        int windowHeight = 140;
        int componentWidth = 150;
        int componentHeight = 20;
        JDialog getFilename = new JDialog(mainFrame, "Podaj nazwę pliku", Dialog.ModalityType.APPLICATION_MODAL);
        getFilename.setResizable(false);
        JTextField filenameField = new JTextField("Nazwa pliku");
        filenameField.setBounds((windowWidth - componentWidth)/2, componentHeight, componentWidth, componentHeight);
        getFilename.add(filenameField);
        JButton saveFilename = new JButton("Zapisz");
        saveFilename.setBounds((windowWidth - componentWidth)/2, componentHeight*3, componentWidth, componentHeight);
        saveFilename.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filename = filenameField.getText();
                if (!filename.contains(" ")) {
                    controller.setFilename(filename);
                    getFilename.setVisible(false);
                    getFilename.dispose();
                }
            }

        });
        getFilename.add(saveFilename);
        getFilename.setSize(windowWidth, windowHeight);
        getFilename.setLayout(null);
        getFilename.setVisible(true);
    }
    public JFrame getMainFrame() {
        return mainFrame;
    }
    public void showAddingOrganismMenu(final int xPosition, final int yPosition) {
        JDialog addingOrganismMenu = new JDialog(mainFrame, "Wybierz typ", Dialog.ModalityType.APPLICATION_MODAL);
        addingOrganismMenu.setResizable(false);
        addingOrganismMenu.setSize(200, 300);
        addingOrganismMenu.setLayout(new GridLayout(11, 1));
        JButton antelopeChoice = new JButton("Antylopa");
        antelopeChoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.addNewOrganism(xPosition, yPosition, Organism.organismType.ANTELOPE);
                addingOrganismMenu.setVisible(false);
                addingOrganismMenu.dispose();
            }
        });
        addingOrganismMenu.add(antelopeChoice);
        JButton dandelionChoice = new JButton("Mlecz");
        dandelionChoice.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.addNewOrganism(xPosition, yPosition, Organism.organismType.DANDELION);
                addingOrganismMenu.setVisible(false);
                addingOrganismMenu.dispose();
            }
        }));
        addingOrganismMenu.add(dandelionChoice);
        JButton foxChoice = new JButton("Lis");
        foxChoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.addNewOrganism(xPosition, yPosition, Organism.organismType.FOX);
                addingOrganismMenu.setVisible(false);
                addingOrganismMenu.dispose();
            }
        });
        addingOrganismMenu.add(foxChoice);
        JButton grassChoice = new JButton("Grass");
        grassChoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.addNewOrganism(xPosition, yPosition, Organism.organismType.GRASS);
                addingOrganismMenu.setVisible(false);
                addingOrganismMenu.dispose();
            }
        });
        addingOrganismMenu.add(grassChoice);
        JButton guaranaChoice = new JButton("Guarana");
        guaranaChoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.addNewOrganism(xPosition, yPosition, Organism.organismType.GUARANA);
                addingOrganismMenu.setVisible(false);
                addingOrganismMenu.dispose();
            }
        });
        addingOrganismMenu.add(guaranaChoice);
        JButton nightshadeChoice = new JButton("Wilcza jagoda");
        nightshadeChoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.addNewOrganism(xPosition, yPosition, Organism.organismType.NIGHTSHADE);
                addingOrganismMenu.setVisible(false);
                addingOrganismMenu.dispose();
            }
        });
        addingOrganismMenu.add(nightshadeChoice);
        JButton sheepChoice = new JButton("Owca");
        sheepChoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.addNewOrganism(xPosition, yPosition, Organism.organismType.SHEEP);
                addingOrganismMenu.setVisible(false);
                addingOrganismMenu.dispose();
            }
        });
        addingOrganismMenu.add(sheepChoice);
        JButton sosnowskyHogweedChoice = new JButton("Barszcz sosnowskiego");
        sosnowskyHogweedChoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.addNewOrganism(xPosition, yPosition, Organism.organismType.SOSNOWSKY_HOGWEED);
                addingOrganismMenu.setVisible(false);
                addingOrganismMenu.dispose();
            }
        });
        addingOrganismMenu.add(sosnowskyHogweedChoice);
        JButton turtleChoice = new JButton("Żółw");
        turtleChoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.addNewOrganism(xPosition, yPosition, Organism.organismType.TURTLE);
                addingOrganismMenu.setVisible(false);
                addingOrganismMenu.dispose();
            }
        });
        addingOrganismMenu.add(turtleChoice);
        JButton wolfChoice = new JButton("Wilk");
        wolfChoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.addNewOrganism(xPosition, yPosition, Organism.organismType.WOLF);
                addingOrganismMenu.setVisible(false);
                addingOrganismMenu.dispose();
            }
        });
        addingOrganismMenu.add(wolfChoice);
        if (controller.getWorld().getHuman() == null) {
            JButton humanChoice = new JButton("Człowiek");
            humanChoice.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    controller.addNewOrganism(xPosition, yPosition, Organism.organismType.HUMAN);
                    addingOrganismMenu.setVisible(false);
                    addingOrganismMenu.dispose();
                }
            });
            addingOrganismMenu.add(humanChoice);
        }
        addingOrganismMenu.setVisible(true);
    }
    public void updateBoard(int boardSizeX, int boardSizeY) {
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
                    final int xPosition = x;
                    final int yPosition = y;
                    panel.setBackground(Color.white);
                    panel.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            showAddingOrganismMenu(xPosition, yPosition);
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {

                        }

                        @Override
                        public void mouseExited(MouseEvent e) {

                        }
                    });
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
