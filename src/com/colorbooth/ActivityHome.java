package com.colorbooth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityHome extends ActivityBase
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onColorParrotButton(View view)
    {
        startActivity(new Intent(this, ActivityParrot.class));
    }

    public void onColorBearButton(View view)
    {
        startActivity(new Intent(this, ActivityBear.class));
    }

    public void onColorTortoiseButton(View view)
    {
        startActivity(new Intent(this, ActivityTortoise.class));
    }

    public void onColorMixButton(View view)
    {
        startActivity(new Intent(this, ActivityColorMix.class));
    }

    public void onColorInfoButton(View view)
    {
        startActivity(new Intent(this, ActivityColors.class));
    }
}
