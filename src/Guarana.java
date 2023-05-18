import java.awt.*;

public class Guarana extends Plant {
    private final int strengthIncrease = 3; //Guarana increases strength of an animal
    public Guarana(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld);
        name = "Guarana";
        type = organismType.GUARANA;
        color = Color.gray;
    }
    public Organism createChild(int xPosition, int yPosition) {
        return new Guarana(xPosition, yPosition, currentWorld);
    }
    public void collision(Organism collidingOrganism) {
        if (currentWorld.checkIfAnimal(collidingOrganism.getX(), collidingOrganism.getY())) {
            collidingOrganism.increaseStrength(strengthIncrease);
            setDeadState(collidingOrganism);
        }
        else {
            super.collision(collidingOrganism);
        }
    }
}
