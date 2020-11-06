public class MatrixOperation {

    public static double[][] product(double[][] M1, double[][]M2) {
        int row1 = M1.length;
        int col1 = M1[0].length;
        int col2 = M2[0].length;

        double[][] product = new double[row1][col2];
        for(int i = 0; i < row1; i++) {
            for (int j = 0; j < col2; j++) {
                for (int k = 0; k < col1; k++) {
                    product[i][j] += M1[i][k] * M2[k][j];
                }
            }
        }
        return product;
    }

    public static double[] vectorProduct(double[][] M, double[] vector){
        double [] y = new double[M.length];
        for(int i = 0 ; i < y.length ; i++){
            double sum = 0;
            for(int j = 0 ; j < M[0].length ; j++){
                sum += M[i][j] * vector[j];
            }
            y[i] = sum;
        }
        return y;
    }

    }
