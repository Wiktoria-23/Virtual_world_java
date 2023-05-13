import java.util.Random;
public class Turtle extends Animal {
    private final int chanceToMove = 4;
    Turtle(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld);
        strength = 2; //base Turtle strength
        initiative = 1; //base Turtle initiative
    }
    void action() {
        Random rand = new Random();
        int move = rand.nextInt(chanceToMove);
        if (move == 0) {
            super.action();
        }
    }
    Organism createChild(int xPosition, int yPosition) {
        Organism newTurtle = new Turtle(xPosition, yPosition, currentWorld);
        return newTurtle;
    }
    void collision(Organism collidingOrganism) {
        /*if (image == collidingOrganism->getImage()) {
            tryToBreed(collidingOrganism);
            return;
        }
        if (collidingOrganism.getStrength() < 5 && currentWorld.checkIfAnimal(collidingOrganism.getX(), collidingOrganism.getY())) {
            Animal collidingAnimal = dynamic_cast<Animal>(collidingOrganism);
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
        }
        else {
            baseFight(collidingOrganism);
            if (collidingOrganism.checkIfAlive() && collidingOrganism.getStrength() != strength) {
                collidingOrganism.collision(this);
            }
        }*/
    }
}
