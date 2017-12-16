import java.util.*;
import java.math.*;

public class NeuralNetwork{

    public int[] layerCount;
    public NeuralLayer[] layers;

    public NeuralNetwork(int[] layerCount){

        this.layerCount=layerCount;

        this.layers = new NeuralLayer[layerCount.length-1];
        for(int i=0;i<layers.length;i++){
            layers[i] = new NeuralLayer(layerCount[i], layerCount[i+1]);
        }
    }

    public double[] feedforward(double[] inputs){

        double[] outputs = inputs;
        for(int i=0;i<layers.length;i++){
            outputs = layers[i].feedforward(outputs);
        }

        return outputs;
    }

    public void generateRandomWeights(double min, double max){

        for(int i=0;i<layers.length;i++){
            layers[i].generateRandomWeights(min, max);
        }
    }
}
