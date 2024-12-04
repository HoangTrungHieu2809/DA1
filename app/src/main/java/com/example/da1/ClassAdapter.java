package com.example.da1;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder>{
    private List<String> classList;

    public ClassAdapter(List<String> classList) {
        this.classList = classList;
    }

    @Override
    public ClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_item, parent, false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClassViewHolder holder, int position) {
        holder.className.setText(classList.get(position));
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }

    public static class ClassViewHolder extends RecyclerView.ViewHolder {

        TextView className;

        public ClassViewHolder(View itemView) {
            super(itemView);
            className = itemView.findViewById(R.id.class_name);
        }
    }
}
