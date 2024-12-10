package com.example.da1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ThongTinCaNhanGV extends AppCompatActivity {
    ImageView navHomeTTCNGV, navScheduleTTCNGV;
    Button btnUpdate;
    TextView tvThongTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thong_tin_ca_nhan_gv);

        // Ánh xạ các thành phần
        navHomeTTCNGV = findViewById(R.id.navHomeTTCNGV);
        navScheduleTTCNGV = findViewById(R.id.navScheduleTTCNGV);
        btnUpdate = findViewById(R.id.btnUpdate);
        tvThongTin = findViewById(R.id.tvThongTin);

        // Chuyển màn hình
        navHomeTTCNGV.setOnClickListener(v -> {
            Intent intent = new Intent(ThongTinCaNhanGV.this, Man1_GV.class);
            startActivity(intent);
        });

//        navScheduleTTCNGV.setOnClickListener(v -> {
//            Intent intent = new Intent(ThongTinCaNhanGV.this, LichHoc.class);
//            startActivity(intent);
//        });

        // Xử lý sự kiện nút cập nhật thông tin
        btnUpdate.setOnClickListener(v -> {
            // Lấy dữ liệu từ các EditText
            EditText etHoTen = findViewById(R.id.etHoTen);
            EditText etQueQuan = findViewById(R.id.etQueQuan);
            EditText etMaGiangVien = findViewById(R.id.etMaGiangVien);
            EditText etNgaySinh = findViewById(R.id.etNgaySinh);
            EditText etMonGiangDay = findViewById(R.id.etNganhDay);

            String hoTen = etHoTen.getText().toString().trim();
            String queQuan = etQueQuan.getText().toString().trim();
            String maGiangVien = etMaGiangVien.getText().toString().trim();
            String ngaySinh = etNgaySinh.getText().toString().trim();
            String monGiangDay = etMonGiangDay.getText().toString().trim();

            // Kiểm tra dữ liệu rỗng
            if (hoTen.isEmpty() || queQuan.isEmpty() || maGiangVien.isEmpty() || ngaySinh.isEmpty() || monGiangDay.isEmpty()) {
                tvThongTin.setText("Vui lòng nhập đầy đủ thông tin!");
                return;
            }

            // Hiển thị thông tin dưới dạng chuỗi
            String thongTin = "Họ và tên: " + hoTen + "\n" +
                    "Quê quán: " + queQuan + "\n" +
                    "Mã Giảng Viên: " + maGiangVien + "\n" +
                    "Ngày Sinh: " + ngaySinh + "\n" +
                    "Môn Giảng Dạy: " + monGiangDay;

            // Cập nhật TextView hiển thị thông tin
            tvThongTin.setText(thongTin);
        });
    }
}
