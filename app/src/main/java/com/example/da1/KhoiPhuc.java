package com.example.da1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class KhoiPhuc extends AppCompatActivity {

    private EditText etHoTen, etMaHS, etMonKhoiPhuc, etLyDo;
    private Button btnGuiXacNhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_khoi_phuc);

        etHoTen = findViewById(R.id.etHoTen);
        etMaHS = findViewById(R.id.etMaHS);
        etMonKhoiPhuc = findViewById(R.id.etMonKhoiPhuc);
        etLyDo = findViewById(R.id.etLyDo);
        btnGuiXacNhan = findViewById(R.id.btnGuiXacNhan);

        btnGuiXacNhan.setOnClickListener(v -> {
            String hoTen = etHoTen.getText().toString();
            String maHS = etMaHS.getText().toString();
            String mon = etMonKhoiPhuc.getText().toString();
            String lyDo = etLyDo.getText().toString();

            if (!hoTen.isEmpty() && !maHS.isEmpty() && !mon.isEmpty() && !lyDo.isEmpty()) {
                Toast.makeText(KhoiPhuc.this, "Khôi phục thành công!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(KhoiPhuc.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}