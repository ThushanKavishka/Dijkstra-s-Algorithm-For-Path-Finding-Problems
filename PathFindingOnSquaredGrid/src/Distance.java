/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author THUSHAN_99
 */
public class Distance {

    // private instance variables
    //Diagonal Distance
    private double diagonalDistance;
    //horizontal and vertical distance
    private double horizontalVerticalDistance;

    //public methods to change values after the distance is constructed 
    public void setDiagonalDistance(double newDiagonalDistance) {
        this.diagonalDistance = newDiagonalDistance;

    }

    public void setHorizontalVerticalDistance(double newHorizontalVerticalDistance) {
        this.horizontalVerticalDistance = newHorizontalVerticalDistance;

    }

    //public method to return diagonal distance
    public double getDiagonalDistance() {
        return diagonalDistance;
    }

    //public method to return horizontal & vertical distance
    public double getHorizontalVerticalDistance() {
        return horizontalVerticalDistance;
    }

}
