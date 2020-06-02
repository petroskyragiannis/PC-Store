package com.example.pcstore.component;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pcstore.R;
import com.example.pcstore.model.Component;
import java.util.List;

public class ComponentAdapter extends RecyclerView.Adapter<ComponentAdapter.ViewHolder> {

    private List<Component> itemList;
    private ItemSelectionListener<Component> itemSelectionListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewGroup listItem;
        public TextView txtComponentName;
        public CheckBox btnSelectComponent;

        public ViewHolder(ViewGroup v) {
            super(v);
            listItem = v;
            txtComponentName = listItem.findViewById(R.id.txt_component_name);
            btnSelectComponent = listItem.findViewById(R.id.btn_select_component);
        }
    }

    public ComponentAdapter(List<Component> mDataset) {
        itemList = mDataset;
    }


    public void  setItemSelectionListener(ItemSelectionListener<Component> itemSelectionListener) {
        this.itemSelectionListener = itemSelectionListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_component, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Component componentAtPosition = itemList.get(position);
        holder.txtComponentName.setText(componentAtPosition.getName() + "\n" + componentAtPosition.getPrice() + "€");
        holder.btnSelectComponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View v) {
                if (itemSelectionListener != null)
                    itemSelectionListener.onItemSelected(componentAtPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

}