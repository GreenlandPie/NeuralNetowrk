public class NeuralManager{

    public static void main(String args[]){

        NeuralNetwork net = new NeuralNetwork(new int[]{3, 4, 5, 3});
        net.generateRandomWeights( -1.0, 1.0);

        double[] outputs = net.feedforward(new double[]{0.5, 0.35, 1.0});

        for(int i=0;i<outputs.length;i++){
            System.out.println(outputs[i]);
        }
    }

}
