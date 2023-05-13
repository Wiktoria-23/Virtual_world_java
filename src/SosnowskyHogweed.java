public class SosnowskyHogweed extends Plant {
    SosnowskyHogweed(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld);
        strength = 10; //base SosnowskyHogweed strength
    }
    Organism createChild(int xPosition, int yPosition) {
        Organism newSosnowskyHogweed = new SosnowskyHogweed(xPosition, yPosition, currentWorld);
        return newSosnowskyHogweed;
    }
    void action() {
        if (currentWorld.checkFieldXY(x - 1, y) && currentWorld.checkIfAnimal(x - 1, y)) {
            currentWorld.getOrganismFromXY(x - 1, y).setDeadState();
        }
        if (currentWorld.checkFieldXY(x + 1, y) && currentWorld.checkIfAnimal(x + 1, y)) {
            currentWorld.getOrganismFromXY(x + 1, y).setDeadState();
        }
        if (currentWorld.checkFieldXY(x, y - 1) && currentWorld.checkIfAnimal(x, y - 1)) {
            currentWorld.getOrganismFromXY(x, y - 1).setDeadState();
        }
        if (currentWorld.checkFieldXY(x, y + 1) && currentWorld.checkIfAnimal(x, y + 1)) {
            currentWorld.getOrganismFromXY(x, y + 1).setDeadState();
        }
        grow();
    }
    void collision(Organism collidingOrganism) {
        setDeadState();
        collidingOrganism.setDeadState();
    }
}
