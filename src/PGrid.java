import processing.core.*;

import java.util.*;

/**
 * Created by juanm on 02/10/2016.
 */
public class PGrid {

    PApplet p;

    // Grid size
    float gridW, gridH;

    // Grid position
    PVector position;
    PVector targetPosition;      // Location to which the grid is moving

    // Grid x-axis and y-axis limits
    int xmin, xmax;
    float ymin, ymax;

    // Plots (data)
    List<PPlot> plots;

    // X-axis labels
    String[] xLabels;

    // X-axis plot limit
    float plotLimitFactor = 0.8f;


    public PGrid(PApplet parent, PVector position, float gridW, float gridH,
                 int xmin, int xmax, float ymin, float ymax) {
        this.p = parent;
        this.position = position;
        this.gridW = gridW;
        this.gridH = gridH;
        targetPosition = new PVector(position.x, position.y);

        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;

        plots = new ArrayList<>();
    }

    public void move(PVector targetPosition) {
        this.targetPosition = targetPosition.copy();
    }

    public void update() {
        // Update grid position
        float distance = PVector.dist(targetPosition, position);
        PVector targetVelocity = PVector.sub(targetPosition, position);
        targetVelocity.setMag(0.2f*distance);
        position.add(targetVelocity);
        if (distance < 1) {
            targetVelocity.mult(0.0f);
            position = targetPosition.copy();
        }

        // Update y-axis scale
        // TODO
    }

    public void setYLimits(float ymin, float ymax) {
        this.ymin = ymin;
        this.ymax = ymax;
    }

    public void advanceXAxis(int offset) {
        this.xmin = this.xmin + offset;
        this.xmax = this.xmax + offset;
    }

    public void addPlot(PPlot plot) {
        plots.add(plot);
    }

    public void setXLabels(String[] xLabels) {
        this.xLabels = xLabels;
    }

    // Grid to Screen mapping (top left border of grid is 0,0)
    public float mapx(float x) {
        return p.map(x, xmin, xmax, 0, gridW);
    }

    public float mapy(float y) {
        return p.map(y, ymin, ymax, gridH, 0);
    }


    public void draw() {
        p.noFill();
        p.pushMatrix();
        p.translate(position.x, position.y);

        // Grid borders
        p.stroke(0);
        p.strokeWeight(2);
        p.rect(0, 0, gridW, gridH);

        // Plot lines
        for (PPlot plot: plots) {
            plot.plot(xmin, (int) ((xmax-xmin)*this.plotLimitFactor+xmin));
        }

        // Draw x-axis labels and gridlines
        if (xLabels != null) {
            for (int i = xmin; i < xmax; i++) {
                if (!xLabels[i].equals("")) {
                    // Draw gridline
                    p.stroke(200);
                    p.strokeWeight(1);
                    p.line(mapx(i), mapy(ymin), mapx(i), mapy(ymax));
                    p.fill(200);
                    p.textAlign(p.CENTER, p.TOP);
                    p.textSize(26);
                    p.text(xLabels[i], mapx(i)-10, mapy(ymin));
                }
            }
        }

        p.popMatrix();
    }


}
