import java.util.Random;
abstract public class Animal extends Organism {
    protected final int baseAnimalSpeed = 1;
    protected int speed = baseAnimalSpeed; //base animal speed
    protected direction moveDirection;
    Animal(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld) ;
        moveDirection = direction.NONE;
    }
    direction randMoveDirection() {
        return super.randMoveDirection();
    }
    void baseMovement() {
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
    void setMoveDirection(direction newMoveDirection) {
        if (this.checkMove(newMoveDirection)) {
            moveDirection = newMoveDirection;
        }
	else {
            moveDirection = direction.NONE;
        }
    }
    boolean checkMove(direction newMoveDirection) {
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
    void action() {
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
    direction getMoveDirection() {
        return moveDirection;
    }
    void breed(int xPosition, int yPosition) {
        Organism newAnimal = createChild(xPosition, yPosition);
        currentWorld.addOrganism(newAnimal);
    }
    boolean checkSameType(Organism collidingOrganism) {
       /* if (image == collidingOrganism.getImage()) {
            return true;
        }*/
        return false;
    }
    void tryToBreed(Organism collidingOrganism) {
       /* if ((currentWorld.checkFieldXY(x, y - 1) && currentWorld.getOrganismFromXY(x, y - 1).getImage()) != image || (currentWorld.checkFieldXY(x, y + 1) && currentWorld->getOrganismFromXY(x, y + 1).getImage() != image) || (currentWorld->checkFieldXY(x - 1, y) && currentWorld->getOrganismFromXY(x - 1, y).getImage() != image) || (currentWorld->checkFieldXY(x + 1, y) && currentWorld->getOrganismFromXY(x + 1, y).getImage() != image)) {
            direction breedDirection = (direction)(rand() % 4);
            if (breedDirection == DOWN && y + 1 < currentWorld->getBoardSizeY() && y + 1 != collidingOrganism->getY()) {
                if ((currentWorld->checkFieldXY(x, y + 1) && currentWorld->getOrganismFromXY(x, y + 1).getImage() != image && currentWorld->getOrganismFromXY(x, y + 1).getAge() > 0) || !currentWorld->checkFieldXY(x, y + 1)) {
                    breed(x, y + 1);
                    currentWorld->addAnimalBreedInfo(*this);
                }
            }
            else if (breedDirection == UP && y - 1 >= 0 && y - 1 != collidingOrganism->getY()) {
                if ((currentWorld->checkFieldXY(x, y - 1) && currentWorld->getOrganismFromXY(x, y - 1).getImage() != image && currentWorld->getOrganismFromXY(x, y - 1).getAge() > 0) || !currentWorld->checkFieldXY(x, y - 1)) {
                    breed(x, y - 1);
                    currentWorld->addAnimalBreedInfo(*this);
                }
            }
            else if (breedDirection == RIGHT && x + 1 < currentWorld->getBoardSizeX() && x + 1 != collidingOrganism->getX()) {
                if ((currentWorld->checkFieldXY(x + 1, y) && currentWorld->getOrganismFromXY(x + 1, y).getImage() != image && currentWorld->getOrganismFromXY(x + 1, y).getAge() > 0) || !currentWorld->checkFieldXY(x + 1, y)) {
                    breed(x + 1, y);
                    currentWorld->addAnimalBreedInfo(*this);
                }
            }
            else if (breedDirection == LEFT && x - 1 >= 0 && x - 1 != collidingOrganism->getX()) {
                if ((currentWorld->checkFieldXY(x - 1, y) && currentWorld->getOrganismFromXY(x - 1, y).getImage() != image && currentWorld->getOrganismFromXY(x - 1, y).getAge() > 0) || !currentWorld->checkFieldXY(x - 1, y)) {
                    breed(x - 1, y);
                    currentWorld->addAnimalBreedInfo(*this);
                }
            }
        }*/
    }
    void collision(Organism collidingOrganism) {
        if (getClass().equals(collidingOrganism.getClass())) {
            tryToBreed(collidingOrganism);
            return;
        }
        else if (collidingOrganism.checkIfAlive() && collidingOrganism.getStrength() != strength) {
            super.collision(collidingOrganism);
        }
        baseMovement();
    }
}
