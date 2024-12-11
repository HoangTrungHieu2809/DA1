package com.example.da1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AddStudent extends AppCompatActivity {

    private LinearLayout studentListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        studentListLayout = findViewById(R.id.studentListLayout);
        Button btnAddStudent = findViewById(R.id.btnAddStudent);

        btnAddStudent.setOnClickListener(v -> {
            Intent intent = new Intent(AddStudent.this, ThemSinhVien.class);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
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
