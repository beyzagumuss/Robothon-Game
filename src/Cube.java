import java.util.Random;

import enigma.console.Console;
import enigma.console.TextWindow;
import enigma.core.Enigma;


public class Cube {

	private int xForce;
    private int yForce;
    private boolean isEmpty;
    private boolean isNull;
    private int pieceNumber;

    TextWindow console = Game.console;
//first value of isEmpty is true
    public Cube(boolean isEmpty) {
        Random random = new Random();
        if (!isEmpty) {
            // If not empty it generates random force values
            this.xForce = random.nextInt(76);
            this.yForce = random.nextInt(76);
        }
        this.isEmpty = isEmpty;
    }

    public int getPieceNumber() {
        return pieceNumber;
    }

    public void setPieceNumber(int pieceNumber) {
        this.pieceNumber = pieceNumber;
    }

    public boolean isNull() {
        return isNull;
    }

    public boolean isEmpty() {
		return isEmpty;
	}

	public int getxForce() {
		return xForce;
	}

	public int getyForce() {
		return yForce;
	}

	public void changeforces() {// used at rotating
		if (!isEmpty) {
			int temp = xForce;
			xForce = yForce;
			yForce = temp;
		}
		}

	public int getX1() {

		return xForce/10;

	}
	public int getX2() {
		return xForce%10;

	}

	public int getY1() {

		return yForce/10;

	}

	public int getY2() {
		return yForce%10;

	}

	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}
	public void printCube(int x, int y) {
        console.setCursorPosition(x, y);
        if (!isEmpty) {
            System.out.print("+ + + + +");
            console.setCursorPosition(x, y + 1);
            System.out.print("+   " + getY1() + "   +");
            console.setCursorPosition(x, y + 2);
            System.out.print("+ " + getX1() + "   " + getX2() + " +");
            console.setCursorPosition(x, y + 3);
            System.out.print("+   " + getY2() + "   +");
            console.setCursorPosition(x, y + 4);
            System.out.print("+ + + + +");
        } else if(!isNull) {
            System.out.print(". . . . .");
            console.setCursorPosition(x, y + 1);
            System.out.print(".       .");
            console.setCursorPosition(x, y + 2);
            System.out.print(".       .");
            console.setCursorPosition(x, y + 3);
            System.out.print(".       .");
            console.setCursorPosition(x, y + 4);
            System.out.print(". . . . .");
        }
    }
}

