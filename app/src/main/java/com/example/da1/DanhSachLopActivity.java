package com.example.da1;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da1.Adapter.HocSinhAdapter;
import com.example.da1.Model.HocSinh;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class DanhSachLopActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseFirestore db;
    private HocSinhAdapter adapter;
    private List<HocSinh> hocSinhList;
    private String className;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_lop);

        recyclerView = findViewById(R.id.recyclerHocSinh);
        db = FirebaseFirestore.getInstance();
        hocSinhList = new ArrayList<>();
        adapter = new HocSinhAdapter(this, hocSinhList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        className = getIntent().getStringExtra("class_name");
        if (className != null) {
            loadHocSinh();
        } else {
            Toast.makeText(this, "Không tìm thấy tên lớp", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadHocSinh() {
        db.collection("ThongTinSinhVien").whereEqualTo("thuocLop", className).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        hocSinhList.clear();
                        for (QueryDocumentSnapshot doc : task.getResult()) {
                            HocSinh hs = doc.toObject(HocSinh.class);
                            hocSinhList.add(hs);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Lỗi khi tải danh sách sinh viên", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
