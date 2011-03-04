package com.colorbooth;

import java.util.LinkedList;
import java.util.Queue;

import android.graphics.Bitmap;

class FloodFill
{
    public FloodFill(Bitmap bmp, int oldColor, int newColor)
    {
        _bmp = bmp;
        _oldColor = oldColor;
        _newColor = newColor;
        _bmpWidth = bmp.getWidth();
        _bmpHeight = bmp.getHeight();
    }

    public void fill(int x, int y)
    {
        // 1. Set Q to the empty queue.
        Queue<Pixel> queue = new LinkedList<Pixel>();

        // 2. If the color of node is not equal to target-color, return.
        if (_bmp.getPixel(x, y) == _oldColor)
        {
            // 3. Add node to Q.
            queue.add(new Pixel(x, y));

            // 4. For each element n of Q.
            while (!queue.isEmpty())
            {
                // 5. If the color of n is equal to target-color.
                Pixel n = queue.poll();
                if (_bmp.getPixel(n._x, n._y) == _oldColor)
                {
                    // 6. Set w and e equal to n.
                    Pixel w = new Pixel(n._x, n._y);
                    Pixel e = new Pixel(n._x, n._y);

                    // 7. Move w to the west until the color of the node to the
                    // west of w no longer matches target-color.
                    while ((_bmp.getPixel(w._x, w._y) == _oldColor) && w._x > 0)
                    {
                        w._x--;
                    }

                    // 8. Move e to the east until the color of the node to the
                    // east of e no longer matches target-color.
                    while ((_bmp.getPixel(e._x, e._y) == _oldColor) && e._x < _bmpWidth - 1)
                    {
                        e._x++;
                    }

                    // 9. Set the color of nodes between w and e to
                    // replacement-color.
                    for (int ix = w._x + 1; ix < e._x; ix++)
                    {
                        _bmp.setPixel(ix, n._y, _newColor);

                        // 10. For each node n between w and e.
                        // 11. If the color of the node to the north of n is
                        // target-color, add that node to Q.
                        if (n._y - 1 >= 0 && _bmp.getPixel(ix, n._y - 1) == _oldColor)
                        {
                            queue.add(new Pixel(ix, n._y - 1));
                        }

                        // 12. If the color of the node to the south of n is
                        // target-color, add that node to Q.
                        if (n._y + 1 < _bmpHeight && _bmp.getPixel(ix, n._y + 1) == _oldColor)
                        {
                            queue.add(new Pixel(ix, n._y + 1));
                        }
                    }
                }
            }
        }
    }

    private class Pixel
    {
        public Pixel(int x, int y)
        {
            _x = x;
            _y = y;
        }

        public int getX()
        {
            return _x;
        }

        public void setX(int x)
        {
            _x = x;
        }

        public int getY()
        {
            return _y;
        }

        public void setY(int y)
        {
            _y = y;
        }

        private int _x;
        private int _y;
    }

    private Bitmap _bmp;
    private int _oldColor;
    private int _newColor;
    private int _bmpWidth;
    private int _bmpHeight;
}
