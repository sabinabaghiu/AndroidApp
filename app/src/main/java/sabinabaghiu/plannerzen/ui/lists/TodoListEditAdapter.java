package sabinabaghiu.plannerzen.ui.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sabinabaghiu.plannerzen.R;
import sabinabaghiu.plannerzen.ui.today.Todo;

public class TodoListEditAdapter extends RecyclerView.Adapter<TodoListEditAdapter.ViewHolder> {

    private ArrayList<Todo> todos;

    TodoListEditAdapter(ArrayList<Todo> todos){
        this.todos = todos;
    }

    @NonNull
    @Override
    public TodoListEditAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.task_list_item_edit, parent, false);
        return new TodoListEditAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListEditAdapter.ViewHolder holder, int position) {
        holder.title.setText(todos.get(position).getTitle());holder.title.setText(todos.get(position).getTitle() + "    " + todos.get(position).getTime() + "   " + todos.get(position).getCategory());
        holder.isImp.setChecked(todos.get(position).isImportant());
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        Switch isImp;


        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.task_edit);
            isImp = itemView.findViewById(R.id.switch_task_edit);
            isImp = itemView.findViewById(R.id.switch_task_add);
        }
    }
}