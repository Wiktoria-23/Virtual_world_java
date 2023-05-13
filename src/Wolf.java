public class Wolf extends Animal {
    Wolf(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld);
        strength = 9; //base Wolf strength
        initiative = 5; //base Wolf initiative
    }
    Organism createChild(int xPosition, int yPosition) {
        Organism newWolf = new Wolf(xPosition, yPosition, currentWorld);
        return newWolf;
    }
}
