package com.company;
import java.util.Comparator;

class PriorityComparator implements Comparator<Node>{

//overides the compare function so we can judge priority from totalcost
    public int compare(Node a, Node b){
        if(a.getTotalCost() < b.getTotalCost()){
            return -1;
        }
        else if (a.getTotalCost() > b.getTotalCost()){
            return 1;
        }
        return 0;
    }
}
