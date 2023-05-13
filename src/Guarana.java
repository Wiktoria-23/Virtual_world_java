import java.awt.*;

public class Guarana extends Plant {
    final int strengthIncrease = 3; //Guarana increases strength of an animal
    public Guarana(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld);
        color = Color.gray;
    }
    public Organism createChild(int xPosition, int yPosition) {
        Organism newGuarana = new Guarana(xPosition, yPosition, currentWorld);
        return newGuarana;
    }
    public void collision(Organism collidingOrganism) {
        if (currentWorld.checkIfAnimal(collidingOrganism.getX(), collidingOrganism.getY())) {
            collidingOrganism.increaseStrength(strengthIncrease);
            setDeadState();
        }
        else {
            super.collision(collidingOrganism);
        }
    }
}
