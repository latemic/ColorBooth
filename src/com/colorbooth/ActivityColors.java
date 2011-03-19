package com.colorbooth;

import android.os.Bundle;

public class ActivityColors extends ActivityBase
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
    }
    
    @Override
    protected void onStart()
    {
        super.onStart();
        logEvent(EVENT_OPEN_COLORS);
    }       
}
