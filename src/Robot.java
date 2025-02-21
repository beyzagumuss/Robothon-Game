import enigma.console.TextWindow;
import enigma.core.Enigma;

public class Robot {
    Cube[][] cubeMatrix;
    int x;
    int y;
    boolean isCompleted;

    TextWindow console = Game.console;
    public Robot(int initialX, int initialY) {
        this.x = initialX;
        this.y = initialY;

        cubeMatrix = new Cube[5][5];

        for (int i = 0; i < cubeMatrix.length; i++) {
            for (int j = 0; j < cubeMatrix.length; j++) {
                cubeMatrix[i][j] = new Cube(true);
            }
        }
        cubeMatrix[0][0].setNull(true);
        cubeMatrix[0][1].setNull(true);
        cubeMatrix[0][3].setNull(true);
        cubeMatrix[0][4].setNull(true);
        cubeMatrix[2][0].setNull(true);
        cubeMatrix[2][4].setNull(true);
        cubeMatrix[3][0].setNull(true);
        cubeMatrix[3][4].setNull(true);
        cubeMatrix[4][0].setNull(true);
        cubeMatrix[4][2].setNull(true);
        cubeMatrix[4][4].setNull(true);
    }

    public Robot() {
        cubeMatrix = new Cube[5][5];

        for (int i = 0; i < cubeMatrix.length; i++) {
            for (int j = 0; j < cubeMatrix.length; j++) {
                cubeMatrix[i][j] = new Cube(false);
            }
        }
        cubeMatrix[0][0].setNull(true);
        cubeMatrix[0][1].setNull(true);
        cubeMatrix[0][3].setNull(true);
        cubeMatrix[0][4].setNull(true);
        cubeMatrix[2][0].setNull(true);
        cubeMatrix[2][4].setNull(true);
        cubeMatrix[3][0].setNull(true);
        cubeMatrix[3][4].setNull(true);
        cubeMatrix[4][0].setNull(true);
        cubeMatrix[4][2].setNull(true);
        cubeMatrix[4][4].setNull(true);
    }

    public void print() {
        for (int i = 0; i < cubeMatrix.length; i++) {
            for (int j = 0; j < cubeMatrix.length; j++) {
                cubeMatrix[i][j].printCube(x + (j * 8), y + (i * 4));
            }

        }
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void addPiece(int px, int py, Piece piece) {
        int xIndex = (px - x) / 8;
        int yIndex = (py - y) / 4;

        if (!piece.isUsed()) {
            Cube[][] pieceCubes = piece.getCubes();
            int pieceLength = pieceCubes.length;
            int robotLength = cubeMatrix.length;
            boolean failed = false;
            for (int i = 0; i < pieceLength; i++) {
                for (int j = 0; j < pieceLength; j++) {
                    Cube pieceCube = pieceCubes[i][j];
                    if (!pieceCube.isEmpty() && (i + yIndex < robotLength && j + xIndex < robotLength)) {
                        Cube robotCube = cubeMatrix[i + yIndex][j + xIndex];
                        if (!robotCube.isEmpty() || robotCube.isNull()) {
                            failed = true;
                        }
                    }
                }
            }
            if (!failed) {
                for (int i = 0; i < pieceLength; i++) {
                    for (int j = 0; j < pieceLength; j++) {
                        Cube pieceCube = pieceCubes[i][j];
                        if (!pieceCube.isEmpty() && (i + yIndex < robotLength && j + xIndex < robotLength)) {
                            cubeMatrix[i + yIndex][j + xIndex] = pieceCube;
                            cubeMatrix[i + yIndex][j + xIndex].setPieceNumber(piece.getPieceNumber());
                        }
                    }
                    piece.setUsed(true);
                }
            }
        }
    }

    public int removePiece(int px, int py) {
        int xIndex = (px - x) / 8;
        int yIndex = (py - y) / 4;
        Cube robotCube = cubeMatrix[yIndex][xIndex];
        int pieceNumber = robotCube.getPieceNumber();

        for (int i = 0; i < cubeMatrix.length; i++) {
            for (int j = 0; j < cubeMatrix.length; j++) {
                Cube curentRobotCube = cubeMatrix[i][j];
                if (!curentRobotCube.isEmpty() && !curentRobotCube.isNull() && curentRobotCube.getPieceNumber() == pieceNumber) {
                    cubeMatrix[i][j] = new Cube(true);
                }
            }
        }
        return pieceNumber;
    }

    public int getIntelligenceScore() {
        return cubeMatrix[0][2].getyForce() + cubeMatrix[1][2].getyForce() + cubeMatrix[2][2].getyForce() + cubeMatrix[3][2].getyForce();
    }

    public int getLeftArmScore() {
        return cubeMatrix[1][0].getxForce() + cubeMatrix[1][1].getxForce();
    }

    public int getRightArmScore() {
        return cubeMatrix[1][3].getxForce() + cubeMatrix[1][4].getxForce();
    }

    public int getSkillScore() {
        int leftArmScore = getLeftArmScore();
        int rightArmScore = getRightArmScore();
        float armBalance = (float) Math.max(leftArmScore, rightArmScore) / Math.min(leftArmScore, rightArmScore);
        return (int) ((leftArmScore + rightArmScore) / armBalance);
    }

    public int getLeftLegScore() {
        return cubeMatrix[2][1].getyForce() + cubeMatrix[3][1].getyForce() + cubeMatrix[4][1].getyForce();
    }

    public int getRightLegScore() {
        return cubeMatrix[2][3].getyForce() + cubeMatrix[3][3].getyForce() + cubeMatrix[4][3].getyForce();
    }

    public int getSpeedScore() {
        int leftLegScore = getLeftLegScore();
        int rightLegScore = getRightLegScore();
        float legBalance = (float) Math.max(leftLegScore, rightLegScore) / Math.min(leftLegScore, rightLegScore);
        return (int) ((leftLegScore + rightLegScore) / legBalance);
    }

    public void printInformation(int x, int y) {
        console.setCursorPosition(x, ++y);
        System.out.print("Intelligence: " + getIntelligenceScore()+"  ");
        console.setCursorPosition(x, ++y);
        System.out.print("Skill: " + getSkillScore()+"  ");
        console.setCursorPosition(x, ++y);
        System.out.print("  Left Arm: " + getLeftArmScore()+"  ");
        console.setCursorPosition(x, ++y);
        System.out.print("  Right Arm: " + getRightArmScore()+"  ");
        console.setCursorPosition(x, ++y);
        System.out.print("Speed: " + getSpeedScore()+"  ");
        console.setCursorPosition(x, ++y);
        System.out.print("  Left Leg: " + getLeftLegScore()+"  ");
        console.setCursorPosition(x, ++y);
        System.out.println("  Right Leg: " + getRightLegScore()+"  ");
    }

    public void printShortInformation(int x, int y) {
        console.setCursorPosition(x, ++y);
        System.out.println("In: " + getIntelligenceScore() + "  Sk: " + getSkillScore() + "  Sp: " + getSpeedScore()+"  ");
    }
}
