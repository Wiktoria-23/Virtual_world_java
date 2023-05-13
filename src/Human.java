public class Human extends Animal {
    private boolean superpowerActive;
    private int roundCounter;
    Human(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld);
        roundCounter = 0;
        strength = 5; //base Human strength
        initiative = 4; //base Human initiative
        superpowerActive = false;
        moveDirection = direction.NONE;
    }
    void action() {
        if (superpowerActive) {
            if (roundCounter == 0) {
                superpowerActive = false;
                roundCounter = 5;
            }
        }
        else {

        }
        if (moveDirection != direction.NONE && checkMove(moveDirection)) {
            Organism collidingOrganism = getCollision(moveDirection);
            if (collidingOrganism != null && collidingOrganism.checkIfAlive()) {
                collision(collidingOrganism);
            }
            else {
                baseMovement();
            }
        }
        moveDirection = direction.NONE;
        if (roundCounter > 0) {
            roundCounter -= 1;
        }
    }
    boolean canSuperpowerBeActivated() {
        if (roundCounter == 0 && !superpowerActive) {
            return true;
        }
        return false;
    }
    int getRoundCounter() {
        return roundCounter;
    }
    void collision(Organism collidingOrganism) {
        if (superpowerActive && collidingOrganism.getStrength() > strength) {
            direction moveDirection = randMoveDirection();
            setMoveDirection(moveDirection);
            baseMovement();
        }
        else {
            super.collision(collidingOrganism);
            baseMovement();
        }
    }
    void setDeadState() {
        if (superpowerActive == false) {
            alive = false;
        }
    }
    boolean superpowerState() {
        return superpowerActive;
    }
    void activateSuperpower() {
        roundCounter = 5;
        superpowerActive = true;
    }
    void setRoundCounter(int newRoundCounter) {
        roundCounter = newRoundCounter;
    }
    void setSuperpowerState(boolean newSuperpowerState) {
        superpowerActive = newSuperpowerState;
    }
    Organism createChild(int xPosition, int yPosition) {
        return null;
    }
}
