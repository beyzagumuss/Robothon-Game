import java.util.Random;

import enigma.console.TextWindow;
import enigma.core.Enigma;

public class Piece {

    private Cube[][] cubes;
    private int cubeCount;
    private int coordinateX;
    private int coordinateY;
    private int pieceNumber;
    private boolean isUsed;
    private boolean isPreviouslyUsed;
   // TextWindow console = Enigma.getConsole("CubeConsole").getTextWindow();
   TextWindow console = Game.console;
    public Piece(int cubeCount, int pieceNumber) {
        this.pieceNumber = pieceNumber;
        this.cubeCount = cubeCount;
        // Initializing cubes array based on cubeCount
        switch (cubeCount) {
            case 1:
                cubes = new Cube[1][1];
                break;
            case 2:
                cubes = new Cube[2][2];
                break;
            case 3:
            case 4:
                cubes = new Cube[3][3];
                break;
        }

        for (int i = 0; i < cubes.length; i++) {
            for (int j = 0; j < cubes.length; j++) {
                cubes[i][j] = new Cube(true); // Creating  empty cube if isEmpty=true
            }
        }

        //cube datasini cube count boyutlarina gore gecirme
        Random random = new Random();

        switch (cubeCount) {
            case 1:
                cubes[0][0] = new Cube(false);
                break;
            case 2:
                cubes[0][0] = new Cube(false);
                cubes[0][1] = new Cube(false);
                break;
            case 3:
                if (0 < random.nextInt(2)) {// 1 generate
                    cubes[0][0] = new Cube(false);
                    cubes[1][0] = new Cube(false);
                    cubes[1][1] = new Cube(false);
                } else {
                    cubes[0][0] = new Cube(false);
                    cubes[0][1] = new Cube(false);
                    cubes[0][2] = new Cube(false);
                }
                break;
            case 4:
                int pos = random.nextInt(4);
                if (pos == 3) {
                    cubes[0][0] = new Cube(false);
                    cubes[1][0] = new Cube(false);
                    cubes[1][1] = new Cube(false);
                    cubes[1][2] = new Cube(false);

                } else if (pos == 2) {
                    cubes[0][1] = new Cube(false);
                    cubes[1][0] = new Cube(false);
                    cubes[1][1] = new Cube(false);
                    cubes[1][2] = new Cube(false);

                } else if (pos == 1) {
                    cubes[0][1] = new Cube(false);
                    cubes[0][2] = new Cube(false);
                    cubes[1][0] = new Cube(false);
                    cubes[1][1] = new Cube(false);

                } else {
                    cubes[0][0] = new Cube(false);
                    cubes[0][1] = new Cube(false);
                    cubes[1][0] = new Cube(false);
                    cubes[1][1] = new Cube(false);
                }
                break;
        }
    }

    public int getPieceNumber() {
        return pieceNumber;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
        if (used) {
            addUsedMark();
        } else {
            deleteMark();
        }
    }

    public boolean isPreviouslyUsed() {
        return isPreviouslyUsed;
    }

    public void setPreviouslyUsed(boolean previouslyUsed) {
        isPreviouslyUsed = previouslyUsed;
        addPreviouslyUsedMark();
    }


    public Cube[][] getCubes() {
        return cubes;
    }


    public int getCubeCount() {
        return cubeCount;
    }


    public int getCoordinateX() {
        return coordinateX;
    }


    public int getCoordinateY() {
        return coordinateY;
    }


    private void addPreviouslyUsedMark() {
        int x = getCoordinateX();
        int y = getCoordinateY();
        console.setCursorPosition(x - 3, y - 1);
        System.out.print("---");
        console.setCursorPosition(x - 3, y);
        System.out.print("-");
        console.setCursorPosition(x - 3, y + 1);
        System.out.print("---");
    }

    public void addUsedMark() {
        int x = getCoordinateX();
        int y = getCoordinateY();
        console.setCursorPosition(x - 3, y - 1);
        System.out.print("===");
        console.setCursorPosition(x - 3, y);
        System.out.print("=");
        console.setCursorPosition(x - 3, y + 1);
        System.out.print("===");
    }

    public void addMark() {
        if (!isUsed()) {
            int x = getCoordinateX();
            int y = getCoordinateY();
            console.setCursorPosition(x - 3, y - 1);
            System.out.print("###");
            console.setCursorPosition(x - 3, y);
            System.out.print("#");
            console.setCursorPosition(x - 3, y + 1);
            System.out.print("###");
        }
    }

    public void deleteMark() {
        if (!isUsed()) {
            int x = getCoordinateX();
            int y = getCoordinateY();
            console.setCursorPosition(x - 3, y - 1);
            System.out.print("   ");
            console.setCursorPosition(x - 3, y);
            System.out.print(" ");
            console.setCursorPosition(x - 3, y + 1);
            System.out.print("   ");
        }
    }

