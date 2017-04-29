/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author THUSHAN_99
 */
public class Node {

    boolean visitedNode;
    boolean blockedNode;
    //declaring directions of the metrix
    int i;
    int j;
    double gCost;
    Node parentNode = null;
    double distance = Integer.MAX_VALUE;

    //Constructor
    public Node(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public double getgCost() {
        return gCost;
    }

    public void setgCost(double gCost) {
        this.gCost = gCost;
    }

    @Override
    public String toString() {
        return "Node{" +
                "i=" + i +
                ", j=" + j +
                ", g=" + gCost +
                '}';
    }
}
