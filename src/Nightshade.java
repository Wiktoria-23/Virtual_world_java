import java.awt.*;

public class Nightshade extends Plant {
    public Nightshade(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld);
        color = Color.magenta;
        strength = 99; //base Nightshade strength
    }
    public Organism createChild(int xPosition, int yPosition) {
        Organism newNightshade = new Nightshade(xPosition, yPosition, currentWorld);
        return newNightshade;
    }
    public void collision(Organism collidingOrganism) {
        setDeadState();
        collidingOrganism.setDeadState();
    }
}
