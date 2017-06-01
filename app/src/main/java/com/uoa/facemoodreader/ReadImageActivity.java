package com.uoa.facemoodreader;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kd.progressbar.SimpleCircularProgressbar;

/**
 * Created by mohankumar on 31/05/2017.
 */
public class ReadImageActivity extends AppCompatActivity {

    String mAngerString;
    String mJoyString;
    String mSorrowString;
    String mSurpriseString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_image);
        SimpleCircularProgressbar joyProgressBar = (SimpleCircularProgressbar) findViewById(R.id.joyProgressBar);
        SimpleCircularProgressbar surpriseProgressBar = (SimpleCircularProgressbar) findViewById(R.id.surpriseProgressBar);
        SimpleCircularProgressbar angerProgressBar = (SimpleCircularProgressbar) findViewById(R.id.angerProgressBar);
        SimpleCircularProgressbar sorrowProgressBar = (SimpleCircularProgressbar) findViewById(R.id.sorrowProgressBar);
        TextView joyText = (TextView) findViewById(R.id.joyText);
        TextView surpriseText = (TextView) findViewById(R.id.surpriseText);
        TextView angerText = (TextView) findViewById(R.id.angerText);
        TextView sorrowText = (TextView) findViewById(R.id.sorrowText);
        Button tryAgainButton = (Button) findViewById(R.id.btn_try_again);
        Bundle bundle = null;
        bundle = this.getIntent().getExtras();
        String string = bundle.getString("mood");
        if (string != null) {
            String[] moodString = string.split(":");
            if (moodString.length == 1) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ReadImageActivity.this);
                alertDialog.setTitle("Error");
                alertDialog.setMessage(string);
                alertDialog.setPositiveButton("TRY AGAIN", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.dismiss();
                        startActivity();
                    }
                }).show();
            } else {
                mAngerString = moodString[0];
                mJoyString = moodString[1];
                mSorrowString = moodString[2];
                mSurpriseString = moodString[3];
                int joyValue = getMoodValue(mJoyString);
                int surpriseValue = getMoodValue(mSurpriseString);
                int angerValue = getMoodValue(mAngerString);
                int sorrowValue = getMoodValue(mSorrowString);
                joyProgressBar.setProgress(joyValue);
                surpriseProgressBar.setProgress(surpriseValue);
                angerProgressBar.setProgress(angerValue);
                sorrowProgressBar.setProgress(sorrowValue);
                joyText.setText(joyValue + "%");
                surpriseText.setText(surpriseValue + "%");
                angerText.setText(angerValue + "%");
                sorrowText.setText(sorrowValue + "%");
            }
        }
        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity();
            }
        });
    }

    private int getMoodValue(String moodString) {
        int moodValue = 0;
        switch (moodString) {
            case "VERY_LIKELY":
                moodValue = 100;
                break;
            case "LIKELY":
                moodValue = 80;
                break;
            case "POSSIBLE":
                moodValue = 60;
                break;
            case "UNLIKELY":
                moodValue = 40;
                break;
            case "VERY_UNLIKELY":
                moodValue = 20;
                break;
            case "UNKNOWN":
                moodValue = 0;
                break;
            default:
                moodValue = 0;
        }
        return moodValue;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity();
    }

    private void startActivity() {
        Intent intent = new Intent(ReadImageActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
