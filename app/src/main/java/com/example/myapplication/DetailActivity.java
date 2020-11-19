package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.model.Product;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private ImageView imgProduct;
    private TextView txtProductName;
    private TextView txtProductPrice;
    private TextView txtProductRemain;
    private TextView txtProductType;
    private TextView txtProductContent;
    private TextView txtAmount;
    private Button btnCart;
    private Button btnMinus;
    private Button btnPlus;
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private int amount = 1;
    private Long total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgProduct = findViewById(R.id.imgProduct);
        txtProductName = findViewById(R.id.txtProductName);
        txtProductPrice = findViewById(R.id.txtProductPrice);
        txtProductRemain = findViewById(R.id.txtProductRemain);
        txtProductType = findViewById(R.id.txtProductType);
        txtProductContent = findViewById(R.id.txtProductContent);
        txtAmount = findViewById(R.id.txtAmount);
        btnCart = findViewById(R.id.btnCart);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);

        Intent intent = getIntent();
        final Product product = (Product) intent.getSerializableExtra("product");
        Picasso.with(this).load(product.getimage()).into(imgProduct);
        txtProductName.setText(product.getName());
        txtProductPrice.setText(product.getPrice() + " VNĐ");
        txtProductRemain.setText("Số lượng: " + product.getRemainQuantity());
        txtProductType.setText(product.getType());
        txtProductContent.setText(product.getContent());
        txtAmount.setText(String.valueOf(amount));

        total = product.getPrice() * amount;

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount >= product.getRemainQuantity()){
                    Toast.makeText(DetailActivity.this, "Bạn không thể chọn quá số lượng", Toast.LENGTH_SHORT).show();
                }
                else if(amount < product.getRemainQuantity()){
                    amount++;
                    txtAmount.setText(String.valueOf(amount));
                    setTotal(amount);
                }
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount > 0) {
                    amount--;
                    txtAmount.setText(String.valueOf(amount));
                    setTotal(amount);
                }
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount == 0){
                    Toast.makeText(DetailActivity.this, "Vui lòng chọn số lượng", Toast.LENGTH_SHORT).show();
                    return;
                }
                insertOrder(amount);
            }
        });
    }

    private void insertOrder(int amount){
        dbHelper = new DBHelper(this, "order_app.db", 2);
        db = dbHelper.getWritableDatabase();
        Intent intent = getIntent();
        final Product product = (Product) intent.getSerializableExtra("product");
        String productId = String.valueOf(product.getProductID());
        int orderQuantity = amount;
        Long priceOrder = product.getPrice();
        db.execSQL(dbHelper.INSERT_TABLE_ORDERITEM, new Object[]{productId, orderQuantity, priceOrder});
        Toast.makeText(DetailActivity.this, "Cho vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
    }

    private Long setTotal(int amount){
        Intent intent = getIntent();
        final Product product = (Product) intent.getSerializableExtra("product");
        total = product.getPrice() * amount;
        Picasso.with(this).load(product.getimage()).into(imgProduct);
        txtProductName.setText(product.getName());
        txtProductPrice.setText(total + " VNĐ");
        txtProductRemain.setText("Số lượng: " + product.getRemainQuantity());
        txtProductType.setText(product.getType());
        txtProductContent.setText(product.getContent());

        return total;
    }
}