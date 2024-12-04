package com.example.da1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DangNhapSV extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dang_nhap_sv);
        Button btnDangNhap = findViewById(R.id.btn_dang_nhap_Sv);
        TextView tvDangKy2 = findViewById(R.id.tv_dang_ky_SV);

        // Sự kiện Đăng nhập (chưa xử lý đăng nhập thật)
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý logic đăng nhập tại đây (ví dụ kiểm tra thông tin với SharedPreferences)
            }
        });

        // Sự kiện chuyển sang màn hình Đăng ký Sinh Viên
        tvDangKy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapSV.this, DangKySV.class);
                startActivity(intent);
            }
        });
    }
}