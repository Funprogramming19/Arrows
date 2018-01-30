package model;

import java.util.ArrayList;

import processing.core.PVector;

public class TrailKeeper extends ArrayList<PVector>
{
    private int cursor;

    public void restart()
    {
        this.cursor=0;
    }

    public int getNext()
    {
        if (cursor >= size())
            cursor = 0;
        return cursor++;
    }
}
