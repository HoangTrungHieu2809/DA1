package com.example.da1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DangKyGV extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dang_ky_gv);
        EditText etTen = findViewById(R.id.et_TenDangKyGV);
        EditText etMatKhau = findViewById(R.id.et_MatKhauGV);
        EditText etNhapLaiMatKhau = findViewById(R.id.et_NhapLaiMatKhauGV);
        EditText etNganhDay = findViewById(R.id.et_NganhDay);
        Button btnDangKy = findViewById(R.id.btn_dang_ky_Gv);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = etTen.getText().toString();
                String matKhau = etMatKhau.getText().toString();
                String nhapLaiMatKhau = etNhapLaiMatKhau.getText().toString();
                String nganhDay = etNganhDay.getText().toString();

                if (ten.isEmpty() || matKhau.isEmpty() || nhapLaiMatKhau.isEmpty() || nganhDay.isEmpty()) {
                    Toast.makeText(DangKyGV.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!matKhau.equals(nhapLaiMatKhau)) {
                    Toast.makeText(DangKyGV.this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Lưu thông tin vào SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("AccountGV", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Ten", ten);
                editor.putString("MatKhau", matKhau);
                editor.putString("NganhDay", nganhDay);
                editor.apply();

                Toast.makeText(DangKyGV.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}