package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.helloworld.databinding.ActivityDetailedBinding;

public class DetailedActivity extends AppCompatActivity {
    private ActivityDetailedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailedBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        Intent intent = getIntent();
        if (intent != null) {
            String data = intent.getStringExtra("number");
            binding.tvDetail.setText(data);
        }
    }
}