package tictactoe;
import java.util.Scanner;

public class Main {
    static void printBox(String XO) {
        System.out.println("---------");
        for (int i = 0; i < 9; i += 3) {
            System.out.printf("| %c %c %c |%n", XO.charAt(i), XO.charAt(i + 1), XO.charAt(i + 2));
        }
        System.out.println("---------");
    }

    static String playGame(char currentTurn, String XO, int[][] position) {
        Scanner input = new Scanner(System.in);

        //Loop until user has input the X or O successfully
        for (; ; ) {
            int row = 0, column = 0;
            while (row == 0 || column == 0) {
                //Check integer
                try {
                    //User input position of 'X' or 'O'
                    String rowColumn = input.nextLine();
                    String[] pieces = rowColumn.split(" ");
                    row = Integer.parseInt(pieces[0]);
                    column = Integer.parseInt(pieces[1]);
                    if (row < 1 || row > 3 || column < 1 || column > 3) {
                        System.out.println("Coordinates should be from 1 to 3!");
                        column = 0;
                        row = 0;
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("You should enter numbers!");
                }
            }
            row = row - 1;
            column = column - 1;
            if (XO.charAt(position[row][column]) == 'X' || XO.charAt(position[row][column]) == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                StringBuilder sb = new StringBuilder(XO);
                sb.setCharAt(position[row][column], currentTurn);
                XO = sb.toString();
                printBox(XO);
                break;
            }
        }
        return XO;
    }

    static boolean checkGameCont(String XO) {
        //Situation which you will win
        int[][] win = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6},
                {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}
        };
        boolean Xwin = false, Owin = false, draw = false, gameIsNotFinish = true;

        //Check win by comparing with cases of win
        //Loop 8 cases
        for (int i = 0; i < 8; i++) {
            int Xwincount = 0, Owincount = 0;
            //Loop each position in the case
            for (int j = 0; j < 3; j++) {
                if (XO.charAt(win[i][j]) == 'O') {
                    Owincount++;
                }
                if (XO.charAt(win[i][j]) == 'X') {
                    Xwincount++;
                }
                if (Owincount >= 3) {
                    Owin = true;
                }
                if (Xwincount >= 3) {
                    Xwin = true;
                }
            }
        }
        int Xcount = XO.length() - XO.replace("X", "").length();
        int Ocount = XO.length() - XO.replace("O", "").length();

        if ((Xcount + Ocount) == 9) {
            draw = true;
        }

        if (Xwin) {
            System.out.print("X wins");
            gameIsNotFinish = false;
        } else if (Owin) {
            System.out.print("O wins");
            gameIsNotFinish = false;
        } else if (draw) {
            System.out.print("Draw");
            gameIsNotFinish = false;
        }
        return gameIsNotFinish;
    }
    public static void main(String[] args) {
        //Initialise empty space to each box
        char currentTurn;
        String XO = "         ";
        boolean gameIsNotFinish = true;
        int turn = 0;

        /* Distribute position to each box
        0 | 1 | 2
        3 | 4 | 5
        6 | 7 | 8
        */
        int counter = 0;
        int[][]position = new int [3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                position[i][j] = counter;
                counter++;
            }
        }
        printBox(XO);
        while(gameIsNotFinish) {
            if(turn % 2 == 0){
                currentTurn = 'X';
            } else {
                currentTurn = 'O';
            }
            XO = playGame(currentTurn, XO, position);
            gameIsNotFinish = checkGameCont(XO);
            turn++;
        }
    }
}



