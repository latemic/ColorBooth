package com.colorbooth;

import java.util.LinkedList;
import java.util.Queue;

import android.graphics.Bitmap;

class FloodFill
{
	public FloodFill(Bitmap bmp, int oldColor, int newColor)
	{
		mBmp = bmp;
		mOldColor = oldColor;
		mNewColor = newColor;
		mBmpWidth = bmp.getWidth();
		mBmpHeight = bmp.getHeight();
	}
	
	public void Fill(int x, int y)
	{
		// 1. Set Q to the empty queue.
		Queue<Pixel> queue = new LinkedList<Pixel>();
		
		// 2. If the color of node is not equal to target-color, return.
		if(mBmp.getPixel(x, y) == mOldColor)
		{
			// 3. Add node to Q.
			queue.add(new Pixel(x,y));
			
			// 4. For each element n of Q.
			while(!queue.isEmpty())
			{
				// 5. If the color of n is equal to target-color.
				Pixel n = queue.poll();
				if(mBmp.getPixel(n._x, n._y) == mOldColor)
				{
					// 6. Set w and e equal to n.
					Pixel w = new Pixel(n._x, n._y);
					Pixel e = new Pixel(n._x, n._y);
					
					// 7. Move w to the west until the color of the node to the west of w no longer matches target-color.
					while((mBmp.getPixel(w._x, w._y) == mOldColor) && w._x > 0)
					{
						w._x--; 
					}
					
					// 8. Move e to the east until the color of the node to the east of e no longer matches target-color.
					while((mBmp.getPixel(e._x, e._y) == mOldColor) && e._x < mBmpWidth - 1)
					{
						e._x++; 
					}
					
					// 9. Set the color of nodes between w and e to replacement-color.
					for(int ix = w._x+1 ; ix < e._x ; ix ++ )
					{
						mBmp.setPixel(ix, n._y, mNewColor);
						
						// 10. For each node n between w and e.
						// 11. If the color of the node to the north of n is target-color, add that node to Q.
						if(n._y-1 >= 0 && mBmp.getPixel(ix, n._y-1) == mOldColor)
						{
							queue.add(new Pixel(ix, n._y-1));
						}

						// 12. If the color of the node to the south of n is target-color, add that node to Q.
						if(n._y+1 < mBmpHeight && mBmp.getPixel(ix, n._y+1) == mOldColor)
						{
							queue.add(new Pixel(ix, n._y+1));
						}
					}
				}
			}
		}
	}
	
    private class Pixel
    {
        public Pixel(int x, int y) {
                _x = x;
                _y = y;
        }

        public int _x;
        public int _y;
    }
	
	private Bitmap mBmp;
	private int mOldColor;
	private int mNewColor;
	private int mBmpWidth;
	private int mBmpHeight;
}