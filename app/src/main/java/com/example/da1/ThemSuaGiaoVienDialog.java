package com.example.da1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class ThemSuaGiaoVienDialog extends Dialog {
    private EditText edtHoTen, edtQueQuan, edtMaGiangVien, edtNgaySinh, edtMonGiangDay;
    private Button btnLuu, btnHuy;
    private FirebaseFirestore db;
    private String giaoVienId;
    private Runnable refreshData;

    public ThemSuaGiaoVienDialog(@NonNull Context context, String giaoVienId, Runnable refreshData) {
        super(context);
        this.giaoVienId = giaoVienId;
        this.refreshData = refreshData;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_them_sua);

        edtHoTen = findViewById(R.id.edtHoTen);
        edtQueQuan = findViewById(R.id.edtQueQuan);
        edtMaGiangVien = findViewById(R.id.edtMaGiangVien);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);
        edtMonGiangDay = findViewById(R.id.edtMonGiangDay);
        btnLuu = findViewById(R.id.btnLuu);
        btnHuy = findViewById(R.id.btnHuy);
        db = FirebaseFirestore.getInstance();

        if (giaoVienId != null) {
            db.collection("ThongTinGiangVien").document(giaoVienId).get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            edtHoTen.setText(documentSnapshot.getString("hoTen"));
                            edtQueQuan.setText(documentSnapshot.getString("queQuan"));
                            edtMaGiangVien.setText(giaoVienId); // ID chính là mã giảng viên
                            edtNgaySinh.setText(documentSnapshot.getString("ngaySinh"));
                            edtMonGiangDay.setText(documentSnapshot.getString("monGiangDay"));
                            edtMaGiangVien.setEnabled(false); // Không cho phép sửa mã GV
                        }
                    });
        }

        btnLuu.setOnClickListener(v -> saveGiaoVien());
        btnHuy.setOnClickListener(v -> dismiss());
    }

    private void saveGiaoVien() {
        String hoTen = edtHoTen.getText().toString().trim();
        String queQuan = edtQueQuan.getText().toString().trim();
        String maGiangVien = edtMaGiangVien.getText().toString().trim();
        String ngaySinh = edtNgaySinh.getText().toString().trim();
        String monGiangDay = edtMonGiangDay.getText().toString().trim();

        if (hoTen.isEmpty() || queQuan.isEmpty() || maGiangVien.isEmpty() || ngaySinh.isEmpty() || monGiangDay.isEmpty()) {
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> data = new HashMap<>();
        data.put("hoTen", hoTen);
        data.put("queQuan", queQuan);
        data.put("maGiangVien", maGiangVien);
        data.put("ngaySinh", ngaySinh);
        data.put("monGiangDay", monGiangDay);

        db.collection("ThongTinGiangVien").document(maGiangVien) // Sử dụng mã GV làm ID
                .set(data)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(getContext(), giaoVienId == null ? "Thêm thành công" : "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    dismiss();
                    refreshData.run();
                })
                .addOnFailureListener(e -> Toast.makeText(getContext(), "Lỗi lưu dữ liệu", Toast.LENGTH_SHORT).show());
    }
}
