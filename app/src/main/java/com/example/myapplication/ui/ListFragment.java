package com.example.myapplication.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.DBHelper;
import com.example.myapplication.R;
import com.example.myapplication.model.Product;
import com.example.myapplication.ui.viewholder.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

	private RecyclerView rcvProduct;
	private ProductAdapter mProductAdapter;
	private EditText edtSearch;
	private ImageButton btnSearch;
	private DBHelper dbHelper;
	private SQLiteDatabase db;
	private List<Product> productList;

	public EditText getEdtSearch() {
		return edtSearch;
	}

	public void setEdtSearch(EditText edtSearch) {
		this.edtSearch = edtSearch;
	}

	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	public ListFragment() {
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment ListFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static ListFragment newInstance(String param1, String param2) {
		ListFragment fragment = new ListFragment();
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
		return inflater.inflate(R.layout.fragment_list, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		rcvProduct = view.findViewById(R.id.rcvProduct);
		edtSearch = view.findViewById(R.id.edtSearch);
		btnSearch = view.findViewById(R.id.btnSearch);
		dbHelper = new DBHelper(view.getContext(), "order_app.db", 1);
		db = dbHelper.getReadableDatabase();

		storeData();

		mProductAdapter = new ProductAdapter(getContext(), productList);
		rcvProduct.setAdapter(mProductAdapter);
		RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
		rcvProduct.setLayoutManager(layoutManager);
		mProductAdapter.notifyDataSetChanged();

		btnSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int count = 0;
				productList = new ArrayList<>();
				Product product = new Product();
				SQLiteDatabase db = dbHelper.getReadableDatabase();
				Cursor cursor = db.rawQuery(dbHelper.SELECT_SEARCH_PRODUCT, new String[]{edtSearch.getText().toString().trim()});
				while (cursor.moveToNext()) {
					product.setimage(cursor.getString(cursor.getColumnIndex("image")));
					product.setName(cursor.getString(cursor.getColumnIndex("name")));
					product.setPrice(cursor.getLong(cursor.getColumnIndex("price")));
					product.setRemainQuantity(cursor.getInt(cursor.getColumnIndex("remain_quantity")));
					product.setType(cursor.getString(cursor.getColumnIndex("type")));
					product.setContent(cursor.getString(cursor.getColumnIndex("content")));
					productList.add(product);
					count ++;
				}
				cursor.close();
				mProductAdapter = new ProductAdapter(getContext(), productList);
				rcvProduct.setAdapter(mProductAdapter);
				RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
				rcvProduct.setLayoutManager(layoutManager);
				mProductAdapter.notifyDataSetChanged();

				if(count == 0){
					Toast.makeText(getContext(), "Không tìm thấy sản phẩm", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	public void storeData(){
		productList = new ArrayList<>();
		Product product = new Product();
		Cursor cursor = db.rawQuery(dbHelper.SELECT_ALL_PRODUCTS, null);
		while (cursor.moveToNext()){
			product.setProductID(cursor.getInt(cursor.getColumnIndex("pro_id")));
			product.setimage(cursor.getString(cursor.getColumnIndex("image")));
			product.setName(cursor.getString(cursor.getColumnIndex("name")));
			product.setRemainQuantity(cursor.getInt(cursor.getColumnIndex("remain_quantity")));
			product.setType(cursor.getString(cursor.getColumnIndex("type")));
			product.setContent(cursor.getString(cursor.getColumnIndex("content")));
			product.setPrice(cursor.getLong(cursor.getColumnIndex("price")));
			productList.add(product);
		}
		cursor.close();
	}
}