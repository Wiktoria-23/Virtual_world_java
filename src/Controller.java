import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Controller {
    private Screen mainWindow;
    private World world = new World();
    private String filename;
    public Controller() {
        getBoardSize();
    }
    public void getBoardSize() {
        BoardSizeChoice choiceWindow = new BoardSizeChoice(world);
        world.initWorld();
    }
    public void setFilename(String newFilename) {
        filename = newFilename;
    }
    public void newRound() {
        getWorld().getAllEventsInfo().clear();
        getWorld().performRound();
        mainWindow.updateLogs();
        mainWindow.updateMainFrame();
    }
    public void showMainWindow() {
        mainWindow = new Screen(world.getBoardSizeX(), world.getBoardSizeY(), this);
        mainWindow.show();
    }
    public void addNewOrganism(int xPosition, int yPosition, Organism.organismType organismType) {
        Organism newOrganism = null;
        if (organismType == Organism.organismType.ANTELOPE) {
            newOrganism = new Antelope(xPosition, yPosition, world);
        }
        else if (organismType == Organism.organismType.DANDELION) {
            newOrganism = new Dandelion(xPosition, yPosition, world);
        }
        else if (organismType == Organism.organismType.FOX) {
            newOrganism = new Fox(xPosition, yPosition, world);
        }
        else if (organismType == Organism.organismType.GRASS) {
            newOrganism = new Grass(xPosition, yPosition, world);
        }
        else if (organismType == Organism.organismType.GUARANA) {
            newOrganism = new Guarana(xPosition, yPosition, world);
        }
        else if (organismType == Organism.organismType.HUMAN) {
            newOrganism = new Human(xPosition, yPosition, world);
        }
        else if (organismType == Organism.organismType.NIGHTSHADE) {
            newOrganism = new Nightshade(xPosition, yPosition, world);
        }
        else if (organismType == Organism.organismType.SHEEP) {
            newOrganism = new Sheep(xPosition, yPosition, world);
        }
        else if (organismType == Organism.organismType.SOSNOWSKY_HOGWEED) {
            newOrganism = new SosnowskyHogweed(xPosition, yPosition, world);
        }
        else if (organismType == Organism.organismType.TURTLE) {
            newOrganism = new Turtle(xPosition, yPosition, world);
        }
        else if (organismType == Organism.organismType.WOLF) {
            newOrganism = new Wolf(xPosition, yPosition, world);
        }
        if (newOrganism != null) {
            world.getAllOrganisms().add(newOrganism);
            world.sortOrganisms();
        }
        mainWindow.updateMainFrame();
    }
    public World getWorld() {
        return world;
    }
    public void save() {
        mainWindow.getFilenameInput();
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException exception) {

            }
        }
        try {
            FileWriter writeToFile = new FileWriter(filename);
            writeToFile.write(Integer.toString(world.getBoardSizeX())+ "\n");
            writeToFile.write(Integer.toString(world.getBoardSizeY()) + "\n");
            writeToFile.write(Integer.toString(world.getAllOrganisms().size()) + "\n");
            for (int i = 0; i < world.getAllOrganisms().size(); i++) {
                Organism currentOrganism = world.getAllOrganisms().get(i);
                writeToFile.write(currentOrganism.getType().toString() + "\n");
                writeToFile.write(Integer.toString(currentOrganism.getAge()) + "\n");
                writeToFile.write(Integer.toString(currentOrganism.getX()) + "\n");
                writeToFile.write(Integer.toString(currentOrganism.getY()) + "\n");
                writeToFile.write(Integer.toString(currentOrganism.getStrength()) + "\n");
                if (currentOrganism instanceof Human) {
                    Human human = (Human)currentOrganism;
                    writeToFile.write(human.getRoundCounter() + "\n");
                    if (human.superpowerState() == true) {
                        writeToFile.write("1");
                    }
                    else {
                        writeToFile.write("0");
                    }
                    writeToFile.write("\n");
                }
            }
            writeToFile.close();
        }
        catch (IOException exception) {

        }
        mainWindow.getMainFrame().requestFocus();
    }
    public void load() {
        File selectedFile;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(new File("").getAbsolutePath()));
        int output = fileChooser.showOpenDialog(mainWindow.getMainFrame());
        if (output == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
        }
        else {
            selectedFile = null;
        }
        if (selectedFile != null) {
            try {
                Scanner fileReader = new Scanner(selectedFile);
                world.getAllEventsInfo().clear();
                world.getAllOrganisms().clear();
                world.setBoardSizeX(Integer.parseInt(fileReader.nextLine()));
                world.setBoardSizeY(Integer.parseInt(fileReader.nextLine()));
                int organismsAmount = Integer.parseInt(fileReader.nextLine());
                for (int i = 0; i < organismsAmount; i++) {
                    Organism currentOrganism;
                    Organism.organismType currentOrganismType = Organism.organismType.valueOf(fileReader.nextLine().trim());
                    int age = Integer.parseInt(fileReader.nextLine());
                    int x = Integer.parseInt(fileReader.nextLine());
                    int y = Integer.parseInt(fileReader.nextLine());
                    int strength = Integer.parseInt(fileReader.nextLine());
                    if (currentOrganismType == Organism.organismType.ANTELOPE) {
                        currentOrganism = new Antelope(x, y, world);
                    }
                    else if (currentOrganismType == Organism.organismType.DANDELION) {
                        currentOrganism = new Dandelion(x, y, world);
                    }
                    else if (currentOrganismType == Organism.organismType.FOX) {
                        currentOrganism = new Fox(x, y, world);
                    }
                    else if (currentOrganismType == Organism.organismType.GRASS) {
                        currentOrganism = new Grass(x, y, world);
                    }
                    else if (currentOrganismType == Organism.organismType.GUARANA) {
                        currentOrganism = new Guarana(x, y, world);
                    }
                    else if (currentOrganismType == Organism.organismType.HUMAN) {

                        Human human = new Human(x, y, world);
                        human.setRoundCounter(Integer.parseInt(fileReader.nextLine()));
                        int superpowerState = Integer.parseInt(fileReader.nextLine());
                        if (superpowerState == 1) {
                            human.setSuperpowerState(true);
                        }
                        else {
                            human.setSuperpowerState(false);
                        }
                        currentOrganism = human;
                    }
                    else if (currentOrganismType == Organism.organismType.NIGHTSHADE) {
                        currentOrganism = new Nightshade(x, y, world);
                    }
                    else if (currentOrganismType == Organism.organismType.SHEEP) {
                        currentOrganism = new Sheep(x, y, world);
                    }
                    else if (currentOrganismType == Organism.organismType.SOSNOWSKY_HOGWEED) {
                        currentOrganism = new SosnowskyHogweed(x, y, world);
                    }
                    else if (currentOrganismType == Organism.organismType.TURTLE) {
                        currentOrganism = new Turtle(x, y, world);
                    }
                    else if (currentOrganismType == Organism.organismType.WOLF) {
                        currentOrganism = new Wolf(x, y, world);
                    }
                    else {
                        currentOrganism = null;
                    }
                    if (currentOrganism != null) {
                        currentOrganism.setAge(age);
                        currentOrganism.setStrength(strength);
                        world.getAllOrganisms().add(currentOrganism);
                    }
                }
            }
            catch (FileNotFoundException exception) {

            }
        }
        mainWindow.resetOrganismImages(world.getBoardSizeX(), world.getBoardSizeY());
        mainWindow.resetBoard(world.getBoardSizeY(), world.getBoardSizeX());
        mainWindow.updateLogs();
        mainWindow.updateMainFrame();
        mainWindow.getMainFrame().requestFocus();
    }
}
