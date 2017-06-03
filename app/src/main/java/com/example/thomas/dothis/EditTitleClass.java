package com.example.thomas.dothis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditTitleClass extends AppCompatActivity {
    String titleText;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("In the EditTitleClass");
        setContentView(R.layout.do_this_layout);
        Intent intent = getIntent();
        titleText = intent.getStringExtra(Intent_Constants.INTENT_TITLE_DATA);
        position = intent.getIntExtra(Intent_Constants.INTENT_ITEM_POSITION, -1);
        EditText titleData = (EditText) findViewById(R.id.title);
        titleData.setText(titleText);

    }

    public void saveButtonClicked(View v) {
        System.out.println("In the EditTitleClass");

        String changedMessageText = ((EditText)findViewById(R.id.title)).getText().toString();
        Intent intent = new Intent();
        intent.putExtra(Intent_Constants.INTENT_CHANGED_TITLE, changedMessageText);
        intent.putExtra(Intent_Constants.INTENT_ITEM_POSITION, position);
        setResult(Intent_Constants.INTENT_REQUEST_EDIT_EXISTING, intent);
        finish();
    }
}
