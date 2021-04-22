package sabinabaghiu.plannerzen.ui.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import sabinabaghiu.plannerzen.R;
import sabinabaghiu.plannerzen.ui.today.Todo;
import sabinabaghiu.plannerzen.ui.today.TodoTodayAdapter;

public class TodoListsAdapter extends RecyclerView.Adapter<TodoListsAdapter.ViewHolder> {

    private ArrayList<Todo> todos;

    TodoListsAdapter(ArrayList<Todo> todos){
        this.todos = todos;
    }

    @NonNull
    @Override
    public TodoListsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.todo_list__item_mylists, parent, false);
        return new TodoListsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListsAdapter.ViewHolder holder, int position) {
        holder.title.setText(todos.get(position).getTitle());
        holder.date.setText(todos.get(position).getDay() + "." + todos.get(position).getMonth() + "." + todos.get(position).getYear());
        holder.description.setText(todos.get(position).getDescription());
        if (todos.get(position).isImportant())
            holder.icon.setImageResource(todos.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView date;
        TextView description;
        ImageView icon;


        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.todo_title);
            date = itemView.findViewById(R.id.todo_date);
            description = itemView.findViewById(R.id.todo_description);
            icon = itemView.findViewById(R.id.icon_todo_mylist);
        }
    }
}
