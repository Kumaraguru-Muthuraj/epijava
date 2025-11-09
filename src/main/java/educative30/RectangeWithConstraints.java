package educative30;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

/** Your goal is to find the largest rectangle (having maximum area) that can be formed using any four points as the corners. The rectangle should meet the following conditions:
 It has its borders parallel to the axes.
 It should not contain any other points inside or along its border.
 Return the area of the largest rectangle you can create. If no such rectangle can be formed.
 */
public class RectangeWithConstraints {
    public static int maxRectangleArea(int[][] points) {
        //Hash for quick lookup.
        HashSet<String> hashedPoints = new HashSet<>();
        for (int[] point : points) {
            hashedPoints.add(point[0] + "#" + point[1]);
        }

        //Compute all diagonals - Ignore points in the same x or y value.
        HashSet<Pair> ds = getDiagonals(points);
        for (Pair p : ds) {
            System.out.println(p);
        }
        //Areas
        System.out.println("Areas");
        int maxArea = -1;
        for (Pair diagonal : ds) {
            System.out.print(diagonal.toString() + " - ");
            int area = getAreaOfValidRectangle(diagonal, hashedPoints, points);
            System.out.println(area);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    public static int getAreaOfValidRectangle(Pair diagonal, HashSet<String> hashedPoints, int[][] points) {
        int[] p1 = diagonal.p1;
        int[] p2 = diagonal.p2;

        int p3X = p2[0];
        int p3Y = p1[1];
        int p4X = p1[0];
        int p4Y = p2[1];

        String p3 = new String(p3X + "#" + p3Y);
        String p4 = new String(p4X + "#" + p4Y);
        if (hashedPoints.contains(p3) && hashedPoints.contains(p4)) {
            if (validRectangle(diagonal, points)) {
                int l = p2[0] - p1[0];
                int b = p2[1] - p1[1];
                return Math.abs(l) * Math.abs(b);
            }
        }
        return -1;
    }

    public static boolean validRectangle(Pair diagonal, int[][] points) {
        int p1X = diagonal.p1[0];
        int p1Y = diagonal.p1[1];
        int p2X = diagonal.p2[0];
        int p2Y = diagonal.p2[1];

        int p3X = p2X;
        int p3Y = p1Y;
        int p4X = p1X;
        int p4Y = p2Y;

        for (int[] point : points) {
            int x = point[0];
            int y = point[1];

            if ((p1X == x && p1Y == y) || (p2X == x && p2Y == y) ||
                    (p3X == x && p3Y == y) || (p4X == x && p4Y == y)) {
                continue;
            }
            if (p1X < p2X && p1Y < p2Y) {
                if (p1X <= x && x <= p2X && p1Y <= y && y <= p2Y) {
                    return false;
                }
            } else if (p1X < p2X && p2Y < p1Y) {
                if (p1X <= x && x <= p2X && p2Y <= y && y <= p1Y) {
                    return false;
                }
            } else if (p2X < p1X && p1Y < p2Y) {
                if (p2X <= x && x <= p1X && p1Y <= y && y <= p2Y) {
                    return false;
                }
            } else if (p2X < p1X && p2Y < p1Y) {
                if (p2X <= x && x <= p1X && p2Y <= y && y <= p1Y) {
                    return false;
                }
            }
        }
        return true;
    }

    public static HashSet<Pair> getDiagonals(int[][] points) {
        HashSet<Pair> diagonals = new HashSet<>();
        for (int i = 0; i < points.length - 1; i++) {
            int[] point1 = points[i];
            for (int j = i + 1; j < points.length; j++) {
                int[] point2 = points[j];
                Diagonal d = new Diagonal(point1, point2);
                if (d.valid()) {
                    diagonals.add(d);
                }
            }
        }
        return diagonals;
    }


    static class Pair {
        public Pair(int[] p1, int[] p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair that = (Pair) o;
            return this.p1[0] == that.p1[0] && this.p2[0] == that.p2[0];
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(p1) ^ Objects.hashCode(p2);
        }

        @Override
        public String toString() {
            if (p1[0] < p2[0]) {
                StringBuilder sb = new StringBuilder();
                sb.append(p1[0]).append(p1[1]).append(p2[0]).append(p2[1]);
                return sb.toString();
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(p2[0]).append(p2[1]).append(p1[0]).append(p1[1]);
                return sb.toString();
            }
        }
        int[] p1;
        int[] p2;
    }

    public static class Diagonal extends Pair {
        public Diagonal(int[] p1, int[] p2) {
            super(p1, p2);
        }
        public boolean valid() {
            return p1[0] != p2[0] && p1[1] != p2[1];
        }
    }


    public static void main(String[] args) {
        int[][] points = {{4,9},{4,10},{4,6},{4,8}};
        Pair p1 = new Pair(points[0], points[1]);
        Pair p2 = new Pair(points[1], points[0]);
        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(p1.equals(p2));

        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());

        System.out.println("Max Area - " + maxRectangleArea(points));
    }
}
