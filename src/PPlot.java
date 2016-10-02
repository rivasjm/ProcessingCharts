import processing.core.PApplet;

/**
 * Created by juanm on 02/10/2016.
 */
public class PPlot {

    PApplet p;
    PGrid g;
    float[] y;

    int color;
    int weight;

    public PPlot(PApplet parent, PGrid grid, float[] ydata, int color, int weight){
        p = parent;
        g = grid;
        this.y = ydata.clone();

        this.color = color;
        this.weight = weight;
    }

    public void plot(int xmin, int xmax) {
        p.stroke(color);
        p.strokeWeight(weight);
        for (int i=xmin; i<xmax; i++){
            p.line(g.mapx(i), g.mapy(y[i]), g.mapx(i+1), g.mapy(y[i+1]));
        }
    }

    public float max(int xmin, int xmax) {
        float max = 0f;
        for (int i=xmin; i<=xmax; i++) {
            if (y[i] > max) {
                max = y[i];
            }
        }
        return max;
    }
}
