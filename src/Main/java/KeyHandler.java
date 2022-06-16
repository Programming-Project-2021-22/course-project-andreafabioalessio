

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public static boolean upPressed, downPressed, leftPressed, rightPressed;



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP || code == KeyEvent.VK_SPACE){ // se premi w
            upPressed = true;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){ // se premi a
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){ // se premi s
            downPressed = true;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){ // se premi d
            rightPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE){
            Gamestate.state = Gamestate.MAINMENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP || code == KeyEvent.VK_SPACE){ // se premi w
            upPressed = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){ // se premi a
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){ // se premi s
            downPressed = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){ // se premi d
            rightPressed = false;
        }
    }


    public static void resetKeyHandler(){
        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;
    }

    public boolean getUp(){
        return upPressed;
    }
    public boolean getDown(){
        return downPressed;
    }
    public boolean getLeft(){
        return leftPressed;
    }
    public boolean getRight(){
        return rightPressed;
    }

}
