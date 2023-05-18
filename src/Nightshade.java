import java.awt.*;

public class Nightshade extends Plant {
    public Nightshade(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld);
        color = Color.magenta;
        name = "Wilcza jagoda";
        type = organismType.NIGHTSHADE;
        strength = 99; //base Nightshade strength
    }
    public Organism createChild(int xPosition, int yPosition) {
        return new Nightshade(xPosition, yPosition, currentWorld);
    }
    public void collision(Organism collidingOrganism) {
        setDeadState(collidingOrganism);
        collidingOrganism.setDeadState(this);
    }
}
