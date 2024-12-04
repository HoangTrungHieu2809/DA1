package com.example.da1;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnGV = findViewById(R.id.btn_GV);
        Button btnSV = findViewById(R.id.btn_SV);

        // Sự kiện chuyển sang màn hình Đăng nhập Giảng viên
        btnGV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DangNhapGV.class);
                startActivity(intent);
            }
        });

        // Sự kiện chuyển sang màn hình Đăng nhập Sinh viên
        btnSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DangNhapSV.class);
                startActivity(intent);
            }
        });
    }
}