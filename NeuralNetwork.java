import java.util.*;
import java.math.*;

public class NeuralNetwork{

    public int[] layerCount;
    public NeuralLayer[] layers;

    public double learningRate, minError, overallError;
    public int maxIterations;

    public double[] expectedOutput;

    public NeuralNetwork(int[] layerCount, double learningRate, double minError, int maxIterations){

        this.layerCount=layerCount;

        this.layers = new NeuralLayer[layerCount.length-1];
        for(int i=0;i<layers.length;i++){
            layers[i] = new NeuralLayer(layerCount[i], layerCount[i+1]);
        }

        this.learningRate = learningRate;
        this.minError = minError;
        this.maxIterations = maxIterations;
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

    public void backpropagate(){

        int outputCount = layerCount[layerCount.length-1], outputIndex = layerCount.length-1;

        for(int i=0;i<outputCount;i++){
            layers[outputIndex].err[i] = (expectedOutput[i]-layers[outputIndex].output[i])*
            layers[outputIndex].output[i]*
            (1-layers[outputIndex].output[i]);
        }

        double sum=0;
        for(int i=layerCount.length-2;i>0;i--){
            for(int j=0;j<layerCount[i];j++){
                sum=0;

                for(int k=0;k<layerCount[i+1];k++){
                    sum += layers[i+1].weights[j][k]*layers[i+1].err[k];
                }

                layers[i].err[j] = layers[i].output[j]*(1-layers[i].output[j])*sum;

            }
        }
    }

    public void updateWeights(){

        for(int i=layerCount.length-1;i>0;i--){
            for(int j=0;j<layerCount[i-1];j++){
                for(int k=0;k<layerCount[i];k++){
                    layers[i-1].deltaWeights[j][k] = this.learningRate * layers[i].err[k] *
                     layers[i-1].output[j] + layers[i-1].deltaWeights[j][k];

                     layers[i-1].weights[j][k] += layers[i-1].deltaWeights[j][k];
                }
            }
        }
    }

    public void calculateOverallError(){

        overallError = 0;
        for(int i=0;i<layerCount[layerCount.length-1];i++){
            overallError+= 0.5*(Math.pow(expectedOutput[i]-layers[layerCount.length-1].output[i], 2));
        }
    }

    public void trainNetwork(double sampleInput[][], double sampleOutputs[][]){

        int epoch = 0;

        do{

            for(int i=0;i<sampleOutputs.length;i++){

                expectedOutput = sampleOutputs[i];

                feedforward(sampleInput[i]);
                backpropagate();
                updateWeights();
            }

            epoch++;

            calculateOverallError();
        }while(overallError > minError && epoch < maxIterations);
    }
}
