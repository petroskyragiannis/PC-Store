package com.example.pcstore.configuration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcstore.R;

public class ConfigurationAdapter extends RecyclerView.Adapter<ConfigurationAdapter.ViewHolder> {

    private String[] items;
    // A reference to a listener for book selection events.
    private ComponentTypeSelectionListener componentTypeSelectionListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewGroup listItem;
        public TextView txtType;
        public ImageButton btnSelectType;

        public ViewHolder(ViewGroup v) {
            super(v);
            listItem = v;
            txtType = listItem.findViewById(R.id.txt_component_type);
            btnSelectType = listItem.findViewById(R.id.btn_select_type);
        }
    }

    public ConfigurationAdapter(String[] dataset) {
        items = dataset;
    }


    public void setComponentTypeSelectionListener(ComponentTypeSelectionListener componentTypeSelectionListener) {
        this.componentTypeSelectionListener = componentTypeSelectionListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_configuration, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final String itemAtPosition = items[position];
        holder.txtType.setText(itemAtPosition);
        holder.btnSelectType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View v) {
                if (componentTypeSelectionListener != null)
                    componentTypeSelectionListener.onComponentTypeSelected(itemAtPosition, holder.txtType);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

}
