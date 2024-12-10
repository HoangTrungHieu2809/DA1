package com.example.da1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DiemDanh2 extends AppCompatActivity {

    private Button btnKhoiPhuc;
    private ImageView navHome, navSchedule, navNotifications, navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diem_danh2);

        btnKhoiPhuc = findViewById(R.id.btnKhoiPhuc);
        navHome = findViewById(R.id.navHome);
        navProfile = findViewById(R.id.navProfile);

        btnKhoiPhuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến màn Khôi Phục
                Intent intent = new Intent(DiemDanh2.this, KhoiPhuc.class);
                startActivity(intent);
            }
        });

        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiemDanh2.this, Man1SV.class);
                startActivity(intent);
            }
        });

        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiemDanh2.this, ThongTinCaNhan.class);
                startActivity(intent);
            }
        });
    }
}
