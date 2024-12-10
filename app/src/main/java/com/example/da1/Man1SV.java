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

import com.google.firebase.auth.FirebaseAuth;

public class Man1SV extends AppCompatActivity {

    private Button btnDiemDanh, btnLichHoc, btnBaiTap, btnHoTro;
    private ImageView iconnguoi, toolbarMan1;
    private static final int MENU_DANG_XUAT = R.id.menu_dang_xuat;
    private static final int MENU_THONG_BAO = R.id.menu_thong_bao;
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

        // Thêm xử lý sự kiện khi chọn mục trong PopupMenu
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case MENU_DANG_XUAT: // ID trong file menu_popup.xml
                    handleLogout(); // Xử lý đăng xuất
                    return true;
                case MENU_THONG_BAO:
                    return true;
                default:
                    return false;
            }
        });
        popupMenu.show();
    }
    // Hàm xử lý đăng xuất
    private void handleLogout() {
        // Đăng xuất khỏi Firebase Authentication
        FirebaseAuth.getInstance().signOut();

        // Chuyển về màn hình đăng nhập
        Intent intent = new Intent(Man1SV.this, DangNhapSV.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Xóa stack lịch sử
        startActivity(intent);
        finish(); // Đóng màn hiện tại
    }

}