package com.example.da1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class ThongTinCaNhanGV extends AppCompatActivity {
    ImageView navHomeTTCNGV, navScheduleTTCNGV, navNotificationsTTCNGV;
    Button btnUpdate;
    TextView tvThongTin;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thong_tin_ca_nhan_gv);

        // Khởi tạo Firestore
        db = FirebaseFirestore.getInstance();

        // Ánh xạ các thành phần
        navHomeTTCNGV = findViewById(R.id.navHomeTTCNGV);
        navScheduleTTCNGV = findViewById(R.id.navScheduleTTCNGV);
        navNotificationsTTCNGV = findViewById(R.id.navNotificationsTTCNGV);
        btnUpdate = findViewById(R.id.btnUpdate);
        tvThongTin = findViewById(R.id.tvThongTin);

        // Chuyển màn hình
        navHomeTTCNGV.setOnClickListener(v -> {
            Intent intent = new Intent(ThongTinCaNhanGV.this, Man1_GV.class);
            startActivity(intent);
        });

        navNotificationsTTCNGV.setOnClickListener(v -> {
            Intent intent = new Intent(ThongTinCaNhanGV.this, ThongBaoGV.class);
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

            // Lưu thông tin vào Firestore
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("hoTen", hoTen);
            userInfo.put("queQuan", queQuan);
            userInfo.put("maGiangVien", maGiangVien);
            userInfo.put("ngaySinh", ngaySinh);
            userInfo.put("monGiangDay", monGiangDay);

            db.collection("ThongTinGiangVien")
                    .document(maGiangVien) // Đặt mã GV làm document ID
                    .set(userInfo, SetOptions.merge()) // Ghi dữ liệu
                    .addOnSuccessListener(unused -> {
                        Toast.makeText(ThongTinCaNhanGV.this, "Cập nhật thông tin thành công!", Toast.LENGTH_SHORT).show();

                        // Hiển thị thông tin dưới dạng chuỗi
                        String thongTin = "Họ và tên: " + hoTen + "\n" +
                                "Quê quán: " + queQuan + "\n" +
                                "Mã Sinh Viên: " + maGiangVien + "\n" +
                                "Ngày Sinh: " + ngaySinh + "\n" +
                                "Môn Giang Dạy: " + monGiangDay;

                        // Cập nhật TextView hiển thị thông tin
                        tvThongTin.setText(thongTin);
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(ThongTinCaNhanGV.this, "Cập nhật thất bại: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });
    }
}
