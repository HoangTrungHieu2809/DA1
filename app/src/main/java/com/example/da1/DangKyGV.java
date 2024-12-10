package com.example.da1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class DangKyGV extends AppCompatActivity {
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dang_ky_gv);
        EditText etTen = findViewById(R.id.et_TenDangKyGV);
        EditText etMatKhau = findViewById(R.id.et_MatKhauGV);
        EditText etNhapLaiMatKhau = findViewById(R.id.et_NhapLaiMatKhauGV);
        Button btnDangKy = findViewById(R.id.btn_dang_ky_Gv);

        auth = FirebaseAuth.getInstance();

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = etTen.getText().toString();
                String matKhau = etMatKhau.getText().toString();
                String nhapLaiMatKhau = etNhapLaiMatKhau.getText().toString();

                if (ten.isEmpty() || matKhau.isEmpty() || nhapLaiMatKhau.isEmpty() ) {
                    Toast.makeText(DangKyGV.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!matKhau.equals(nhapLaiMatKhau)) {
                    Toast.makeText(DangKyGV.this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Tạo tài khoản trên Firebase
                auth.createUserWithEmailAndPassword(ten, matKhau)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(DangKyGV.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                finish(); // Đóng màn hình đăng ký
                            } else {
                                Log.e("DangKy", "Đăng ký thất bại", task.getException());
                                Toast.makeText(DangKyGV.this, "Đăng ký thất bại: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(DangKyGV.this, "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
                            } else if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(DangKyGV.this, "Email này đã được sử dụng!", Toast.LENGTH_SHORT).show();
                            }

                        });


            }
        });
    }
}