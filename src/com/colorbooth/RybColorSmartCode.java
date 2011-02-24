package com.colorbooth;

import java.util.Random;

public class RybColorSmartCode
{
	public RybColorSmartCode()
	{
	}
	
	public RybColorSmartCode(int r, int y, int b)
	{
		if((r < 0 || r > maxValue)
				|| (y < 0 || y > maxValue)
				|| (b < 0 || b > maxValue))
		{
			throw new IllegalArgumentException();
		}
		
		setR(r);
		setY(y);
		setB(b);
	}
	
	protected RybColorSmartCode(RybColorSmartCode colorCode)
	{
		r.value = colorCode.r.value;
		y.value = colorCode.y.value;
		b.value = colorCode.b.value;
	}	
	
    public int getR() { return r.value; }
    public void setR(int newR)
    {
    	changeColor(newR, r, y, b);
    }

    public int getY() { return y.value; }
    public void setY(int newY)
    {
    	changeColor(newY, y, r, b);
    }

    public int getB() { return b.value; }
    public void setB(int newB)
    {
    	changeColor(newB, b, r, y);
    }
    
    public void random()
    {
    	reset();
    	
    	Random rnd = new Random();
    	int first = rnd.nextInt(maxValue) + 1; // make sure this will be always
    	int second = rnd.nextInt(maxValue) + 1; // a secondary color.
    	int third = rnd.nextInt();
    	
    	if(third % 2 == 0)
    	{
    		setR(first);
    		setY(second);
    	}
    	else if(third % 3 == 0)
    	{
    		setR(first);
    		setB(second);
    	}
    	else
    	{
    		setB(first);
    		setY(second);
    	}
    }
    
    public void reset() { r.value = 0; y.value = 0; b.value = 0; }
    
    public boolean isGray() { return r.value != 0 && y.value != 0 && b.value != 0; }
    
    public RybColorSmartCode copy()
    {
    	return new RybColorSmartCode(this);
    }
    
    private void changeColor(int newX, RefInteger curX, RefInteger curY, RefInteger curZ)
    {
    	if(newX <= maxValue && !isGray())
    	{
    		// Mix colors if not primary.
    		if(!(curY.value == 0 && curZ.value == 0 && curX.value != 0))
    		{
    			curX.value = newX;
    			
	    		// Subtract code if two colors in equal proportion.
	    		if(curX.value == maxValue)
	    		{
	    			if(curY.value == maxValue)
	    			{
	    				curX.value = 1;
	    				curY.value = 1;
	    			}
	    			else if (curZ.value == maxValue)
	    			{
	    				curX.value = 1;
	    				curZ.value = 1;
	    			}
	    		}
    		}
    	}
    }

	private class RefInteger
	{
		RefInteger(int v) { this.value = v; }
		public int value;
	}
    
    private final int maxValue = 2;
    private RefInteger r = new RefInteger(0);
    private RefInteger y = new RefInteger(0);
    private RefInteger b = new RefInteger(0);
}
