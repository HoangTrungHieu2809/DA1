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

public class DangNhapGV extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dang_nhap_gv);

        Button btnDangNhap = findViewById(R.id.btn_dang_nhap_Gv);
        TextView tvDangKy = findViewById(R.id.tv_dang_ky_GV);

        // Sự kiện Đăng nhập (chưa xử lý đăng nhập thật)
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý logic đăng nhập tại đây (ví dụ kiểm tra thông tin với SharedPreferences)
                Intent intent = new Intent(DangNhapGV.this, Man1_GV.class); // Chuyển tới Man1SV
                startActivity(intent);
                finish();
            }
        });

        // Sự kiện chuyển sang màn hình Đăng ký Giảng viên
        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapGV.this, DangKyGV.class);
                startActivity(intent);
            }
        });
    }
}