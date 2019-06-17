package com.example.celebritydatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ReadWriteFile extends AppCompatActivity {

    EditText etOutput;
    TextView tvInput;


    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_write_file);

        etOutput = findViewById(R.id.etFileInput);
        tvInput = findViewById(R.id.tvFileOutput);


    }

    // write text to file
    public void submit(View v) {
        // add-write text into file
        try {
            FileOutputStream fileOut = openFileOutput("textFile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileOut);
            outputWriter.write(etOutput.getText().toString());
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read text from file
    public void read(View v) {
        //reading text from file
        try {
            FileInputStream fileIn = openFileInput("textFile.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String string="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readString = String.copyValueOf(inputBuffer,0,charRead);
                string += readString;
            }
            InputRead.close();
            tvInput.setText(string);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
