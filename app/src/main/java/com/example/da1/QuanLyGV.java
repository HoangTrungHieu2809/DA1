package com.example.da1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da1.Adapter.GiaoVienAdapter;
import com.example.da1.Model.GiaoVien;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class QuanLyGV extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btnThemGiaoVien;
    private FirebaseFirestore db;
    private GiaoVienAdapter adapter;
    private List<GiaoVien> giaoVienList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_gv);

        recyclerView = findViewById(R.id.recyclerGiaoVien);
        btnThemGiaoVien = findViewById(R.id.btnThemGiaoVien);
        db = FirebaseFirestore.getInstance();
        giaoVienList = new ArrayList<>();
        adapter = new GiaoVienAdapter(this, giaoVienList, this::loadGiaoVien);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnThemGiaoVien.setOnClickListener(v -> {
            ThemSuaGiaoVienDialog dialog = new ThemSuaGiaoVienDialog(this, null, this::loadGiaoVien);
            dialog.show();
        });

        loadGiaoVien();
    }

    private void loadGiaoVien() {
        db.collection("ThongTinGiangVien").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                giaoVienList.clear();
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    GiaoVien gv = new GiaoVien();
                    gv.setId(doc.getId()); // ID chính là mã giảng viên
                    gv.setHoTen(doc.getString("hoTen"));
                    gv.setQueQuan(doc.getString("queQuan"));
                    gv.setMaGiangVien(doc.getId()); // Gán ID vào mã giảng viên
                    gv.setNgaySinh(doc.getString("ngaySinh"));
                    gv.setMonGiangDay(doc.getString("monGiangDay"));
                    giaoVienList.add(gv);
                }
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Lỗi khi tải dữ liệu!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}