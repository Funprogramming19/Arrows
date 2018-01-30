package view;

import processing.core.PApplet;
import processing.core.PVector;

class Arrow
{
    private Arrow()
    {

    }

    static void draw(PVector origin, float angle, int color, float strokeWeight, PApplet p)
    {
        p.pushMatrix();
        p.translate(origin.x, origin.y);
        p.rotate(angle);
        p.stroke(color);
        p.strokeWeight(strokeWeight);
        p.noFill();
        p.strokeJoin(p.MITER);
        p.strokeCap(p.SQUARE);
        p.line(0, 0, 20, 0);
        p.beginShape();
        p.vertex(15, 5);
        p.vertex(20, 0);
        p.vertex(15, -5);
        p.endShape();
        p.popMatrix();
    }
}
