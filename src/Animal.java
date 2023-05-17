import java.util.Random;
abstract public class Animal extends Organism {
    protected final int baseAnimalSpeed = 1;
    protected int speed = baseAnimalSpeed; //base animal speed
    protected direction moveDirection;
    public Animal(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld) ;
        moveDirection = direction.NONE;
    }
    public direction randMoveDirection() {
        return super.randMoveDirection();
    }
    public void baseMovement() {
        if (moveDirection == direction.LEFT) {
            x -= speed;
        }
        else if (moveDirection == direction.RIGHT) {
            x += speed;
        }
        else if (moveDirection == direction.UP) {
            y -= speed;
        }
        else if (moveDirection == direction.DOWN) {
            y += speed;
        }
    }
    public void setMoveDirection(direction newMoveDirection) {
        if (this.checkMove(newMoveDirection)) {
            moveDirection = newMoveDirection;
        }
	    else {
            moveDirection = direction.NONE;
        }
    }
    public boolean checkMove(direction newMoveDirection) {
        int tmpX = x;
        int tmpY = y;
        if (newMoveDirection == direction.LEFT) {
            tmpX -= speed;
        }
        else if (newMoveDirection == direction.RIGHT) {
            tmpX += speed;
        }
        else if (newMoveDirection == direction.UP) {
            tmpY -= speed;
        }
        else if (newMoveDirection == direction.DOWN) {
            tmpY += speed;
        }
        if (tmpX >= 0 && tmpX < currentWorld.getBoardSizeX() && tmpY >= 0 && tmpY < currentWorld.getBoardSizeY()) {
            return true;
        }
        return false;
    }
    public void action() {
        while (true) {
            moveDirection = randMoveDirection();
            if (checkMove(moveDirection)) {
                Organism collidingOrganism = getCollision(moveDirection);
                if (collidingOrganism != null && collidingOrganism.checkIfAlive()) {
                    collision(collidingOrganism);
                }
                else {
                    baseMovement();
                }
                break;
            }
            break;
        }
    }
    public direction getMoveDirection() {
        return moveDirection;
    }
    public void breed(int xPosition, int yPosition) {
        currentWorld.addEventInfo(name + " (" + x + ", " + y + ") rozmnożył się");
        Organism newAnimal = createChild(xPosition, yPosition);
        currentWorld.addOrganism(newAnimal);
    }
    public void tryToBreed(Organism collidingOrganism) {
        if (currentWorld.getOrganismFromXY(x, y - 1) == null || (currentWorld.getOrganismFromXY(x, y - 1).getColor()) != color || currentWorld.getOrganismFromXY(x, y + 1) == null || currentWorld.getOrganismFromXY(x, y + 1).getColor() != color || currentWorld.getOrganismFromXY(x - 1, y) == null || currentWorld.getOrganismFromXY(x - 1, y).getColor() != color || currentWorld.getOrganismFromXY(x + 1, y) == null || currentWorld.getOrganismFromXY(x + 1, y).getColor() != color) {
            direction breedDirection = randMoveDirection();
            if (breedDirection == direction.DOWN && y + 1 < currentWorld.getBoardSizeY() && y + 1 != collidingOrganism.getY()) {
                if ((currentWorld.checkFieldXY(x, y + 1) && currentWorld.getOrganismFromXY(x, y + 1).getColor() != color && currentWorld.getOrganismFromXY(x, y + 1).getAge() > 0) || !currentWorld.checkFieldXY(x, y + 1)) {
                    breed(x, y + 1);
                }
            }
            else if (breedDirection == direction.UP && y - 1 >= 0 && y - 1 != collidingOrganism.getY()) {
                if ((currentWorld.checkFieldXY(x, y - 1) && currentWorld.getOrganismFromXY(x, y - 1).getColor() != color && currentWorld.getOrganismFromXY(x, y - 1).getAge() > 0) || !currentWorld.checkFieldXY(x, y - 1)) {
                    breed(x, y - 1);
                }
            }
            else if (breedDirection == direction.RIGHT && x + 1 < currentWorld.getBoardSizeX() && x + 1 != collidingOrganism.getX()) {
                if ((currentWorld.checkFieldXY(x + 1, y) && currentWorld.getOrganismFromXY(x + 1, y).getColor() != color && currentWorld.getOrganismFromXY(x + 1, y).getAge() > 0) || !currentWorld.checkFieldXY(x + 1, y)) {
                    breed(x + 1, y);
                }
            }
            else if (breedDirection == direction.LEFT && x - 1 >= 0 && x - 1 != collidingOrganism.getX()) {
                if ((currentWorld.checkFieldXY(x - 1, y) && currentWorld.getOrganismFromXY(x - 1, y).getColor() != color && currentWorld.getOrganismFromXY(x - 1, y).getAge() > 0) || !currentWorld.checkFieldXY(x - 1, y)) {
                    breed(x - 1, y);
                }
            }
        }
    }
    public void collision(Organism collidingOrganism) {
        if (type == collidingOrganism.getType()) {
            tryToBreed(collidingOrganism);
            return;
        }
        else if (collidingOrganism.checkIfAlive() && collidingOrganism.getStrength() != strength) {
            super.collision(collidingOrganism);
        }
        baseMovement();
    }
}
