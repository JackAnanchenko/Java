public class Coordinate {
    private int i;
    private int j;

    /**
     * coordinates of case(state)
     * @param state
     * @param statesMatrix
     */
    public Coordinate(int state, int [][] statesMatrix){

        for (int i = 0; i < statesMatrix.length; i++) {
            for (int j = 0; j < statesMatrix.length; j++) {
                if(state == statesMatrix[i][j]) {
                    this.i = i;
                    this.j = j;
                }

            }
        }

    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
