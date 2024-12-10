package com.example.da1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.view.MenuItem;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class Man1SV extends AppCompatActivity {

    private Button btnDiemDanh, btnLichHoc, btnBaiTap, btnHoTro;
    private ImageView iconnguoi, toolbarMan1, iconLich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man1_sv);

        btnDiemDanh = findViewById(R.id.btn_DiemDanh);
        btnLichHoc = findViewById(R.id.btn_LichHoc);
        btnBaiTap = findViewById(R.id.btn_BaiTap);
        btnHoTro = findViewById(R.id.btn_HoTro);
        iconnguoi = findViewById(R.id.iconNguoi);
        toolbarMan1 = findViewById(R.id.toolbarMan1);
        iconLich = findViewById(R.id.iconLich);

        btnDiemDanh.setOnClickListener(v -> {
            Intent intent = new Intent(Man1SV.this, DiemDanh1.class);
            startActivity(intent);
        });

        btnLichHoc.setOnClickListener(v -> {
            Intent intent = new Intent(Man1SV.this, LichHoc.class);
            startActivity(intent);
        });

        btnHoTro.setOnClickListener(v -> {
            Intent intent = new Intent(Man1SV.this, HoTroLienHe.class);
            startActivity(intent);
        });

        iconnguoi.setOnClickListener(v -> {
            Intent intent = new Intent(Man1SV.this, ThongTinCaNhan.class);
            startActivity(intent);
        });

        iconLich.setOnClickListener(v -> {
            Intent intent = new Intent(Man1SV.this, LichHoc.class);
            startActivity(intent);
        });


        toolbarMan1.setOnClickListener(v -> showPopupMenu(v));
    }

    // Hàm hiển thị PopupMenu
    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_popup, popupMenu.getMenu());
        popupMenu.show();

        // Xử lý sự kiện chọn item trong PopupMenu
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_dang_xuat) {
                showLogoutConfirmationDialog();
                return true;
            } else if (item.getItemId() == R.id.menu_thong_bao) {
                // Xử lý cho Thông Báo
                return true;
            } else if (item.getItemId() == R.id.action_news) {
                // Xử lý khi ấn vào Tin Tức
                showNewsDialog();
                return true;
            } else if (item.getItemId() == R.id.action_diem_theo_mon) {
                // Xử lý cho Lịch Sử Điểm Theo Môn
                return true;
            }
            return false;
        });
    }

    // Hàm hiển thị hộp thoại thông báo Tin Tức
    private void showNewsDialog() {
        String newsContent = "-Sáng nay sẽ có buổi chào cờ tại sân trường lúc 7:00 AM.\n\n" +
                "-Hôm nay sẽ có buổi trình diễn văn nghệ ở sân khấu trường lúc 3:00 PM.\n\n" +
                "-Hạn cuối nộp tiền học kì 2 Là 23h ngày 4-1-2025.\n";

        new AlertDialog.Builder(this)
                .setTitle("Tin Tức Hôm Nay")
                .setMessage(newsContent)  // Thông tin sự kiện trong ngày
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss()) // Nút OK để đóng hộp thoại
                .create()
                .show();
    }

    // Hàm hiển thị hộp thoại xác nhận khi đăng xuất
    private void showLogoutConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận đăng xuất")
                .setMessage("Bạn có chắc chắn muốn đăng xuất không?")
                .setPositiveButton("Đăng xuất", (dialog, which) -> {
                    // Đăng xuất khỏi Firebase
                    FirebaseAuth.getInstance().signOut();
                    // Chuyển hướng về màn hình đăng nhập
                    Intent intent = new Intent(Man1SV.this, DangNhapSV.class);
                    startActivity(intent);
                    finish(); // Đóng màn hình hiện tại
                })
                .setNegativeButton("Hủy", (dialog, which) -> {
                    // Đóng hộp thoại và không làm gì cả
                    dialog.dismiss();
                })
                .create()
                .show();
    }
}
