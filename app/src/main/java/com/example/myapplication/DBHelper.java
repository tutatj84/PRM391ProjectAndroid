package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
//	create
private final String CREATE_TABLE_PRODUCT = "CREATE TABLE if not exists Product (" +
		"pro_id integer primary key autoincrement," +
		"image text," +
		"name text," +
		"remain_quantity integer," +
		"type text," +
		"content text," +
		"price real" +
		")";
	private final String CREATE_TABLE_CUSTOMER = "CREATE TABLE if not exists Customer (" +
		"cus_id integer primary key autoincrement, " +
		"name text," +
		"phone text," +
		"email text," +
		"city text" +
		")";
	private final String CREATE_TABLE_ORDER = "CREATE TABLE if not exists OrderTbl (" +
		"ord_id integer primary key autoincrement, " +
		"cus_id integer," +
		"status text," +
		"order_date text," +
		"deliver_date text," +
		"address text," +
		"total_price real" +
		")";
	private final String CREATE_TABLE_ORDERITEM = "CREATE TABLE if not exists Order_Item (" +
		"item_id integer primary key autoincrement," +
		"pro_id text," +
		"ord_quantity integer," +
		"price real" +
		")";

//drop
	private final String DROP_TABLE_PRODUCT = "DROP TABLE Product";
	private final String DROP_TABLE_CUSTOMER = "DROP TABLE Customer";
	private final String DROP_TABLE_ORDER = "DROP TABLE OrderTbl";
	private final String DROP_TABLE_ORDERITEM = "DROP TABLE Order_Item";

//	insert
//	private final String INSERT_TABLE_PRODUCT = "INSERT INTO Product(pro_id, name, remain_quantity, type, price) VALUES (?, ?, ?, ?, ?)";
	private final String INSERT_TABLE_PRODUCT = "INSERT INTO Product(pro_id, image,  name, remain_quantity, type,content, price) VALUES (1, 'https://media.cooky.vn/recipe/g5/40048/s/recipe40048-cook-step5-636894576207321843.jpg', 'chicken rice', 15, 'rice', 'cơm gà là một món ăn mà mọi nơi trên thế giới đều có, ở Việt Nam món cơm gà cũng rất phổ biến và vô cùng ngon', 30000)";
	private final String INSERT_TABLE_CUSTOMER = "INSERT INTO Customer(cus_id, name, phone, email, city) VALUES (?, ?, ?, ?, ?)";
	private final String INSERT_TABLE_ORDER = "INSERT INTO OrderTbl(ord_id, cus_id, status, order_date, deliver_date, address, total_price) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private final String INSERT_TABLE_ORDERITEM = "INSERT INTO Order_Item(item_id, pro_id, ord_quantity, price) VALUES (?, ?, ?, ?)";

//	select
	public final String SELECT_ALL_PRODUCTS = "SELECT * FROM Product";
	public final String SELECT_SEARCH_PRODUCT = "SELECT * FROM Product WHERE name like ?";
	//

	public DBHelper(Context context, String dbName, int version) {
		super(context, dbName, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_PRODUCT);
		db.execSQL(CREATE_TABLE_CUSTOMER);
		db.execSQL(CREATE_TABLE_ORDER);
		db.execSQL(CREATE_TABLE_ORDERITEM);
		db.execSQL(INSERT_TABLE_PRODUCT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion < newVersion) {
			db.execSQL(DROP_TABLE_PRODUCT);
			db.execSQL(DROP_TABLE_CUSTOMER);
			db.execSQL(DROP_TABLE_ORDER);
			db.execSQL(DROP_TABLE_ORDERITEM);
		}
	}
}
