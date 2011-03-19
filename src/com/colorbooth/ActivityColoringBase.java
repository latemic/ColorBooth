package com.colorbooth;

import java.util.HashMap;
import java.util.Map;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioGroup;

public class ActivityColoringBase extends ActivityBase
{
    public final int[][][] _rybColorCube =
        {
            {
                // R = 0
                    { R.color.color_white, R.color.color_blue, R.color.color_blue },
                    { R.color.color_yellow, R.color.color_green, R.color.color_bluegreen },
                    { R.color.color_yellow, R.color.color_yellowgreen, R.color.color_green } },
            {
                // R = 1
                    { R.color.color_red, R.color.color_violet, R.color.color_blueviolet },
                    { R.color.color_orange, R.color.color_grey, R.color.color_grey },
                    { R.color.color_yelloworange, R.color.color_grey, R.color.color_grey } },
            {
                // R = 2
                    { R.color.color_red, R.color.color_redviolet, R.color.color_violet },
                    { R.color.color_redorange, R.color.color_grey, R.color.color_grey },
                    { R.color.color_grey, R.color.color_grey, R.color.color_grey } } };

    public final Map<Integer, RybColorSmartCode> _rybColorMap = new HashMap<Integer, RybColorSmartCode>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // treat transparent as white
        _rybColorMap.put(Color.TRANSPARENT, new RybColorSmartCode(0, 0, 0));
        _rybColorMap.put(getResources().getColor(R.color.color_blue), new RybColorSmartCode(0, 0, 1));
        _rybColorMap.put(getResources().getColor(R.color.color_bluegreen), new RybColorSmartCode(0, 1, 2));
        _rybColorMap.put(getResources().getColor(R.color.color_blueviolet), new RybColorSmartCode(1, 0, 2));
        _rybColorMap.put(getResources().getColor(R.color.color_green), new RybColorSmartCode(0, 1, 1));
        _rybColorMap.put(getResources().getColor(R.color.color_grey), new RybColorSmartCode(1, 1, 1));
        _rybColorMap.put(getResources().getColor(R.color.color_orange), new RybColorSmartCode(1, 1, 0));
        _rybColorMap.put(getResources().getColor(R.color.color_red), new RybColorSmartCode(1, 0, 0));
        _rybColorMap.put(getResources().getColor(R.color.color_redorange), new RybColorSmartCode(2, 1, 0));
        _rybColorMap.put(getResources().getColor(R.color.color_redviolet), new RybColorSmartCode(2, 0, 1));
        _rybColorMap.put(getResources().getColor(R.color.color_violet), new RybColorSmartCode(1, 0, 1));
        _rybColorMap.put(getResources().getColor(R.color.color_white), new RybColorSmartCode(0, 0, 0));
        _rybColorMap.put(getResources().getColor(R.color.color_yellow), new RybColorSmartCode(0, 1, 0));
        _rybColorMap.put(getResources().getColor(R.color.color_yellowgreen), new RybColorSmartCode(0, 2, 1));
        _rybColorMap.put(getResources().getColor(R.color.color_yelloworange), new RybColorSmartCode(1, 2, 0));
    }

    protected final void mixColors(final RybColorSmartCode currentColorRybCode)
    {
        RadioGroup rg = (RadioGroup) findViewById(R.id.rg_colors);
        int checkedButtonId = rg.getCheckedRadioButtonId();

        switch (checkedButtonId)
        {
        case R.id.btn_red:
            currentColorRybCode.setR(currentColorRybCode.getR() + 1);
            break;
        case R.id.btn_yellow:
            currentColorRybCode.setY(currentColorRybCode.getY() + 1);
            break;
        case R.id.btn_blue:
            currentColorRybCode.setB(currentColorRybCode.getB() + 1);
            break;
        case R.id.btn_white:
            currentColorRybCode.reset();
            break;
        default:
            break;
        }
    }

    protected final int rybToArgb(final RybColorSmartCode rybCode)
    {
        int resid = _rybColorCube[rybCode.getR()][rybCode.getY()][rybCode.getB()];
        int argb = getResources().getColor(resid);
        return argb;
    }
}
