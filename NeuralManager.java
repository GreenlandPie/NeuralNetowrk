import java.math.*;

public class NeuralManager{

    public static Wall[] walls;

    public NeuralManager(){

        walls = new Wall[]{new Wall(0, 400, 200, 100)};
    }

    public static void main(String args[]){

        new NeuralManager();

        NeuralNetwork net = new NeuralNetwork(new int[]{3, 4, 5, 3});
        net.generateRandomWeights( -1.0, 1.0);

        double[] outputs = net.feedforward(new double[]{0.5, 0.35, 1.0});

        for(int i=0;i<outputs.length;i++){
            System.out.println(outputs[i]);
        }

        RayCast temp = new RayCast(50, 0, -Math.PI/2);

        System.out.println(temp.dist(walls[0]));
    }
}
