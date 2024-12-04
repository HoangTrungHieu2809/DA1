package com.example.da1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class Man1_GV extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<String> classList;
    private ClassAdapter classAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man1_gv);

        recyclerView = findViewById(R.id.recycler_view);
        classList = new ArrayList<>();
        classAdapter = new ClassAdapter(classList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(classAdapter);

        // Tìm FAB trong layout
        FloatingActionButton fabAddClass = findViewById(R.id.fab_add_class);

        // Set sự kiện click cho FAB
        fabAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mở màn hình thêm lớp học
                Intent intent = new Intent(Man1_GV.this, ThemLop.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Lấy tên lớp từ Activity_ThemLop
            String className = data.getStringExtra("className");
            classList.add(className); // Thêm lớp vào danh sách
            classAdapter.notifyDataSetChanged(); // Cập nhật RecyclerView
        }
    }
}
