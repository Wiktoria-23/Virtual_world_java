import java.util.Random;
public class Coordinates {
    private int x;
    private int y;
    Coordinates(int newX, int newY) {
        x = newX;
        y = newY;
    }
    Coordinates(World currentWorld) {
        Random rand = new Random();
        int newX = rand.nextInt(currentWorld.getBoardSizeX());
        int newY = rand.nextInt(currentWorld.getBoardSizeY());
        while (currentWorld.checkFieldXY(newX, newY)) {
            newX = rand.nextInt(currentWorld.getBoardSizeX());
            newY = rand.nextInt(currentWorld.getBoardSizeY());
        }
        x = newX;
        y = newY;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int newX) {
        x = newX;
    }
    public void setY(int newY) {
        y = newY;
    }
}
