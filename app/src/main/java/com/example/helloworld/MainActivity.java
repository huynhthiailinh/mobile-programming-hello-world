package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.helloworld.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MyViewModel model;

    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                arrayList);
        binding.lvCount.setAdapter(arrayAdapter);

        model = new ViewModelProvider(this).get(MyViewModel.class);
        model.getNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.tvCount.setText("" + integer);
                arrayList.add("" + integer);
                arrayAdapter.notifyDataSetChanged();
            }
        });

        binding.btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.increaseNumber();
            }
        });

        binding.btnDown.setOnClickListener(onClickButtonDown);

        binding.lvCount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                arrayList.remove(i);
                arrayAdapter.notifyDataSetChanged();
            }
        });

        binding.lvCount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
                intent.putExtra("number", arrayList.get(i));
                startActivity(intent);
            }
        });
    }

    private final View.OnClickListener onClickButtonDown = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            model.decreaseNumber();
        }
    };
}