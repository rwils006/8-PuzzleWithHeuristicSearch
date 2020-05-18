package com.company;
import java.util.Scanner;
import java.util.ArrayList;


public class Main {

    final static private String trivial = "123456780"; //userIn = 0
    final static private String veryEasy = "123456708"; //userIn = 1
    final static private String easy = "120453786"; //userIn = 2
    final static private String doable = "012453786"; //userIn = 3
    final static private String ohBoy = "871602543"; //userIn = 4
    final static private String impossible = "123456870"; //userIn = 5


    public static void main(String[] args) {

        System.out.println("Welcome to 861218685's 8-puzzle solver.");
        System.out.println("Type \"1\" to use a default puzzle, or \"2\" to enter your own puzzle.");

        Scanner userIn = new Scanner(System.in);
        String userChoice = userIn.nextLine();
        String userPuzzle;
        ArrayList<Character> used = new ArrayList<Character>();

        if (userChoice.charAt(0) == '1'){ //these ifs get the puzzle from the user
            System.out.println("Enter 0 for Trivial, 1 for VeryEasy, 2 for Easy, 3 for Doable, 4 for OhBoy, and 5 for Impossible");
            userChoice = userIn.nextLine();
            if (userChoice.charAt(0) == '0'){
                userPuzzle = trivial;
            }
            else if (userChoice.charAt(0) == '1'){
                userPuzzle = veryEasy;
            }
            else if (userChoice.charAt(0) == '2'){
                userPuzzle = easy;
            }
            else if (userChoice.charAt(0) == '3'){
                userPuzzle = doable;
            }
            else if (userChoice.charAt(0) == '4'){
                userPuzzle = ohBoy;
            }
            else if (userChoice.charAt(0) == '5'){
                userPuzzle = impossible;
            }
            else {
                System.out.println("Invalid input");
                return;
            }
        }
        else if (userChoice.charAt(0) == '2'){ //creates a puzzle from user input
            System.out.println("Enter your puzzle, use a zero to represent the blank");
            System.out.println("Enter the first row, use space between numbers");
            userChoice = userIn.nextLine();
            char[] temp = new char[9];
            int check = 0;
            for(int i = 0; i < userChoice.length() && check < 3;i++){ //make sure we only take first 3
                if (userChoice.charAt(i) == ' '){ //dont input spaces
                    continue;
                }
                if (used.contains(userChoice.charAt(i))){
                    System.out.println("Duplicate entry, Please Try Again");
                    return;
                }
                if (userChoice.charAt(i) == '9'){
                    System.out.println("Must be between 0 and 8");
                    return;
                }
                temp[check] = userChoice.charAt(i);
                used.add(userChoice.charAt(i));
                check++;
            }
            if (check != 3){ //they didnt input enough characters
                System.out.println("Invalid input, too few numbers");
                return;
            }
            System.out.println("Enter the second row, use space between numbers");
            userChoice = userIn.nextLine();
            for (int i = 0; i < userChoice.length() && check < 6; i++){
                if (userChoice.charAt(i) == ' '){
                    continue;
                }
                if (used.contains(userChoice.charAt(i))){
                    System.out.println("Duplicate entry, Please Try Again");
                    return;
                }
                if (userChoice.charAt(i) == '9'){
                    System.out.println("Must be between 0 and 8");
                    return;
                }
                temp[check] = userChoice.charAt(i);
                used.add(userChoice.charAt(i));
                check++;
            }
            if (check != 6){
                System.out.println("Invalid input, too few numbers");
                return;
            }
            System.out.println("Enter the third row, use space between numbers");
            userChoice = userIn.nextLine();
            for (int i = 0; i < userChoice.length() && check < 9; i++){
                if(userChoice.charAt(i) == ' '){
                    continue;
                }
                if (used.contains(userChoice.charAt(i))){
                    System.out.println("Duplicate entry, Please Try Again");
                    return;
                }
                if (userChoice.charAt(i) == '9'){
                    System.out.println("Must be between 0 and 8");
                    return;
                }
                temp[check] = userChoice.charAt(i);
                used.add(userChoice.charAt(i));
                check++;
            }
            if (check != 9){
                System.out.println("Invalid input, too few numbers");
                return;
            }
            userPuzzle = new String(temp); //turn the character array into a string
        }
        else {
            System.out.println("Invalid input");
            return;
        }
        //now we have the puzzle stored in userPuzzle
        System.out.println(userPuzzle);
        //now we have to get the heuristic
        System.out.println("Enter your choice of algorithm.");
        System.out.println("0) Uniform Cost Search");
        System.out.println("1) A* with the Misplaced Tile Heuristic");
        System.out.println("2) A* with the Eucledian Distance Heuristic");

        userChoice = userIn.nextLine();

        Problem p = new Problem(userPuzzle);

        final long startTime = System.currentTimeMillis(); //for tracking runtime
        if (userChoice.charAt(0) == '0'){
            p.search(0);
        }
        else if (userChoice.charAt(0) == '1'){
            p.search(1);
        }
        else if (userChoice.charAt(0) == '2'){
            p.search(2);
        }
        else {
            System.out.println("Invalid Input");
            return;
        }
        final long endTime = System.currentTimeMillis();
        System.out.print("Runtime: ");
        System.out.println(endTime - startTime);

    }
}
