import java.awt.*;

public class Wolf extends Animal {
    public Wolf(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld);
        color = Color.darkGray;
        strength = 9; //base Wolf strength
        initiative = 5; //base Wolf initiative
    }
    public Organism createChild(int xPosition, int yPosition) {
        Organism newWolf = new Wolf(xPosition, yPosition, currentWorld);
        return newWolf;
    }
}
