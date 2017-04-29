/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author THUSHAN_99
 */

import java.util.*;

public class PathFindingOnSquaredGrid {

    /**the grid size**/
    static int N = 10;

    // given an N-by-N matrix of open cells, return an N-by-N matrix
    // of cells reachable from the top
    public static boolean[][] flow(boolean[][] open) {
        int N = open.length;

        boolean[][] full = new boolean[N][N];
        for (int j = 0; j < N; j++) {
            flow(open, full, 0, j);
        }

        return full;
    }

    // determine set of open/blocked cells using depth first search
    public static void flow(boolean[][] open, boolean[][] full, int i, int j) {
        int N = open.length;

        // base cases
        if (i < 0 || i >= N) {
            return;    // invalid row
        }
        if (j < 0 || j >= N) {
            return;    // invalid column
        }
        if (!open[i][j]) {
            return;        // not an open cell
        }
        if (full[i][j]) {
            return;         // already marked as open
        }
        full[i][j] = true;

        flow(open, full, i + 1, j);   // down
        flow(open, full, i, j + 1);   // right
        flow(open, full, i, j - 1);   // left
        flow(open, full, i - 1, j);   // up
    }

    // does the system percolate?
    public static boolean percolates(boolean[][] open) {
        int N = open.length;

        boolean[][] full = flow(open);
        for (int j = 0; j < N; j++) {
            if (full[N - 1][j]) {
                return true;
            }
        }

        return false;
    }

    // does the system percolate vertically in a direct way?
    public static boolean percolatesDirect(boolean[][] open) {
        int N = open.length;

        boolean[][] full = flow(open);
        int directPerc = 0;
        for (int j = 0; j < N; j++) {
            if (full[N - 1][j]) {
                // StdOut.println("Hello");
                directPerc = 1;
                int rowabove = N - 2;
                for (int i = rowabove; i >= 0; i--) {
                    if (full[i][j]) {
                        // StdOut.println("i: " + i + " j: " + j + " " + full[i][j]);
                        directPerc++;
                    } else {
                        break;
                    }
                }
            }
        }

        // StdOut.println("Direct Percolation is: " + directPerc);
        if (directPerc == N) {
            return true;
        } else {
            return false;
        }
    }

