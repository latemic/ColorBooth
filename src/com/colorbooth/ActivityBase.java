package com.colorbooth;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.flurry.android.FlurryAgent;

public class ActivityBase extends Activity
{
    static final int DIALOG_ABOUT_ID = 1;
    static final String EVENT_COLOR_BEAR = "Color bear";
    static final String EVENT_COLOR_PARROT = "Color parrot";
    static final String EVENT_COLOR_TORTOISE = "Color tortoise";
    static final String EVENT_COLOR_MIX = "Color mix";
    static final String EVENT_OPEN_COLORS = "Open colors";
    static final String EVENT_OPEN_INFO = "Open info";
    static final String EVENT_CLICK_LINK = "Click link";

    @Override
    protected void onStart()
    {
        super.onStart();
        FlurryAgent.onStartSession(this, "K1EK5A7K6JMD3UE7VZTB");
        FlurryAgent.onPageView();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        FlurryAgent.onEndSession(this);
    }

    protected void logEvent(String event)
    {
        FlurryAgent.logEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item)
    {
        boolean result = false;

        switch (item.getItemId())
        {
        case R.id.menu_item_info:
            showDialog(DIALOG_ABOUT_ID);
            break;

        default:
            break;
        }

        return result;
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        Dialog dialog = null;
        switch (id)
        {
        case DIALOG_ABOUT_ID:
            logEvent(EVENT_OPEN_INFO);

            int padding = (int) getResources().getDimension(R.dimen.padding_dialog);

            ScrollView sv = new ScrollView(this);
            TextView tv = new TextView(this);
            tv.setMovementMethod(LinkMovementMethod.getInstance());
            addBlogLink(tv);

            tv.setTextColor(getResources().getColor(R.color.foreground4));
            tv.setTextSize(getResources().getDimension(R.dimen.text_size_small));
            tv.setPadding(padding, padding, padding, padding);
            sv.addView(tv);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.text_about_title).setCancelable(true).setView(sv).setPositiveButton("Ok",
                    new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            dialog.cancel();
                        }
                    });
            dialog = builder.create();
            break;
        default:
            throw new InvalidParameterException();
        }
        return dialog;
    }

    private void addBlogLink(TextView tv)
    {
        String about = getResources().getString(R.string.task_about_text);
        SpannableStringBuilder builder = new SpannableStringBuilder(about);

        Pattern p = Pattern.compile("Denys Nikolayenko");
        Matcher m = p.matcher(about);

        if (m.find())
        {
            int start = m.start();
            int end = m.end();

            URLSpan span = new URLSpan(getResources().getString(R.string.blog))
            {
                @Override
                public void onClick(View widget)
                {
                    logEvent(EVENT_CLICK_LINK);
                    super.onClick(widget);
                }
            };

            builder.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        }

        tv.setText(builder);
    }
}
