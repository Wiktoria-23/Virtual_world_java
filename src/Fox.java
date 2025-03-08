import java.awt.*;

public class Fox extends Animal {
    public Fox(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld);
        color = Color.red;
        type = organismType.FOX;
        name = "Lis";
        strength = 3; //base fox strength
        initiative = 7; //base fox initiative
    }
    public Organism createChild(int xPosition, int yPosition) {
        return new Fox(xPosition, yPosition, currentWorld);
    }
    public boolean checkField(direction moveDirection) {
        if (moveDirection == direction.LEFT) {
            if (currentWorld.checkFieldXY(x - speed, y)) {
                if (currentWorld.getOrganismFromXY(x - speed, y) instanceof Fox) {
                    return true;
                }
                if (currentWorld.getOrganismFromXY(x - speed, y).getStrength() > strength) {
                    return false;
                }
            }
            return true;
        }
        else if (moveDirection == direction.RIGHT) {
            if (currentWorld.checkFieldXY(x + speed, y)) {
                if (currentWorld.getOrganismFromXY(x + speed, y) instanceof Fox) {
                    return true;
                }
                if (currentWorld.getOrganismFromXY(x + speed, y).getStrength() > strength) {
                    return false;
                }
            }
            return true;
        }
        else if (moveDirection == direction.UP) {
            if (currentWorld.checkFieldXY(x, y - speed)) {
                if (currentWorld.getOrganismFromXY(x, y - speed) instanceof Fox) {
                    return true;
                }
                if (currentWorld.getOrganismFromXY(x, y - speed).getStrength() > strength) {
                    return false;
                }
            }
            return true;
        }
        else if (moveDirection == direction.DOWN) {
            if (currentWorld.checkFieldXY(x, y + speed)) {
                if (currentWorld.getOrganismFromXY(x, y + speed) instanceof Fox) {
                    return true;
                }
                if (currentWorld.getOrganismFromXY(x, y + speed).getStrength() > strength) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    public boolean checkIfAnyMovePossible() {
        if (x < 0 || x >= currentWorld.getBoardSizeX() || y < 0 || y >= currentWorld.getBoardSizeY()) {
            return false;
        }
        if (
                (currentWorld.checkFieldXY(x - speed, y) && currentWorld.getOrganismFromXY(x - speed, y).getStrength() < strength)
                || (currentWorld.checkFieldXY(x + speed, y) && currentWorld.getOrganismFromXY(x + speed, y).getStrength() < strength)
                || (currentWorld.checkFieldXY(x, y - speed) && currentWorld.getOrganismFromXY(x, y - speed).getStrength() < strength)
                || (currentWorld.checkFieldXY(x, y + speed) && currentWorld.getOrganismFromXY(x, y + speed).getStrength() < strength)) {
            return true;
        }
        else if (
                (!currentWorld.checkFieldXY(x - speed, y) && x - 1 >= 0)
                || (!currentWorld.checkFieldXY(x + speed, y) && x + speed < currentWorld.getBoardSizeX())
                || (!currentWorld.checkFieldXY(x, y - speed) && y - speed >= 0)
                || (!currentWorld.checkFieldXY(x, y + speed) && y + speed < currentWorld.getBoardSizeX())
        ) {
            return true;
        }
        return false;
    }
    public void action() {
        if (checkIfAnyMovePossible()) {
            direction moveDirection;
            while (true) {
               moveDirection = randMoveDirection();
                if (checkMove(moveDirection)) {
                    if (checkField(moveDirection)) {
                        Organism collidingOrganism = getCollision(moveDirection);
                        if (collidingOrganism != null) {
                            collision(collidingOrganism);
                        }
                        setMoveDirection(moveDirection);
                        baseMovement();
                        break;
                    }
                }
            }
        }
    }
}