    public void print(int x, int y, int pieceNumber) {// printing pieces

        console.setCursorPosition(x - 2, y);
        if (pieceNumber < 9) {
            System.out.print("0" + pieceNumber);
        } else {
            System.out.print(pieceNumber);
        }
        this.coordinateX = x;
        this.coordinateY = y;

        for (int i = 0; i < cubes.length; i++) {
            for (int j = 0; j < cubes.length; j++) {
                if (cubes[i][j].isEmpty())
                    cubes[i][j].printCube(x + (j * 8), y + (i * 4));
            }

        }

        for (int i = 0; i < cubes.length; i++) {
            for (int j = 0; j < cubes.length; j++) {
                if (!cubes[i][j].isEmpty())
                    cubes[i][j].printCube(x + (j * 8), y + (i * 4));
            }
        }

        printAverageX(x, y);
        printAverageY(x, y);

    }


    public void printAverageX(int x, int y) {

        int sum = 0;
        int count = 0;
        int average = 0;

        for (int i = 0; i < cubes.length; i++) {
            for (int j = 0; j < cubes.length; j++) {

                if (!cubes[i][j].isEmpty()) {
                    sum = sum + cubes[i][j].getxForce();
                    count++;
                }
            }
            if (count > 0) {
                average = (int) Math.ceil((float) sum / count);
            }
            console.setCursorPosition(x + (cubes.length * 8) + 1, (y + 2) + (i * 4));
            System.out.print(average);
            sum = 0;
            count = 0;
            average = 0;
        }


    }

    public void printAverageY(int x, int y) {
        int sum = 0;
        int count = 0;
        int average = 0;

        for (int i = 0; i < cubes.length; i++) {
            for (int j = 0; j < cubes.length; j++) {

                if (!cubes[j][i].isEmpty()) {
                    sum = sum + cubes[j][i].getyForce();
                    count++;
                }
            }
            if (count > 0) {
                average = (int) Math.ceil((float) sum / count);
            }
            console.setCursorPosition((x + 4) + (i * 8), y + (cubes.length * 4) + 1);
            System.out.print(average);
            sum = 0;
            count = 0;
            average = 0;
        }

    }

    public void rotateRight(int x, int y) {
        int matrixSize = cubes.length;
        Cube[][] temp = new Cube[matrixSize][matrixSize];

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {

                if (matrixSize == 3) {
                    temp[i][j] = cubes[2 - j][i];
                    temp[i][j].changeforces();

                } else if (matrixSize == 2) {
                    temp[i][j] = cubes[1 - j][i];
                    temp[i][j].changeforces();

                } else {
                    cubes[0][0].changeforces();
                }
            }
        }
        cubes = temp;
        shift();
        print(x, y, pieceNumber);

    }

    public void rotateLeft(int x, int y) {
        int matrixSize = cubes.length;
        Cube[][] temp = new Cube[matrixSize][matrixSize];

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (matrixSize == 3) {
                    temp[i][j] = cubes[j][2 - i];
                    temp[i][j].changeforces();
                } else if (matrixSize == 2) {
                    temp[i][j] = cubes[j][1 - i];
                    temp[i][j].changeforces();
                } else {
                    cubes[0][0].changeforces();

                }
            }
        }
        cubes = temp;
        shift();
        print(x, y, pieceNumber);

    }

    public void reverse(int x, int y) {
        int matrixSize = cubes.length;
        for (int i = 0; i < matrixSize; i++) {
            Cube reverseTemp = cubes[i][0];
            cubes[i][0] = cubes[i][matrixSize - 1];
            cubes[i][matrixSize - 1] = reverseTemp;
        }
        shift();
        print(x, y, pieceNumber);
    }

    public Cube[][] shift() {
        int matrixSize = cubes.length;
        if (matrixSize == 3) {
            while (cubes[0][0].isEmpty() && cubes[0][1].isEmpty() && cubes[0][2].isEmpty()) {
                cubes[0][0] = cubes[1][0];
                cubes[0][1] = cubes[1][1];
                cubes[0][2] = cubes[1][2];
                cubes[1][0] = cubes[2][0];
                cubes[1][1] = cubes[2][1];
                cubes[1][2] = cubes[2][2];
                cubes[2][0] = new Cube(true);
                cubes[2][1] = new Cube(true);
                cubes[2][2] = new Cube(true);
            }
            while (cubes[0][0].isEmpty() && cubes[1][0].isEmpty() && cubes[2][0].isEmpty()) {
                cubes[0][0] = cubes[0][1];
                cubes[1][0] = cubes[1][1];
                cubes[2][0] = cubes[2][1];
                cubes[0][1] = cubes[0][2];
                cubes[1][1] = cubes[1][2];
                cubes[2][1] = cubes[2][2];
                cubes[0][2] = new Cube(true);
                cubes[1][2] = new Cube(true);
                cubes[2][2] = new Cube(true);
            }
        } else if (matrixSize == 2) {
            while (cubes[0][0].isEmpty() && cubes[0][1].isEmpty()) {// for empty row
                cubes[0][0] = cubes[1][0];
                cubes[0][1] = cubes[1][1];
                cubes[1][0] = new Cube(true);
                cubes[1][1] = new Cube(true);
            }
            while (cubes[0][0].isEmpty() && cubes[1][0].isEmpty()) {// for empty column
                cubes[0][0] = cubes[0][1];
                cubes[1][0] = cubes[1][1];
                cubes[0][1] = new Cube(true);
                cubes[1][1] = new Cube(true);
            }
        }

        return cubes;
    }


}
