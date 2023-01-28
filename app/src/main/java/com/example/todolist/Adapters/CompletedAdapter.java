package com.example.todolist.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Models.Tasks;
import com.example.todolist.R;

import java.util.List;

public class CompletedAdapter extends RecyclerView.Adapter<CompletedAdapter.ViewHolder> {

    private List<Tasks> tasks_data;

    public CompletedAdapter(List<Tasks> tasks_data) {
        this.tasks_data = tasks_data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_completed_task,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int adapterPosition = holder.getAdapterPosition();
        Tasks tasks = tasks_data.get(adapterPosition);
        holder.text_title.setText(tasks.getTitle());
        holder.text_description.setText(tasks.getDescription());
        holder.text_date.setText(tasks.getDue_date());
    }

    @Override
    public int getItemCount() {
        return tasks_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text_title;
        public TextView text_description;
        public TextView text_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_title = itemView.findViewById(R.id.title_text);
            text_description = itemView.findViewById(R.id.to_do_description);
            text_date = itemView.findViewById(R.id.last_date);

        }
    }
}
