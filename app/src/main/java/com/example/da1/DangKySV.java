package com.example.da1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class DangKySV extends AppCompatActivity {
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_sv); // Đảm bảo file layout chính xác

        // Ánh xạ các View từ layout
        EditText etTen = findViewById(R.id.et_TenDangKySV);
        EditText etMatKhau = findViewById(R.id.et_MatKhauSV);
        EditText etNhapLaiMatKhau = findViewById(R.id.et_NhapLaiMatKhauSV);
        Button btnDangKy = findViewById(R.id.btn_dang_ky_Sv);

        auth = FirebaseAuth.getInstance();

        // Sự kiện khi nhấn nút Đăng ký
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = etTen.getText().toString().trim();
                String matKhau = etMatKhau.getText().toString().trim();
                String nhapLaiMatKhau = etNhapLaiMatKhau.getText().toString().trim();

                Log.d("DangKy", "Tên: " + ten + ", Mật khẩu: " + matKhau + ", Nhập lại: " + nhapLaiMatKhau);
                // Kiểm tra thông tin nhập vào
                if (ten.isEmpty() || matKhau.isEmpty() || nhapLaiMatKhau.isEmpty()) {
                    Toast.makeText(DangKySV.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!matKhau.equals(nhapLaiMatKhau)) {
                    Toast.makeText(DangKySV.this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Tạo tài khoản trên Firebase
                auth.createUserWithEmailAndPassword(ten, matKhau)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(DangKySV.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                finish(); // Đóng màn hình đăng ký
                            } else {
                                Log.e("DangKy", "Đăng ký thất bại", task.getException());
                                Toast.makeText(DangKySV.this, "Đăng ký thất bại: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(DangKySV.this, "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
                            } else if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(DangKySV.this, "Email này đã được sử dụng!", Toast.LENGTH_SHORT).show();
                            }

                        });

                // Lưu thông tin tài khoản vào SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("AccountSV", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Ten", ten);
                editor.putString("MatKhau", matKhau);
                editor.apply();

                Toast.makeText(DangKySV.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                finish(); // Đóng màn hình đăng ký sau khi hoàn tất
            }
        });
    }
}
