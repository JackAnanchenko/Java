import java.io.PrintWriter;
class Markov{


public static void checkIn(String name, String login, double[] sol1, boolean[] sol2 )

{

System.out.println("****************************************************** " );
System.out.println(" " );
System.out.println(" " );
System.out.println( "NAME: " +  name +"  " + login);

//###########
//Write feedback sheet
//##################

try{
PrintWriter writer = new PrintWriter("/home/cur/dfc/feedback"+".mark", "UTF-8");
String domi= new String("Dominique Chu");


if(name.equals(domi)) {name="replacemexxx";}


writer.println ("************************************");
writer.println ("************************************");
writer.println ("Feedback for CO528 Assessment 1: Genetic Algorithms for " + name + " ("+login+")");
writer.println ("************************************");
writer.println ("************************************");


}
catch(Exception e)
{
System.out.println(e);
}
}


//##################################


public static  double getTransProb(int s1,int s2,int numStates){
    double proba = 0.0;

    double[] steadyProb = new double[numStates];

    //steady state probabilities of all states are equal
    for (int i = 0; i < numStates; i++) {
        steadyProb[i] = 1d/numStates;
    }

    double[][] matrixProba = new double[numStates][numStates];
    for (int i = 0; i < numStates; i++) {
        for (int j = 0; j < numStates; j++) {
            matrixProba[i][j] = clacTransProbAt(i+1, j+1, numStates);
        }
    }

    double[] x1 = MatrixOperation.vectorProduct(matrixProba, steadyProb);
    proba = x1[s2-1];

    return proba;
}

//this method calculate matrix of transition proba
public static  double clacTransProbAt(int s1,int s2,int numStates){

    double proba = 0.0;

    double[] steadyProb = new double[numStates];

    //steady state probabilities of all states are equal
    for (int i = 0; i < numStates; i++) {
        steadyProb[i] = 1d/numStates;
    }





    //indexing states
    /**
     * statesMatrix :   7   8   9
     *                  4   5   6
     *                  1   2   3
     */
    int [][] statesMatrix = getStateMatrix(numStates);

    Coordinate c1 = new Coordinate(s1, statesMatrix);
    Coordinate c2 = new Coordinate(s2, statesMatrix);


        if(canWalk(c1, c2))
            proba = 1/getWalks(c1.getI(), c1.getJ(), statesMatrix);

            return proba;
    }

    private static int[][] getStateMatrix(int numStates) {
        int [][] statesMatrix = new int[(int) Math.sqrt((numStates))] [(int) Math.sqrt((numStates))];
        int s = 1;
        for(int i = (int) (Math.sqrt(numStates)-1); i >= 0 ; i--) {
            for (int j = 0; j < Math.sqrt(numStates); j++) {
                statesMatrix[i][j] = s;
                System.out.println("i = " + i + ", j = " + j + ", s = " + s);
                s++;
            }
        }
        return statesMatrix;
    }

    private static boolean canWalk(Coordinate c1, Coordinate c2) {
    boolean can = false;

    if(c1.getI() == c2.getI()) {
        if(c1.getJ() == c2.getJ()+1 || c1.getJ() == c2.getJ()-1){
            can = true;
        }
    } else if(c1.getJ() == c2.getJ()) {
            if(c1.getI() == c2.getI()+1 || c1.getI() == c2.getI()-1){
                can = true;
            }
        }

    return can;
}

    /**
     * Calculating number of walks
     * @param i
     * @param j
     * @return
     */
    private static double getWalks(int i, int j, int[][] statesMatrix) {
        //if()
        return getWalkUp(i, j, statesMatrix) + getWalkDown(i, j, statesMatrix)
                + getWalkDown(i, j, statesMatrix) + getWalkLeft(i, j, statesMatrix);
    }

    private static double getWalkUp(int i, int j, int[][] statesMatrix) {
        int walk = 0;
        if(j > 0) walk = 1;
        return walk;
    }

    private static double getWalkDown(int i, int j, int[][] statesMatrix) {
        int walk = 0;
        if(j < statesMatrix.length-1) walk = 1;
        return walk;
    }

    private static double getWalkRight(int i, int j, int[][] statesMatrix) {
        int walk = 0;
        if(i < statesMatrix.length-1) walk = 1;
        return walk;
    }

