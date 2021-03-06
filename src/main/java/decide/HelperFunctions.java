
package decide;

import com.sun.source.tree.NewArrayTree;

import java.util.*;



public class HelperFunctions {

    /**
     *
     * @param p1,p2,p3: three points in the plane
     * @return double : Returns angle formed at vertex at point p2.
     */
    double getAngle(double[] p1, double[] p2, double[] p3){
        double[] vec1 = { p1[0]-p2[0],p1[1]-p2[1]};
        double[] vec2 = { p3[0]-p2[0],p3[1]-p2[1]};

        double[] zeroVec = {0,0};

        double dotProd = vec1[0]*vec2[0] + vec1[1]*vec2[1];
        double l1 = euclideanDistance(vec1,zeroVec);
        double l2 = euclideanDistance(vec2,zeroVec);

        double denom = l1*l2;
        if(Math.abs(dotProd)>denom){
            denom = denom + 0.0000000001;
        }

        // Return radians instead of degrees
        double angle = Math.acos(dotProd/(denom));
        return angle;
    }


    static double euclideanDistance(double[] p1,double[] p2){

        return Math.sqrt(   Math.pow((p1[0]-p2[0]),2)     +   Math.pow((p1[1]-p2[1]),2)    );
    }

    double calcTriangleArea(double[] p1,double[] p2, double[] p3){
        double a = euclideanDistance(p1,p2);
        double b =  euclideanDistance(p1,p3);
        double c =  euclideanDistance(p2,p3);

        double S = (a + b + c) / 2;
        double Area = Math.sqrt(S * (S - a) * (S - b) * (S - c));
        return Area;
    }


    /**
     *
     * @param R: Radius of circle
     * @param points: Array of three points
     * @return Boolean: if the points fit inside any circle with radius R
     */
    Boolean insideCircle(Double R, double[][] points) {

        double[] p1 = points[0];
        double[] p2 = points[1];
        double[] p3 = points[2];

        double a = euclideanDistance(p1,p2);
        double b =  euclideanDistance(p1,p3);
        double c =  euclideanDistance(p2,p3);

        double angle;

        double[] middle_point;

        double biggest_dist = Math.max(a,Math.max(b,c));
        if (a == biggest_dist) {
            middle_point = p3;
            angle = getAngle(p1,middle_point,p2);
        }
        else if (b == biggest_dist) {
            middle_point = p2;
            angle = getAngle(p1,middle_point,p3);
        }
        else {
            middle_point = p1;
            angle = getAngle(p2,middle_point,p3);
        }

        if (angle > Decide.PI/2) {
            return !(biggest_dist > 2 * R);
        }

        else {
            double Area = calcTriangleArea(p1,p2,p3);
            double compareRadius = a * b * c / (4 * Area);
            return compareRadius < R;

        }
    }


    /**
     * Generates a Preliminary Unlocking Matrix, PUM. Given state of CMV and LCM,
     * elements of PUM are set in accordance with specifications found in 2.2
     */
    void PUMgenerator(){

        // Conditionals as per specification in 2.2
        for(int i =0; i<15; i++){
            for(int j=i; j<15; j++){
                if(Decide.LCM2[i][j] == Decide.CONNECTORS.ANDD) {
                    if(Decide.CMV2[i] && Decide.CMV2[j]){
                        Decide.PUM2[i][j] = true;
                        Decide.PUM2[j][i] = true;

                    }
                    else{
                        Decide.PUM2[i][j] = false;
                        Decide.PUM2[j][i] = false;
                        }
                    }
                else if(Decide.LCM2[i][j] == Decide.CONNECTORS.ORR){
                    if(Decide.CMV2[i] || Decide.CMV2[j]) {
                        Decide.PUM2[i][j] = true;
                        Decide.PUM2[j][i] = true;
                    }
                    else{
                        Decide.PUM2[i][j] = false;
                        Decide.PUM2[j][i] = false;
                    }
                }
                else if(Decide.LCM2[i][j] == Decide.CONNECTORS.NOTUSED){
                        Decide.PUM2[i][j] = true;
                        Decide.PUM2[j][i] = true;
                    }
                }
            }
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