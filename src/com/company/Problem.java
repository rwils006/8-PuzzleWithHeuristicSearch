package com.company;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.lang.*;



public class Problem {

    private Node initialState;
    private String goalState;
    private PriorityQueue<Node> frontier; //will have to sort this to make it a priority queue
    private HashSet<String> explored; //hash set is a fast way to store explored nodes with no duplicates
    private int maxNodes; //maximum nodes in the frontier during search

    public Problem() {
        this.initialState = new Node("123456780"); //trivial case
        this.goalState = "123456780"; //normal goal
        this.frontier = new PriorityQueue<Node>(10, new PriorityComparator());
        this.frontier.add(this.initialState);
        this.maxNodes = 1;
        this.explored = new HashSet<String>();
    }

    public Problem(String root) {
        this.initialState = new Node(root);
        this.goalState = "123456780";
        this.frontier = new PriorityQueue<Node>(10, new PriorityComparator());
        this.frontier.add(this.initialState);
        this.maxNodes = 1;
        this.explored = new HashSet<String>();
    }

    public String moveLeft(String state, int index) { //swap with index -1
        char[] c = state.toCharArray();
        char temp = c[index];
        c[index] = c[index - 1];
        c[index - 1] = temp;
        String end = String.valueOf(c);
        //System.out.println("Move Left");
        return end;
    }

    public String moveRight(String state, int index) { //swap with index + 1
        char[] c = state.toCharArray();
        char temp = c[index];
        c[index] = c[index + 1];
        c[index + 1] = temp;
        String end = String.valueOf(c);
        //System.out.println("Move Right");
        return end;
    }

    public String moveUp(String state, int index) { //swap with index -3
        char[] c = state.toCharArray();
        char temp = c[index];
        c[index] = c[index - 3];
        c[index - 3] = temp;
        String end = String.valueOf(c);
        //System.out.println("Move Up");
        return end;
    }

    public String moveDown(String state, int index) { //swap with index + 3
        char[] c = state.toCharArray();
        char temp = c[index];
        c[index] = c[index + 3];
        c[index + 3] = temp;
        String end = String.valueOf(c);
        //System.out.println("Move Down");
        return end;
    }

    public void search(int heuristic) {
        /*
        this is the function used to search through the tree and get the solution
        this function takes in a heuristic value that determines the value for estimated cost h(n)
        at the end, this function prints the solution
        */

        Node current;
        ArrayList<String> children = new ArrayList<String>();

        //System.out.println(node.getState().indexOf('0'));
        //children = getPossibleStates(node);
        //System.out.println(children.size());


        while (true) {
            if (this.frontier.isEmpty()) { //fails if frontier is ever empty
                System.out.println("Failed to find Solution");
                System.out.print("Total Nodes: ");
                System.out.println(this.explored.size());
                System.out.print("MaxNodes in Frontier: ");
                System.out.println(this.maxNodes);
                return;
            }
            if (this.frontier.size() > this.maxNodes){ //keeps track of maxnodes
                this.maxNodes = this.frontier.size();
            }

            current = this.frontier.poll(); //removes lowest cost from frontier

            if (current.getState().equals(this.goalState)) {
                this.printSolution(current, this.initialState); //prints from root to goal
                return;
            }

            this.explored.add(current.getState()); //add this state to the explored list
            children = getPossibleStates(current); //get all possible moves from current

            for (String i : children) { //creates nodes for unique states from current
                if (this.explored.contains(i)) {
                    //System.out.println("Seen it");
                    continue; //go to next child because weve already seen this one cheaper
                }
                Node child = new Node(i);
                child.setParent(current); //used for making solution

                if (heuristic == 1) {
                    child.setCost(current.getCost() + 1);
                    child.setEstimatedCost(this.misplacedTiles(child.getState(), this.goalState));
                    //System.out.print("Misplaced Tiles: ");
                    //System.out.println(child.getEstimatedCost());
                    child.setTotalCost();
                } else if (heuristic == 2) {
                    child.setCost(current.getCost() + 1);
                    child.setEstimatedCost(this.eucledian(child.getState(), this.goalState));
                    child.setTotalCost();
                } else { //Uniform Cost
                    child.setCost(current.getCost() + 1);
                    child.setEstimatedCost(0);
                    child.setTotalCost();
                }
                this.frontier.add(child);
            }
        }
    }







