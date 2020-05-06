package com.company;
import java.util.HashSet;
import java.util.ArrayList;

public class Problem {

    private Node initialState;
    private String goalState;
    private ArrayList<Node> frontier; //will have to sort this to make it a priority queue
    private HashSet<String> explored; //hash set is a fast way to store explored nodes with no duplicates

    public Problem(){
        this.initialState = new Node("123456780"); //trivial case
        this.goalState = "123456780"; //normal goal
        this.frontier = new ArrayList<Node>();
        this.explored = new HashSet<String>();
    }

    public Problem(String root){
        this.initialState = new Node(root);
        this.goalState = "123456780";
        this.frontier = new ArrayList<Node>();
        this.explored = new HashSet<String>();
    }

    public void search(int heuristic){
        /*
        this is the function used to search through the tree and get the solution
        this function takes in a heuristic value that determines the value for estimated cost h(n)
        at the end, this function prints the solution
        */

        Node node = new Node(this.initialState.getState()); //starts with root node

        while(node.getState() != this.goalState){
            this.explored.add(node.getState()); //we have visited this node

        }

    }
}
