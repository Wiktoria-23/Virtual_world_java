import java.util.Random;
import java.awt.Color;
abstract public class Organism {
    enum direction {
        LEFT,
        RIGHT,
        UP,
        DOWN,
        NONE
    }
    enum organismType {
        ANTELOPE,
        DANDELION,
        FOX,
        GRASS,
        GUARANA,
        HUMAN,
        NIGHTSHADE,
        SHEEP,
        SOSNOWSKY_HOGWEED,
        TURTLE,
        WOLF
    }
    protected organismType type;
    protected String name;
    protected Color color;
    protected int strength;
    protected int initiative;
    protected int x;
    protected int y;
    protected int age;
    protected boolean alive;
    protected World currentWorld;
    public Organism(int xPosition, int yPosition, World newWorld) {
        x = xPosition;
        y =yPosition;
        currentWorld = newWorld;
        age = 0;
        alive = true;
    }
    public organismType getType() {
        return type;
    }
    public direction randMoveDirection() {
        Random rand = new Random();
        int number = (rand.nextInt(direction.values().length - 1));
        return direction.values()[number];
    }
    public abstract Organism createChild(int xPosition, int yPosition);
    public Organism getCollision(direction moveDirection) {
        Organism collidingOrganism = null;
        if (moveDirection == direction.UP) {
            if (currentWorld.checkFieldXY(x, y - 1)) {
                collidingOrganism = currentWorld.getOrganismFromXY(x, y - 1);
            }
        }
        else if (moveDirection == direction.DOWN) {
            if (currentWorld.checkFieldXY(x, y + 1)) {
                collidingOrganism = currentWorld.getOrganismFromXY(x, y + 1);
            }
        }
        else if (moveDirection == direction.RIGHT) {
            if (currentWorld.checkFieldXY(x + 1, y)) {
                collidingOrganism = currentWorld.getOrganismFromXY(x + 1, y);
            }
        }
        else if (moveDirection == direction.LEFT) {
            if (currentWorld.checkFieldXY(x - 1, y)) {
                collidingOrganism = currentWorld.getOrganismFromXY(x - 1, y);
            }
        }
        if (collidingOrganism != null && collidingOrganism.checkIfAlive()) {
            return collidingOrganism;
        }
        return null;
    }
    public Color getColor() {
        return color;
    }
    public abstract void action();
    public void setDeadState(Organism collidingOrganism) {
        currentWorld.addEventInfo(name + " (" + x + ", " + y + ") został zabity przez " + collidingOrganism.getName());
        alive = false;
    }
    public boolean checkIfAlive() {
        return alive;
    }
    public void increaseStrength(int amount) {
        strength += amount;
    }
    public String getName() {
        return name;
    }
    public void collision(Organism collidingOrganism) {
        baseFight(collidingOrganism);
        if (collidingOrganism.checkIfAlive() && alive) {
            collidingOrganism.collision(this);
        }
    }
    public void baseFight(Organism collidingOrganism) {
        if (collidingOrganism.strength > strength) {
            setDeadState(collidingOrganism);
        }
    }
    public void setStrength(int newStrength) {
        strength = newStrength;
    }
    public void setAge(int newAge) {
        age = newAge;
    }
    public int getStrength() {
        return strength;
    }
    public int getInitiative() {
        return initiative;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getAge() {
        return age;
    }
    public void incrementAgeCounter() {
        age += 1;
    }
}
