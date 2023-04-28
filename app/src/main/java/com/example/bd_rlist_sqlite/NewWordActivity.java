package com.example.bd_rlist_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.example.bd_rlist_sqlite.room.Dia;

import java.util.HashMap;

public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_ILONG = "IL";
    public static final String EXTRA_ISHORT = "IS";
    public static final String EXTRA_GLUCOSE = "GL";
    public static final String EXTRA_XE = "XE";

    private EditText mEditInjectLongView;
    private EditText mEditInjectShortView;
    private EditText mEditGlucoseView;
    private EditText mEditXeView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        mEditInjectLongView = findViewById(R.id.edit_injectLong);
        mEditInjectShortView = findViewById(R.id.edit_injectShort);
        mEditGlucoseView = findViewById(R.id.edit_glucose);
        mEditXeView = findViewById(R.id.edit_xe);

        final Button button = findViewById(R.id.button_save);

        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditInjectLongView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String injectLong = mEditInjectLongView.getText().toString();
                String injectShort = mEditInjectShortView.getText().toString();
                String glucose = mEditGlucoseView.getText().toString();
                String xe = mEditXeView.getText().toString();

                replyIntent.putExtra(EXTRA_ILONG, injectLong);
                replyIntent.putExtra(EXTRA_ISHORT, injectShort);
                replyIntent.putExtra(EXTRA_GLUCOSE, glucose);
                replyIntent.putExtra(EXTRA_XE, xe);

                setResult(RESULT_OK, replyIntent);
            }
            finish();

        });
    }
}