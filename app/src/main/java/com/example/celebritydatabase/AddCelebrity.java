package com.example.celebritydatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddCelebrity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.celebritylistsql.REPLY";

    EditText etFirst;
    EditText etLast;
    EditText etPro;

    String firstName;
    String lastName;
    String profession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_celebrity);

        etFirst = findViewById(R.id.etFirstName);
        etLast = findViewById(R.id.etLastName);
        etPro = findViewById(R.id.etProfession);


        FloatingActionButton fabBack = findViewById(R.id.fabBackFromAdd);
        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        FloatingActionButton fabSubmit = findViewById(R.id.fabSubmit);
        fabSubmit.setOnClickListener(new View.OnClickListener() {
            private Object Celebrity;

            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(etFirst.getText())| TextUtils.isEmpty(etLast.getText())| TextUtils.isEmpty(etPro.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                }
                else {
                    firstName = etFirst.getText().toString();
                    lastName = etLast.getText().toString();
                    profession = etPro.getText().toString();
                    Celebrity = new Celebrity(firstName, lastName, profession);
                    replyIntent.putExtra(EXTRA_REPLY, (Parcelable) Celebrity);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();

            }
        });
    }
}
