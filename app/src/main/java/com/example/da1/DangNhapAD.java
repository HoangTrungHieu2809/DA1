package com.example.da1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class DangNhapAD extends AppCompatActivity {
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dang_nhap_ad);

        Button btnDangNhap = findViewById(R.id.btn_dang_nhap_AD);
        TextView tvDangKy = findViewById(R.id.tv_dang_ky_GV);
        EditText edtTaiKhoan = findViewById(R.id.et_TenDangNhapAD);
        EditText edtMatKhau = findViewById(R.id.et_MatKhauAD);

        // Khởi tạo Firebase Authentication
        auth = FirebaseAuth.getInstance();

        // Sự kiện Đăng nhập
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taiKhoan = edtTaiKhoan.getText().toString().trim();
                String matKhau = edtMatKhau.getText().toString().trim();

                if (taiKhoan.isEmpty() || matKhau.isEmpty()) {
                    Toast.makeText(DangNhapAD.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    // Xác thực đăng nhập với Firebase
                    auth.signInWithEmailAndPassword(taiKhoan, matKhau)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(DangNhapAD.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(DangNhapAD.this, HomeAD.class);
                                    startActivity(intent);
                                    finish(); // Đóng màn hình đăng nhập
                                } else {
                                    Toast.makeText(DangNhapAD.this, "Đăng nhập thất bại: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                }
            }
        });
    }
}