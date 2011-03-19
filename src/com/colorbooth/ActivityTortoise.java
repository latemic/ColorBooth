package com.colorbooth;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ImageView;

public class ActivityTortoise extends ActivityColoringPage
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_coloring);
        ((ImageView) findViewById(R.id.iv_coloring_figure)).setImageResource(R.drawable.tortoise);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        logEvent(EVENT_COLOR_TORTOISE);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        setContentView(R.layout.activity_coloring);
        ((ImageView) findViewById(R.id.iv_coloring_figure)).setImageResource(R.drawable.tortoise);
        super.onConfigurationChanged(newConfig);
    }
}
