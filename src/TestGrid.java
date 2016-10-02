import processing.core.*;

import java.util.Arrays;

/**
 * Created by juanm on 01/10/2016.
 */
public class TestGrid extends PApplet {

    PGrid grid;


    public void settings(){
        size(1000, 700);
    }

    public void setup() {
        grid = new PGrid(this, new PVector(10,10), 800, 400, 0, 200, 0, 20);
        background(0);
        frameRate(30);

        String[] xlabels = new String[10000];
        float[] ydata = new float[10000];


        noiseDetail(1, 0.5f);
        for (int i=0; i<10000; i++) {
            ydata[i] = noise(i)+10 + 2*cos(i/(100f*noise(i)));
            if (i % 100 == 0) {
                xlabels[i] = str(i);
            } else {
                xlabels[i] = "";
            }
        }
        PPlot plot = new PPlot(this, grid, ydata, color(50, 55, 100), 1);
        grid.addPlot(plot);

        for (int i=0; i<10000; i++) {
            ydata[i] = 2*noise(i+10000)+2;
        }
        plot = new PPlot(this, grid, ydata, color(255, 204, 0), 1);
        grid.addPlot(plot);

        for (int i=0; i<10000; i++) {
            ydata[i] = sin(i/10.0f) + 15;
        }
        plot = new PPlot(this, grid, ydata, color(255, 100, 255), 1);
        grid.addPlot(plot);

        grid.setXLabels(xlabels);

     }

    public void draw(){
        background(0);
        grid.advanceXAxis(1);
        grid.update();
        grid.draw();
        if (mousePressed) {
            grid.move(new PVector(mouseX, mouseY));
        }

        //saveFrame("data/line-######.png");
    }

    public static void main(String... args){
        PApplet.main("TestGrid");
    }

}
