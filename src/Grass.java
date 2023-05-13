public class Grass extends Plant {
    Grass(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld);
    }
    Organism createChild(int xPosition, int yPosition) {
        Organism newGrass = new Grass(xPosition, yPosition, this.currentWorld);
        return newGrass;
    }
}
