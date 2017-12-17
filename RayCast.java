import java.math.*;

public class RayCast{

    public int x, y;

    public double magnitude;
    public double angle;

    public RayCast(){
        this(0, 0, 0.0);
    }

    public RayCast(double angle){
        this(0, 0, angle);
    }

    public RayCast(int x, int y, double angle){
        this.x=x;
        this.y=y;
        this.angle=angle;
        this.magnitude=0;
    }

    public double[] hit(Object other){

        if(other instanceof Wall){
            double tempX = this.x;
            double tempY = this.y;

            Wall w = (Wall) other;

            while(tempX<1000 && tempX>=0 && tempY<1000 && tempY>=0){

                //System.out.println(tempX+" "+tempY+" "+angle+" "+Math.cos(angle) + " "+Math.sin(angle));

                tempX+=Math.cos(angle);
                tempY+=-Math.sin(angle);

                if(tempX > w.x && tempY > w.y && tempX < w.x+w.width && y<w.y+w.height){
                    return new double[]{1, tempX, tempY};
                }
            }
        }

        return new double[]{0, -1, -1};
    }

    public double dist(Wall w){

        double[] h = hit(w);

        if(h[0]== 1){
            return Math.sqrt(Math.pow(this.x-h[1], 2)+Math.pow(this.y-h[2], 2));
        }
        return -1;
    }
}
