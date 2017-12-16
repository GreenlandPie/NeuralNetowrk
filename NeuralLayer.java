import java.math.*;
import java.util.*;

public class NeuralLayer{

    //number of neurons in the layer
    public int pCount;

    //number of inputs;
    public int inputCount;

    //number of outputs;
    public int outputCount;

    public double[][] weights;

    public NeuralLayer(int inputCount, int outputCount){
        this.inputCount=inputCount;
        this.outputCount=outputCount;

        this.weights = new double[inputCount][];
        for(int i=0;i<weights.length;i++){
            this.weights[i] = new double[outputCount];
        }
    }

    public void setWeights(double[][] newWeights){

        for(int i=0;i<weights.length;i++){
            for(int j=0;j<weights[i].length;j++){
                this.weights[i][j] = newWeights[i][j];
            }
        }
    }

    public double[] feedforward(double[] inputs){

        double[] outputs = new double[this.outputCount];

        for(int i=0;i<outputCount;i++){
            for(int j=0;j<inputCount;j++){
                outputs[i]+=inputs[j]*weights[j][i];
            }
        }

        for(int i=0;i<outputs.length;i++){
            outputs[i] = MathUtils.sigmoidFunction(outputs[i]);
        }

        return outputs;
    }

    public void generateRandomWeights(double min, double max){

        for(int i=0;i<weights.length;i++){
            for(int j=0;j<weights[i].length;j++){
                weights[i][j]=min + (new Random().nextDouble()*Math.abs(max-min));
            }
        }
    }

}
