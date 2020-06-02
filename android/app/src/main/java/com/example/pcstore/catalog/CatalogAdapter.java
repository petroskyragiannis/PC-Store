package com.example.pcstore.catalog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pcstore.R;
import com.example.pcstore.model.Product;
import java.util.List;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.ViewHolder> {

    private List<Product> itemList;
    private CatalogSelectionListener<Product> catalogSelectionListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewGroup listItem;
        public TextView txtProductName;
        public ImageButton btnAddToCart;
        public ImageButton btnAddToWishlist;

        public ViewHolder(ViewGroup v) {
            super(v);
            listItem = v;
            txtProductName = listItem.findViewById(R.id.txt_product_name);
            btnAddToCart = listItem.findViewById(R.id.btn_add_to_cart);
            btnAddToWishlist = listItem.findViewById(R.id.btn_add_to_wishlist);
        }
    }

    public CatalogAdapter(List<Product> dataset) {
        itemList = dataset;
    }


    public void setCatalogSelectionListener(CatalogSelectionListener<Product> catalogSelectionListener) {
        this.catalogSelectionListener = catalogSelectionListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_catalog, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Product productAtPosition = itemList.get(position);
        holder.txtProductName.setText(productAtPosition.getName() + "\n" + productAtPosition.getPrice() + "€");
        holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View v) {
                if (catalogSelectionListener != null)
                    catalogSelectionListener.onItemSelectedCart(productAtPosition);
            }
        });
        holder.btnAddToWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View v) {
                if (catalogSelectionListener != null)
                    catalogSelectionListener.onItemSelectedWishlist(productAtPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

}
