import enigma.console.TextWindow;
import enigma.core.Enigma;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game {
    public static TextWindow console = Enigma.getConsole("Console").getTextWindow();

    public KeyListener klis; // used receiving input from keyboard
    public int keypr; // key pressed?
    public int rkey; // key (for press/release)
    private GameScreen screen = new GameScreen();

    Game() throws Exception { // --- Constructor
        // Standard code for keyboard
        klis = new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                if (keypr == 0) {
                    keypr = 1;
                    rkey = e.getKeyCode();
                }
            }

            public void keyReleased(KeyEvent e) {
            }
        };
        console.addKeyListener(klis);


        //boolean gameStarted = false;// controls if the initial screen part is over for the other keys to involved
        while (true) {
            if (keypr == 1) { // if keyboard button pressed
                if (gameStarted) {

                    if (rkey == KeyEvent.VK_LEFT || rkey == KeyEvent.VK_RIGHT || rkey == KeyEvent.VK_UP
                            || rkey == KeyEvent.VK_DOWN) { //Arrow keys control for pieces area



                        if (rkey == KeyEvent.VK_LEFT) {

                        } else if (rkey == KeyEvent.VK_RIGHT) {


                        } else if (rkey == KeyEvent.VK_UP) {

                        } else if (rkey == KeyEvent.VK_DOWN) {

                        }

                    }

                    if (rkey == KeyEvent.VK_SPACE) {
                    }


                }
                if (rkey == KeyEvent.VK_ENTER) { // to exit from initial screen and start the game
                    screen.clearScreen();
                    console.setCursorPosition(0, 0);
                    //screen.printScreen();
                    gameStarted = true;
                }

                keypr = 0; // last action
            }
            Thread.sleep(20);
        }
    }


}
