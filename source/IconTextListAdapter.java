package com.example.hi;

import java.util.ArrayList;
import java.util.List;
 
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
 

public class IconTextListAdapter extends BaseAdapter {
 
    private Context Cont;
 
    private List<IconTextItem> Items = new ArrayList<IconTextItem>();
 
    public IconTextListAdapter(Context context) {
        Cont = context;
    }
 
    public void addItem(IconTextItem it) {
        Items.add(it);
    }
 
    public void setListItems(List<IconTextItem> lit) {
        Items = lit;
    }
 
    public int getCount() {
        return Items.size();
    }
 
    public Object getItem(int position) {
        return Items.get(position);
    }
 
    public boolean areAllItemsSelectable() {
        return false;
    }
 
    public boolean isSelectable(int position) {
        try {
            return Items.get(position).isSelectable();
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }
 
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        IconTextView itemView;
        if (convertView == null) {
            itemView = new IconTextView(Cont, Items.get(position));
        } else {
            itemView = (IconTextView) convertView;
             
          
            itemView.setText(0, Items.get(position).getData(0));
            itemView.setText(1, Items.get(position).getData(1));
            itemView.setText(2, Items.get(position).getData(2));
        }
 
        return itemView;
    }
 
}