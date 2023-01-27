package com.example.todolist.Adapters;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Models.Tasks;
import com.example.todolist.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHoder> {

    public interface CheckboxCheckedChangeListener {
        void onCheckedChanged(int position, boolean isChecked);
    }

    CheckboxCheckedChangeListener listener;
    private List<Tasks> tasks_data;
    //private ImageButton buttonOption;

    public MyAdapter(CheckboxCheckedChangeListener listener, List<Tasks> tasks_data) {
        this.listener = listener;
        this.tasks_data = tasks_data;
        // this.buttonOption = buttonOption;
    }

    @NonNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_task_row, parent, false);
        return new MyViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoder holder, int position) {
        int adapterPosition = holder.getAdapterPosition();
        Tasks task = tasks_data.get(adapterPosition);
        holder.text_title.setText(task.getTitle());
        holder.text_description.setText(task.getDescription());
        holder.text_date.setText(task.getDue_date());
        holder.finish_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (listener != null) {
                    listener.onCheckedChanged(holder.getAdapterPosition(), b);
                }
            }
        });
        holder.buttonOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showButton(view, adapterPosition);

            }

            private void showButton(View view, int adapterPosition) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.delete_item:
                                Toast.makeText(menuItem.getActionView().getContext(), "Deleted", Toast.LENGTH_SHORT).show();

                            case R.id.edit_item:
                                Toast.makeText(menuItem.getActionView().getContext(), "Updated", Toast.LENGTH_SHORT).show();

                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }

        });
    }

    @Override
    public int getItemCount() {
        return tasks_data.size();
    }


    public class MyViewHoder extends RecyclerView.ViewHolder {
        public TextView text_title;
        public TextView text_description;
        public TextView text_date;
        public CheckBox finish_check;
        public ImageButton buttonOption;

        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            finish_check = itemView.findViewById(R.id.finish_tasks);
            text_title = itemView.findViewById(R.id.title_text);
            text_description = itemView.findViewById(R.id.to_do_description);
            text_date = itemView.findViewById(R.id.last_date);
            buttonOption = itemView.findViewById(R.id.task_options);
        }
    }
}
