import java.util.Random;
abstract public class Plant extends Organism {
    final int growChance = 40;
    Plant(int xPosition, int yPosition, World newWorld) {
        super(xPosition, yPosition, newWorld);
        initiative = 0; //base initiative
        strength = 0; //base strength
    }
    void action() {
        grow();
    }
    boolean checkPossibilityToGrow() {
        if (!currentWorld.checkFieldXY(x - 1, y) || !currentWorld.checkFieldXY(x + 1, y) || !currentWorld.checkFieldXY(x, y - 1) || !currentWorld.checkFieldXY(x, y + 1)) {
            return true;
        }
        return false;
    }
    void grow() {
        if (checkPossibilityToGrow()) {
            Random rand = new Random();
            int chanceToGrow = rand.nextInt(growChance);
            if (chanceToGrow == 0) {
                direction growDirection = super.randMoveDirection();
                if (growDirection == direction.UP && y - 1 >= 0) {
                    Organism newPlant = createChild(x, y - 1);
                    currentWorld.addOrganism(newPlant);
                }
                else if (growDirection == direction.DOWN && y + 1 < currentWorld.getBoardSizeY()) {
                    Organism newPlant = createChild(x, y + 1);
                    currentWorld.addOrganism(newPlant);
                }
                else if (growDirection == direction.RIGHT && x + 1 < currentWorld.getBoardSizeX()) {
                    Organism newPlant = createChild(x + 1, y);
                    currentWorld.addOrganism(newPlant);
                }
                else if (growDirection == direction.LEFT && x - 1 >= 0) {
                    Organism newPlant = createChild(x - 1, y);
                    currentWorld.addOrganism(newPlant);
                }
            }
        }
    }
}
