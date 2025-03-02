package com.example.da1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da1.Model.GiaoVien;
import com.example.da1.R;
import com.example.da1.ThemSuaGiaoVienDialog;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.List;

public class GiaoVienAdapter extends RecyclerView.Adapter<GiaoVienAdapter.ViewHolder> {
    private Context context;
    private List<GiaoVien> giaoVienList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Runnable refreshData;

    public GiaoVienAdapter(Context context, List<GiaoVien> giaoVienList, Runnable refreshData) {
        this.context = context;
        this.giaoVienList = giaoVienList;
        this.refreshData = refreshData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_giao_vien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GiaoVien gv = giaoVienList.get(position);
        holder.txtHoTen.setText("Họ Tên: " + gv.getHoTen());
        holder.txtQueQuan.setText("Quê Quán: " + gv.getQueQuan());
        holder.txtMaGiangVien.setText("Mã GV: " + gv.getMaGiangVien());
        holder.txtNgaySinh.setText("Ngày Sinh: " + gv.getNgaySinh());
        holder.txtMonGiangDay.setText("Môn Giảng Dạy: " + gv.getMonGiangDay());

        // Sửa thông tin giáo viên
        holder.btnSua.setOnClickListener(v -> {
            ThemSuaGiaoVienDialog dialog = new ThemSuaGiaoVienDialog(context, gv.getId(), refreshData);
            dialog.show();
        });

        // Xóa giáo viên
        holder.btnXoa.setOnClickListener(v -> {
            db.collection("ThongTinGiangVien").document(gv.getId())
                    .delete()
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        refreshData.run();
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(context, "Lỗi khi xóa", Toast.LENGTH_SHORT).show()
                    );
        });
    }

    @Override
    public int getItemCount() {
        return giaoVienList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtHoTen, txtQueQuan, txtMaGiangVien, txtNgaySinh, txtMonGiangDay;
        Button btnSua, btnXoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHoTen = itemView.findViewById(R.id.txtHoTen);
            txtQueQuan = itemView.findViewById(R.id.txtQueQuan);
            txtMaGiangVien = itemView.findViewById(R.id.txtMaGiangVien);
            txtNgaySinh = itemView.findViewById(R.id.txtNgaySinh);
            txtMonGiangDay = itemView.findViewById(R.id.txtMonGiangDay);
            btnSua = itemView.findViewById(R.id.btnSua);
            btnXoa = itemView.findViewById(R.id.btnXoa);
        }
    }
}
