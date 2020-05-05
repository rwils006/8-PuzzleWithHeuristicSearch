package com.company;
import java.util.*;

public class Problem {

    Node current;
    String goal;

    public Problem(){
        this.current = new Node("123456780"); //trivial case
        this.goal = "123456780"; //normal goal
    }

    public Problem(String root){
        this.current = new Node(root);
        this.goal = "123456780";
    }

    public void printCurrent(){
        this.current.printNode();
    }
}
