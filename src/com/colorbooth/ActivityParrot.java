package com.colorbooth;

import android.content.res.Configuration;
import android.os.Bundle;

public class ActivityParrot extends ActivityColoringPage
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_parrot);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        setContentView(R.layout.activity_parrot);
        super.onConfigurationChanged(newConfig);
    }
}
