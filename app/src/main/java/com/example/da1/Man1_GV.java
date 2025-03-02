package com.example.da1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class Man1_GV extends AppCompatActivity {
    private ImageView iconHomeGV, iconLichGV, iconLogoGV, iconNguoiGV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man1_gv);

        // Ánh xạ các view
        iconLichGV = findViewById(R.id.iconLichGV);
        iconLogoGV = findViewById(R.id.iconLogoGV);
        iconNguoiGV = findViewById(R.id.iconNguoiGV);

        // Xử lý sự kiện trên Toolbar
        ImageButton menuButton = findViewById(R.id.menu_button);
        menuButton.setOnClickListener(this::showPopupMenu);

        // Lắng nghe sự kiện khi ấn vào các lớp
        findViewById(R.id.btn_10A1).setOnClickListener(v -> openAddStudentScreen("10A1"));
        findViewById(R.id.btn_10A2).setOnClickListener(v -> openAddStudentScreen("10A2"));
        findViewById(R.id.btn_10A3).setOnClickListener(v -> openAddStudentScreen("10A3"));
        findViewById(R.id.btn_11A1).setOnClickListener(v -> openAddStudentScreen("11A1"));
        findViewById(R.id.btn_11A2).setOnClickListener(v -> openAddStudentScreen("11A2"));
        findViewById(R.id.btn_11A3).setOnClickListener(v -> openAddStudentScreen("11A3"));
        findViewById(R.id.btn_12A1).setOnClickListener(v -> openAddStudentScreen("12A1"));
        findViewById(R.id.btn_12A2).setOnClickListener(v -> openAddStudentScreen("12A2"));
        findViewById(R.id.btn_12A3).setOnClickListener(v -> openAddStudentScreen("12A3"));

        // Lắng nghe sự kiện khi ấn vào biểu tượng "Người" (Profile)
        iconNguoiGV.setOnClickListener(v -> {
            Intent intent = new Intent(Man1_GV.this, ThongTinCaNhanGV.class);
            startActivity(intent);
        });

        // Lắng nghe sự kiện khi ấn vào các biểu tượng khác
        iconLichGV.setOnClickListener(v -> {
            Intent intent = new Intent(Man1_GV.this, LichDay.class);
            startActivity(intent);
        });
        iconLogoGV.setOnClickListener(v -> {
            Intent intent = new Intent(Man1_GV.this, ThongBaoGV.class);
            startActivity(intent);
        });
    }

    // Hàm hiển thị PopupMenu
    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, popupMenu.getMenu());
        popupMenu.show();

        // Xử lý sự kiện chọn item trong PopupMenu
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_logout) {
                showLogoutConfirmationDialog();
                return true;
            } else if (item.getItemId() == R.id.menu_assignment) {
                Intent intentThongBao = new Intent(Man1_GV.this, ThongBaoGV.class);
                startActivity(intentThongBao);
                return true;
            } else if (item.getItemId() == R.id.menu_news) {
                showNewsDialog();
                return true;
            } else {
                return false;
            }
        });
    }

    // Hàm mở màn hình thêm sinh viên
    private void openAddStudentScreen(String className) {
        Intent intent = new Intent(Man1_GV.this, AddStudent.class);
        intent.putExtra("class_name", className); // Truyền tên lớp
        startActivity(intent);
    }

    // Hàm hiển thị hộp thoại thông báo Tin Tức
    private void showNewsDialog() {
        String newsContent = "- Sáng nay sẽ có buổi chào cờ tại sân trường lúc 7:00 AM.\n\n" +
                "- Hôm nay sẽ có buổi trình diễn văn nghệ ở sân khấu trường lúc 3:00 PM.\n\n" +
                "- Ngày 20-12-2024 lúc 8:00 AM sẽ họp giao ban tại Phòng Hội Trường CĐ FPT.\n";

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
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(Man1_GV.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Đóng màn hình hiện tại
                })
                .setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }
}
