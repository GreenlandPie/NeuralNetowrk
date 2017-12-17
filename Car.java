import java.util.*;
import java.math.*;

public class Car{

    public NeuralNetwork net;

    public int x, y;
    public double angle;

    public Car(){
        this(new int[]{3, 4, 5, 3});
    }

    public Car(int[] layerCount){
        this(0, 0, layerCount);
    }

    public Car(int x, int y, int[] layerCount){
        this.x=x;
        this.y=y;
        this.net = new NeuralNetwork(layerCount);

        this.angle=0;

        this.net.generateRandomWeights(-4.0, 4.0);
    }

    public void move(){

        RayCast fwd = new RayCast(this.x, this.y, this.angle);
        RayCast dx = new RayCast(this.x, this.y, this.angle-Math.PI/2);
        RayCast sx = new RayCast(this.x, this.y, this.angle+Math.PI/2);

        double[] var;
        for(int i=0;i<NeuralManager.walls.length;i++){

            var = net.feedforward(new double[]{fwd.dist(NeuralManager.walls[i]), dx.dist(NeuralManager.walls[i]), sx.dist(NeuralManager.walls[i])});

            this.angle+=var[2];
            this.x+=Math.cos(angle)*var[0];
            this.y+=Math.sin(angle)*var[1];
        }
    }
}