    public ArrayList<String> getPossibleStates(Node n) {
        /*This Function will return all possible states you can get to from the parent node*/

        ArrayList<String> possibleStates = new ArrayList<String>(0);
        //System.out.println(n.getState().indexOf('0'));
        switch (n.getState().indexOf('0')) { //finds where the blank is
            case 0: { //only 2 moves if its in the upper left corner
                possibleStates.add(moveRight(n.getState(), 0));
                possibleStates.add(moveDown(n.getState(), 0));
                //System.out.println("Case 0");
                break; //need this to stop it from going to other cases
            }

            case 1: { //upper middle
                possibleStates.add(moveRight(n.getState(), 1));
                possibleStates.add(moveDown(n.getState(), 1));
                possibleStates.add(moveLeft(n.getState(), 1));
                //System.out.println("Case 1");
                break;
            }

            case 2: { //upper right
                possibleStates.add(moveDown(n.getState(), 2));
                possibleStates.add(moveLeft(n.getState(), 2));
                //System.out.println("Case 2");
                break;
            }

            case 3: { //middle left
                possibleStates.add(moveUp(n.getState(), 3));
                possibleStates.add(moveDown(n.getState(), 3));
                possibleStates.add(moveRight(n.getState(), 3));
                //System.out.println("Case 3");
                break;
            }

            case 4: { //middle middle
                possibleStates.add(moveUp(n.getState(), 4));
                possibleStates.add(moveDown(n.getState(), 4));
                possibleStates.add(moveLeft(n.getState(), 4));
                possibleStates.add(moveRight(n.getState(), 4));
                //System.out.println("Case 4");
                break;
            }

            case 5: { //middle right
                possibleStates.add(moveUp(n.getState(), 5));
                possibleStates.add(moveDown(n.getState(), 5));
                possibleStates.add(moveLeft(n.getState(), 5));
                //System.out.println("Case 5");
                break;
            }

            case 6: { //lower left
                possibleStates.add(moveUp(n.getState(), 6));
                possibleStates.add(moveRight(n.getState(), 6));
                //System.out.println("Case 6");
                break;
            }

            case 7: { //lower middle
                possibleStates.add(moveUp(n.getState(), 7));
                possibleStates.add(moveLeft(n.getState(), 7));
                possibleStates.add(moveRight(n.getState(), 7));
                //System.out.println("Case 7");
                break;
            }

            case 8: { //lower right
                possibleStates.add(moveUp(n.getState(), 8));
                possibleStates.add(moveLeft(n.getState(), 8));
                //System.out.println("Case 8");
                break;
            }
        }
        return possibleStates;
    }


    public int misplacedTiles(String state, String goal) { //how many total tiles are different
        int diff = 0;
        for (int i = 0; i < state.length(); i++) {
            if (state.charAt(i) != goal.charAt(i)) {
                diff++;
            }
        }
        return diff;
    }

    public int eucledian(String state, String goal){
        //splits a string up and basically turns it into a 3x3 grid to find distance

        double difference = 0;
        for (int i = 0; i < 3; i++){ //coordinates (0,0),(1,0),(2,0), y = 0
            for (int j = 0; j < 3; j++){ //b = 0
                if(state.charAt(i) == goal.charAt(j)){ //found the match
                    difference = difference + Math.sqrt((i-j)*(i-j));
                }
            }
            for (int j = 3; j < 6; j++){ //b = 1
                if(state.charAt(i) == goal.charAt(j)){ //found the match
                    difference = difference + Math.sqrt((i-j)*(i-j) + 1);
                }
            }
            for (int j = 6; j < goal.length(); j++){ //b = 2
                if(state.charAt(i) == goal.charAt(j)){ //found the match
                    difference = difference + Math.sqrt((i-j)*(i-j) + 4);
                }
            }
        }
        for (int i = 3; i < 6; i++){ //coordinates (0,1),(1,1),(2,1) y = 1
            for (int j = 0; j < 3; j++){ //b = 0
                if(state.charAt(i) == goal.charAt(j)){ //found the match
                    difference = difference + Math.sqrt((i-j)*(i-j) + 1);
                }
            }
            for (int j = 3; j < 6; j++){ //b = 1
                if(state.charAt(i) == goal.charAt(j)){ //found the match
                    difference = difference + Math.sqrt((i-j)*(i-j));
                }
            }
            for (int j = 6; j < goal.length(); j++){ //b = 2
                if(state.charAt(i) == goal.charAt(j)){ //found the match
                    difference = difference + Math.sqrt((i-j)*(i-j) + 1);
                }
            }
        }
        for (int i = 6; i < state.length(); i++){ //coordinates (0,2),(1,2),(2,2), y = 2
            for (int j = 0; j < 3; j++){ //b = 0
                if(state.charAt(i) == goal.charAt(j)){ //found the match
                    difference = difference + Math.sqrt((i-j)*(i-j) + 4);
                }
            }
            for (int j = 3; j < 6; j++){ //b = 1
                if(state.charAt(i) == goal.charAt(j)){ //found the match
                    difference = difference + Math.sqrt((i-j)*(i-j) + 1);
                }
            }
            for (int j = 6; j < goal.length(); j++){ //b = 2
                if(state.charAt(i) == goal.charAt(j)){ //found the match
                    difference = difference + Math.sqrt((i-j)*(i-j));
                }
            }
        }

        return (int)difference;
    }

    public void printSolution(Node endNode, Node root){



        Node n = endNode;
        ArrayList<Node> solution = new ArrayList<Node>();


        while(n.getState() != root.getState()){
            solution.add(n);
            n = n.getParent();
        }

        solution.add(root);

        for (int i = solution.size() - 1; i >= 0; i--){
            if (solution.get(i).equals(root)){
                System.out.println("Expanding State");
                solution.get(i).printNode();
            }
            else{
                System.out.print("The best state to expand with g(n) = ");
                System.out.print(solution.get(i).getCost());
                System.out.print(" and h(n) = ");
                System.out.print(solution.get(i).getEstimatedCost());
                System.out.println(" is...");
                solution.get(i).printNode();
            }
        }

        System.out.println("Goal!!!");
        System.out.print("Total Moves: ");
        System.out.println(endNode.getCost());
        System.out.print("To solve this problem the algorithm expanded a total of ");
        System.out.print(this.explored.size());
        System.out.println(" nodes.");
        System.out.print("The maximum number of nodes in the queue at any one time: ");
        System.out.println(this.maxNodes);

    }
}
