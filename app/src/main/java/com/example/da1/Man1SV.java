package com.example.da1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Man1SV extends AppCompatActivity {

    private Button btnDiemDanh, btnLichHoc, btnBaiTap, btnHoTro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_man1_sv);

        btnDiemDanh = findViewById(R.id.btn_DiemDanh);
        btnLichHoc = findViewById(R.id.btn_LichHoc);
        btnBaiTap = findViewById(R.id.btn_BaiTap);
        btnHoTro = findViewById(R.id.btn_HoTro);

        btnDiemDanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Man1SV.this, DiemDanh1.class);
                startActivity(intent);
            }
        });

        btnLichHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Man1SV.this, LichHoc.class);
                startActivity(intent);
            }
        });
    }
}