    // draw the N-by-N boolean matrix to standard draw
    public static void show(boolean[][] a, boolean which) {
        int N = a.length;
        StdDraw.setXscale(-1, N);
        ;
        StdDraw.setYscale(-1, N);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (a[i][j] == which) {
                    StdDraw.square(j, N - i - 1, .5);
                } else {
                    StdDraw.filledSquare(j, N - i - 1, .5);
                }
            }
        }
    }

    // draw the N-by-N boolean matrix to standard draw, including the points A (x1, y1) and B (x2,y2) to be marked by a circle
    public static void show(boolean[][] a, boolean which, int x1, int y1, int x2, int y2) {
        int N = a.length;
        StdDraw.setXscale(-1, N);
        ;
        StdDraw.setYscale(-1, N);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (a[i][j] == which) {
                    if ((i == x1 && j == y1) || (i == x2 && j == y2)) {
                        StdDraw.circle(j, N - i - 1, .5);
                    } else {
                        StdDraw.square(j, N - i - 1, .5);
                    }
                } else {
                    StdDraw.filledSquare(j, N - i - 1, .5);
                }
            }
        }
    }

    // return a random N-by-N boolean matrix, where each entry is
    // true with probability p
    public static boolean[][] random(int N, double p) {
        boolean[][] a = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = StdRandom.bernoulli(p);
            }
        }
        return a;
    }

    // test client
    public static void main(String[] args) {
        System.out.println("");
        System.out.println("********************************************************");
        System.out.println("****************FIND SHORTEST PATH**********************");
        System.out.println("**********************USING*****************************");
        System.out.println("***************DIJKSTRA'S ALGORITHM*********************");
        System.out.println("********************************************************");
        System.out.println();
        // boolean[][] open = StdArrayIO.readBoolean2D();

        // The following will generate a 10x10 squared grid with relatively few obstacles in it
        // The lower the second parameter, the more obstacles (black cells) are generated
        boolean[][] randomlyGenMatrix = random(N, 0.6);
        System.out.println("**-----Generated Matrix-----**");
        System.out.println();
        StdArrayIO.print(randomlyGenMatrix);
        show(randomlyGenMatrix, true);

        System.out.println();
        System.out.println();

        /*      System.out.println("The system percolates: " + percolates(randomlyGenMatrix));

        System.out.println();
        System.out.println("The system percolates directly: " + percolatesDirect(randomlyGenMatrix));
        System.out.println();

         Reading the coordinates for points A and B on the input squared grid.
         THIS IS AN EXAMPLE ONLY ON HOW TO USE THE JAVA INTERNAL WATCH
         Start the clock ticking in order to capture the time being spent on inputting the coordinates
         You should position this command accordingly in order to perform the algorithmic analysis*/
        Scanner in = new Scanner(System.in);
        System.out.println("---Enter The Coordinates for Start Point & End Point---");
        System.out.print("Enter i for Start Point > ");
        int Ai = in.nextInt();

        System.out.print("Enter j for Start Point > ");
        int Aj = in.nextInt();

        System.out.print("Enter i for End Point > ");
        int Bi = in.nextInt();

        System.out.print("Enter j for End Point > ");
        int Bj = in.nextInt();


        /* THIS IS AN EXAMPLE ONLY ON HOW TO USE THE JAVA INTERNAL WATCH
         Stop the clock ticking in order to capture the time being spent on inputting the coordinates
         You should position this command accordingly in order to perform the algorithmic analysis

         System.out.println("Coordinates for A: [" + Ai + "," + Aj + "]");
         System.out.println("Coordinates for B: [" + Bi + "," + Bj + "]");*/
        show(randomlyGenMatrix, true, Ai, Aj, Bi, Bj);

        DijkstrasAlgo dijkstrasAlgo = new DijkstrasAlgo();

        List<Node> path = dijkstrasAlgo.distance(randomlyGenMatrix, Ai, Aj, Bi, Bj);

        StdDraw.setPenColor(StdDraw.RED);

        for (Node node : path) {
            //StdDraw.circle(node.j, 10 - node.i - 1, .5);
            StdDraw.filledSquare(node.j, 10 - node.i - 1, .5);

            // StdDraw.line(Aj, Ai, Bj, Bi);
        }

        StdOut.println("\nElapsed time = " + dijkstrasAlgo.getRunningTime());

        if (path.isEmpty()) {
            System.out.println("No path found using " + dijkstrasAlgo.getMetric() + " distance \n");
        } else {
            StdDraw.setPenRadius(0.01);
            StdDraw.setPenColor(StdDraw.BLUE);
            //reversing the arraylist
            Collections.reverse(path);

            for (Node node : path) {

                try {

                    StdDraw.line(node.j, randomlyGenMatrix.length - node.i - 1, node.parentNode.j, randomlyGenMatrix.length - node.parentNode.i - 1);

                    //Draw the start node
                    StdDraw.setPenColor(StdDraw.GREEN);
                    StdDraw.filledCircle(Aj, N - Ai - 1, .5);

                    //Draw the end node
                    StdDraw.setPenColor(StdDraw.ORANGE);
                    StdDraw.filledCircle(Bj, N - Bi - 1, .5);

                    StdDraw.setPenColor(StdDraw.BLUE); //reset  pen color back to black

                } catch (NullPointerException nullPointer) {

                }

            }

            //System.out.println("\nPath followed - " + Arrays.toString(path.toArray()));
            System.out.println("Total Cost - " + path.get(path.size() - 1).getgCost());

        }
    }

}
