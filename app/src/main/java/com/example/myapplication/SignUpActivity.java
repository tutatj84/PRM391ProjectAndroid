package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    private EditText etUserName;
    private EditText etPass;
    private EditText etName;
    private EditText etPhone;
    private EditText etCity;

    String username;
    String pass;
    String name;
    String phone;
    String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUserName = findViewById(R.id.etUsername);
        etPass = findViewById(R.id.etPass);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etCity = findViewById(R.id.etCity);

    }

    public void signUpC(View view) {
        try {
            username = etUserName.getText().toString();
            pass = etPass.getText().toString();
            name = etName.getText().toString();
            phone = etPhone.getText().toString();
            city = etCity.getText().toString();

            dbHelper = new DBHelper(this, "order_app.db", 2);
            db = dbHelper.getWritableDatabase();
            db.execSQL(DBHelper.INSERT_TABLE_CUSTOMER, new String[]{username, pass, name, phone, city});
            Toast.makeText(this, "Đăng kí thành công!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}