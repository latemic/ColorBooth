package com.colorbooth;

import java.security.InvalidParameterException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ActivityBase extends Activity
{
    static final int DIALOG_ABOUT_ID = 1;

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
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.task_about_text).setTitle(R.string.text_about_title).setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener()
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
}
