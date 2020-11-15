package com.example.myapplication.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.ui.CartFragment;
import com.example.myapplication.ui.entity.OrderItem;

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
        TextView tvItemName, tvQuantity, tvPrice, tvTotal;
        final CheckBox chooseItem;

        if (convertView == null) {
            convertView = cartFragment.getActivity().getLayoutInflater().inflate(R.layout.item_order, null);
            tvItemName = convertView.findViewById(R.id.tvItemName);
            tvQuantity = convertView.findViewById(R.id.tvQuantity);
            tvPrice = convertView.findViewById(R.id.tvPrice);
            tvTotal = convertView.findViewById(R.id.tvTotal);
            chooseItem = convertView.findViewById(R.id.chk_selectitem);

            convertView.setTag(R.id.tvItemName);
            convertView.setTag(R.id.tvQuantity);
            convertView.setTag(R.id.tvPrice);
            convertView.setTag(R.id.tvTotal);
            convertView.setTag(R.id.chk_selectitem);
        } else {
            tvItemName = (TextView) convertView.getTag(R.id.tvItemName);
            tvQuantity = (TextView) convertView.getTag(R.id.tvQuantity);
            tvPrice = (TextView) convertView.getTag(R.id.tvPrice);
            tvTotal = (TextView) convertView.getTag(R.id.tvTotal);
            chooseItem = (CheckBox) convertView.getTag(R.id.chk_selectitem);
        }

        final OrderItem orderItem = listItems.get(position);
        tvItemName.setText(String.valueOf(orderItem.getProId()));
        tvQuantity.setText(String.valueOf(orderItem.getQuantity()));
        tvPrice.setText(String.valueOf(orderItem.getPrice()));
        tvTotal.setText(String.valueOf(orderItem.getPrice() * orderItem.getQuantity()));

        chooseItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseItem.isChecked()) {
                    orderItem.setDelete(false);
                    cartFragment.calculateTotal(orderItem);
                }else {
                    orderItem.setDelete(true);
                    cartFragment.calculateTotal(orderItem);
                }
            }
        });
        return convertView;
    }

}
