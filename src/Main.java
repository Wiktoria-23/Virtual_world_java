import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        boolean active = true;
        World newWorld = new World();
        BoardSizeChoice choiceWindow = new BoardSizeChoice(newWorld);
        newWorld.initWorld();
        Screen mainWindow = new Screen(newWorld.getBoardSizeX(), newWorld.getBoardSizeY());
        mainWindow.show();
        /*
        while (active) {
            character = _getch();
            if (character == ARROW_CHAR) {
                character = _getch();
                if (newWorld.getHuman() != nullptr) {
                    if (character == KEY_ARROW_UP) {
                        newWorld.getHuman()->setMoveDirection(UP);
                    }
                    else if (character == KEY_ARROW_DOWN) {
                        newWorld.getHuman()->setMoveDirection(DOWN);
                    }
                    else if (character == KEY_ARROW_LEFT) {
                        newWorld.getHuman()->setMoveDirection(LEFT);
                    }
                    else if (character == KEY_ARROW_RIGHT) {
                        newWorld.getHuman()->setMoveDirection(RIGHT);
                    }
                }
            }
            else if (character == 'x' && newWorld.getHuman() != nullptr && newWorld.getHuman()->canSuperpowerBeActivated()) {
                newWorld.getHuman()->activateSuperpower();
            }
		else if (character == 'z') {
                system("cls");
                cout << "Podaj nazwe pliku, w ktorym chcesz zapisac stan: " << endl;
                string filename;
                cin >> filename;
                newWorld.sortOrganisms();
                newWorld.save(filename);
                newWorld.printWorld();
            }
            else if (character == 'w') {
                system("cls");
                cout << "Podaj nazwe pliku, z ktorego chcesz odczytac stan: " << endl;
                string filename;
                cin >> filename;
                newWorld.loadFromFile(filename);
                newWorld.printWorld();
            }
            else if (character == NEW_ROUND) {
                newWorld.performRound();
                newWorld.incrementRoundCounter();
                newWorld.printWorld();
            }*/
    }
}