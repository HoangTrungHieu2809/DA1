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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da1.Adapter.NotificationAdapter;
import com.example.da1.Model.Notification;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ThongBaoGV extends AppCompatActivity {
    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private NotificationAdapter adapter;
    private List<Notification> notificationList;
    ImageView iconHomeGV, iconLichGV, iconNguoiGV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao_gv);

        //ánh xạ
        iconHomeGV = findViewById(R.id.iconHomeGV);
        iconLichGV = findViewById(R.id.iconLichGV);
        iconNguoiGV = findViewById(R.id.iconNguoiGV);

        // Khởi tạo Firestore
        db = FirebaseFirestore.getInstance();

        // Thiết lập RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        notificationList = new ArrayList<>();
        adapter = new NotificationAdapter(notificationList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        //chọn home
        iconHomeGV.setOnClickListener(v -> {
            Intent intent = new Intent(ThongBaoGV.this, Man1_GV.class);
            startActivity(intent);
        });
        //chọn lịch học trên menu
        iconLichGV.setOnClickListener(v -> {
            Intent intent = new Intent(ThongBaoGV.this, LichHoc.class);
            startActivity(intent);
        });
        //chọn thông tin cá nhân
        iconNguoiGV.setOnClickListener(v -> {
            Intent intent = new Intent(ThongBaoGV.this, ThongTinCaNhanGV.class);
            startActivity(intent);
        });
        // Tải dữ liệu thông báo
        fetchNotifications();

        // Floating Action Button - Thêm thông báo
        findViewById(R.id.fab_add_notification).setOnClickListener(view -> showAddNotificationDialog());
    }

    private void showAddNotificationDialog() {
        // Tạo dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_notification, null);
        builder.setView(dialogView);

        // Các trường nhập dữ liệu
        EditText etTitle = dialogView.findViewById(R.id.etTitle);
        EditText etContent = dialogView.findViewById(R.id.etContent);
        Button btnSave = dialogView.findViewById(R.id.btnSave);

        AlertDialog dialog = builder.create();

        // Xử lý khi nhấn nút Lưu
        btnSave.setOnClickListener(view -> {
            String title = etTitle.getText().toString().trim();
            String content = etContent.getText().toString().trim();

            if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) {
                Toast.makeText(this, "Vui lòng nhập tiêu đề và nội dung!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lưu thông báo vào Firestore
            saveNotification(title, content);
            dialog.dismiss();
        });

        dialog.show();
    }

    private void saveNotification(String title, String content) {
        Notification notification = new Notification(title, content);

        db.collection("ThongBaoGV")
                .add(notification)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Thêm thông báo thành công!", Toast.LENGTH_SHORT).show();
                    fetchNotifications(); // Cập nhật danh sách
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
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
