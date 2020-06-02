package com.example.pcstore.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcstore.R;
import com.example.pcstore.model.OrderLine;
import com.example.pcstore.model.PcConfiguration;
import com.example.pcstore.model.Product;
import com.example.pcstore.model.SimpleOrderLine;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<OrderLine> itemList;
    private CartItemSelectionListener<OrderLine> cartItemSelectionListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewGroup listItem;
        public TextView txtCartProductName;
        public EditText edtQuantity;
        public ImageButton btnRemoveFromCart;

        public ViewHolder(ViewGroup v) {
            super(v);
            listItem = v;
            txtCartProductName = listItem.findViewById(R.id.txt_cart_product_name);
            edtQuantity = listItem.findViewById(R.id.edt_quantity);
            btnRemoveFromCart = listItem.findViewById(R.id.btn_remove_from_cart);
        }
    }

    public CartAdapter(List<OrderLine> dataset) {
        itemList = dataset;
    }

    public void setDataset(List<OrderLine> dataset) {
        itemList = dataset;
    }

    public void setCartItemSelectionListener(CartItemSelectionListener<OrderLine> cartItemSelectionListener) {
        this.cartItemSelectionListener = cartItemSelectionListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_cart, parent, false);
        CartAdapter.ViewHolder vh = new CartAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final OrderLine itemAtPosition = itemList.get(position);
        holder.edtQuantity.setText(Integer.toString(itemAtPosition.getQuantity()));
        //int max = itemAtPosition.getQuantity() + itemAtPosition.getStock();
        //holder.edtQuantity.setHint("Max: " + max);
        if (itemAtPosition instanceof SimpleOrderLine) {
            SimpleOrderLine simpleOrderLine = (SimpleOrderLine) itemAtPosition;
            holder.txtCartProductName.setText(simpleOrderLine.getProduct().getName() + "\n" + simpleOrderLine.getProduct().getPrice() + "€");
        }
        else if (itemAtPosition instanceof PcConfiguration) {
            PcConfiguration pcConfiguration = (PcConfiguration) itemAtPosition;
            holder.txtCartProductName.setText("Custom PC Configuration: " + pcConfiguration.getSubTotal() + "€");
        }

        holder.btnRemoveFromCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View v) {
                if (cartItemSelectionListener != null)
                    cartItemSelectionListener.onItemSelected(itemAtPosition);
            }
        });

        holder.edtQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View v) {
                int quantity = Integer.parseInt(holder.edtQuantity.getText().toString());
                if (cartItemSelectionListener != null)
                    cartItemSelectionListener.onItemSelected(itemAtPosition, quantity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

}
