import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
    private World world;
    private Controller controller;
    public KeyboardListener(Controller newController) {
        world = newController.getWorld();
        controller = newController;
    }
    public void keyTyped(KeyEvent e) {

    }
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && world.getHuman() != null) {
            world.getHuman().setMoveDirection(Organism.direction.UP);
            controller.newRound();
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && world.getHuman() != null) {
            world.getHuman().setMoveDirection(Organism.direction.DOWN);
            controller.newRound();
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && world.getHuman() != null) {
            world.getHuman().setMoveDirection(Organism.direction.LEFT);
            controller.newRound();
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && world.getHuman() != null) {
            world.getHuman().setMoveDirection(Organism.direction.RIGHT);
            controller.newRound();
        }
        else if (e.getKeyCode() == KeyEvent.VK_X) {
            world.getHuman().activateSuperpower();
        }
    }
    public void keyReleased(KeyEvent e) {

    }
}
