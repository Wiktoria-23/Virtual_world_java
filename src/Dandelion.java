public class Dandelion extends Plant {
    Dandelion(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld);
    }
    Organism createChild(int xPosition, int yPosition) {
        Organism newDandelion = new Dandelion(xPosition, yPosition, currentWorld);
        return newDandelion;
    }
    void action() {
        for (int i = 0; i < 3; i++) {
            grow();
        }
    }
}
