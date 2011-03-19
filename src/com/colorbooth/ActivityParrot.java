package com.colorbooth;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ImageView;

public class ActivityParrot extends ActivityColoringPage
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_coloring);
        ((ImageView)findViewById(R.id.iv_coloring_figure)).setImageResource(R.drawable.parrot);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        setContentView(R.layout.activity_coloring);
        ((ImageView)findViewById(R.id.iv_coloring_figure)).setImageResource(R.drawable.parrot);
        super.onConfigurationChanged(newConfig);
    }
}
