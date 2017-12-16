import java.math.*;

public class MathUtils{

    public static double sigmoidFunction(double input){
         return 1.0 / (1.0 + Math.exp(-input));
    }
}
