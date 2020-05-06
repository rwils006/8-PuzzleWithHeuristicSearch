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

    public String moveLeft(String state, int index){ //swap with index -1
        char[] c = state.toCharArray();
        char temp = c[index];
        c[index] = c[index - 1];
        c[index - 1] = temp;
        String end = String.valueOf(c);
        return end;
    }

    public String moveRight(String state, int index){ //swap with index + 1
        char[] c = state.toCharArray();
        char temp = c[index];
        c[index] = c[index + 1];
        c[index + 1] = temp;
        String end = String.valueOf(c);
        return end;
    }

    public String moveUp(String state, int index){ //swap with index -3
        char[] c = state.toCharArray();
        char temp = c[index];
        c[index] = c[index - 3];
        c[index - 3] = temp;
        String end = String.valueOf(c);
        return end;
    }

    public String moveDown(String state, int index){ //swap with index + 3
        char[] c = state.toCharArray();
        char temp = c[index];
        c[index] = c[index + 3];
        c[index + 3] = temp;
        String end = String.valueOf(c);
        return end;
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
            ArrayList<String> children = getPossibleStates(node);
            for(String i : children){
                if(this.explored.contains(i)){ //check if the state has already been seen
                    continue;
                }

                this.explored.add(i);
                Node futureChild = new Node(i);
                node.addChild(futureChild);
                futureChild.setParent(node);

                if (heuristic == 0){
                    //call Uniform Cost
                } else if (heuristic == 1){
                    //call misplaced tile
                } else if (heuristic == 2){
                    //call eucledian dist
                }
            }
        }

    }

    public ArrayList<String> getPossibleStates(Node n){
        /*This Function will return all possible states you can get to from the parent node*/

        ArrayList<String> possibleStates = new ArrayList<String>();

        switch (n.getState().indexOf('0')){ //finds where the blank is
            case 0: { //only 2 moves if its in the upper left corner
                possibleStates.add(moveRight(n.getState(), 0));
                possibleStates.add(moveDown(n.getState(), 0));
            }

            case 1: { //upper middle
                possibleStates.add(moveRight(n.getState(), 1));
                possibleStates.add(moveDown(n.getState(), 1));
                possibleStates.add(moveLeft(n.getState(), 1));
            }

            case 2: { //upper right
                possibleStates.add(moveDown(n.getState(), 2));
                possibleStates.add(moveLeft(n.getState(), 2));
            }

            case 3: { //middle left
                possibleStates.add(moveUp(n.getState(), 3));
                possibleStates.add(moveDown(n.getState(), 3));
                possibleStates.add(moveRight(n.getState(), 3));
            }

            case 4: { //middle middle
                possibleStates.add(moveUp(n.getState(), 4));
                possibleStates.add(moveDown(n.getState(), 4));
                possibleStates.add(moveLeft(n.getState(), 4));
                possibleStates.add(moveRight(n.getState(), 4));
            }

            case 5: { //middle right
                possibleStates.add(moveUp(n.getState(), 5));
                possibleStates.add(moveDown(n.getState(), 5));
                possibleStates.add(moveLeft(n.getState(), 5));
            }

            case 6: { //lower left
                possibleStates.add(moveUp(n.getState(), 6));
                possibleStates.add(moveRight(n.getState(), 6));
            }

            case 7: { //lower middle
                possibleStates.add(moveUp(n.getState(), 7));
                possibleStates.add(moveLeft(n.getState(), 7));
                possibleStates.add(moveRight(n.getState(), 7));
            }

            case 8: { //lower right
                possibleStates.add(moveUp(n.getState(), 8));
                possibleStates.add(moveLeft(n.getState(), 8));
            }
        }
        return possibleStates;
    }
}
