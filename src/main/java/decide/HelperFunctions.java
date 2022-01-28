
package decide;

import java.util.*;



public class HelperFunctions {

    double getAngle(double[] p1, double[] p2, double[] p3){
        double[] vec1 = { p2[0]-p1[0],p2[1]-p1[1]};
        double[] vec2 = { p2[0]-p3[0],p2[1]-p3[1]};

        double[] zeroVec = {0,0};


        double dotProd = vec1[0]*vec2[0] + vec1[1]*vec2[1];
        double l1 = euclideanDistance(vec1,zeroVec);
        double l2 = euclideanDistance(vec2,zeroVec);


        double angle = Math.acos(dotProd/l1*l2);
        double nDegrees =  angle * 180 / Decide.PI;
        return nDegrees;
    }


    double euclideanDistance(double[] p1,double[] p2){

        return Math.sqrt(   Math.pow((p1[0]-p2[0]),2)     +   Math.pow((p1[1]-p2[1]),2)    );
    }

    Boolean insideCircle(Double R, double[][] points) {
        double a = euclideanDistance(points[0],points[1]);
        double b =  euclideanDistance(points[0],points[2]);
        double c =  euclideanDistance(points[1],points[2]);

        double S = (a+b+c)/2;
        double Area = Math.sqrt( S * (S - a) * (S - b) * (S - c)  );
        double compareRadius = a * b * c / (4 * Area);

        System.out.print("\n\nCompareradius: " +  compareRadius);

        return compareRadius < R;
    }

    public static void main(String[] args) {
        HelperFunctions c = new HelperFunctions();
        double[] point1 = {0,0};
        double[] point2 = {3,4};
        double[] point3 = {1,1};

        double e = c.euclideanDistance(point1,point2);

        double[][] triplet = {point1,point2, point3};

        Boolean test = c.insideCircle(10.5, triplet);

        double testDist = c.euclideanDistance(point1,point2);

//        System.out.print("\n\nresult: " + testDist);
//        System.out.print("\n\nresult of circleTest: " + test);

    }

}