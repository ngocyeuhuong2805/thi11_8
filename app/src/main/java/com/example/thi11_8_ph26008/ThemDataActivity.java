package com.example.thi11_8_ph26008;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thi11_8_ph26008.adapter.SpAdapter;
import com.example.thi11_8_ph26008.api.ApiService;
import com.example.thi11_8_ph26008.models.Spmodel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemDataActivity extends AppCompatActivity {
    private EditText edName, edDes, edImage, edColor, edPrice;
    private Button btnThem;

    private List<Spmodel> list;
    private SpAdapter adapter;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_data);
        edName = findViewById(R.id.ed_name);
        edDes = findViewById(R.id.ed_description);
        edImage = findViewById(R.id.ed_img);
        edColor = findViewById(R.id.ed_color);
        edPrice = findViewById(R.id.ed_price);
        btnThem = findViewById(R.id.btnThemData);


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemData();
            }
        });

    }

    private void ThemData() {
        String name = edName.getText().toString().trim();
        String des = edDes.getText().toString().trim();
        String img = edImage.getText().toString().trim();
        String color = edColor.getText().toString().trim();
        int price = Integer.parseInt(edPrice.getText().toString().trim());
        Spmodel sp = new Spmodel(name, img, des, color, price);

        ApiService.apiService.ThemDataToSever(sp).enqueue(new Callback<Spmodel>() {
            @Override
            public void onResponse(Call<Spmodel> call, Response<Spmodel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(ThemDataActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                }


                onBackPressed();




            }

            @Override
            public void onFailure(Call<Spmodel> call, Throwable t) {
                Toast.makeText(ThemDataActivity.this, "Eror", Toast.LENGTH_SHORT).show();

            }
        });
    }
}