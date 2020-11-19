package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

	private EditText etUsername;
	private EditText etPass;
	private Button btnLogin;
	private DBHelper dbHelper;
	private SQLiteDatabase db;
	private SharedPreferences sharedPreferences;
	private Customer customer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		etUsername = findViewById(R.id.etUsername);
		etPass = findViewById(R.id.etPass);

	}

	public void signUp(View view) {

	}

	public void logIn(View view) {
		try {
			dbHelper = new DBHelper(view.getContext(), "order_app.db", 2);
			db = dbHelper.getReadableDatabase();

			String query = "SELECT * FROM Customer WHERE username = ? AND pass = ?";
			Cursor cursor = db.rawQuery(query, new String[]{etUsername.getText().toString(), etPass.getText().toString()});
			while (cursor.moveToNext()) {
				customer = new Customer();
				customer.setId(cursor.getInt(cursor.getColumnIndex("cus_id")));
				customer.setUsername(cursor.getString(cursor.getColumnIndex("username")));
				customer.setName(cursor.getString(cursor.getColumnIndex("name")));
				customer.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
				customer.setEmail(cursor.getString(cursor.getColumnIndex("email")));
				customer.setCity(cursor.getString(cursor.getColumnIndex("city")));

//				Intent intent = new Intent(this, HomeActivity.class);
//				intent.putExtra("customer", customer);
//				startActivity(intent);

				sharedPreferences = getSharedPreferences("sessionLogin", MODE_PRIVATE);
				SharedPreferences.Editor editor = sharedPreferences.edit();

				editor.putInt("cusId", customer.getId());
				editor.putString("username", customer.getUsername());
				editor.putString("name", customer.getName());
				editor.putString("phone", customer.getPhone());
				editor.putString("email", customer.getEmail());
				editor.putString("city", customer.getCity());
				editor.commit();
			}
			int a = sharedPreferences.getInt("cusId", 0);
			if (sharedPreferences != null) {
				Intent intent = new Intent(this, HomeActivity.class);
				startActivity(intent);
			}
//
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}