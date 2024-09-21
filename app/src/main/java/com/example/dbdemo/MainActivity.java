package com.example.dbdemo;

import  androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText courseNameEdt, courseTrackEdt, courseDurationEdt, courseDescriptionEdt;
    private Button addCourseBtn, updateCourseBtn, showCourseBtn, deleteCourseBtn;
    private DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        courseNameEdt = findViewById(R.id.coursename);
        courseTrackEdt = findViewById(R.id.coursetracks);
        courseDurationEdt = findViewById(R.id.courseduration);
        courseDescriptionEdt = findViewById(R.id.coursedescription);
        addCourseBtn = findViewById(R.id.addCourseBTN);
        updateCourseBtn= findViewById(R.id.UpdateCourseBTN);
        showCourseBtn = findViewById(R.id.ShowCourseBTN);
        deleteCourseBtn = findViewById(R.id.DeleteCourseBTN);

        dbHandler = new DbHandler(MainActivity.this);

        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseName = courseNameEdt.getText().toString();
                String courseTracks = courseTrackEdt.getText().toString();
                String courseDuration = courseDurationEdt.getText().toString();
                String courseDescription = courseDescriptionEdt.getText().toString();

                if (courseName.isEmpty() && courseTracks.isEmpty() && courseDuration.isEmpty() && courseDescription.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addNewCourses(courseName, courseDuration, courseTracks, courseDescription);
                Toast.makeText(MainActivity.this, "Course Added", Toast.LENGTH_SHORT).show();
                courseNameEdt.setText("");
                courseDurationEdt.setText("");
                courseTrackEdt.setText("");
                courseDescriptionEdt.setText("");

            }

        });

        updateCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        showCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str= dbHandler.showCourses();
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });

        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}