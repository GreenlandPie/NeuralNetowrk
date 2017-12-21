public class Node{

    public double input;
    public double output;
    public double err;

    public double[] weight;
    public double[] deltaWeight;

    public Node(int weightSize){

        weight = new double[weightSize];
        deltaWeight = new double[weightSize];
    }
}
