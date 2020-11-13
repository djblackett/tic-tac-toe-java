package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static int[] updateMatrixAndC(String[][] matrix, String[] c) {
        List<String> str = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                str.add(matrix[i][j]);
            }
        }

        for (int i = 0; i < 9; i++) {
            c[i] = str.get(i);
        }


        int xCount = 0;
        int oCount = 0;
        for (String s : c) {
            if (s.equals("X")) {
                xCount++;
            }
            if (s.equals(("O"))) {
                oCount++;
            }
        }

        return new int[]{xCount, oCount};

    }
    public static void main(String[] args) {

        boolean xWin = false;
        boolean oWin = false;
        int xCount = 0;
        int oCount = 0;
        String regex = "[^\\d ]";
        Pattern p = Pattern.compile(regex);


        String[] c = new String[9];



        String[][] matrix = new String[3][3];
        matrix[2][0] = " ";
        matrix[2][1] = " ";
        matrix[2][2] = " ";
        matrix[1][0] = " ";
        matrix[1][1] = " ";
        matrix[1][2] = " ";
        matrix[0][0] = " ";
        matrix[0][1] = " ";
        matrix[0][2] = " ";


        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                list.add(matrix[i][j]);
            }
        }

        for (int i = 0; i < 9; i++) {
            c[i] = list.get(i);
        }

        printField(matrix);

        while (true) {

            move(matrix, "X");
            // need to put a checker function in here
            int[] updated = updateMatrixAndC(matrix, c);
            printField(matrix);
            if (c[0].equals("X") && c[1].equals("X") && c[2].equals("X")
                        || c[3].equals("X") && c[4].equals("X") && c[5].equals("X")
                        || c[6].equals("X") && c[7].equals("X") && c[8].equals("X")
                        || c[0].equals("X") && c[3].equals("X") && c[6].equals("X")
                        || c[1].equals("X") && c[4].equals("X") && c[7].equals("X")
                        || c[2].equals("X") && c[5].equals("X") && c[8].equals("X")
                        || c[0].equals("X") && c[4].equals("X") && c[8].equals("X")
                        || c[2].equals("X") && c[4].equals("X") && c[6].equals("X")) {
                xWin = true;
                break;
            }

            move(matrix, "O");
            updated = updateMatrixAndC(matrix, c);
            printField(matrix);
            if (c[0].equals("O") && c[1].equals("O") && c[2].equals("O")
                    || c[3].equals("O") && c[4].equals("O") && c[5].equals("O")
                    || c[6].equals("O") && c[7].equals("O") && c[8].equals("O")
                    || c[0].equals("O") && c[3].equals("O") && c[6].equals("O")
                    || c[1].equals("O") && c[4].equals("O") && c[7].equals("O")
                    || c[2].equals("O") && c[5].equals("O") && c[8].equals("O")
                    || c[0].equals("O") && c[4].equals("O") && c[8].equals("O")
                    || c[2].equals("O") && c[4].equals("O") && c[6].equals("O")
            ) {
                oWin = true;
                break;
            }



            updated = updateMatrixAndC(matrix, c);
            xCount = updated[0];
            oCount = updated[1];



            List<String> str = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    str.add(matrix[i][j]);
                }
            }

            if (!xWin && !oWin) {
                if (Math.abs(xCount - oCount) > 1) {
                    System.out.println("Impossible");
                    break;
                } else if (!str.contains(" ")) {
                    System.out.println("Draw");
                    break;
                }
            }

        }
        if (xWin) {
            System.out.println("X wins");
            System.exit(0);
        } else if (oWin) {
            System.out.println("O wins");
            System.exit(0);
        }
    }

    public static void move(String[][] matrix, String whoseTurn) {
        while (true) {
            String regex = "[^\\d ]";
            Pattern p = Pattern.compile(regex);
            Scanner sc = new Scanner(System.in);
            String userMove = sc.nextLine();
            Matcher m = p.matcher(userMove);
            if (m.find()) {
                System.out.println("You should enter numbers!");
                continue;
            }
            String[] moveChars = userMove.split("");
            int digit1 = Integer.parseInt(moveChars[0]) - 1;
            int digit2 = Integer.parseInt(moveChars[2]) - 1;
            int flippedDigit2 = digit2;

            if (digit1 < 0 || digit1 > 2 || digit2 < 0 || digit2 > 2) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (digit2 == 0) {
                flippedDigit2 = 2;
            } else if (digit2 == 2) {
                flippedDigit2 = 0;
            }
            String coordinate = matrix[flippedDigit2][digit1];
            if (coordinate.equals("X") || coordinate.equals("O")) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            if (coordinate.equals(" ")) {
                matrix[flippedDigit2][digit1] = whoseTurn;
                break;
            }
        }
    }

    public static void printField(String[][] matrix) {
        System.out.println("---------");

        System.out.print("| " + matrix[0][0] + " " + matrix[0][1] + " " + matrix[0][2] + " |\n" +
                "| " + matrix[1][0] + " " + matrix[1][1] + " " + matrix[1][2] + " |\n" +
                "| " + matrix[2][0] + " " + matrix[2][1] + " " + matrix[2][2] + " |\n");
        System.out.println("---------");
    }

//    public static boolean xWin(String[] c) {
//        if (c != null && c.length == 9) {
//            return c[0].equals("X") && c[1].equals("X") && c[2].equals("X")
//                    || c[3].equals("X") && c[4].equals("X") && c[5].equals("X")
//                    || c[6].equals("X") && c[7].equals("X") && c[8].equals("X")
//                    || c[0].equals("X") && c[3].equals("X") && c[6].equals("X")
//                    || c[1].equals("X") && c[4].equals("X") && c[7].equals("X")
//                    || c[2].equals("X") && c[5].equals("X") && c[8].equals("X")
//                    || c[0].equals("X") && c[4].equals("X") && c[8].equals("X")
//                    || c[2].equals("X") && c[4].equals("X") && c[6].equals("X");
//        }
//        return false;
//    }

//    public static boolean oWin(String[] c) {
//        if (c.length == 9) {
//            if (c[0].equals("O") && c[1].equals("O") && c[2].equals("O")
//                    || c[3].equals("O") && c[4].equals("O") && c[5].equals("O")
//                    || c[6].equals("O") && c[7].equals("O") && c[8].equals("O")
//                    || c[0].equals("O") && c[3].equals("O") && c[6].equals("O")
//                    || c[1].equals("O") && c[4].equals("O") && c[7].equals("O")
//                    || c[2].equals("O") && c[5].equals("O") && c[8].equals("O")
//                    || c[0].equals("O") && c[4].equals("O") && c[8].equals("O")
//                    || c[2].equals("O") && c[4].equals("O") && c[6].equals("O")
//            ) {
//                return true;
//            }
//        }
//        return false;
//    }


}