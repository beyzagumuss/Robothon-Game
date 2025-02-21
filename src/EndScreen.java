import java.awt.Color;
import java.util.Random;

import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class EndScreen {

    enigma.console.Console console =

    Robot hr1;
    Robot hr2;
    Robot cr1;
    Robot cr2;

    Color customred = new Color(221, 71, 71);
    // Color customorange = new Color(251, 198, 135);
    Color customblue = new Color(170, 205, 190);
    Color customwblue = new Color(85, 136, 163);
    // Color custompurple = new Color(153, 153, 255);
    // Color customyellow = new Color(244, 247, 197);
    Color customwyellow = new Color(253, 255, 171);
    TextAttributes wred = new TextAttributes(customred);
    TextAttributes red = new TextAttributes(customblue);
    TextAttributes blue = new TextAttributes(customblue);
    TextAttributes wblue = new TextAttributes(customwblue);
    TextAttributes yellow = new TextAttributes(customblue);
    TextAttributes wyellow = new TextAttributes(customwyellow);
    TextAttributes white = new TextAttributes(Color.white);

    public EndScreen(Robot humanRobot1, Robot humanRobot2, Robot computerRobot1, Robot computerRobot2) {
        this.hr1 = humanRobot1;
        this.hr2 = humanRobot2;
        this.cr1 = computerRobot1;
        this.cr2 = computerRobot2;
    }

    public void printEndScreen() throws InterruptedException {

        console.getTextWindow().setCursorPosition(0, 0);
        printinfo(1);
        console.getTextWindow().setCursorPosition(0, 13);
        printinfo(2);
        console.getTextWindow().setCursorPosition(0, 26);
        printinfo(3);

        int hwins = 0;
        int cwins = 0;
        int hr1IntelligenceScore = hr1.getIntelligenceScore();
        int hr2IntelligenceScore = hr2.getIntelligenceScore();
        int cr1IntelligenceScore = cr1.getIntelligenceScore();
        int cr2IntelligenceScore = cr2.getIntelligenceScore();
        int totalScore = hr1IntelligenceScore + hr2IntelligenceScore + cr1IntelligenceScore + cr2IntelligenceScore;// -1
        Random rnd = new Random();

        int h1x = 0;
        int c1x = 0;
        int h2x = 0;
        int c2x = 0;

        while (h1x != 20 && h2x != 20 && c1x != 20 && c2x != 20) {
            int randomInt = rnd.nextInt(totalScore);
            if (randomInt < hr1IntelligenceScore) {
                console.getTextWindow().setCursorPosition(35 + h1x, 3);
                System.out.print("X");
                h1x++;
            } else if (randomInt < hr2IntelligenceScore + hr1IntelligenceScore) {
                console.getTextWindow().setCursorPosition(35 + h2x, 7);
                System.out.print("X");
                h2x++;
            } else if (randomInt < cr1IntelligenceScore + hr2IntelligenceScore + hr1IntelligenceScore) {
                console.getTextWindow().setCursorPosition(35 + c1x, 5);
                System.out.print("X");
                c1x++;
            } else {
                console.getTextWindow().setCursorPosition(35 + c2x, 9);
                System.out.print("X");
                c2x++;
            }
            Thread.sleep(120);
        }
        selectwinner(0, h1x, h2x, c1x, c2x);
        if (h1x == 20 || h2x == 20)
            hwins++;
        else
            cwins++;

        int hr1SkillScore = hr1.getSkillScore();
        int hr2SkillScore = hr2.getSkillScore();
        int cr1SkillScore = cr1.getSkillScore();
        int cr2SkillScore = cr2.getSkillScore();
        int totalSkillScore = cr1SkillScore + cr2SkillScore + hr1SkillScore + hr2SkillScore;
        h1x = 0;
        c1x = 0;
        h2x = 0;
        c2x = 0;
        while (h1x != 20 && h2x != 20 && c1x != 20 && c2x != 20) {
            int randomSkill = rnd.nextInt(totalSkillScore);
            if (randomSkill < hr1SkillScore) {
                console.getTextWindow().setCursorPosition(35 + h1x, 16);
                System.out.print("X");
                h1x++;
            } else if (randomSkill < hr2SkillScore + hr1SkillScore) {
                console.getTextWindow().setCursorPosition(35 + h2x, 20);
                System.out.print("X");
                h2x++;
            } else if (randomSkill < cr1SkillScore + hr2SkillScore + hr1SkillScore) {
                console.getTextWindow().setCursorPosition(35 + c1x, 18);
                System.out.print("X");
                c1x++;
            } else {
                console.getTextWindow().setCursorPosition(35 + c2x, 22);
                System.out.print("X");
                c2x++;
            }
            Thread.sleep(120);

        }
        selectwinner(1, h1x, h2x, c1x, c2x);
        if (h1x == 20 || h2x == 20)
            hwins++;
        else
            cwins++;

        int hr1SpeedScore = hr1.getSpeedScore();
        int hr2SpeedScore = hr2.getSpeedScore();
        int cr1SpeedScore = cr1.getSpeedScore();
        int cr2SpeedScore = cr2.getSpeedScore();
        int totalSpeedScore =hr1SpeedScore + hr2SpeedScore+ cr1SpeedScore + cr2SpeedScore;
        h1x = 0;
        c1x = 0;
        h2x = 0;
        c2x = 0;
        while (h1x != 20 && h2x != 20 && c1x != 20 && c2x != 20) {
            int randomSpeedScore = rnd.nextInt(totalSpeedScore);
            if (randomSpeedScore < hr1SpeedScore) {
                console.getTextWindow().setCursorPosition(35 + h1x, 29);
                System.out.print("X");
                h1x++;
            } else if (randomSpeedScore < hr2SpeedScore + hr1SpeedScore) {
                console.getTextWindow().setCursorPosition(35 + h2x, 33);
                System.out.print("X");
                h2x++;
            } else if (randomSpeedScore < cr1SpeedScore + hr2SpeedScore + hr1SpeedScore) {
                console.getTextWindow().setCursorPosition(35 + c1x, 31);
                System.out.print("X");
                c1x++;
            } else {
                console.getTextWindow().setCursorPosition(35 + c2x, 35);
                System.out.print("X");
                c2x++;
            }
            Thread.sleep(120);
        }
        selectwinner(2, h1x, h2x, c1x, c2x);
        if (h1x == 20 || h2x == 20)
            hwins++;
        else
            cwins++;
        console.getTextWindow().setCursorPosition(0, 40);
        console.setTextAttributes(wyellow);
        if (hwins > cwins) {
            System.out.println("Total winner of the Robolympics: Blue Team");
        } else {
            System.out.println("Total winner of the Robolympics: Red Team");
        }

    }

    public void printinfo(int type) {
        String game = null;
        if (type == 1) {
            game = "Chess===";
            console.setTextAttributes(blue);
        } else if (type == 2) {
            game = "PingPong";
            console.setTextAttributes(yellow);
        } else if (type == 3) {
            game = "Run=====";
            console.setTextAttributes(red);
        }
        System.out.println("===Robo" + game + "=========================================");
        System.out.println("                                  0    5    10   15   20 ");
        System.out.println("                                  +----+----+----+----+  ");
        System.out.println("HR1 - In:" + hr1.getIntelligenceScore() + " Sk:" + hr1.getSkillScore() + " Sp:" + hr1.getSpeedScore()
                + "                             ");
        System.out.println("                                  +----+----+----+----+ ");
        System.out.println("CR1 - In:" + cr1.getIntelligenceScore() + " Sk:" + cr1.getSkillScore() + " Sp:" + cr1.getSpeedScore()
                + "                             ");
        System.out.println("                                  +----+----+----+----+ ");
        System.out.println("HR2 - In:" + hr2.getIntelligenceScore() + " Sk:" + hr2.getSkillScore() + " Sp:" + hr2.getSpeedScore()
                + "                             ");
        System.out.println("                                  +----+----+----+----+ ");
        System.out.println("CR2 - In:" + cr2.getIntelligenceScore() + " Sk:" + cr2.getSkillScore() + " Sp:" + cr2.getSpeedScore()
                + "                             ");
        System.out.println("                                  +----+----+----+----+ ");
        console.setTextAttributes(white);
    }

    public void cleanscreen() {
        for (int i = 0; i < 167; i++) {
            for (int j = 0; j < 49; j++) {
                console.getTextWindow().setCursorPosition(i, j);
                System.out.print(" ");

            }
        }
    }

    public void selectwinner(int tour, int h1x, int h2x, int c1x, int c2x) {
        console.getTextWindow().setCursorPosition(0, 11 + tour * 13);
        if (h1x == 20 || h2x == 20) {
            console.setTextAttributes(wblue);
            if (h1x > h2x) {

                System.out.println("Winner: Blue Team(HR1)");
            } else {
                System.out.println("Winner: Blue Team(HR2)");
            }
        } else {
            console.setTextAttributes(wred);
            if (c1x > c2x) {
                System.out.println("Winner: Red Team(CR1)");
            } else {
                System.out.println("Winner: Red Team(CR2)");
            }
        }
        console.setTextAttributes(white);
    }

}
