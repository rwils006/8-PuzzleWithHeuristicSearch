package com.company;
import java.util.Scanner;

public class Main {

    String trivial = "123456780"; //userIn = 0
    String veryEasy = "123456708"; //userIn = 1
    String easy = "120453786"; //userIn = 2
    String doable = "012453786"; //userIn = 3
    String ohBoy = "871602543"; //userIn = 4
    String impossible = "123456870"; //userIn = 5

    public static void main(String[] args) {

        System.out.println("Pick a number between 0 and 5: ");
        Scanner userIn = new Scanner(System.in); //this gets the user input
        char user = userIn.next().charAt(0);
        if (user == '0'){

        } else if (user == '1'){

        } else if (user == '2'){

        } else if (user == '3'){

        } else if (user == '4'){

        } else if (user == '5'){

        } else {
            System.out.println("Invalid input");
        }
    }
}
