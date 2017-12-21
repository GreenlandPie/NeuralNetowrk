import java.math.*;

public class NeuralManager{

    public static void main(String args[]){

        NeuralNetwork net = new NeuralNetwork(new int[]{3, 4, 2, 3}, 0.01, 0.1, 1000);
        net.generateRandomWeights( -2.0, 2.0);

        net.trainNetwork(new double[][]{{0.5, 0.5, 0.3}, {0.3, 0.25, 0.67}, {0.76, 0.5, -0.34}, {0.3, 0.1, -0.98}}, new double[][]{{0.4, 0.4, 0.2}, {0.3, 0.3, 0.2}, {0.7, 0.5, 0.34}, {0.56, 0.543, 0.21}});

        System.out.println("Net trained");
    }
}
