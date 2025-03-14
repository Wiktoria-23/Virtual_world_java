import java.awt.*;
import java.util.Random;
public class Antelope extends Animal {
    private final int baseAntelopeSpeed = 2;
    public Antelope(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld);
        type = organismType.ANTELOPE;
        name = "Antylopa";
        color = Color.orange;
        speed = baseAntelopeSpeed; //base antelope speed
        strength = 4; //base antelope strength
        initiative = 4; //base antelope initiative
    }
    public Organism createChild(int xPosition, int yPosition) {
        return new Antelope(xPosition, yPosition, currentWorld);
    }
    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }
    public void collision(Organism collidingOrganism) {
        if (collidingOrganism.checkIfAlive() && !(collidingOrganism instanceof Antelope)) {
            Random rand = new Random();
            int survive = rand.nextInt(2);
            if (survive == 0) {
                if (
                        currentWorld.checkFieldXY(x, y - 1)
                        || currentWorld.checkFieldXY(x, y + 1)
                        || currentWorld.checkFieldXY(x - 1, y)
                        || currentWorld.checkFieldXY(x + 1, y)
                ) {
                    setSpeed(this.baseAnimalSpeed);
                    currentWorld.addEventInfo(name + " (" + x + ", " + y + ") ucieka od walki");
                    while (true) {
                        direction moveDirection = randMoveDirection();
                        if (checkMove(moveDirection)) {
                            setMoveDirection(moveDirection);
                            baseMovement();
                            setSpeed(this.baseAntelopeSpeed);
                            break;
                        }
                    }
                }
            }
            baseFight(collidingOrganism);
            if (collidingOrganism.checkIfAlive() && collidingOrganism.getStrength() != strength) {
                collidingOrganism.collision(this);
            }
        } else if (collidingOrganism.checkIfAlive() && collidingOrganism instanceof Antelope) {
            tryToBreed(collidingOrganism);
        }

    }
}
