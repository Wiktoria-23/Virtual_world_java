import java.awt.*;

public class Sheep extends Animal {
    public Sheep(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld);
        color = Color.cyan;
        name = "Owca";
        type = organismType.SHEEP;
        strength = 4; //base Sheep strength
        initiative = 4; //base Sheep initiative
    }
    public Organism createChild(int xPosition, int yPosition) {
        Organism newSheep = new Sheep(xPosition, yPosition, currentWorld);
        return newSheep;
    }
}
