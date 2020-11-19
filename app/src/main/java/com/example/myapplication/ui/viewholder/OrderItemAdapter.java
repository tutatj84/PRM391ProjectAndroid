package com.example.myapplication.ui.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.Product;
import com.example.myapplication.ui.CartFragment;
import com.example.myapplication.model.OrderItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderItemAdapter extends BaseAdapter {

    private CartFragment cartFragment;
    private List<OrderItem> listItems;

    public OrderItemAdapter(CartFragment cartFragment, List<OrderItem> listItems) {
        this.cartFragment = cartFragment;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listItems.get(position).getItemId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final TextView tvItemName, tvQuantity, tvPrice, tvTotal;
        final CheckBox chooseItem;
        Button btnDelete, btnQuantityMinus, btnQuantityPlus;
        ImageView ivProduct;


        if (convertView == null) {
            convertView = cartFragment.getActivity().getLayoutInflater().inflate(R.layout.item_order, null);
            tvItemName = convertView.findViewById(R.id.tvItemName);
            tvQuantity = convertView.findViewById(R.id.tvQuantity);
            tvPrice = convertView.findViewById(R.id.tvPrice);
            tvTotal = convertView.findViewById(R.id.tvTotal);
            chooseItem = convertView.findViewById(R.id.chk_selectitem);
            btnDelete = convertView.findViewById(R.id.btnDelete);
            btnQuantityMinus = convertView.findViewById(R.id.btnQuantityMinus);
            btnQuantityPlus = convertView.findViewById(R.id.btnQuantityPlus);
            ivProduct = convertView.findViewById(R.id.ivProduct);

            convertView.setTag(R.id.tvItemName);
            convertView.setTag(R.id.tvQuantity);
            convertView.setTag(R.id.tvPrice);
            convertView.setTag(R.id.tvTotal);
            convertView.setTag(R.id.chk_selectitem);
            convertView.setTag(R.id.btnDelete);
            convertView.setTag(R.id.btnQuantityPlus);
            convertView.setTag(R.id.btnQuantityMinus);
            convertView.setTag(R.id.ivProduct);
        } else {
            tvItemName = (TextView) convertView.getTag(R.id.tvItemName);
            tvQuantity = (TextView) convertView.getTag(R.id.tvQuantity);
            tvPrice = (TextView) convertView.getTag(R.id.tvPrice);
            tvTotal = (TextView) convertView.getTag(R.id.tvTotal);
            chooseItem = (CheckBox) convertView.getTag(R.id.chk_selectitem);
            btnDelete = (Button) convertView.getTag(R.id.btnDelete);
            btnQuantityMinus = (Button) convertView.getTag(R.id.btnQuantityMinus);
            btnQuantityPlus = (Button) convertView.getTag(R.id.btnQuantityPlus);
            ivProduct = (ImageView) convertView.getTag(R.id.ivProduct);
        }

        final OrderItem orderItem = listItems.get(position);
        Product p = cartFragment.getProduct(orderItem.getProId());
        Picasso.with(cartFragment.getContext()).load(p.getimage()).into(ivProduct);
        tvItemName.setText(p.getName());
        tvQuantity.setText(String.valueOf(orderItem.getQuantity()));
        tvPrice.setText(String.valueOf(orderItem.getPrice()));
        tvTotal.setText(String.valueOf(orderItem.getPrice() * orderItem.getQuantity()));

        chooseItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseItem.isChecked()) {
                    orderItem.setChoosing(true);
                    cartFragment.calculateTotal(orderItem);
                }else {
                    orderItem.setChoosing(false);
                    cartFragment.calculateTotal(orderItem);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderItem.setChoosing(false);
                cartFragment.deleteOrder(orderItem);
                Toast.makeText(cartFragment.getContext(), "Delete Successfully!!!", Toast.LENGTH_SHORT).show();
            }
        });

        btnQuantityPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!chooseItem.isChecked()) {
                    int newQuantity = orderItem.getQuantity() + 1;
                    tvQuantity.setText(String.valueOf(newQuantity));
                    tvTotal.setText(String.valueOf(orderItem.getPrice() * newQuantity));
                    cartFragment.setQuantity(newQuantity, position);
                }else {
                    Toast.makeText(cartFragment.getContext(), "Uncheck to adjust quantity.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnQuantityMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!chooseItem.isChecked()) {
                    int newQuantity = orderItem.getQuantity() - 1;
                    if (newQuantity < 0) {
                        Toast.makeText(cartFragment.getContext(), "Quantity cannot be less than 0!", Toast.LENGTH_SHORT).show();
                    }else {
                        tvQuantity.setText(String.valueOf(newQuantity));
                        tvTotal.setText(String.valueOf(orderItem.getPrice() * newQuantity));
                        cartFragment.setQuantity(newQuantity, position);
                    }
                }else {
                    Toast.makeText(cartFragment.getContext(), "Uncheck to adjust quantity.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;
    }

}