    private static double getWalkLeft(int i, int j, int[][] statesMatrix) {
        int walk = 0;
        if(i > 0) walk = 1;
        return walk;
    }

//##################################


public static double getSejProb(int s1,int s2,int numStates,double TS){

    //First we calculate the TrasProb Matrix

    double[][] matrixProba = new double[numStates][numStates];
    for (int i = 0; i < numStates; i++) {
        for (int j = 0; j < numStates; j++) {
            matrixProba[i][j] = clacTransProbAt(i+1, j+1, numStates);
        }
    }

    double[] steadyProb = new double[numStates];

    //steady state probabilities of all states are equal
    for (int i = 0; i < numStates; i++) {
        steadyProb[i] = 1d/numStates;
    }

    //Second calculate new state matrix
    double[][] newStateMatrix = matrixProba;

    for (int i = 0; i < TS-1; i++) {
        newStateMatrix = MatrixOperation.product(newStateMatrix, matrixProba);
    }

    double[] x3 = MatrixOperation.vectorProduct(newStateMatrix, steadyProb);

return x3[s2-1];

}

//##################################
public static double getBiasTransProb(int s1, int s2,double[] ssprob) {

    double proba = 0.0;

    double[] steadyProb = ssprob;
    int numStates = ssprob.length;

    //steady state probabilities of all states are equal
    for (int i = 0; i < numStates; i++) {
        steadyProb[i] = 1d/numStates;
    }

    double[][] matrixProba = new double[numStates][numStates];
    for (int i = 0; i < numStates; i++) {
        for (int j = 0; j < numStates; j++) {
            matrixProba[i][j] = clacTransProbAt(i+1, j+1, numStates);
        }
    }

    double[] x1 = MatrixOperation.vectorProduct(matrixProba, steadyProb);
    proba = x1[s2-1];
    return proba;

}


//##################################



public static double  getContTransProb(int s1,int s2,double[] rates){


        //States x1  x2  x3

    int x1 = 1, x2 = 2,  x3 = 3;

    /**
     *     Transition Proba Matrix
     *          1                           2                     3
     *   1                          y1,2/(y1,2 + y1,3)       y1,3/(y1,2 + y1,3)
     *
     *
     *   2   y2,1/(y2,1 + y2,3)                              y2,3/(y2,1 + y2,3)
     *
     *
     *   3  y3,1/(y3,1 + y3,2)         y3,2/(y3,1 + y3,2)
     */

    double[][] ratesMatrix = new double[3][3];

    ratesMatrix[0][1] = rates[0];
    ratesMatrix[0][2] = rates[1];
    ratesMatrix[1][0] = rates[2];
    ratesMatrix[1][2] = rates[3];
    ratesMatrix[2][0] = rates[4];
    ratesMatrix[2][1] = rates[5];

    double sum;
    double[][] tpmatrix = new double[3][3];
    for (int i = 0; i < 3; i++) {

        for (int j = 0; j < 3; j++) {
            sum = 0.0;

            for (int k = 0; k < 3; k++) {
                if(i != k) {
                    sum +=  ratesMatrix[i][k];
                }
                tpmatrix[i][j] = ratesMatrix[i][j] / sum;
            }
            //System.out.println("tpmatrix["+i+"]["+j+"] = " + tpmatrix[i][j]);
        }
    }


return tpmatrix[s1-1][s2-1];
}



public static double getContSejProb(int s1,int s2,double[] rates,double TSC){

    //States x1  x2  x3

    int x1 = 1, x2 = 2,  x3 = 3;

    /**
     *     Transition Proba Matrix
     *          1                           2                     3
     *   1                                 Exp(y0,1 * t)
     *
     *
     *   2
     *
     *
     *   3
     */

    double[][] ratesMatrix = new double[3][3];

    ratesMatrix[0][1] = rates[0];
    ratesMatrix[0][2] = rates[1];
    ratesMatrix[1][0] = rates[2];
    ratesMatrix[1][2] = rates[3];
    ratesMatrix[2][0] = rates[4];
    ratesMatrix[2][1] = rates[5];


    double[][] tpmatrix = new double[3][3];
    for (int i = 0; i < 3; i++) {

        for (int j = 0; j < 3; j++) {
            if(i != j) {
                tpmatrix[i][j] = Math.exp(-ratesMatrix[i][j] * TSC);
            }
        }
    }


    return tpmatrix[s1-1][s2-1];
}




}//end class
