import java.awt.*;
import java.util.Random;
public class Turtle extends Animal {
    private final int chanceToMove = 4;
    public Turtle(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld);
        color = Color.blue;
        name = "Żółw";
        type = organismType.TURTLE;
        strength = 2; //base Turtle strength
        initiative = 1; //base Turtle initiative
    }
    public void action() {
        Random rand = new Random();
        int move = rand.nextInt(chanceToMove);
        if (move == 0) {
            super.action();
        }
    }
    public Organism createChild(int xPosition, int yPosition) {
        return new Turtle(xPosition, yPosition, currentWorld);
    }
    public void collision(Organism collidingOrganism) {
        if (color == collidingOrganism.getColor()) {
            tryToBreed(collidingOrganism);
            return;
        }
        if (
                collidingOrganism.getStrength() < 5
                        && currentWorld.checkIfAnimal(collidingOrganism.getX(), collidingOrganism.getY())
        ) {
            Animal collidingAnimal = (Animal)collidingOrganism;
            if (collidingAnimal.getMoveDirection() == direction.UP) {
                collidingAnimal.setMoveDirection(direction.DOWN);
            }
            else if (collidingAnimal.getMoveDirection() == direction.DOWN) {
                collidingAnimal.setMoveDirection(direction.UP);
            }
            else if (collidingAnimal.getMoveDirection() == direction.RIGHT) {
                collidingAnimal.setMoveDirection(direction.LEFT);
            }
            else if (collidingAnimal.getMoveDirection() == direction.LEFT) {
                collidingAnimal.setMoveDirection(direction.RIGHT);
            }
            currentWorld.addEventInfo(name + " (" + x + ", " + y + ") odbił atak");
        }
        else {
            baseFight(collidingOrganism);
            if (collidingOrganism.checkIfAlive() && collidingOrganism.getStrength() != strength) {
                collidingOrganism.collision(this);
            }
        }
    }
}
