package com.example.da1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ThemSinhVien extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_them_sinh_vien);

        EditText edtFullName = findViewById(R.id.edtFullName);
        EditText edtAddress = findViewById(R.id.edtAddress);
        EditText edtStudentID = findViewById(R.id.edtStudentID);
        EditText edtDateOfBirth = findViewById(R.id.edtDateOfBirth);
        Button btnAddStudent = findViewById(R.id.btnAddStudent);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = edtFullName.getText().toString().trim();
                String address = edtAddress.getText().toString().trim();
                String studentID = edtStudentID.getText().toString().trim();
                String dateOfBirth = edtDateOfBirth.getText().toString().trim();

                if (fullName.isEmpty() || address.isEmpty() || studentID.isEmpty() || dateOfBirth.isEmpty()) {
                    Toast.makeText(ThemSinhVien.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("fullName", fullName);
                    resultIntent.putExtra("address", address);
                    resultIntent.putExtra("studentID", studentID);
                    resultIntent.putExtra("dateOfBirth", dateOfBirth);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
}