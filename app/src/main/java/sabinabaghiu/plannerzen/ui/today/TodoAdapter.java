package sabinabaghiu.plannerzen.ui.today;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sabinabaghiu.plannerzen.R;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private ArrayList<Todo> todos;

    TodoAdapter(ArrayList<Todo> todos){
        this.todos = todos;
    }

    @NonNull
    @Override
    public TodoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.todo_list_item_today, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.ViewHolder holder, int position) {
        holder.title.setText(todos.get(position).getTitle() + " - " + todos.get(position).getTime() + " min");
        if (todos.get(position).isImportant())
            holder.icon.setImageResource(todos.get(position).getIcon());
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
            title = itemView.findViewById(R.id.todo_today);
            icon = itemView.findViewById(R.id.icon_today);
        }
    }
}
