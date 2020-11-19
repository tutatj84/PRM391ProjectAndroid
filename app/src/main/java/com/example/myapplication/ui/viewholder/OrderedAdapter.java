package com.example.myapplication.ui.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Customer;
import com.example.myapplication.model.Order;
import com.example.myapplication.ui.OrderFragment;

import java.util.List;

public class OrderedAdapter extends BaseAdapter {

    private OrderFragment orderFragment;
    private List<Order> listOrders;

    public OrderedAdapter(OrderFragment orderFragment, List<Order> listOrders) {
        this.orderFragment = orderFragment;
        this.listOrders = listOrders;
    }

    @Override
    public int getCount() {
        return listOrders.size();
    }

    @Override
    public Object getItem(int position) {
        return listOrders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listOrders.get(position).getOrderId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tvCustomerName, tvOrderDate, tvDeliveryDate, tvTotalAmount, tvStatus;

        if (convertView == null) {
            convertView = orderFragment.getActivity().getLayoutInflater().inflate(R.layout.list_ordered, null);
            tvCustomerName = convertView.findViewById(R.id.tvCustomerName);
            tvOrderDate = convertView.findViewById(R.id.tvOrderDate);
            tvDeliveryDate = convertView.findViewById(R.id.tvDeliveryDate);
            tvTotalAmount = convertView.findViewById(R.id.tvTotalAmount);
            tvStatus = convertView.findViewById(R.id.tvStatus);

            convertView.setTag(R.id.tvCustomerName);
            convertView.setTag(R.id.tvOrderDate);
            convertView.setTag(R.id.tvDeliveryDate);
            convertView.setTag(R.id.tvTotalAmount);
            convertView.setTag(R.id.tvStatus);
        } else {
            tvCustomerName = (TextView) convertView.getTag(R.id.tvCustomerName);
            tvOrderDate = (TextView) convertView.getTag(R.id.tvOrderDate);
            tvDeliveryDate = (TextView) convertView.getTag(R.id.tvDeliveryDate);
            tvTotalAmount = (TextView) convertView.getTag(R.id.tvTotalAmount);
            tvStatus = (TextView) convertView.getTag(R.id.tvStatus);
        }

        final Order order = listOrders.get(position);
        Customer customer = orderFragment.getCustomer();
        tvCustomerName.setText(customer.getName());
        tvOrderDate.setText(order.getOrderDate());
        tvDeliveryDate.setText(order.getDeliverDate());
        tvTotalAmount.setText(String.valueOf(order.getTotalPrice()));
        tvStatus.setText(order.getStatus());

        return convertView;
    }
}
