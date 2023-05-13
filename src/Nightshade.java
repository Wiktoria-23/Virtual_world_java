public class Nightshade extends Plant {
    Nightshade(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld);
        strength = 99; //base Nightshade strength
    }
    Organism createChild(int xPosition, int yPosition) {
        Organism newNightshade = new Nightshade(xPosition, yPosition, currentWorld);
        return newNightshade;
    }
    void collision(Organism collidingOrganism) {
        setDeadState();
        collidingOrganism.setDeadState();
    }
}
