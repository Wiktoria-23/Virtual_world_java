public class Controller {
    private Screen screen;
    private boolean programActive = true;
    private World world = new World();
    public Controller() {
        getBoardSize();
    }
    public void getBoardSize() {
        BoardSizeChoice choiceWindow = new BoardSizeChoice(world);
        world.initWorld();
    }
    public void showMainWindow() {
        Screen mainWindow = new Screen(world.getBoardSizeX(), world.getBoardSizeY(), this);
        mainWindow.show();
    }
    public World getWorld() {
        return world;
    }
}
