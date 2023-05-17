import java.awt.*;

public class Human extends Animal {
    private boolean superpowerActive;
    private int roundCounter;
    public Human(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld);
        color = Color.pink;
        name = "Człowiek";
        type = organismType.HUMAN;
        roundCounter = 0;
        strength = 5; //base Human strength
        initiative = 4; //base Human initiative
        superpowerActive = false;
        moveDirection = direction.NONE;
    }
    public void action() {
        if (superpowerActive) {
            if (roundCounter == 0) {
                superpowerActive = false;
                roundCounter = 5;
                currentWorld.addEventInfo("Super umiejętność człowieka nie aktywna");
            }
            else {
                currentWorld.addEventInfo("Super umiejętność człowieka aktywna");
            }
        }
        else {
            currentWorld.addEventInfo("Super umiejętność człowieka nie aktywna");
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
    public void collision(Organism collidingOrganism) {
        if (superpowerActive && collidingOrganism.getStrength() > strength) {
            direction moveDirection = randMoveDirection();
            setMoveDirection(moveDirection);
            baseMovement();
        }
        else {
            super.collision(collidingOrganism);
        }
    }
    public void setDeadState(Organism collidingOrganism) {
        if (!superpowerActive) {
            currentWorld.addEventInfo(name + " (" + x + ", " + y + ") został zabity przez " + collidingOrganism.getName());
            alive = false;
        }
    }
    public boolean superpowerState() {
        return superpowerActive;
    }
    public void activateSuperpower() {
        roundCounter = 5;
        superpowerActive = true;
    }
    public void setRoundCounter(int newRoundCounter) {
        roundCounter = newRoundCounter;
    }
    public void setSuperpowerState(boolean newSuperpowerState) {
        superpowerActive = newSuperpowerState;
    }
    public Organism createChild(int xPosition, int yPosition) {
        return null;
    }
}
