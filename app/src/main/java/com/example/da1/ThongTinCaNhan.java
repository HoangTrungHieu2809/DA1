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

public class ThongTinCaNhan extends AppCompatActivity {
    ImageView navHomeTTCN;
    ImageView navScheduleTTCN;
    Button btnUpdate;
    TextView tvThongTin;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thong_tin_ca_nhan);

        // Khởi tạo Firestore
        db = FirebaseFirestore.getInstance();

        // Ánh xạ các thành phần
        navHomeTTCN = findViewById(R.id.navHomeTTCN);
        navScheduleTTCN = findViewById(R.id.navScheduleTTCN);
        btnUpdate = findViewById(R.id.btnUpdate);
        tvThongTin = findViewById(R.id.tvThongTin);

        // Chuyển màn hình
        navHomeTTCN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongTinCaNhan.this, Man1SV.class);
                startActivity(intent);
            }
        });

        navScheduleTTCN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongTinCaNhan.this, LichHoc.class);
                startActivity(intent);
            }
        });

        // Xử lý sự kiện nút cập nhật thông tin
        btnUpdate.setOnClickListener(v -> {
            // Lấy dữ liệu từ các EditText
            EditText etHoTen = findViewById(R.id.etHoTen);
            EditText etQueQuan = findViewById(R.id.etQueQuan);
            EditText etMaSinhVien = findViewById(R.id.etMaSinhVien);
            EditText etNgaySinh = findViewById(R.id.etNgaySinh);
            EditText etThuocLop = findViewById(R.id.etThuocLop);

            String hoTen = etHoTen.getText().toString().trim();
            String queQuan = etQueQuan.getText().toString().trim();
            String maSinhVien = etMaSinhVien.getText().toString().trim();
            String ngaySinh = etNgaySinh.getText().toString().trim();
            String thuocLop = etThuocLop.getText().toString().trim();

            // Kiểm tra dữ liệu rỗng
            if (hoTen.isEmpty() || queQuan.isEmpty() || maSinhVien.isEmpty() || ngaySinh.isEmpty() || thuocLop.isEmpty()) {
                tvThongTin.setText("Vui lòng nhập đầy đủ thông tin!");
                return;
            }

            // Lưu thông tin vào Firestore
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("hoTen", hoTen);
            userInfo.put("queQuan", queQuan);
            userInfo.put("maSinhVien", maSinhVien);
            userInfo.put("ngaySinh", ngaySinh);
            userInfo.put("soThich", thuocLop);

            db.collection("ThongTinSinhVien")
                    .document(maSinhVien) // Đặt mã sinh viên làm document ID
                    .set(userInfo, SetOptions.merge()) // Ghi dữ liệu
                    .addOnSuccessListener(unused -> {
                        Toast.makeText(ThongTinCaNhan.this, "Cập nhật thông tin thành công!", Toast.LENGTH_SHORT).show();

                        // Hiển thị thông tin dưới dạng chuỗi
                        String thongTin = "Họ và tên: " + hoTen + "\n" +
                                "Quê quán: " + queQuan + "\n" +
                                "Mã Sinh Viên: " + maSinhVien + "\n" +
                                "Ngày Sinh: " + ngaySinh + "\n" +
                                "Thuộc Lớp: " + thuocLop;

                        // Cập nhật TextView hiển thị thông tin
                        tvThongTin.setText(thongTin);
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(ThongTinCaNhan.this, "Cập nhật thất bại: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });
    }
}
