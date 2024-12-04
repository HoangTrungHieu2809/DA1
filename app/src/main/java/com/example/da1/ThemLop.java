package com.example.da1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ThemLop extends AppCompatActivity {
    EditText edtClassName;
    Button btnAddClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_them_lop);

        edtClassName = findViewById(R.id.edtClassName);
        btnAddClass = findViewById(R.id.btnAddClass);

        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String className = edtClassName.getText().toString();
                // Thêm lớp vào danh sách (có thể lưu vào một cơ sở dữ liệu hoặc ArrayList)
                // Ví dụ, ta chỉ in ra thông tin lớp vừa thêm
                System.out.println("Lớp mới: " + className);
                finish(); // Đóng màn hình thêm lớp và quay lại màn hình chính
            }
        });
    }
}