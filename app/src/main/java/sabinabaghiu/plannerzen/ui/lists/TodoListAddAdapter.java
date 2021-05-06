package sabinabaghiu.plannerzen.ui.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sabinabaghiu.plannerzen.R;
import sabinabaghiu.plannerzen.ui.today.Todo;

public class TodoListAddAdapter extends RecyclerView.Adapter<TodoListAddAdapter.ViewHolder> {

    private ArrayList<Todo> todos;

    TodoListAddAdapter(ArrayList<Todo> todos){
        this.todos = todos;
    }

    @NonNull
    @Override
    public TodoListAddAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.task_list_item_add, parent, false);
        return new TodoListAddAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListAddAdapter.ViewHolder holder, int position) {
        holder.title.setText(todos.get(position).getTitle() + "    " + todos.get(position).getTime() + "   " + todos.get(position).getCategory());
         if (todos.get(position).isImportant())
            holder.icon.setImageResource(R.drawable.ic_baseline_priority_high_24);
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView icon;


        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.task_add);
            icon = itemView.findViewById(R.id.icon_task_add);
        }
    }
}
