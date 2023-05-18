import java.util.ArrayList;

public class World {
    private int roundCounter;
    private int boardSizeX;
    private int boardSizeY;
    private ArrayList<String> allEventsInfo;
    private ArrayList<Organism> allOrganisms;
    public World() {
        roundCounter = 0;
    }
    public void initWorld() {
        allEventsInfo = new ArrayList<String>();
        allOrganisms = new ArrayList<Organism>();
        int amount = countOrganismsAmount();
        for (int i = 0; i < amount; i++) {
            Coordinates emptyField = new Coordinates(this);
            Sheep newSheep = new Sheep(emptyField.getX(), emptyField.getY(), this);
            allOrganisms.add(newSheep);
            emptyField = new Coordinates(this);
            Fox newFox = new Fox(emptyField.getX(), emptyField.getY(), this);
            allOrganisms.add(newFox);
            emptyField = new Coordinates(this);
            Turtle newTurtle = new Turtle(emptyField.getX(), emptyField.getY(), this);
            allOrganisms.add(newTurtle);
            emptyField = new Coordinates(this);
            Wolf newWolf = new Wolf(emptyField.getX(), emptyField.getY(), this);
            allOrganisms.add(newWolf);
            emptyField = new Coordinates(this);
            Antelope newAntelope = new Antelope(emptyField.getX(), emptyField.getY(), this);
            allOrganisms.add(newAntelope);
            emptyField = new Coordinates(this);
            Grass newGrass = new Grass(emptyField.getX(), emptyField.getY(), this);
            allOrganisms.add(newGrass);
            emptyField = new Coordinates(this);
            Dandelion newDandelion = new Dandelion(emptyField.getX(), emptyField.getY(), this);
            allOrganisms.add(newDandelion);
            emptyField = new Coordinates(this);
            Guarana newGuarana = new Guarana(emptyField.getX(), emptyField.getY(), this);
            allOrganisms.add(newGuarana);
            emptyField = new Coordinates(this);
            Nightshade newNightshade = new Nightshade(emptyField.getX(), emptyField.getY(), this);
            allOrganisms.add(newNightshade);
            emptyField = new Coordinates(this);
            SosnowskyHogweed newSosnowskyHogweed = new SosnowskyHogweed(emptyField.getX(), emptyField.getY(), this);
            allOrganisms.add(newSosnowskyHogweed);
        }
        Coordinates emptyField = new Coordinates(this);
        Human newHuman = new Human(emptyField.getX(), emptyField.getY(), this);
        allOrganisms.add(newHuman);
        sortOrganisms();
    }
    public int countOrganismsAmount() {
        float field = boardSizeX * boardSizeY;
        int maxOccupied = (int)Math.ceil(field / 100 * 2); // setting maxOccupied field by organism type to 2% of whole field
        return maxOccupied + 1;
    }
    public ArrayList<String> getAllEventsInfo() {
        return allEventsInfo;
    }
    public void addEventInfo(String info) {
        allEventsInfo.add(info);
    }
    public void performRound() {
        for (int i = 0; i < allOrganisms.size(); i++) {
            if (allOrganisms.get(i).checkIfAlive()) {
                allOrganisms.get(i).action();
                allOrganisms.get(i).incrementAgeCounter();
                sortOrganisms();
            }
        }
        for (int i = allOrganisms.size() - 1; i >= 0; i--) {
            if (!allOrganisms.get(i).checkIfAlive()) {
                if (allOrganisms.get(i) instanceof Human) {
                    Human humanPointer = (Human)(allOrganisms.get(i));
                    if (!humanPointer.superpowerState()) {
                        allOrganisms.remove(i);
                    }
                }
                else {
                        allOrganisms.remove(i);
                }
            }
        }
    }
    public boolean checkFieldXY(int x, int y) {
        int organismsAmount = allOrganisms.size();
        for (int i = 0; i < organismsAmount; i++) {
            if (allOrganisms.get(i).getX() == x && allOrganisms.get(i).getY() == y) {
                return true;
            }
        }
        return false;
    }
    public int getBoardSizeX() {
        return boardSizeX;
    }
    public int getBoardSizeY() {
        return boardSizeY;
    }
    public void setBoardSizeX(int newX) {
        boardSizeX = newX;
    }
    public void setBoardSizeY(int newY) {
        boardSizeY = newY;
    }
    public Organism getOrganismFromXY(int x, int y) {
        for (int i = 0; i < allOrganisms.size(); i++) {
            if (allOrganisms.get(i).getX() == x && allOrganisms.get(i).getY() == y) {
                return allOrganisms.get(i);
            }
        }
        return null;
    }
    public boolean checkIfAnimal(int xPosition, int yPosition) {
        Organism neighbourOrganism;
        if (xPosition >= 0 && xPosition < boardSizeX && yPosition >= 0 && yPosition < boardSizeY) {
            if (checkFieldXY(xPosition, yPosition)) {
                neighbourOrganism = getOrganismFromXY(xPosition, yPosition);
            }
            else {
                neighbourOrganism = null;
            }
            if (neighbourOrganism instanceof Animal) {
                return true;
            }
        }
        return false;
    }
    public Human getHuman() {
        for (int i = 0; i < allOrganisms.size(); i++) {
            if (allOrganisms.get(i) instanceof Human) {
                return (Human)allOrganisms.get(i);
            }
        }
        return null;
    }
    public void addOrganism(Organism newOrganism) {
        allOrganisms.add(newOrganism);
    }
    public void sortOrganisms() {
        int i = 0;
        for (int j = i; j < allOrganisms.size(); j++) {
            int highestInitiative = 0;
            for (int k = i; k < allOrganisms.size(); k++) {
                if (allOrganisms.get(k).getInitiative() > highestInitiative) {
                    highestInitiative = allOrganisms.get(k).getInitiative();
                }
            }
            int highestAge = 0;
            for (int l = i; l < allOrganisms.size(); l++) {
                if (allOrganisms.get(l).getInitiative() == highestInitiative && allOrganisms.get(l).getAge() > highestAge) {
                    highestAge = allOrganisms.get(l).getAge();
                }
            }
            int m = i;
            while (allOrganisms.get(m).getInitiative() != highestInitiative && allOrganisms.get(m).getAge() == highestAge) {
                m++;
            }
            allOrganisms.add(i, allOrganisms.get(m));
            allOrganisms.remove(m + 1);
            i++;
        }
    }
    public ArrayList<Organism> getAllOrganisms() {
        return allOrganisms;
    }
}
