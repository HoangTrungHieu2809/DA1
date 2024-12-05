package com.example.da1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DiemDanh2 extends AppCompatActivity {

    private Button btnKhoiPhuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diem_danh2);

        btnKhoiPhuc = findViewById(R.id.btnKhoiPhuc);

        btnKhoiPhuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến màn Khôi Phục
                Intent intent = new Intent(DiemDanh2.this, KhoiPhuc.class);
                startActivity(intent);
            }
        });
    }
}
