package com.example.da1;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import androidx.appcompat.app.AppCompatActivity;

public class Man1_GV extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man1_gv);

        // Lắng nghe sự kiện khi ấn vào menu_button
        ImageButton menuButton = findViewById(R.id.menu_button);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo PopupMenu và chỉ định vị trí để hiển thị
                PopupMenu popupMenu = new PopupMenu(Man1_GV.this, v);

                // Inflate menu từ menu.xml
                getMenuInflater().inflate(R.menu.toolbar_menu, popupMenu.getMenu());

                // Hiển thị menu
                popupMenu.show();
            }
        });
    }
}
