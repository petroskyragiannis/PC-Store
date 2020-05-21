package com.example.pcstore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pcstore.model.Product;
import java.util.List;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.ViewHolder> {

    private List<Product> itemList;
    // A reference to a listener for book selection events.
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

    public CatalogAdapter(List<Product> mDataset) {
        itemList = mDataset;
    }


    public void setCatalogSelectionListener(CatalogSelectionListener<Product> catalogSelectionListener) {
        this.catalogSelectionListener = catalogSelectionListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_product, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Product productAtPosition = itemList.get(position);
        holder.txtProductName.setText(productAtPosition.getName());
        holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View v) {
                if (catalogSelectionListener != null)
                    catalogSelectionListener.onProductSelectedCart(productAtPosition);
            }
        });
        holder.btnAddToWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View v) {
                if (catalogSelectionListener != null)
                    catalogSelectionListener.onProductSelectedWishlist(productAtPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

}
