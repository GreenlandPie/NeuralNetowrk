import java.util.*;
import java.math.*;

public class NeuralNetwork{

    public int[] layerCount;
    public NeuralLayer[] layer;

    public double learningRate, minError, overallError;
    public int maxIterations;

    public double[] expectedOutput;

    public NeuralNetwork(int[] layerCount, double learningRate, double minError, int maxIterations){

        this.layerCount=layerCount;

        this.layer = new NeuralLayer[layerCount.length];

        layer[0] = new NeuralLayer(layerCount[0], layerCount[0]);
        for(int i=1;i<layer.length;i++){
            layer[i] = new NeuralLayer(layerCount[i], layerCount[i-1]);
        }

        this.learningRate = learningRate;
        this.minError = minError;
        this.maxIterations = maxIterations;
    }

    public void feedforward(){

        for(int i=0;i<layer[0].node.length;i++){
            layer[0].node[i].output = layer[0].input[i];
        }

        layer[1].input = layer[0].input;
        for(int i=1;i<layer.length;i++){
            layer[i].feedforward();

            if(i != layer.length-1){
                layer[i+1].input = layer[i].out();
            }
        }
    }

    public void generateRandomWeights(double min, double max){

        for(int i=1;i<layer.length-1;i++){
            layer[i].generateRandomWeights(min, max);
        }
    }

    public void backpropagate(){

        int lastLayerNodeNumber = layer[layer.length-1].node.length;
        int lastLayerIndex = layer.length-1;

        for(int i=0;i<lastLayerIndex;i++){
            layer[lastLayerNodeNumber].node[i].err =(expectedOutput[i]-layer[lastLayerIndex].node[i].output)*
                                        layer[lastLayerIndex].node[i].output*
                                        (1-layer[lastLayerIndex].node[i].output);
        }

        double sum=0;
        for(int i=layerCount.length-2;i>0;i--){
            System.out.println("\t"+layerCount[i]+" "+layerCount[i+1]);
            for(int j=0;j<layerCount[i];j++){
                sum=0;

                for(int k=0;k<layerCount[i+1];k++){
                    sum += layer[i+1].node[k].weight[j]*layer[i+1].node[k].err;
                }

                layer[i].node[j].err = layer[i].node[j].output*(1-layer[i].node[j].output)*sum;
            }
        }
    }

    public void updateWeights(){

        for(int i=layerCount.length-1;i>0;i--){
            for(int j=0;j<layer[i].node.length;j++){
                for(int k=0;k<layer[i].input.length;k++){
                    System.out.println("\t\t\t\t\t"+i+","+k+","+j+": ");
                    layer[i].node[j].deltaWeight[k] = this.learningRate * layer[i].node[j].err *
                     layer[i-1].node[k].output + layer[i].node[j].deltaWeight[k];

                     layer[i].node[j].weight[k] += layer[i].node[j].weight[k];
                }
            }
        }
    }

    public void calculateOverallError(){

        overallError = 0;
        for(int i=0;i<layerCount[layerCount.length-1];i++){
            overallError+= 0.5*(Math.pow(expectedOutput[i]-layer[layerCount.length-1].node[i].output, 2));
        }
    }

    public void trainNetwork(double sampleInput[][], double sampleOutputs[][]){

        int epoch = 0;

        do{

            for(int i=0;i<sampleOutputs.length;i++){
                for(int j=0;j<layer[0].node.length;j++){
                    layer[0].input = sampleInput[i];
                }

                expectedOutput = sampleOutputs[i];

                feedforward();
                backpropagate();
                updateWeights();
            }

            epoch++;

            calculateOverallError();
        }while(overallError > minError && epoch < maxIterations);
    }
}
