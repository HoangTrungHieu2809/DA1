package com.example.da1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class Man1_GV extends AppCompatActivity {
    private ImageView iconHomeGV, iconLichGV, iconLogoGV,iconNguoiGV;

    private ImageView navHomeTTCNGV, navScheduleTTCNGV, navNotificationsTTCNGV, navProfileTTCNGV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man1_gv);

        //ánh xạ
        iconLichGV = findViewById(R.id.iconLichGV);
        iconLogoGV = findViewById(R.id.iconLogoGV);

        // Lắng nghe sự kiện khi ấn vào menu_button
        ImageButton menuButton = findViewById(R.id.menu_button);

        iconLichGV.setOnClickListener(v -> {
            Intent intent = new Intent(Man1_GV.this, LichHoc.class);
            startActivity(intent);
        });
        iconLogoGV.setOnClickListener(v -> {
            Intent intent = new Intent(Man1_GV.this, ThongBaoGV.class);
            startActivity(intent);
        });

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo PopupMenu và chỉ định vị trí để hiển thị
                PopupMenu popupMenu = new PopupMenu(Man1_GV.this, v);

                // Inflate menu từ menu.xml
                getMenuInflater().inflate(R.menu.toolbar_menu, popupMenu.getMenu());

                // Thiết lập sự kiện khi người dùng chọn mục trong menu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // Kiểm tra ID của item được chọn
                        if (item.getItemId() == R.id.menu_news) {
                            // Xử lý mục "Tin Tức"
                            showNewsDialog();
                            return true;
                        } else if (item.getItemId() == R.id.menu_assignment) {
                            // Xử lý mục "Bài Tập"
                            return true;
                        } else if (item.getItemId() == R.id.menu_logout) {
                            // Xử lý mục "Đăng Xuất"
                            showLogoutConfirmationDialog();
                            return true;
                        }
                        return false;
                    }
                });

                // Hiển thị menu
                popupMenu.show();
            }
        });

        // Xử lý sự kiện click cho các nút lớp học
        findViewById(R.id.btn_10A1).setOnClickListener(v -> openAddStudentScreen("10A1"));
        findViewById(R.id.btn_10A2).setOnClickListener(v -> openAddStudentScreen("10A2"));
        findViewById(R.id.btn_10A3).setOnClickListener(v -> openAddStudentScreen("10A3"));
        findViewById(R.id.btn_11A1).setOnClickListener(v -> openAddStudentScreen("11A1"));
        findViewById(R.id.btn_11A2).setOnClickListener(v -> openAddStudentScreen("11A2"));
        findViewById(R.id.btn_11A3).setOnClickListener(v -> openAddStudentScreen("11A3"));
        findViewById(R.id.btn_12A1).setOnClickListener(v -> openAddStudentScreen("12A1"));
        findViewById(R.id.btn_12A2).setOnClickListener(v -> openAddStudentScreen("12A2"));
        findViewById(R.id.btn_12A3).setOnClickListener(v -> openAddStudentScreen("12A3"));

        // Xử lý sự kiện khi ấn vào biểu tượng "Người" (Profile)
        ImageView iconNguoiGV = findViewById(R.id.iconNguoiGV);
        iconNguoiGV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mở màn hình Thông tin cá nhân giảng viên
                Intent intent = new Intent(Man1_GV.this, ThongTinCaNhanGV.class);
                startActivity(intent);
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
        String newsContent = "-Sáng nay sẽ có buổi chào cờ tại sân trường lúc 7:00 AM.\n\n" +
                "-Hôm nay sẽ có buổi trình diễn văn nghệ ở sân khấu trường lúc 3:00 PM.\n\n" +
                "-Ngày 20-12-2024 lúc 8:00 AM sẽ họp giao ban tại Phòng Hội Trường CĐ FPT.\n";

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
                    Intent intent = new Intent(Man1_GV.this, DangNhapGV.class);
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
