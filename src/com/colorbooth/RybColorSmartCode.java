package com.colorbooth;

import java.util.Random;

public class RybColorSmartCode
{
    public RybColorSmartCode()
    {
    }

    public RybColorSmartCode(int r, int y, int b)
    {
        if ((r < 0 || r > MAX_VALUE) || (y < 0 || y > MAX_VALUE) || (b < 0 || b > MAX_VALUE))
        {
            throw new IllegalArgumentException();
        }

        setR(r);
        setY(y);
        setB(b);
    }

    protected RybColorSmartCode(RybColorSmartCode colorCode)
    {
        _r._value = colorCode._r._value;
        _y._value = colorCode._y._value;
        _b._value = colorCode._b._value;
    }

    public int getR()
    {
        return _r._value;
    }

    public void setR(int newR)
    {
        changeColor(newR, _r, _y, _b);
    }

    public int getY()
    {
        return _y._value;
    }

    public void setY(int newY)
    {
        changeColor(newY, _y, _r, _b);
    }

    public int getB()
    {
        return _b._value;
    }

    public void setB(int newB)
    {
        changeColor(newB, _b, _r, _y);
    }

    public void random()
    {
        reset();

        Random rnd = new Random();
        int first = rnd.nextInt(MAX_VALUE) + 1; // make sure this will be always
        int second = rnd.nextInt(MAX_VALUE) + 1; // a secondary color.
        int third = rnd.nextInt();

        if (third % 2 == 0)
        {
            setR(first);
            setY(second);
        } else if (third % COLOR_LEVELS == 0)
        {
            setR(first);
            setB(second);
        } else
        {
            setB(first);
            setY(second);
        }
    }

    public void reset()
    {
        _r._value = 0;
        _y._value = 0;
        _b._value = 0;
    }

    public boolean isGray()
    {
        return _r._value != 0 && _y._value != 0 && _b._value != 0;
    }

    public RybColorSmartCode copy()
    {
        return new RybColorSmartCode(this);
    }

    private void changeColor(int newX, RefInteger curX, RefInteger curY, RefInteger curZ)
    {
        if (newX <= MAX_VALUE && !isGray())
        {
            // Mix colors if not primary.
            if (!(curY._value == 0 && curZ._value == 0 && curX._value != 0))
            {
                curX._value = newX;

                // Subtract code if two colors in equal proportion.
                if (curX._value == MAX_VALUE)
                {
                    if (curY._value == MAX_VALUE)
                    {
                        curX._value = 1;
                        curY._value = 1;
                    } else if (curZ._value == MAX_VALUE)
                    {
                        curX._value = 1;
                        curZ._value = 1;
                    }
                }
            }
        }
    }

    private class RefInteger
    {
        RefInteger(int v)
        {
            this._value = v;
        }

        public int _value;
    }

    private static final int MAX_VALUE = 2;
    private static final int COLOR_LEVELS = 3;
    private RefInteger _r = new RefInteger(0);
    private RefInteger _y = new RefInteger(0);
    private RefInteger _b = new RefInteger(0);
}
