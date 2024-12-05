package com.example.da1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DiemDanh1 extends AppCompatActivity {

    private Button btnDiemDanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diem_danh1);

        btnDiemDanh = findViewById(R.id.btnDiemDanh);

        btnDiemDanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy ngày hiện tại
                String today = DateUtils.getCurrentDate();

                // Lấy ngày điểm danh lần cuối từ SharedPreferences
                String lastAttendanceDate = AttendanceManager.getLastAttendanceDate(DiemDanh1.this);

                if (!today.equals(lastAttendanceDate)) {
                    // Nếu ngày điểm danh lần cuối khác ngày hiện tại
                    // Thực hiện chuyển đến màn hình DiemDanh2
                    Toast.makeText(DiemDanh1.this, "Điểm danh hôm qua, chuyển đến màn khôi phục!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DiemDanh1.this, DiemDanh2.class);
                    startActivity(intent);
                } else {
                    // Nếu đã điểm danh hôm nay
                    Toast.makeText(DiemDanh1.this, "Bạn đã điểm danh hôm nay!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
