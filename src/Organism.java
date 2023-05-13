import java.util.Random;
abstract public class Organism {
    enum direction {
        LEFT,
        RIGHT,
        UP,
        DOWN,
        NONE
    }
    protected int strength;
    protected int initiative;
    protected int x;
    protected int y;
    protected int age;
    protected boolean alive;
    protected World currentWorld;
    protected direction moveDirection;
    public Organism(int xPosition, int yPosition, World newWorld) {
        x = xPosition;
        y =yPosition;
        currentWorld = newWorld;
        age = 0;
        alive = true;
    }
    direction randMoveDirection() {
        Random rand = new Random();
        int number = (rand.nextInt(direction.values().length));
        direction moveDirection = direction.values()[number];
        return moveDirection;
    }
    Organism createChild(int xPosition, int yPosition) {
        return null;
    }
    Organism getCollision(direction moveDirection) {
        Organism collidingOrganism = null;
        if (moveDirection == moveDirection.UP) {
            if (currentWorld.checkFieldXY(x, y - 1)) {
                collidingOrganism = currentWorld.getOrganismFromXY(x, y - 1);//rozwiąż kwestię wskaźników!!!
            }
        }
        else if (moveDirection == moveDirection.DOWN) {
            if (currentWorld.checkFieldXY(x, y + 1)) {
                collidingOrganism = currentWorld.getOrganismFromXY(x, y + 1);
            }
        }
        else if (moveDirection == moveDirection.RIGHT) {
            if (currentWorld.checkFieldXY(x + 1, y)) {
                collidingOrganism = currentWorld.getOrganismFromXY(x + 1, y);
            }
        }
        else if (moveDirection == moveDirection.LEFT) {
            if (currentWorld.checkFieldXY(x - 1, y)) {
                collidingOrganism = currentWorld.getOrganismFromXY(x - 1, y);
            }
        }
        if (collidingOrganism != null && collidingOrganism.checkIfAlive()) {
            return collidingOrganism;
        }
        return null;
    }
    void action() {

    }
    void setDeadState() {
        alive = false;
    }
    boolean checkIfAlive() {
        return alive;
    }
    void increaseStrength(int amount) {
        strength += amount;
    }
    void collision(Organism collidingOrganism) {
        baseFight(collidingOrganism);
        if (collidingOrganism.checkIfAlive() && alive) {
            collidingOrganism.collision(this);
        }
    }
    void baseFight(Organism collidingOrganism) {
        if (collidingOrganism.strength > strength) {
            setDeadState();
        }
    }
    void setStrength(int newStrength) {
        strength = newStrength;
    }
    void setInitiative(int newInitiative) {
        initiative = newInitiative;
    }
    void setAge(int newAge) {
        age = newAge;
    }
    void setAliveState(boolean newAlive) {
        alive = newAlive;
    }
    int getStrength() {
        return strength;
    }
    int getInitiative() {
        return initiative;
    }
    int getX() {
        return x;
    }
    int getY() {
        return y;
    }
    int getAge() {
        return age;
    }
    void incrementAgeCounter() {
        age += 1;
    }
}
