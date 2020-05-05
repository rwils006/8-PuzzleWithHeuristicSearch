package com.company;
import java.util.ArrayList;

public class Node {
    private String state; //holds the state of the game board at this node
    private Node parent; //pointer to the parent node
    private ArrayList<Node> children; //list of all the nodes children

    public Node(String state){ //constructor
        this.state = state;
        children = new ArrayList<Node>(); //create an empty list
    }

    //methods for retrieving information about the node
    public String getState(){
        return this.state;
    }

    public Node getParent(){
        return this.parent;
    }

    public ArrayList<Node> getChildren(){
        return this.children;
    }

    public void printNode(){
        System.out.print('[');
        for (int i = 0; i < 3; i++){
            System.out.print(this.state.charAt(i));
            if(i != 2) {
                System.out.print(", ");
            }
        }
        System.out.println(']');
        System.out.print('[');
        for (int i = 3; i < 6; i++){
            System.out.print(this.state.charAt(i));
            if(i != 5) {
                System.out.print(", ");
            }
        }
        System.out.println(']');
        System.out.print('[');
        for (int i = 6; i < 9; i++){
            System.out.print(this.state.charAt(i));
            if(i != 8) {
                System.out.print(", ");
            }
        }
        System.out.println(']');
    }
}
