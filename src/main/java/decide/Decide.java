package decide;

public class Decide {

    //Constant
    static double PI = 3.1415926535;

    //Type Declarations
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

        // Constructor for parameters
        public PARAMETERS_T(
                double LENGTH1,
        double RADIUS1 ,
        double EPSILON ,
        double AREA1,
        int Q_PTS,
        int QUADS,
        double DIST ,
        int N_PTS,
        int K_PTS,
        int A_PTS,
        int B_PTS,
        int C_PTS,
        int D_PTS,
        int E_PTS,
        int F_PTS,
        int G_PTS,
        double LENGTH2,
        double RADIUS2 ,
        double AREA2
        ) {
            this.LENGTH1 = LENGTH1;
            this.RADIUS1  = RADIUS1;
            this.EPSILON  = EPSILON;
            this.AREA1 = AREA1;
            this.Q_PTS = Q_PTS;
            this.QUADS = QUADS;
            this.DIST  = DIST;
            this.N_PTS = N_PTS;
            this.K_PTS = K_PTS;
            this.A_PTS = A_PTS;
            this.B_PTS = B_PTS;
            this.C_PTS = C_PTS;
            this.D_PTS = D_PTS;
            this.E_PTS = E_PTS;
            this.F_PTS = F_PTS;
            this.G_PTS = G_PTS;
            this.LENGTH2 = LENGTH2;
            this.RADIUS2  = RADIUS2;
            this.AREA2 = AREA2;

        }

    }

    ////////////////// Global variable declarations
    PARAMETERS_T PARAMETERS = new PARAMETERS_T(
            1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1,
            1, 1, 1);

    static PARAMETERS_T PARAMETERS2 = new PARAMETERS_T(
            1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1,
            1, 1, 1);

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

    // Preliminary Unlocking Vector
    static boolean[] PUV = new boolean[15];

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



    //Compares floating point numbers  ??? see Nonfunctional Requirements
    public static COMPTYPE DOUBLECOMPARE(double A, double B) {
        if (Math.abs(A-B) < 0.000001) {
            return COMPTYPE.EQ;
        }
        if(A<B) {
            return COMPTYPE.LT;
        }
        return COMPTYPE.GT;

    }


    /**
     * Final decide function.
     * Creates CMV, PUM, FUV and decides whether to launch or not.
     */
    public static void decide() {
        /** Create CMV */
        LIC lic = new LIC();
        lic.condition0();
        lic.condition1();
        lic.condition2();
        lic.condition3();
        lic.condition4();
        lic.condition5();
        lic.condition6();
        lic.condition7();
        lic.condition8();
        lic.condition9();
        lic.condition10();
        lic.condition11();
        lic.condition12();
        lic.condition13();
        lic.condition14();

        /** Create PUM */
        HelperFunctions h = new HelperFunctions();
        h.PUMgenerator();

        /** Create FUV */
        FUVGenerator fuv = new FUVGenerator();
        fuv.generate();

        /** Decide final launch/no launch decision depending on values of FUV */
        LAUNCH2 = true;
        for (int i = 0; i < FUV2.length; i++){
            if (!FUV2[i]){
                LAUNCH2 = false;
                break;
            }
        }
        if (LAUNCH2) {
            System.out.println("Launch");
        } else {
            System.out.println("No launch");
        }

    }

    // To run, execute $ mvn exec:java
    public static void main(String[] args) {


        PARAMETERS2 = new PARAMETERS_T(
                1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1,
                1, 1, 1);

        decide();

    }
}