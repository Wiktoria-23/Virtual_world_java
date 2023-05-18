import java.awt.Color;
public class Dandelion extends Plant {
    public Dandelion(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld);
        type = organismType.DANDELION;
        name = "Mlecz";
        color = Color.yellow;
    }
    public Organism createChild(int xPosition, int yPosition) {
        return new Dandelion(xPosition, yPosition, currentWorld);
    }
    public void action() {
        for (int i = 0; i < 3; i++) {
            grow();
        }
    }
}
