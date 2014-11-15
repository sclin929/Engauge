package com.app.engauge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.data.Group;

import java.util.ArrayList;

/**
 * Created by Maury on 11/15/14.
 */
public class GroupArrayAdapter extends ArrayAdapter<Group> {
    public GroupArrayAdapter(Context context, int resource, ArrayList<Group> objects) {
        super(context, resource, objects);
    }

    static class ViewHolder {
        TextView groupName;
        TextView groupDescription;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Group item = getItem(position);

        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.item_group, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.groupName = (TextView) convertView.findViewById(R.id.group_name);
            viewHolder.groupDescription = (TextView)
                    convertView.findViewById(R.id.group_short_description);

            convertView.setTag(viewHolder);
        }

        // Fill the data.
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.groupName.setText(item.getName());
        viewHolder.groupDescription.setText(item.getDescription());

        return convertView;
    }
}
