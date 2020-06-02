package com.example.pcstore.catalog_update;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pcstore.R;
import com.example.pcstore.component.ItemSelectionListener;
import com.example.pcstore.login.LoginActivity;
import com.example.pcstore.model.Employee;
import com.example.pcstore.model.Product;

import java.util.List;

public class CatalogUpdateActivity extends AppCompatActivity
        implements ItemSelectionListener<Product>, CatalogUpdateView {

    public static final String SIGNED_OUT_EMPLOYEE = "signed out employee";
    public static final String SELECTED_PRODUCT = "selected product";
    private static final int REQUEST_CODE_UPDATE_PRODUCT = 0;

    Employee employee;
    RecyclerView recyclerView;
    private CatalogUpdateAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private CatalogUpdateViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog_update);
        recyclerView = findViewById(R.id.rv_catalog_update);

        Intent intent = getIntent();
        employee = (Employee) intent.getSerializableExtra(LoginActivity.SIGNED_IN_EMPLOYEE);

        viewModel = new ViewModelProvider(this).get(CatalogUpdateViewModel.class);
        final CatalogUpdatePresenter presenter = viewModel.getPresenter();
        presenter.setView(this);
        List<Product> catalog = presenter.getCatalog();

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CatalogUpdateAdapter(catalog);
        recyclerView.setAdapter(adapter);
        adapter.setItemSelectionListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        viewModel.getPresenter().signOutEmployee(employee);
    }

    @Override
    public void onItemSelected(Product item) {
        viewModel.getPresenter().deleteProduct(item);
        Intent intent = new Intent(this, ProductUpdateActivity.class);
        intent.putExtra(SELECTED_PRODUCT, item);
        startActivityForResult(intent, REQUEST_CODE_UPDATE_PRODUCT);
    }

    @Override
    public void returnEmployee(Employee employee) {
        Intent intent = new Intent();
        intent.putExtra(SIGNED_OUT_EMPLOYEE, employee);
        setResult(RESULT_OK, intent);
    }

    @Override
    public void showStatus(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_UPDATE_PRODUCT)
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Product product = (Product) data.getSerializableExtra(ProductUpdateActivity.UPDATED_PRODUCT);
                    viewModel.getPresenter().saveProduct(product);
                    adapter.setDataset(viewModel.getPresenter().getCatalog());
                    adapter.notifyDataSetChanged();
                }
            }
    }

}