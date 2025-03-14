import java.awt.*;

public class SosnowskyHogweed extends Plant {
    public SosnowskyHogweed(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld);
        color = Color.black;
        name = "Barszcz sosnowskiego";
        type = organismType.SOSNOWSKY_HOGWEED;
        strength = 10; //base SosnowskyHogweed strength
    }
    public Organism createChild(int xPosition, int yPosition) {
        return new SosnowskyHogweed(xPosition, yPosition, currentWorld);
    }
    public void action() {
        if (currentWorld.checkFieldXY(x - 1, y) && currentWorld.checkIfAnimal(x - 1, y)) {
            currentWorld.getOrganismFromXY(x - 1, y).setDeadState(this);
        }
        if (currentWorld.checkFieldXY(x + 1, y) && currentWorld.checkIfAnimal(x + 1, y)) {
            currentWorld.getOrganismFromXY(x + 1, y).setDeadState(this);
        }
        if (currentWorld.checkFieldXY(x, y - 1) && currentWorld.checkIfAnimal(x, y - 1)) {
            currentWorld.getOrganismFromXY(x, y - 1).setDeadState(this);
        }
        if (currentWorld.checkFieldXY(x, y + 1) && currentWorld.checkIfAnimal(x, y + 1)) {
            currentWorld.getOrganismFromXY(x, y + 1).setDeadState(this);
        }
        grow();
    }
    public void collision(Organism collidingOrganism) {
        setDeadState(collidingOrganism);
        collidingOrganism.setDeadState(this);
    }
}
