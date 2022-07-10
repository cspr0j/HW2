package homework2;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class ShootingAtTheSquare {
    public static void main(String[] args) {
        Random rnd = new Random();
        String[][] myArr = new String[6][6];
        String target = "x";

        setArray(myArr);
        setAdvancedTarget(rnd, myArr);
        //uncomment for simple version
        //int[][] targetInfo = new int[1][2];
        //setSimpleTarget(rnd, myArr, targetInfo);

        System.out.println("All set. Get ready to rumble!");

        printArr(myArr);
        advancedGame(myArr, target);
        //simpleGame(myArr,targetInfo);
    }

    /**
     * This method describes all the basic work and logic of the game(Advanced with 3 targets)
     */
    public static void advancedGame(String[][] myArr, String targetInfo) {
        int row, col;
        int targets = 3;//num of all targets in array
        while (true) {
            System.out.println("Select the line for fire: ");
            row = correctness();

            System.out.println("Select the column for fire: ");
            col = correctness();

            //check if the data in bar equals 'x'
            if (Objects.equals(myArr[row][col], targetInfo)) {
                targets--;
                System.out.println("You hit the target! " + targets + " targets left");
            }
            myArr[row][col] = "*";
            printArr(myArr);

            if (targets == 0) {
                System.out.println("You have won!");
                break;
            }
        }
    }

    /**
     * Check for correctness of the data
     */
    public static int correctness() {
        Scanner scanner = new Scanner(System.in);
        int inputNum;

        while (true) {
            String buffer = scanner.nextLine();
            try {
                inputNum = Integer.parseInt(buffer);
                if (inputNum > 5 || inputNum <= 0) {
                    System.out.println("Wrong input. Please, try again");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Wrong input. Please, try again");
            }
        }
        return inputNum;
    }

    /**
     * This method sets 3 targets in array(Advanced)
     */
    public static void setAdvancedTarget(Random rnd, String[][] myArr) {
        int row = rnd.nextInt(1, 6);
        int col = rnd.nextInt(1, 6);

        switch (col) {
            case 1 -> {
                myArr[row][col] = "x";
                myArr[row][col + 1] = "x";
                if (row != 5) {
                    myArr[row + 1][col] = "x";
                } else {
                    myArr[row - 1][col] = "x";
                }
            }
            case 2, 5 -> {
                myArr[row][col] = "x";
                myArr[row][col - 1] = "x";
                if (row != 5) {
                    myArr[row + 1][col] = "x";
                } else {
                    myArr[row - 1][col] = "x";
                }
            }
            case 3, 4 -> {
                myArr[row][col] = "x";
                myArr[row][col - 1] = "x";
                myArr[row][col + 1] = "x";
            }
        }
    }


    public static void setArray(String[][] myArr) {
        for (int i = 0; i < myArr[0].length; i++) {
            for (int j = 0; j < myArr[1].length; j++) {
                if (i == 0) {
                    myArr[i][j] = String.valueOf(j);
                } else if (j == 0) {
                    myArr[i][j] = String.valueOf(i);
                } else {
                    myArr[i][j] = "-";
                }
            }
        }
    }

    public static void printArr(String[][] myArr) {
        for (int i = 0; i < myArr[0].length; i++) {
            for (int j = 0; j < myArr[1].length; j++) {
                System.out.print(myArr[i][j] + " | ");
            }
            System.out.println();
        }
    }

    /**
     * This method describes all the basic work and logic of the game (Simple logic)
     */
    public static void simpleGame(String[][] myArr, int[][] targetInfo) {
        int row, col;
        while (true) {
            System.out.println("Select the line for fire: ");
            row = correctness();

            System.out.println("Select the column for fire: ");
            col = correctness();

            myArr[row][col] = "*";
            printArr(myArr);
            if (targetInfo[0][0] == row && targetInfo[0][1] == col) {
                System.out.println("You have won!");
                break;
            }
        }
    }

    /**
     * This method sets target in array(Simple)
     */
    public static void setSimpleTarget(Random random, String[][] myArr, int[][] targetInfo) {
        int row = random.nextInt(1, 6);
        int col = random.nextInt(1, 6);

        targetInfo[0][0] = row;
        targetInfo[0][1] = col;
        myArr[row][col] = "x";
    }
}
