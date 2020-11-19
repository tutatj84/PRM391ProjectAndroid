package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
	private DBHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//init db
		dbHelper = new DBHelper(this, "order_app.db", 2);
		db = dbHelper.getReadableDatabase();


		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}
}