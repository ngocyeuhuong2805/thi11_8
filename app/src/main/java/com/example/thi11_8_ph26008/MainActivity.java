package com.example.thi11_8_ph26008;

import static com.example.thi11_8_ph26008.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.thi11_8_ph26008.adapter.SpAdapter;
import com.example.thi11_8_ph26008.api.ApiService;
import com.example.thi11_8_ph26008.models.Spmodel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcv;
    private SpAdapter adapter;
    private List<Spmodel> list;
    private Button btnThemData;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);



        rcv = findViewById(id.rcv);
        list = new ArrayList<>();
        adapter = new SpAdapter(this);
        rcv.setAdapter(adapter);
        btnThemData = findViewById(id.btnThemData);
        btnThemData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThemDataActivity.class);
                startActivity(intent);

            }
        });

        getAllData();
    }

    private void getAllData() {
        ApiService.apiService.GetDataMockApi().enqueue(new Callback<List<Spmodel>>() {
            @Override
            public void onResponse(Call<List<Spmodel>> call, Response<List<Spmodel>> response) {
                Log.d("aaa", response.body().toString());
                list = response.body();
                adapter.setData(list);
                rcv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Spmodel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "get Data Fail", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    protected void onResume() {

        super.onResume();
        getAllData();

    }
}