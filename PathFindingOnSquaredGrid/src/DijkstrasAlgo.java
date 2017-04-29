/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author THUSHAN_99
 */
import java.util.*;

public class DijkstrasAlgo {

    //declaring starting point and end point of the nodes
    Node startPoint;
    Node endPoint;
    /*declaring gridArea
      gridArea is used to store nodes
     */
    Node[][] gridArea;
//    //Diagonal Distance
//    double diagonalDistance;
//    //horizontal and vertical distance
//    double horizontalVerticalDistance;
//    //create an object of Distance class
    Distance distnce = new Distance();
    Scanner sc = new Scanner(System.in);

    String metric;
    double runningTime;

    /**
     * matrix boolean matrix from the framework given in the spec
     * @starti start x value
     * @startj start y value
     * @endi end x value
     * @endj end y value
     * @return The path nodes
     */
    List<Node> distance(boolean[][] matrix, int starti, int startj, int endi, int endj) {
        System.out.println();
        System.out.println("****************************************");
        System.out.println("----METRICS----");
        System.out.println("1)Euclidean distance");
        System.out.println("2)Manhattan distance");
        System.out.println("3)Chebyshev distance");
        System.out.print("Choose Metric From The Above: ");
        String choiceDistance = sc.next();
        Stopwatch timerFlow = new Stopwatch();

        switch (choiceDistance) {
            case "1":
                //Euclidean Distances
                distnce.setDiagonalDistance(1.4);
                distnce.setHorizontalVerticalDistance(1.0);
                metric = "Euclidean";
                break;
            case "2":
                //Manhattan Distances
                distnce.setDiagonalDistance(2.0);
                distnce.setHorizontalVerticalDistance(1.0);
                metric = "Manhattan";
                break;
            case "3":
                //Chebyshev Distances
                distnce.setDiagonalDistance(1.0);
                distnce.setHorizontalVerticalDistance(1.0);
                metric = "Chebyshev";
                break;
            default:
                break;
        }
        //store length of the matrix into size variable
        int sizeMetrix = matrix.length;
        //creating new start node
        startPoint = new Node(starti, startj);
        //creating new end node
        endPoint = new Node(endi, endj);
        //store size of the node into gridArea variable
        gridArea = new Node[sizeMetrix][sizeMetrix];

        // Creating nodes and finding Blocked Cells in Matrix 
        for (int k = 0; k < sizeMetrix; ++k) {
            for (int l = 0; l < sizeMetrix; ++l) {
                gridArea[k][l] = new Node(k, l);
                if (matrix[k][l] == false) {
                    gridArea[k][l].blockedNode = true;
                }
            }
        }

        /* set startPoint distance to 0 int in the beginning. 
         All other nodes will have infinity distance at the beginning*/
        startPoint.distance = 0;
        double totCost = 0;

        // a comparator object to deal with Priority Queue
        Comparator<Node> comparator = (left, right) -> {
            if (left.distance > (right.distance)) {
                return 1;
            }
            return -1;
        };

        // Queue to store visiting nodes in the metrix
        Queue<Node> visitingNodesQueue = new PriorityQueue(sizeMetrix, comparator);

        //Add first start point node to the visitingNodesQueue and repeat it
        visitingNodesQueue.add(startPoint);

        while (visitingNodesQueue.size() > 0) {
            //take a node from visitingNodesQueue and assign to currentNode variable and aalso remove that node from the queue
            Node currentNode = visitingNodesQueue.remove();
            Node node;

            // Concerning Top Grids
            if (currentNode.i - 1 >= 0) {

                // Top Grid
                //Assign surrounding grids one by one from the current grid into node variable
                node = gridArea[currentNode.i - 1][currentNode.j];
                /*Checked if node is visited node or blocked node or node distance is greater than
                            total of current node distance + horizontal and vertical distance that given metric*/
                if (!node.visitedNode && !node.blockedNode && node.distance > currentNode.distance + distnce.getHorizontalVerticalDistance()) {
                    node.distance = currentNode.distance + distnce.getHorizontalVerticalDistance();
                    node.parentNode = currentNode;
                    visitingNodesQueue.add(node);
                }

                /** Do not consider diagonal nodes when manhattan distance is applied**/
                if(!metric.equals("Manhattan")) {
                    // Top Left Grid
                    if (currentNode.j - 1 > 0) {
                        //Assign surrounding grids one by one from the current grid into node variable
                        node = gridArea[currentNode.i - 1][currentNode.j - 1];
                    /*Checked if node is visited node or blocked node or node distance is greater than
                            total of current node distance + diagonal distance that given metric*/
                        if (!node.visitedNode && !node.blockedNode && node.distance > currentNode.distance + distnce.getDiagonalDistance()) {
                            node.distance = currentNode.distance + distnce.getDiagonalDistance();
                            node.parentNode = currentNode;
                            visitingNodesQueue.add(node);
                        }
                    }

                    // Top Right Grid
                    if (currentNode.j + 1 < sizeMetrix) {
                        //Assign surrounding grids one by one from the current grid into node variable
                        node = gridArea[currentNode.i - 1][currentNode.j + 1];
                    /*Checked if node is visited node or blocked node or node distance is greater than
                            total of current node distance + diagonal distance that given metric*/
                        if (!node.visitedNode && !node.blockedNode && node.distance > currentNode.distance + distnce.getDiagonalDistance()) {
                            node.distance = currentNode.distance + distnce.getDiagonalDistance();
                            node.parentNode = currentNode;
                            visitingNodesQueue.add(node);
                        }
                    }
                }
            }

            // Concerning Left Grid
            if (currentNode.j - 1 > 0) {
                //Assign surrounding grids one by one from the current grid into node variable
                node = gridArea[currentNode.i][currentNode.j - 1];
                /*Checked if node is visited node or blocked node or node distance is greater than
                            total of current node distance + horizontal and vertical distance that given metric*/
                if (!node.visitedNode && !node.blockedNode && node.distance > currentNode.distance + distnce.getHorizontalVerticalDistance()) {
                    node.distance = currentNode.distance + distnce.getHorizontalVerticalDistance();
                    node.parentNode = currentNode;
                    visitingNodesQueue.add(node);
                }
            }

            // Concerning Right Grid
            if (currentNode.j + 1 < sizeMetrix) {
                //Assign surrounding grids one by one from the current grid into node variable
                node = gridArea[currentNode.i][currentNode.j + 1];
                if (!node.visitedNode && !node.blockedNode && node.distance > currentNode.distance + distnce.getHorizontalVerticalDistance()) {
                    node.distance = currentNode.distance + distnce.getHorizontalVerticalDistance();
                    node.parentNode = currentNode;
                    visitingNodesQueue.add(node);
                }
            }
            // Concerning Below Grids
            if (currentNode.i + 1 < sizeMetrix) {

                // Down Grid
                //Assign surrounding grids one by one from the current grid into node variable
                node = gridArea[currentNode.i + 1][currentNode.j];
                /*Checked if node is visited node or blocked node or node distance is greater than
                            total of current node distance + horizontal and vertical distance that given metric*/
                if (!node.visitedNode && !node.blockedNode && node.distance > currentNode.distance + distnce.getHorizontalVerticalDistance()) {
                    node.distance = currentNode.distance + distnce.getHorizontalVerticalDistance();
                    node.parentNode = currentNode;
                    visitingNodesQueue.add(node);
                }

                /** Do not consider diagonal nodes when manhattan distance is applied**/
                if(!metric.equals("Manhattan")) {
                    // Down Left Grid
                    if (currentNode.j - 1 >= 0) {
                        //Assign surrounding grids one by one from the current grid into node variable
                
                        
                        node = gridArea[currentNode.i + 1][currentNode.j - 1];
                    /*Checked if node is visited node or blocked node or node distance is greater than
                            total of current node distance + diagonal distance that given metric*/
                        if (!node.visitedNode && !node.blockedNode && node.distance > currentNode.distance + distnce.getDiagonalDistance()) {
                            node.distance = currentNode.distance + distnce.getDiagonalDistance();
                            node.parentNode = currentNode;
                            visitingNodesQueue.add(node);
                        }
                    }

                    // Down Right Grid
                    if (currentNode.j + 1 < sizeMetrix) {
                        //Assign surrounding grids one by one from the current grid into node variable
                        node = gridArea[currentNode.i + 1][currentNode.j + 1];
                    /*Checked if node is visited node or blocked node or node distance is greater than
                            total of current node distance + diagonal distance that given metric*/
                        if (!node.visitedNode && !node.blockedNode && node.distance > currentNode.distance + distnce.getDiagonalDistance()) {
                            node.distance = currentNode.distance + distnce.getDiagonalDistance();
                            node.parentNode = currentNode;
                            visitingNodesQueue.add(node);
                        }
                    }
                }
            }
            //put the current node as visited node
            currentNode.visitedNode = true;
            //setting the g cost for the current node
            currentNode.setgCost(startPoint.distance+currentNode.distance);
        }

        //creating a setPath Arraylist to add current end node
        List<Node> setPath = new ArrayList<>();

        // Checking if a path is already exists
        if (!(gridArea[endPoint.i][endPoint.j].distance == Integer.MAX_VALUE)) {
            //Trace Back the path
            Node currentEndNode = gridArea[endPoint.i][endPoint.j];

            setPath.add(currentEndNode);
            while (currentEndNode.parentNode != null) {
                setPath.add(currentEndNode.parentNode);
                currentEndNode = currentEndNode.parentNode;
            }
        } else {
            System.out.println("OOPS! NO POSSIBLE PATH!!!");
        }
        //return the Arraylist
        setRunningTime(timerFlow.elapsedTime());
        return setPath;
          
    }

    public double getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(double runningTime) {
        this.runningTime = runningTime;
    }

    public String getMetric() {
        return metric;
    }
}
