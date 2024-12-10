package com.example.da1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DiemDanh1 extends AppCompatActivity {

    private Button btnDiemDanh;
    private ImageView navHome, navSchedule, navNotifications, navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diem_danh1);

        btnDiemDanh = findViewById(R.id.btnDiemDanh);
        navHome = findViewById(R.id.navHome);
        navProfile = findViewById(R.id.navProfile);
        navSchedule = findViewById(R.id.navSchedule);

        // Lấy ngày hiện tại
        final String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        // Lấy ngày điểm danh lần cuối từ SharedPreferences
        final String lastAttendanceDate = AttendanceManager.getLastAttendanceDate(DiemDanh1.this);

        btnDiemDanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastAttendanceDate.isEmpty()) {
                    // Nếu chưa có ngày điểm danh trước đó, cho phép điểm danh lần đầu
                    Toast.makeText(DiemDanh1.this, "Điểm danh thành công!", Toast.LENGTH_SHORT).show();
                    // Lưu lại ngày điểm danh hôm nay
                    AttendanceManager.saveAttendanceDate(DiemDanh1.this, today);
                } else if (today.equals(lastAttendanceDate)) {
                    // Nếu đã điểm danh hôm nay
                    Toast.makeText(DiemDanh1.this, "Bạn đã điểm danh hôm nay rồi!", Toast.LENGTH_SHORT).show();
                } else {
                    // Nếu ấn vào ngày trước đó (không phải ngày hôm nay)
                    // Kiểm tra nếu ngày hôm qua so với ngày điểm danh trước đó
                    if (isYesterday(today, lastAttendanceDate)) {
                        // Nếu là ngày hôm qua, cho phép khôi phục điểm danh
                        Toast.makeText(DiemDanh1.this, "Điểm danh cho ngày hôm qua, chuyển đến màn hình khôi phục!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DiemDanh1.this, DiemDanh2.class);
                        startActivity(intent);
                    } else if (isTomorrow(today, lastAttendanceDate)) {
                        // Nếu người dùng cố gắng điểm danh cho ngày mai
                        Toast.makeText(DiemDanh1.this, "Chưa đến ngày để điểm danh lại!", Toast.LENGTH_SHORT).show();
                    } else {
                        // Nếu chưa đến ngày để điểm danh lại
                        Toast.makeText(DiemDanh1.this, "Chưa đến ngày để điểm danh lại!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiemDanh1.this, Man1SV.class);
                startActivity(intent);
            }
        });

        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiemDanh1.this, ThongTinCaNhan.class);
                startActivity(intent);
            }
        });

        navSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiemDanh1.this, LichHoc.class);
                startActivity(intent);
            }
        });
    }

    // Kiểm tra xem ngày hôm nay có phải là ngày hôm qua so với ngày điểm danh không
    private boolean isYesterday(String today, String lastAttendanceDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date todayDate = sdf.parse(today);
            Date lastDate = sdf.parse(lastAttendanceDate);

            // Kiểm tra xem ngày điểm danh trước có phải là ngày hôm qua không
            long timeDifference = todayDate.getTime() - lastDate.getTime();
            return timeDifference == 86400000L; // 1 ngày = 86400000 milliseconds
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Kiểm tra xem ngày hôm nay có phải là ngày mai so với ngày điểm danh không
    private boolean isTomorrow(String today, String lastAttendanceDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date todayDate = sdf.parse(today);
            Date lastDate = sdf.parse(lastAttendanceDate);

            // Kiểm tra xem ngày điểm danh có phải là ngày mai không
            long timeDifference = lastDate.getTime() - todayDate.getTime();
            return timeDifference == 86400000L; // 1 ngày = 86400000 milliseconds
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
