package com.example.pcstore.catalog_update;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pcstore.R;
import com.example.pcstore.model.Hardware;
import com.example.pcstore.model.Product;

public class ProductUpdateActivity extends AppCompatActivity {

    public static final String UPDATED_PRODUCT = "updated product";

    Product product;
    TextView txtProductId;
    EditText edtProductId;
    TextView txtProductName;
    EditText edtProductName;
    TextView txtProductPrice;
    EditText edtProductPrice;
    TextView txtProductStock;
    EditText edtProductStock;
    TextView txtProductCategory;
    EditText edtProductCategory;
    Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_update);
        txtProductId = findViewById(R.id.txt_product_id);
        edtProductId = findViewById(R.id.edt_product_id);
        txtProductName = findViewById(R.id.txt_product_name);
        edtProductName = findViewById(R.id.edt_product_name);
        txtProductPrice = findViewById(R.id.txt_product_price);
        edtProductPrice = findViewById(R.id.edt_product_price);
        txtProductStock = findViewById(R.id.txt_product_stock);
        edtProductStock = findViewById(R.id.edt_product_stock);
        txtProductCategory = findViewById(R.id.txt_product_category);
        edtProductCategory = findViewById(R.id.edt_product_category);
        btnDone = findViewById(R.id.btn_done);

        Intent intent = getIntent();
        product = (Product) intent.getSerializableExtra(CatalogUpdateActivity.SELECTED_PRODUCT);

        edtProductId.setText(String.valueOf(product.getId()));
        edtProductName.setText(product.getName());
        edtProductPrice.setText(product.getPrice() + "€");
        edtProductStock.setText(String.valueOf(product.getStock()));
        edtProductCategory.setText(product.getCategory().toString());

        edtProductId.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int id = Integer.parseInt(edtProductId.getText().toString());
                if (id >= 0) product.setId(id);
                else showStatus("Wrong id number.");
            }
        });

        edtProductName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = edtProductName.getText().toString().trim();
                if (name != null) product.setName(name);
                else showStatus("Wrong product name.");
            }
        });

        edtProductPrice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int price = Integer.parseInt(edtProductPrice.getText().toString());
                if (price > 0) product.setPrice(price);
                else showStatus("Wrong price number.");
            }
        });

        edtProductStock.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int stock = Integer.parseInt(edtProductStock.getText().toString());
                if (stock >= 0) product.setStock(stock);
                else showStatus("Wrong stock number.");
            }
        });

        edtProductCategory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Boolean set = false;
                String category = edtProductCategory.getText().toString().toUpperCase();
                for (Hardware hardware: Hardware.values()) {
                    if (hardware.toString().equals(category)) {
                        product.setCategory(hardware);
                        set = true;
                    }
                }
                if (!set) showStatus("Wrong product category.");
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (product.getName() == null || product.getPrice() <= 0 || product.getStock() < 0 || product.getCategory() == null)
                    showStatus("Missing required information.");
                else {
                    returnProduct(product);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        returnProduct(product);
    }

    public void returnProduct(Product product) {
        Intent intent = new Intent();
        intent.putExtra(UPDATED_PRODUCT, product);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void showStatus(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}