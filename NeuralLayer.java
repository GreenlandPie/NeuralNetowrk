import java.math.*;
import java.util.*;

public class NeuralLayer{

    public Node[] node;

    public double[] input;


    public NeuralLayer(int nodeCount, int inputCount){

        node = new Node[nodeCount];
        for(int i=0;i<node.length;i++){
            node[i] = new Node(inputCount);
        }

        input = new double[inputCount];
    }

    public void feedforward(){

        for(int i=0;i<node.length;i++){
            double sum=0;

            for(int j=0;j<node[i].weight.length;j++){
                sum+= input[j]*node[i].weight[j];
            }

            node[i].output = MathUtils.sigmoidFunction(sum);
        }
    }

    public double[] out(){

        double[] outputs = new double[node.length];
        for(int i=0;i<outputs.length;i++){
            outputs[i] = node[i].output;
        }

        return outputs;
    }

    public void generateRandomWeights(double min, double max){

        for(int i=0;i<node.length;i++){
            for(int j=0;j<node[i].weight.length;j++){
                node[i].weight[j] = min + (new Random().nextDouble()*Math.abs(max-min));
            }
        }
    }

}
