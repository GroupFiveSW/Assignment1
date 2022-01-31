package decide;

public class Decide {

    //Constant
    static double PI = 3.1415926535;

    ///////Type Declarations
    enum CONNECTORS {
        NOTUSED,
        ORR,
        ANDD
    }


    enum COMPTYPE {
        EQ,
        GT,
        LT
    }

    // inputs to the DECIDE()function
    static class PARAMETERS_T {
        double LENGTH1; // Length in LICs 0, 7 , 12
        double RADIUS1 ; // Radius in LICs 1 , 8 , 13
        double EPSILON ; // Deviation from PI i n L ICs 2 , 9
        double AREA1; // Area in L ICs 3 , 1 0 , 14
        int Q_PTS; // No. of consecutive points i n LIC 4
        int QUADS; // No . of quadrants in LIC 4
        double DIST ; // Distance in LIC 6
        int N_PTS; // No. of consecutive pts. in LIC 6
        int K_PTS; // No. of int. pts. in LICs 7 , 12
        int A_PTS; // No. of int. pts. in LICs 8 , 13
        int B_PTS; // No. of int. pts. in LICs 8 , 13
        int C_PTS; // No. of int. pts. in LIC 9
        int D_PTS; // No. of int. pts. in LIC 9
        int E_PTS; // No. of int. pts. in LICs 1 0 , 14
        int F_PTS; // No. of int. pts. in LICs 1 0 , 14
        int G_PTS; // No. of int. pts. in LIC 11
        double LENGTH2; // Maximum length in LIC 12
        double RADIUS2 ; // Maximum radius in LIC 13
        double AREA2; // Maximum area in LIC 14

    }

    ////////////////// Global variable declarations

    PARAMETERS_T PARAMETERS = new PARAMETERS_T();
    static PARAMETERS_T PARAMETERS2 = new PARAMETERS_T();

    // X coordinates of data points
    double[] X = new double[100];
    static double[] X2 = new double[100];

    // Y coordinates of data points
    double[] Y = new double[100];
    static double[] Y2 = new double[100];

    //Number of data points
    int NUMPOINTS;
    static int NUMPOINTS2;

    // Logical Connector Matrix
    CONNECTORS [][] LCM = new CONNECTORS[15][15];
    static CONNECTORS [][] LCM2 = new CONNECTORS[15][15];

    //Preliminery Unlocking Matrix
    boolean [][] PUM = new boolean[15][15];
    static boolean [][] PUM2 = new boolean[15][15];

    // Conditions Met Vector
    boolean [] CMV = new boolean[15];
    static boolean [] CMV2 = new boolean[15];

    // Final Unlocking Vector
    boolean [] FUV = new boolean[15];
    static boolean [] FUV2 = new boolean[15];

    // Decision: Launch or No Launch
    boolean Launch;
    static boolean LAUNCH2;

    //Compares floating point numbers  – see Nonfunctional Requirements
    public static COMPTYPE DOUBLECOMPARE(double A, double B) {
        if (Math.abs(A-B) < 0.000001) {
            return COMPTYPE.EQ;
        }
        if(A<B) {
            return COMPTYPE.LT;
        }
        return COMPTYPE.GT;

    }


    // @TODO: Main function we must write
    public static void decide() {


    }

    // Debug function for Hello World
    public static void main(String[] args) {
        System.out.println("Hello World from Decide!");
    }
}