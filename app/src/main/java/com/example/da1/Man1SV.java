package com.example.da1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Man1SV extends AppCompatActivity {

    private Button btnDiemDanh, btnLichHoc, btnBaiTap, btnHoTro;
    private ImageView iconnguoi, toolbarMan1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_man1_sv);

        btnDiemDanh = findViewById(R.id.btn_DiemDanh);
        btnLichHoc = findViewById(R.id.btn_LichHoc);
        btnBaiTap = findViewById(R.id.btn_BaiTap);
        btnHoTro = findViewById(R.id.btn_HoTro);
        iconnguoi =findViewById(R.id.iconNguoi);
        toolbarMan1 = findViewById(R.id.toolbarMan1);


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

        btnHoTro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Man1SV.this, HoTroLienHe.class);
                startActivity(intent);
            }
        });

        iconnguoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Man1SV.this, ThongTinCaNhan.class);
                startActivity(intent);
            }
        });
        toolbarMan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });
    }

    // Hàm hiển thị PopupMenu
    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_popup, popupMenu.getMenu()); // Kết nối file menu_popup.xml
        popupMenu.show();

    }
}