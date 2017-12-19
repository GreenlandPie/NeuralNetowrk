import java.math.*;

public class NeuralManager{

    public static void main(String args[]){

        NeuralNetwork net = new NeuralNetwork(new int[]{2, 2, 1}, 0.01, 0.1, 1000);
        net.generateRandomWeights( -1.0, 1.0);

        net.trainNetwork(new double[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}}, new double[][]{{0}, {1}, {1}, {0}});

        System.out.println("Net trained");
    }
}
