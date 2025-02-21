import enigma.console.TextWindow;
import enigma.core.Enigma;

public class GameScreen {

    Piece[] pieces = new Piece[20];
    Robot humanRobot1 = new Robot(2, 2);
    Robot humanRobot2 = new Robot(2, 2);
    Robot computerRobot1 = new Robot();
    Robot computerRobot2 = new Robot();


    //TextWindow console = Enigma.getConsole("CubeConsole").getTextWindow();
    TextWindow console = Game.console;
    public GameScreen() {

        for (int i = 0; i < 4; i++) {

            pieces[i] = new Piece(4, i + 1);// 4-cube pieces
        }
        for (int i = 4; i < 8; i++) {
            pieces[i] = new Piece(3, i + 1); // 3-cube pieces
        }
        for (int i = 8; i < 13; i++) {
            pieces[i] = new Piece(2, i + 1); // 2-cube pieces
        }
        for (int i = 13; i < 20; i++) {
            pieces[i] = new Piece(1, i + 1); // 1-cube pieces
        }
    }

    public Piece[] getPieces() {
        return pieces;
    }

    public Robot getHumanRobot() {
        if (!humanRobot1.isCompleted) {
            return humanRobot1;
        } else {
            return humanRobot2;
        }
    }

    public void printScreen() {
        console.setCursorPosition(0, 0);
        System.out.print(
                "+-1 - - - 2 - - - 3 - - - 4 - - - 5 - - - > X     - - - - - - - - - - - - - - - - - - - - - - -  P   I   E   C   E   S  - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.print("|\n+\n+\n+\n2\n+\n+\n+\n3\n+\n+\n+\n4\n+\n+\n+\n5\n+\n+\n+\nv\n \nY");

        int x = 50;
        int y = 2;
        addMark(x, y);
        int cubeCount = 4;
        int cubeSize = 3;

        for (int i = 0; i < pieces.length; i++) {

            if (cubeCount != pieces[i].getCubeCount()) {
                y += (cubeSize + 1) * 4;
                x = 50;
                cubeCount = pieces[i].getCubeCount();
                cubeSize = pieces[i].getCubes().length;
            }

            // Print cubes

            pieces[i].print(x, y, pieces[i].getPieceNumber());
            x += (pieces[i].getCubes().length + 1) * 8;

        }
        printRobot();
    }

    public void deleteMark(int x, int y) {
        console.setCursorPosition(x - 3, y - 1);
        System.out.print("   ");
        console.setCursorPosition(x - 3, y);
        System.out.print(" ");
        console.setCursorPosition(x - 3, y + 1);
        System.out.print("   ");
    }

    public void addMark(int x, int y) {

        console.setCursorPosition(x - 3, y - 1);
        System.out.print("###");
        console.setCursorPosition(x - 3, y);
        System.out.print("#");
        console.setCursorPosition(x - 3, y + 1);
        System.out.print("###");
    }

    public void printRobot() {
        getHumanRobot().print();
    }

    public void printInformationArea(int px, int py, int pieceNumber) {
        int xStart = 2;
        int yStart = 26;
        console.setCursorPosition(xStart, yStart);
        System.out.println("Current piece (#) : " + (pieceNumber + 1) + " ");
        console.setCursorPosition(xStart, yStart + 1);
        System.out.println("X: " + px + "   Y: " + py + " ");
        console.setCursorPosition(xStart, yStart + 2);
        System.out.println("Used pieces (=/-):");

        int cubeCount = 4;
        int x = 2;
        int y = 29;
        for (int i = 0; i < pieces.length; i++) {
            Piece piece = pieces[i];
            if (cubeCount != piece.getCubeCount()) {
                y++;
                x = xStart;
                cubeCount = piece.getCubeCount();
            }
            console.setCursorPosition(x, y);
            int currentPieceNumber = piece.getPieceNumber();
            if (currentPieceNumber < 10) {
                System.out.print("0" + (currentPieceNumber));
            } else {
                System.out.print(currentPieceNumber);
            }
            if (piece.isPreviouslyUsed()) {
                console.setCursorPosition(x - 1, y);
                System.out.print("-");
            } else if (piece.isUsed()) {
                console.setCursorPosition(x - 1, y);
                System.out.print("=");
            } else {
                console.setCursorPosition(x - 1, y);
                System.out.print(" ");
            }
            x += 4;
        }
        x = xStart;
        console.setCursorPosition(x, y + 2);

        if (!humanRobot1.isCompleted) {
            System.out.println("Human Robot 1 (HR1)");
            humanRobot1.printInformation(x, y + 2);
        } else {
            System.out.println("Human Robot 2 (HR2)");
            humanRobot2.printInformation(x, y + 2);
            console.setCursorPosition(x, y + 12);
            System.out.println("Human Robot 1 (HR1)");
            humanRobot1.printShortInformation(x, y + 12);
            console.setCursorPosition(x, y + 15);
            System.out.println("Computer Robot 1 (CR1)");
            computerRobot1.printShortInformation(x, y + 15);
        }
        System.out.println();
    }

    public void addPreviouslyUsedMark() {
        for (Piece piece : pieces) {
            if (piece.isUsed()) {
                piece.setPreviouslyUsed(true);
            }
        }
    }
    
    public void clearScreen(){
        for (int i = 0; i <180 ; i++) {
            for (int j = 0; j <52 ; j++) {
                console.setCursorPosition(i,j);
                System.out.print(" ");
            }
        }
    }

    public void printInitialScreen() {
        Robot robot = new Robot(10,8);
        Robot robot2 = new Robot(90,8);
        robot.print();
        robot2.print();
        console.setCursorPosition(44,5);
        System.out.println("=====================================================");
        console.setCursorPosition(44,6);
        System.out.println("=            WELCOME TO THE ROBOTHON GAME           =");
        console.setCursorPosition(44,7);
        System.out.println("=====================================================");

//        console.setCursorPosition(64,14);
//        System.out.println("Instructions");
//        console.setCursorPosition(64,15);
//        System.out.println("------------");
        
        console.setCursorPosition(60,25);
        System.out.println("To start the game");
        console.setCursorPosition(52,26);
        System.out.println("              _");
        console.setCursorPosition(52,27);
        System.out.println("Please press |6| on the keyboard!!!");
        console.setCursorPosition(52,28);
        System.out.println("              ˆ");
        
        
        Cube displayCube=new Cube(false);
        displayCube.printCube(11,3);
        displayCube.printCube(125,4);
        displayCube.printCube(4,20);
        displayCube.printCube(30,1);
        displayCube.printCube(104,1);
        displayCube.printCube(74,18);
        displayCube.printCube(60,10);
        displayCube.printCube(127,20);
        displayCube.printCube(48,20);
    }
    
}
