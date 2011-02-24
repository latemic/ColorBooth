package com.colorbooth;

import java.security.InvalidParameterException;

import com.colorbooth.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityHome extends Activity {

	static final int DIALOG_ABOUT_ID = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}

	public void onColorParrotButton(View view) {
		startActivity(new Intent(this, ActivityParrot.class));
	}

	public void onColorBearButton(View view) {
		startActivity(new Intent(this, ActivityBear.class));
	}

	public void onColorMixButton(View view) {
		startActivity(new Intent(this, ActivityColorMix.class));
	}

	public void onColorInfoButton(View view) {
		startActivity(new Intent(this, ActivityColors.class));
	}

	public void onAboutButton(View view) {
		showDialog(DIALOG_ABOUT_ID);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		switch (id) {
		case DIALOG_ABOUT_ID:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(R.string.task_about_text)
			.setTitle(R.string.text_about_title)
			.setCancelable(
					false).setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
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