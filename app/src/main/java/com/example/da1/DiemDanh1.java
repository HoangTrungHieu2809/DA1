package com.example.da1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DiemDanh1 extends AppCompatActivity {

    private Button btnDiemDanh, btnChooseDate;
    private ImageView navHome, navSchedule, navNotifications, navProfile;
    private String selectedDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diem_danh1);

        btnDiemDanh = findViewById(R.id.btnDiemDanh);
        btnChooseDate = findViewById(R.id.btnChooseDate); // Nút chọn ngày
        navHome = findViewById(R.id.navHome);
        navProfile = findViewById(R.id.navProfile);
        navSchedule = findViewById(R.id.navSchedule);

        // Hiển thị DatePickerDialog để chọn ngày
        btnChooseDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    DiemDanh1.this,
                    (view, year1, month1, dayOfMonth) -> {
                        // Cập nhật ngày được chọn
                        Calendar selectedCalendar = Calendar.getInstance();
                        selectedCalendar.set(year1, month1, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        selectedDate = sdf.format(selectedCalendar.getTime());
                        Toast.makeText(DiemDanh1.this, "Ngày được chọn: " + selectedDate, Toast.LENGTH_SHORT).show();
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });

        btnDiemDanh.setOnClickListener(v -> {
            if (selectedDate.isEmpty()) {
                Toast.makeText(DiemDanh1.this, "Vui lòng chọn ngày trước khi điểm danh!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lấy ngày điểm danh lần cuối từ SharedPreferences
            final String lastAttendanceDate = AttendanceManager.getLastAttendanceDate(DiemDanh1.this);

            if (lastAttendanceDate.isEmpty()) {
                // Nếu chưa có ngày điểm danh trước đó
                Toast.makeText(DiemDanh1.this, "Điểm danh thành công!", Toast.LENGTH_SHORT).show();
                AttendanceManager.saveAttendanceDate(DiemDanh1.this, selectedDate);
            } else if (selectedDate.equals(lastAttendanceDate)) {
                // Nếu đã điểm danh ngày này
                Toast.makeText(DiemDanh1.this, "Bạn đã điểm danh ngày này rồi!", Toast.LENGTH_SHORT).show();
            } else if (isYesterday(selectedDate, lastAttendanceDate)) {
                // Nếu là ngày hôm qua, chuyển sang màn hình khôi phục
                Toast.makeText(DiemDanh1.this, "Điểm danh cho ngày hôm qua, chuyển đến màn hình khôi phục!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DiemDanh1.this, DiemDanh2.class);
                startActivity(intent);
            } else if (isTomorrow(selectedDate, lastAttendanceDate)) {
                // Nếu là ngày mai
                Toast.makeText(DiemDanh1.this, "Chưa đến ngày để điểm danh lại!", Toast.LENGTH_SHORT).show();
            } else {
                // Trường hợp khác
                Toast.makeText(DiemDanh1.this, "Ngày được chọn không hợp lệ để điểm danh!", Toast.LENGTH_SHORT).show();
            }
        });

        // Điều hướng
        navHome.setOnClickListener(v -> {
            Intent intent = new Intent(DiemDanh1.this, Man1SV.class);
            startActivity(intent);
        });

        navProfile.setOnClickListener(v -> {
            Intent intent = new Intent(DiemDanh1.this, ThongTinCaNhan.class);
            startActivity(intent);
        });

        navSchedule.setOnClickListener(v -> {
            Intent intent = new Intent(DiemDanh1.this, LichHoc.class);
            startActivity(intent);
        });
    }

    private boolean isYesterday(String selectedDate, String lastAttendanceDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date selected = sdf.parse(selectedDate);
            Date lastDate = sdf.parse(lastAttendanceDate);

            long timeDifference = selected.getTime() - lastDate.getTime();
            return timeDifference == 86400000L; // 1 ngày = 86400000 milliseconds
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isTomorrow(String selectedDate, String lastAttendanceDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date selected = sdf.parse(selectedDate);
            Date lastDate = sdf.parse(lastAttendanceDate);

            long timeDifference = lastDate.getTime() - selected.getTime();
            return timeDifference == 86400000L; // 1 ngày = 86400000 milliseconds
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
