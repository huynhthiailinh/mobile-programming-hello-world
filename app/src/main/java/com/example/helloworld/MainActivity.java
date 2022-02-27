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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int DETAILED_ACTIVITY_REQUEST_CODE = 1;

    private ActivityMainBinding binding;
    private TextViewModel tvModel;
    private ListViewModel lvModel;

    private ArrayAdapter<Integer> arrayAdapter;
    private ArrayList<Integer> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        lvModel = new ViewModelProvider(this).get(ListViewModel.class);
        arrayList = (ArrayList<Integer>) lvModel.getList().getValue();
        arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                arrayList);
        binding.lvCount.setAdapter(arrayAdapter);

        tvModel = new ViewModelProvider(this).get(TextViewModel.class);
        tvModel.getNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.tvCount.setText("" + integer);
            }
        });

        lvModel.getList().observe(this, new Observer<List<Integer>>() {
            @Override
            public void onChanged(List<Integer> integers) {
                arrayList = (ArrayList<Integer>) integers;
            }
        });

        binding.btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvModel.increaseNumber();
                lvModel.addToList(tvModel.getNumber().getValue());
                arrayAdapter.notifyDataSetChanged();
            }
        });

        binding.btnDown.setOnClickListener(onClickButtonDown);

        binding.lvCount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
                intent.putExtra("index", "" + i);
                intent.putExtra("number", lvModel.getList().getValue().get(i).toString());
                startActivityForResult(intent, DETAILED_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    private final View.OnClickListener onClickButtonDown = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            tvModel.decreaseNumber();
            lvModel.addToList(tvModel.getNumber().getValue());
            arrayAdapter.notifyDataSetChanged();
        }
    };

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("onActivity ");

        if (requestCode == DETAILED_ACTIVITY_REQUEST_CODE) {
            int index = Integer.parseInt(data.getStringExtra("indexPassBack"));
            int value = Integer.parseInt(data.getStringExtra("numberPassBack"));

            lvModel.updateList(index, value);
            arrayAdapter.notifyDataSetChanged();
        }
    }
}