package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.helloworld.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        binding.btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(binding.tvCount.getText().toString());
                binding.tvCount.setText("" + ++count);
            }
        });

        binding.btnDown.setOnClickListener(onClickButtonDown);
    }

    private final View.OnClickListener onClickButtonDown = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int count = Integer.parseInt(binding.tvCount.getText().toString());
            binding.tvCount.setText("" + --count);
        }
    };
}