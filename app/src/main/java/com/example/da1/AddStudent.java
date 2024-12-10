package com.example.da1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class AddStudent extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD_STUDENT = 1;
    private LinearLayout studentListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_student);

        studentListLayout = findViewById(R.id.studentListLayout);
        Button btnAddStudent = findViewById(R.id.btnAddStudent);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddStudent.this, ThemSinhVien.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_STUDENT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD_STUDENT && resultCode == RESULT_OK) {
            String fullName = data.getStringExtra("fullName");
            String address = data.getStringExtra("address");
            String studentID = data.getStringExtra("studentID");
            String dateOfBirth = data.getStringExtra("dateOfBirth");

            addStudentToList(fullName, address, studentID, dateOfBirth);
        }
    }

    private void addStudentToList(String fullName, String address, String studentID, String dateOfBirth) {
        TextView studentView = new TextView(this);
        studentView.setText("Họ và Tên: " + fullName + "\n"
                + "Quê Quán: " + address + "\n"
                + "Mã Sinh Viên: " + studentID + "\n"
                + "Ngày Sinh: " + dateOfBirth);
        studentView.setPadding(16, 16, 16, 16);
        studentView.setBackgroundResource(android.R.drawable.dialog_holo_light_frame);

        studentListLayout.addView(studentView);

    }
}