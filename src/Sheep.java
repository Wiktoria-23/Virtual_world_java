public class Sheep extends Animal {
    Sheep(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld);
        strength = 4; //base Sheep strength
        initiative = 4; //base Sheep initiative
    }
    Organism createChild(int xPosition, int yPosition) {
        Organism newSheep = new Sheep(xPosition, yPosition, currentWorld);
        return newSheep;
    }
}
