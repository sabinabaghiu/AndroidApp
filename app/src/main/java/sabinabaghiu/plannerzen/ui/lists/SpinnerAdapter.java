package sabinabaghiu.plannerzen.ui.lists;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sabinabaghiu.plannerzen.R;

public class SpinnerAdapter extends ArrayAdapter<SpinnerRow> {
    private List<SpinnerRow> rows;
    LayoutInflater inflater;
    ViewHolder viewHolder = null;

    public SpinnerAdapter(Context context, int textViewResourceId, List<SpinnerRow> rows) {
        super(context, textViewResourceId, rows);
        inflater = ((Activity)context).getLayoutInflater();
        this.rows = rows;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        SpinnerRow spinnerRow = rows.get(position);
        View row = convertView;
        if (null == row) {
            viewHolder = new ViewHolder();
            row = inflater.inflate(R.layout.spinner_row, parent, false);
            viewHolder.name = (TextView) row.findViewById(R.id.text);
            viewHolder.img = (ImageView) row.findViewById(R.id.icon);
            row.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) row.getTag();
        }
        viewHolder.name.setText(spinnerRow.getText());
        viewHolder.img.setBackgroundResource(spinnerRow.getIconId());
        return row;
    }

    static class ViewHolder {
        TextView name;
        ImageView img;
    }
}
