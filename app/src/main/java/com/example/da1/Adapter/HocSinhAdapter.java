package com.example.da1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da1.Model.HocSinh;
import com.example.da1.R;

import java.util.List;

public class HocSinhAdapter extends RecyclerView.Adapter<HocSinhAdapter.ViewHolder> {
    private Context context;
    private List<HocSinh> hocSinhList;

    public HocSinhAdapter(Context context, List<HocSinh> hocSinhList) {
        this.context = context;
        this.hocSinhList = hocSinhList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hoc_sinh, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HocSinh hs = hocSinhList.get(position);
        holder.txtHoTen.setText("Họ Tên: " + hs.getHoTen());
        holder.txtMaHS.setText("Mã HS: " + hs.getMaSinhVien());
        holder.txtLop.setText("Lớp: " + hs.getThuocLop());
    }

    @Override
    public int getItemCount() {
        return hocSinhList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtHoTen, txtMaHS, txtLop;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHoTen = itemView.findViewById(R.id.txtHoTen);
            txtMaHS = itemView.findViewById(R.id.txtMaHS);
            txtLop = itemView.findViewById(R.id.txtLop);
        }
    }
}
