
import java.util.*;

public class containsCircle {

    Boolean containsCircle(Double R, double[][] points) {
        double maxDist = 0;
        double sideProduct = 1;
        double S = 0;
        double a;
        double b;
        double c;
        for (int i = 0; i < points.length; i++) {
            for (int j = i; j < points.length; j++) {
                double dist = euclideanDistance(points(i), points(j));
                sumLength += dist;
                if (i != j) {
                    sideProduct = sideProduct * dist;
                }
            }
        }
        double Area = Math.sqrt(S * (S - a) * (S - b) * (S - c));
        double compareRadius = a * b * c / 4 * Area;

        return compareRadius < R;
    }

    public static void main(String[] args) {

    }

}