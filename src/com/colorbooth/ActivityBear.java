package com.colorbooth;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ImageView;

public class ActivityBear extends ActivityColoringPage
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_coloring);
        ((ImageView) findViewById(R.id.iv_coloring_figure)).setImageResource(R.drawable.bear);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        logEvent(EVENT_COLOR_BEAR);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        setContentView(R.layout.activity_coloring);
        ((ImageView) findViewById(R.id.iv_coloring_figure)).setImageResource(R.drawable.bear);
        super.onConfigurationChanged(newConfig);
    }
}
