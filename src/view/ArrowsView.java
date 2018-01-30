package view;

import model.KeyManager;
import model.TrailKeeper;
import processing.core.PApplet;
import processing.core.PVector;

import static core.Settings.*;

public class ArrowsView extends PApplet
{

    private KeyManager manager;
    private TrailKeeper trail;
    private boolean showTrail = true;
    private char activeChar = 'a';
    private PVector defaultPoint;

    public static void main(String[] args)
    {
        PApplet.main("view.ArrowsView", args);
    }

    @Override
    public void settings()
    {
        size(WIDTH, HEIGHT);
    }

    public void setup()
    {
        smooth();
        defaultPoint = new PVector(width / 2, height / 2);
        manager = new KeyManager();
    }

    public void draw()
    {
        background(BACKGROUND_RED, BACKGROUND_GREEN, BACKGROUND_BLUE);
        PVector bullet;
        if (trail != null)
        {
            if (showTrail)
            {
                bullet = trail.get(trail.getNext());
            }
            else
            {
                bullet = new PVector(mouseX, mouseY);
                trail.add(bullet);
            }
        }
        else
            bullet = defaultPoint;

        for (float i = SPACING / 2; i < width; i += SPACING)
        {
            for (float j = SPACING / 2; j < height; j += SPACING)
            {
                PVector position = new PVector(i, j);
                PVector sub = PVector.sub(bullet, position);
                float angle = sub.heading();

                float distance = sub.mag();
                float border = map(distance, 25, width, 0.5f, 3);
                int c = color(
                        map(distance, 0, width, 150, 100),
                        0,
                        map(distance, 0, width, 150, 100)
                );
                Arrow.draw(new PVector(i, j), angle, c, border, this);
            }
        }
    }

    @Override
    public void keyPressed()
    {
        if (activeChar == key)
        {
            showTrail = !showTrail;
            if (showTrail)
            {
                manager.put(key, trail);
                trail.restart();
            }
            else
            {
                trail = new TrailKeeper();
            }
        }
        else
        {
            showTrail = true;
            activeChar = key;
            trail = manager.get(key);
            if (trail != null)
                trail.restart();
        }
    }
}
