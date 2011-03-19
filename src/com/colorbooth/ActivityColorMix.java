package com.colorbooth;

import java.util.HashMap;
import java.util.Map;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class ActivityColorMix extends ActivityColoringBase
{
    private static final int PREVIEW_PAUSE = 1000;
    private MediaPlayer _mediaPlayer;
    private RybColorSmartCode _colorCode = new RybColorSmartCode();
    private RybColorSmartCode _neededColorCode = new RybColorSmartCode();

    static final Map<Integer, Integer> COLOR_NAMES_DICT = new HashMap<Integer, Integer>();
    static
    {
        COLOR_NAMES_DICT.put(R.color.color_blue, R.string.color_name_blue);
        COLOR_NAMES_DICT.put(R.color.color_bluegreen, R.string.color_name_bluegreen);
        COLOR_NAMES_DICT.put(R.color.color_blueviolet, R.string.color_name_blueviolet);
        COLOR_NAMES_DICT.put(R.color.color_green, R.string.color_name_green);
        COLOR_NAMES_DICT.put(R.color.color_grey, R.string.color_name_grey);
        COLOR_NAMES_DICT.put(R.color.color_orange, R.string.color_name_orange);
        COLOR_NAMES_DICT.put(R.color.color_red, R.string.color_name_red);
        COLOR_NAMES_DICT.put(R.color.color_redorange, R.string.color_name_redorange);
        COLOR_NAMES_DICT.put(R.color.color_redviolet, R.string.color_name_redviolet);
        COLOR_NAMES_DICT.put(R.color.color_violet, R.string.color_name_violet);
        COLOR_NAMES_DICT.put(R.color.color_white, R.string.color_name_white);
        COLOR_NAMES_DICT.put(R.color.color_yellow, R.string.color_name_yellow);
        COLOR_NAMES_DICT.put(R.color.color_yellowgreen, R.string.color_name_yellowgreen);
        COLOR_NAMES_DICT.put(R.color.color_yelloworange, R.string.color_name_yelloworange);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colormix);
        _mediaPlayer = MediaPlayer.create(this, R.raw.ting);
        _neededColorCode.random();
        refreshNewColor();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        logEvent(EVENT_COLOR_MIX);
    }

    @Override
    protected void onDestroy()
    {
        _mediaPlayer.release();
        super.onDestroy();
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
    private final Handler _handler = new Handler();

    // Create runnable for posting
    private final Runnable _checkResult = new Runnable()
    {
        public void run()
        {
            checkResult();
        }
    };

    public void onColorClick(View view)
    {
        RadioGroup rg = (RadioGroup) findViewById(R.id.rg_colors);
        int checkedButtonId = rg.getCheckedRadioButtonId();
        switch (checkedButtonId)
        {
        case R.id.btn_red:
            _colorCode.setR(_colorCode.getR() + 1);
            break;
        case R.id.btn_yellow:
            _colorCode.setY(_colorCode.getY() + 1);
            break;
        case R.id.btn_blue:
            _colorCode.setB(_colorCode.getB() + 1);
            break;
        case R.id.btn_white:
        default:
            _colorCode.reset();
            break;
        }

        refreshColor();

        new Thread(new Runnable()
        {
            public void run()
            {
                _handler.post(_checkResult);
            }
        }).start();
    }

    protected void refreshNewColor()
    {
        int resid = _rybColorCube[_neededColorCode.getR()][_neededColorCode.getY()][_neededColorCode.getB()];
        Button colorButton = (Button) findViewById(R.id.btn_needed_color);
        colorButton.setBackgroundResource(resid);
        String colorName = getString((int) COLOR_NAMES_DICT.get(resid));
        String text = String.format(getString(R.string.task_color_mix), colorName);
        colorButton.setText(text);
    }

    protected void refreshColor()
    {
        int resid = _rybColorCube[_colorCode.getR()][_colorCode.getY()][_colorCode.getB()];
        Button currentColorButton = (Button) findViewById(R.id.btn_current_color);
        currentColorButton.setBackgroundResource(resid);
        currentColorButton.invalidate();
    }

    protected void checkResult()
    {
        if (_colorCode.getB() == _neededColorCode.getB() && _colorCode.getR() == _neededColorCode.getR()
                && _colorCode.getY() == _neededColorCode.getY())
        {
            try
            {
                // Media player instance may be not created.
                if (_mediaPlayer != null)
                {
                    _mediaPlayer.start();
                }

                // Delay to let user see the color.
                Thread.sleep(PREVIEW_PAUSE);
                _neededColorCode.random();
                refreshNewColor();
                _colorCode.reset();
                refreshColor();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
