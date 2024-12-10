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

public class ThongTinCaNhan extends AppCompatActivity {
    ImageView navHomeTTCN;
    ImageView navScheduleTTCN;
    Button btnUpdate;
    TextView tvThongTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thong_tin_ca_nhan);

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
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ các EditText
                EditText etHoTen = findViewById(R.id.etHoTen);
                EditText etQueQuan = findViewById(R.id.etQueQuan);
                EditText etMaSinhVien = findViewById(R.id.etMaSinhVien);
                EditText etNgaySinh = findViewById(R.id.etNgaySinh);
                EditText etThuocLop  = findViewById(R.id.etSoThich);

                String hoTen = etHoTen.getText().toString().trim();
                String queQuan = etQueQuan.getText().toString().trim();
                String maSinhVien = etMaSinhVien.getText().toString().trim();
                String ngaySinh = etNgaySinh.getText().toString().trim();
                String thuocLop  = etThuocLop.getText().toString().trim();

                // Kiểm tra dữ liệu rỗng
                if (hoTen.isEmpty() || queQuan.isEmpty() || maSinhVien.isEmpty() || ngaySinh.isEmpty() || thuocLop.isEmpty()) {
                    tvThongTin.setText("Vui lòng nhập đầy đủ thông tin!");
                    return;
                }

                // Hiển thị thông tin dưới dạng chuỗi
                String thongTin = "Họ và tên: " + hoTen + "\n" +
                        "Quê quán: " + queQuan + "\n" +
                        "Mã Sinh Viên: " + maSinhVien + "\n" +
                        "Ngày Sinh: " + ngaySinh + "\n" +
                        "Sở thích: " + thuocLop;

                // Cập nhật TextView hiển thị thông tin
                tvThongTin.setText(thongTin);
            }
        });
    }
}
