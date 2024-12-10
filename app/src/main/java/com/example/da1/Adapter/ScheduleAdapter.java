package com.example.da1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da1.Model.Schedule;
import com.example.da1.R;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {

    private List<Schedule> scheduleList;

    public ScheduleAdapter(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lich_hoc, parent, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        Schedule schedule = scheduleList.get(position);
        holder.tvSubject.setText(schedule.getSubject());
        holder.tvDate.setText(schedule.getDate());
        holder.tvRoom.setText(schedule.getRoom());
        holder.tvClassTime.setText(schedule.getClassTime());
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public static class ScheduleViewHolder extends RecyclerView.ViewHolder {
        TextView tvSubject, tvDate, tvRoom, tvClassTime;

        public ScheduleViewHolder(View itemView) {
            super(itemView);
            tvSubject = itemView.findViewById(R.id.tvTenMonHoc);
            tvDate = itemView.findViewById(R.id.tvNgay);
            tvRoom = itemView.findViewById(R.id.tvPhong);
            tvClassTime = itemView.findViewById(R.id.tvCaHoc);
        }
    }
}

