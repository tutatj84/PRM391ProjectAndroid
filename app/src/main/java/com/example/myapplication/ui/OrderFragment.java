package com.example.myapplication.ui;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.DBHelper;
import com.example.myapplication.R;
import com.example.myapplication.model.Customer;
import com.example.myapplication.model.Order;
import com.example.myapplication.model.Product;
import com.example.myapplication.ui.viewholder.OrderedAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<Order> listOrders = new ArrayList<>();
    private ListView lvOrdered;
    private DBHelper helper;
    private SQLiteDatabase db;
    private OrderedAdapter adapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
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
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvOrdered = view.findViewById(R.id.lvOrdered);

        helper = new DBHelper(getContext(), "order_app.db", 2);
        helper.getReadableDatabase();
        db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(DBHelper.SELECT_ALL_ORDER, null);

        while (c.moveToNext()) {
            int cId = c.getInt(c.getColumnIndex("cus_id"));
            String status = c.getString(c.getColumnIndex("status"));
            String orderDate = c.getString(c.getColumnIndex("order_date"));
            String deliveryDate = c.getString(c.getColumnIndex("deliver_date"));
            String address = c.getString(c.getColumnIndex("address"));
            Long totalPrice = c.getLong(c.getColumnIndex("total_price"));
            Order order = new Order(cId, status, orderDate, deliveryDate, address, totalPrice);
            listOrders.add(order);
        }

        adapter = new OrderedAdapter(this, listOrders);
        lvOrdered.setAdapter(adapter);

    }
	public Customer getCustomer() {
		Customer customer = new Customer();
		Activity a = getActivity();
      SharedPreferences sharedPreferences= getActivity().getSharedPreferences("sessionLogin", Context.MODE_PRIVATE);
      customer.setId(sharedPreferences.getInt("cusId", 0));
      customer.setUsername(sharedPreferences.getString("username", ""));
      customer.setName(sharedPreferences.getString("name", "Mr. A"));
      customer.setPhone(sharedPreferences.getString("phone", ""));
      customer.setEmail(sharedPreferences.getString("email", ""));
      customer.setCity(sharedPreferences.getString("city", ""));
		return customer;
	}
}