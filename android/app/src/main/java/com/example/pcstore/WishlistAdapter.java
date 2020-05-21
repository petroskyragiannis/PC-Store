package com.example.pcstore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pcstore.model.Product;
import java.util.List;


public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {

    private List<Product> itemList;
    // A reference to a listener for book selection events.
    private ItemSelectionListener<Product> itemSelectionListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewGroup listItem;
        public TextView txtWishlistProductName;
        public ImageButton btnRemoveFromWishlist;

        public ViewHolder(ViewGroup v) {
            super(v);
            listItem = v;
            txtWishlistProductName = listItem.findViewById(R.id.txt_wishlist_product_name);
            btnRemoveFromWishlist = listItem.findViewById(R.id.btn_remove_from_wishlist);
        }
    }

    public WishlistAdapter(List<Product> mDataset) {
        itemList = mDataset;
    }


    public void setItemSelectionListener(ItemSelectionListener<Product> itemSelectionListener) {
        this.itemSelectionListener = itemSelectionListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_wishlist, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Product itemAtPosition = itemList.get(position);
        holder.txtWishlistProductName.setText(itemAtPosition.getName());
        holder.btnRemoveFromWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View v) {
                if (itemSelectionListener != null)
                    itemSelectionListener.onItemSelected(itemAtPosition);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

}
