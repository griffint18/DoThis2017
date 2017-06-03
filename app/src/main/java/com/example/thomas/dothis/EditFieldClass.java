package com.example.thomas.dothis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditFieldClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.do_this_layout);
    }

    public void saveButtonClicked(View v) {
        String titleText = ((EditText) findViewById(R.id.title)).getText().toString();
        String locationText = ((EditText) findViewById(R.id.location)).getText().toString();
        if (titleText.equals("") || locationText.equals("")) {

        } else{
            Intent intent = new Intent();
            intent.putExtra(Intent_Constants.INTENT_TITLE_FIELD, titleText);
            intent.putExtra(Intent_Constants.INTENT_LOCATION_FIELD, locationText);
            setResult(Intent_Constants.INTENT_RESULT_CODE, intent);
            finish();
        }
    }
}
