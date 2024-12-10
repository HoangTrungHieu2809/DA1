package com.example.da1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da1.Adapter.ScheduleAdapter;
import com.example.da1.Model.Schedule;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class LichHoc extends AppCompatActivity {
    private FirebaseFirestore db;
    ImageView navProfile, navHome;
    private RecyclerView recyclerView;
    private ScheduleAdapter adapter;
    private List<Schedule> scheduleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lich_hoc);



        navProfile = findViewById(R.id.navProfile);
        navHome = findViewById(R.id.navHome);

        recyclerView = findViewById(R.id.recyclerViewSchedule);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        scheduleList = new ArrayList<>();
        adapter = new ScheduleAdapter(scheduleList);
        recyclerView.setAdapter(adapter);

        // Khởi tạo Firestore
        db = FirebaseFirestore.getInstance();

        // Gọi hàm lấy dữ liệu
        fetchScheduleFromFirestore();

        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LichHoc.this, ThongTinCaNhan.class);
                startActivity(intent);
            }
        });

        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LichHoc.this, Man1SV.class);
                startActivity(intent);
            }
        });
    }
    private void fetchScheduleFromFirestore() {
        db.collection("Schedule")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        scheduleList.clear(); // Xóa dữ liệu cũ nếu có
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Lấy dữ liệu từng document
                            String subject = document.getString("subject");
                            String date = document.getString("date");
                            String room = document.getString("room");
                            String classTime = document.getString("classTime");

                            // Thêm vào danh sách
                            scheduleList.add(new Schedule(subject, date, room, classTime));
                        }
                        // Cập nhật RecyclerView
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.e("FirestoreError", "Error getting documents: ", task.getException());
                    }
                });
    }
}