package com.colorbooth;

import java.util.HashMap;
import java.util.Map;

import com.colorbooth.R;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class ActivityColorMix extends ActivityBase
{
	RybColorSmartCode colorCode = new RybColorSmartCode();
	RybColorSmartCode neededColorCode = new RybColorSmartCode();
	
	static final Map<Integer, Integer> colorNamesDict = new HashMap<Integer, Integer>(); 
	static
	{
		colorNamesDict.put(R.color.color_blue, R.string.color_name_blue);
		colorNamesDict.put(R.color.color_bluegreen, R.string.color_name_bluegreen);
		colorNamesDict.put(R.color.color_blueviolet, R.string.color_name_blueviolet);
		colorNamesDict.put(R.color.color_green, R.string.color_name_green);
		colorNamesDict.put(R.color.color_grey, R.string.color_name_grey);
		colorNamesDict.put(R.color.color_orange, R.string.color_name_orange);
		colorNamesDict.put(R.color.color_red, R.string.color_name_red);
		colorNamesDict.put(R.color.color_redorange, R.string.color_name_redorange);
		colorNamesDict.put(R.color.color_redviolet, R.string.color_name_redviolet);
		colorNamesDict.put(R.color.color_violet, R.string.color_name_violet);
		colorNamesDict.put(R.color.color_white, R.string.color_name_white);
		colorNamesDict.put(R.color.color_yellow, R.string.color_name_yellow);
		colorNamesDict.put(R.color.color_yellowgreen, R.string.color_name_yellowgreen);
		colorNamesDict.put(R.color.color_yelloworange, R.string.color_name_yelloworange);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colormix);
       
        neededColorCode.random();
        refreshNewColor();
    }
    
	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_colormix);
        refreshNewColor();
        refreshColor();
	}    
    
 // Need handler for callbacks to the UI thread
    final Handler mHandler = new Handler();

    // Create runnable for posting
    final Runnable mCheckResult = new Runnable() {
        public void run() {
        	checkResult();
        }
    };    
    
    public void onColorClick(View view)
    {
    	RadioGroup rg = (RadioGroup)findViewById(R.id.rg_colors);
    	int checkedButtonId = rg.getCheckedRadioButtonId();
    	switch(checkedButtonId)
    	{
    	case R.id.btn_red:
    		colorCode.setR(colorCode.getR() + 1);
    		break;
    	case R.id.btn_yellow:
    		colorCode.setY(colorCode.getY() + 1);
    		break;
    	case R.id.btn_blue:
    		colorCode.setB(colorCode.getB() + 1);
    		break;
    	case R.id.btn_white:
    		colorCode.reset();
    		break;
    	}
    	
    	refreshColor();
    	
    	new Thread(
                new Runnable()
                {
                    public void run()
                    {
            			mHandler.post(mCheckResult);
                    }
                }
            ).start();
    }

    protected void refreshNewColor()
    {
    	int resid = rybColorCube[neededColorCode.getR()][neededColorCode.getY()][neededColorCode.getB()];
    	Button colorButton = (Button)findViewById(R.id.btn_needed_color);
    	colorButton.setBackgroundResource(resid);
    	String colorName = getString((int)colorNamesDict.get(resid)); 
    	String text = String.format(getString(R.string.task_color_mix), colorName);
    	colorButton.setText(text);
    }
    
    protected void refreshColor()
    {
    	int resid = rybColorCube[colorCode.getR()][colorCode.getY()][colorCode.getB()];
    	Button currentColorButton = (Button)findViewById(R.id.btn_current_color);
    	currentColorButton.setBackgroundResource(resid);
    	currentColorButton.invalidate();
    }
    
    protected void checkResult()
    {
    	if(colorCode.getB() == neededColorCode.getB()
    		&& colorCode.getR() == neededColorCode.getR()
    		&& colorCode.getY() == neededColorCode.getY())
    	{
    		try
    		{
        		MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.ting);
        		mediaPlayer.start();

    			// Delay to let user see the color.
    			Thread.sleep(1000);
        		
    			neededColorCode.random();
        		refreshNewColor();
        		colorCode.reset();
        		refreshColor();
    		}
    		catch (InterruptedException e)
    		{
    			e.printStackTrace();
    		}

    	}
    }
}