import javax.swing.*;

public class Controller {
    private Screen mainWindow;
    private World world = new World();
    public Controller() {
        getBoardSize();
    }
    public void getBoardSize() {
        BoardSizeChoice choiceWindow = new BoardSizeChoice(world);
        world.initWorld();
    }
    public void newRound() {
        getWorld().performRound();
        mainWindow.updateMainFrame();
    }
    public void showMainWindow() {
        mainWindow = new Screen(world.getBoardSizeX(), world.getBoardSizeY(), this);
        mainWindow.show();
    }
    public World getWorld() {
        return world;
    }
}
