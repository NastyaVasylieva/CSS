package com.example.nastya.css;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button findByName;
    Button findByFaculty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findByName = (Button) findViewById(R.id.findByNameBtn);
        findByFaculty = (Button) findViewById(R.id.findByFacultuBtn);

        findByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchByNameActivity.class);
                startActivity(intent);


            }
        });

        findByFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchByFacultyActivity.class);
                startActivity(intent);

            }
        });


    }
}
