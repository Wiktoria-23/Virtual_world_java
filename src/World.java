import java.util.ArrayList;

public class World {
    private int roundCounter;
    private int boardSizeX;
    private int boardSizeY;
    /*ArrayList<String> allEventsInfo;*/
    ArrayList<Organism> allOrganisms;
    public World() {
        roundCounter = 0;
    }
    public void initWorld() {
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
        /*String info = new String("Stworzono wszystkie organizmy");*/
       /* allEventsInfo.add(info);*/
    }
    public int countOrganismsAmount() {
        float field = boardSizeX * boardSizeY;
        int maxOccupied = (int)Math.ceil(field / 100 * 2); // setting maxOccupied field by organism type to 2% of whole field
        return maxOccupied + 1;
    }
    public void performRound() {
        for (int i = 0; i < allOrganisms.size(); i++) {
            if (allOrganisms.get(i).checkIfAlive()) {
                allOrganisms.get(i).action();
                allOrganisms.get(i).incrementAgeCounter();
            }
        }
        for (int i = allOrganisms.size() - 1; i >= 0; i--) {
            if (!allOrganisms.get(i).checkIfAlive()) {
                Organism tmp = allOrganisms.get(i);
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
    public int getRoundCounter() {
        return roundCounter;
    }
    public void incrementRoundCounter() {
        roundCounter += 1;
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
    /*void save(String filename) {
        fstream file;
        file.open(filename, fstream::out);
        file << boardSizeX << endl;
        file << boardSizeY << endl;
        file << allOrganisms.size() << endl;
        for (int i = 0; i < allOrganisms.size(); i++) {
            file << allOrganisms[i]->getImage() << endl << allOrganisms[i]->checkIfAlive() << endl << allOrganisms[i]->getAge() << endl << allOrganisms[i]->getInitiative() << endl;
            file << allOrganisms[i]->getStrength() << endl << allOrganisms[i]->getX() << endl << allOrganisms[i]->getY() << endl;
            if (allOrganisms[i]->getImage() == HUMAN_IMAGE) {
                Human* humanPointer = dynamic_cast<Human*>(allOrganisms[i]);
                file << humanPointer->superpowerState() << endl << humanPointer->getRoundCounter() << endl;
            }
        }
        file.close();
    }*/
    /*void loadFromFile(String filename) {
        fstream file;
        file.open(filename, fstream::in);
        if (file.is_open()) {
            for (int i = allOrganisms.size() - 1; i >= 0; i--) {
                Organism* tmp = allOrganisms[i];
                allOrganisms.erase(allOrganisms.begin() + i);
                delete tmp;
            }
            int amountOfOrganisms;
            file >> boardSizeX;
            file >> boardSizeY;
            file >> amountOfOrganisms;
            char organismImage;
            bool alive, superpowerActive;
            int age, initiative, strength, x, y, roundCount;
            for (int i = 0; i < amountOfOrganisms; i++) {
                file >> organismImage >> alive >> age >> initiative >> strength >> x >> y;
                Organism* newOrganism = nullptr;
                if (organismImage == ANTELOPE_IMAGE) {
                    newOrganism = new Antelope(x, y, this);
                }
                else if (organismImage == DANDELION_IMAGE) {
                    newOrganism = new Dandelion(x, y, this);
                }
                else if (organismImage == FOX_IMAGE) {
                    newOrganism = new Fox(x, y, this);
                }
                else if (organismImage == GRASS_IMAGE) {
                    newOrganism = new Grass(x, y, this);
                }
                else if (organismImage == GUARANA_IMAGE) {
                    newOrganism = new Guarana(x, y, this);
                }
                else if (organismImage == HUMAN_IMAGE) {
                    newOrganism = new Human(x, y, this);
                    file >> superpowerActive >> roundCounter;
                }
                else if (organismImage == NIGHTSHADE_IMAGE) {
                    newOrganism = new Nightshade(x, y, this);
                }
                else if (organismImage == SHEEP_IMAGE) {
                    newOrganism = new Sheep(x, y, this);
                }
                else if (organismImage == SOSNOWSKY_HOGWEED_IMAGE) {
                    newOrganism = new SosnowskyHogweed(x, y, this);
                }
                else if (organismImage == TURTLE_IMAGE) {
                    newOrganism = new Turtle(x, y, this);
                }
                else if (organismImage == WOLF_IMAGE) {
                    newOrganism = new Wolf(x, y, this);
                }
                newOrganism->setStrength(strength);
                newOrganism->setInitiative(initiative);
                newOrganism->setAliveState(alive);
                newOrganism->setAge(age);
                if (newOrganism->getImage() == HUMAN_IMAGE) {
                    Human* humanPointer = dynamic_cast<Human*>(newOrganism);
                    humanPointer->setSuperpowerState(superpowerActive);
                    humanPointer->setRoundCounter(roundCounter);
                }
                allOrganisms.push_back(newOrganism);
            }
            file.close();
        }
        else {
            cout << "Plik nie istnieje!";
            char input = EMPTY;
            while (true) {
                input = _getch();
                if (input == NEW_LINE) {
                    break;
                }
            }
        }
    }*/
}
