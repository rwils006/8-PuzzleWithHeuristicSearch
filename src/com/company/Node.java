package com.company;
import java.util.ArrayList;

public class Node {
    private String state; //holds the state of the game board at this node
    private Node parent; //pointer to the parent node
    private ArrayList<Node> children; //list of all the nodes children

    private int cost; //cost of getting to this node g(n)
    private int estimatedCost; //estimated cost of getting to goal h(n)
    private int totalCost; // this is going to be cost + estimated cost, used for comparing nodes

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

    public ArrayList<String> getPossibleStates(){
        /*This Function will return all possible states you can get to from the parent node*/

        ArrayList<String> possibleStates = new ArrayList<String>();

        switch (this.state.indexOf('0')){ //finds where the blank is
            case 0: { //only 2 moves if its in the upper left corner
                //move right
                //move down
            }

            case 1: { //upper middle
                //move right
                //move down
                //move left
            }

            case 2: { //upper right
                //move down
                //move left
            }

            case 3: { //middle left
                //move up
                //move down
                //move right
            }

            case 4: { //middle middle
                //move up
                //move down
                //move left
                //move right
            }

            case 5: { //middle right
                //move up
                //move down
                //move left
            }

            case 6: { //lower left
                //move up
                //move right
            }

            case 7: { //lower middle
                //move up
                //move left
                //move right
            }

            case 8: { //lower right
                //move up
                //move left
            }
        }

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

    public void addChild(Node child){
        this.children.add(child);
    }

    public void setParent(Node parent){
        this.parent = parent;
    }

    public void setCost(int cost){
        this.cost = cost;
    }

    public void setEstimatedCost(int eCost){
        this.estimatedCost = eCost;
    }

    public void setTotalCost(){
        this.totalCost = this.cost + this.estimatedCost;
    }
}
