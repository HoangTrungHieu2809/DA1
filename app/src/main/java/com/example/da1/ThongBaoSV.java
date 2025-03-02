package com.example.da1;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da1.Adapter.NotificationAdapter;
import com.example.da1.Model.Notification;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ThongBaoSV extends AppCompatActivity {

    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private NotificationAdapter adapter;
    private List<Notification> notificationList;
    ImageView navProfile, navHome, navSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao_sv);

        //ánh xạ
        navHome = findViewById(R.id.navHome);
        navSchedule = findViewById(R.id.navSchedule);
        navProfile = findViewById(R.id.navProfile);

        // Khởi tạo Firestore
        db = FirebaseFirestore.getInstance();

        // Thiết lập RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        notificationList = new ArrayList<>();
        adapter = new NotificationAdapter(notificationList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        //chọn home
        navHome.setOnClickListener(v -> {
            Intent intent = new Intent(ThongBaoSV.this, Man1SV.class);
            startActivity(intent);
        });
        //chọn lịch học trên menu
        navSchedule.setOnClickListener(v -> {
            Intent intent = new Intent(ThongBaoSV.this, LichHoc.class);
            startActivity(intent);
        });
        //chọn thông tin cá nhân
        navProfile.setOnClickListener(v -> {
            Intent intent = new Intent(ThongBaoSV.this, ThongTinCaNhan.class);
            startActivity(intent);
        });
        // Tải dữ liệu thông báo
        fetchNotifications();

    }


    private void fetchNotifications() {
        db.collection("ThongBaoGV")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    notificationList.clear();
                    for (DocumentSnapshot document : queryDocumentSnapshots) {
                        Notification notification = document.toObject(Notification.class);
                        notificationList.add(notification);
                        // Log thông tin thông báo
                        Log.d("Firestore", "Title: " + notification.getTitle() + ", Content: " + notification.getContent());
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Lỗi khi tải thông báo: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("FirestoreError", e.getMessage(), e);
                });
    }
}