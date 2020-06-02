package com.example.pcstore.catalog_update;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pcstore.R;
import com.example.pcstore.component.ItemSelectionListener;
import com.example.pcstore.model.Product;
import java.util.List;

public class CatalogUpdateAdapter extends RecyclerView.Adapter<CatalogUpdateAdapter.ViewHolder> {

    private List<Product> itemList;
    private ItemSelectionListener<Product> itemSelectionListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewGroup listItem;
        public TextView txtProductName;
        public ImageButton btnEditProduct;

        public ViewHolder(ViewGroup v) {
            super(v);
            listItem = v;
            txtProductName = listItem.findViewById(R.id.txt_product_name);
            btnEditProduct = listItem.findViewById(R.id.btn_edit_product);
        }
    }

    public CatalogUpdateAdapter(List<Product> dataset) {
        itemList = dataset;
    }

    public void setDataset(List<Product> dataset) {
        itemList = dataset;
    }

    public void setItemSelectionListener(ItemSelectionListener<Product> itemSelectionListener) {
        this.itemSelectionListener = itemSelectionListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_catalog_update, parent, false);
        ViewHolder vh = new CatalogUpdateAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Product productAtPosition = itemList.get(position);
        holder.txtProductName.setText(productAtPosition.getName());
        holder.btnEditProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View v) {
                if (itemSelectionListener != null)
                    itemSelectionListener.onItemSelected(productAtPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

}