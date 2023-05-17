import java.awt.*;

public class Grass extends Plant {
    public Grass(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld);
        name = "Trawa";
        type = organismType.GRASS;
        color = Color.green;
    }
    public Organism createChild(int xPosition, int yPosition) {
        Organism newGrass = new Grass(xPosition, yPosition, this.currentWorld);
        return newGrass;
    }
}
