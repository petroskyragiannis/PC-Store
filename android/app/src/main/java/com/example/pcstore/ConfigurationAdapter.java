package com.example.pcstore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class ConfigurationAdapter extends RecyclerView.Adapter<ConfigurationAdapter.ViewHolder> {

    private String[] items;
    // A reference to a listener for book selection events.
    private  ItemSelectionListener<String> itemSelectionListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewGroup listItem;
        public TextView txtCategory;
        public ImageButton btnSelectCategory;

        public ViewHolder(ViewGroup v) {
            super(v);
            listItem = v;
            txtCategory = listItem.findViewById(R.id.txt_category);
            btnSelectCategory = listItem.findViewById(R.id.btn_select_category);
        }
    }

    public ConfigurationAdapter(String[] dataset) {
        items = dataset;
    }


    public void  setItemSelectionListener(ItemSelectionListener<String> itemSelectionListener) {
        this.itemSelectionListener = itemSelectionListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_configuration, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String itemAtPosition = items[position];

        holder.txtCategory.setText(itemAtPosition);
        holder.btnSelectCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View v) {
                if (itemSelectionListener != null)
                    itemSelectionListener.onItemSelected(itemAtPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.length;
    }
}
