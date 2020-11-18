package com.example.myapplication.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.DBHelper;
import com.example.myapplication.R;
import com.example.myapplication.model.Order;
import com.example.myapplication.model.OrderItem;
import com.example.myapplication.model.Product;
import com.example.myapplication.ui.viewholder.OrderItemAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<OrderItem> listItems = new ArrayList<>();
    private TextView tvTotalPrice;
    private Button btnPlaceOrder;
    private ListView lvOrders;
    private DBHelper helper;
    private SQLiteDatabase db;
    private OrderItemAdapter adapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Long totalAmount = 0L;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnPlaceOrder = view.findViewById(R.id.btnPlaceOrder);
        lvOrders = view.findViewById(R.id.lvOrder);
        tvTotalPrice = view.findViewById(R.id.tvTotalPrice);

        helper = new DBHelper(getContext(), "order_app.db", 2);
        helper.getReadableDatabase();
        db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(DBHelper.SELECT_ALL_ORDERITEM, null);

        while (c.moveToNext()) {
            int itemId = c.getInt(c.getColumnIndex("item_id"));
            int proId = c.getInt(c.getColumnIndex("pro_id"));
            int quantity = c.getInt(c.getColumnIndex("ord_quantity"));
            Long price = c.getLong(c.getColumnIndex("price"));
            OrderItem item = new OrderItem(proId, quantity, price, false);
            listItems.add(item);
        }

        adapter = new OrderItemAdapter(this, listItems);
        lvOrders.setAdapter(adapter);

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Order order = new Order(1, "Ordered", getCurrentDate(), getDeliveryDate(), "customer.Address", totalAmount);

                //Order order = new Order(getCurrentCustomer().getId(), "Ordered", getCurrentDate(), getDeliveryDate(), getCurrentCustomer().getAddress(), totalAmount);

                db = helper.getWritableDatabase();
                for (OrderItem item : listItems) {
                    if (item.isChoosing()) {
                        db.execSQL(DBHelper.INSERT_TABLE_ORDERITEM, new Object[]{
                                item.getProId(),
                                item.getQuantity(),
                                item.getPrice()
                        });
                    }
                }
                db.execSQL(DBHelper.INSERT_TABLE_ORDER, new Object[]{
                        order.getCusId(),
                        order.getStatus(),
                        order.getOrderDate(),
                        order.getDeliverDate(),
                        order.getAddress(),
                        order.getTotalPrice(),
                });

                Toast.makeText(getContext(), "Place Order Successfully!!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void calculateTotal(OrderItem item) {
        if (item.isChoosing()) {
            Long total = item.getQuantity() * item.getPrice();
            totalAmount += total;
        } else {
            Long total = item.getQuantity() * item.getPrice();
            totalAmount -= total;
        }
        tvTotalPrice.setText(totalAmount + " VND");
    }

    public void deleteOrder(OrderItem item) {
        listItems.remove(item);
        tvTotalPrice.setText("0 VND");
        lvOrders.setAdapter(adapter);
    }

    public void setQuantity(int newQuantity, int index) {
        listItems.get(index).setQuantity(newQuantity);
    }

    public Product getProduct(int productId) {
        Product p = new Product();
        helper.getReadableDatabase();
        db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(DBHelper.GET_PRODUCT_BY_ID, new String[]{String.valueOf(productId)});
        while (c.moveToNext()) {
            p.setProductID(c.getInt(c.getColumnIndex("pro_id")));
            p.setimage(c.getString(c.getColumnIndex("image")));
            p.setName(c.getString(c.getColumnIndex("name")));
            p.setRemainQuantity(c.getInt(c.getColumnIndex("remain_quantity")));
            p.setType(c.getString(c.getColumnIndex("type")));
            p.setContent(c.getString(c.getColumnIndex("content")));
            p.setPrice(c.getLong(c.getColumnIndex("price")));
        }
        return p;
    }

    private String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date currentDate = new Date();
        return formatter.format(currentDate);
    }

    private String getDeliveryDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 3);
        Date deliveryDate = c.getTime();
        return formatter.format(deliveryDate);
    }

}