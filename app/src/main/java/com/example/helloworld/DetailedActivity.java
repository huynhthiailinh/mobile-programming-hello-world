package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.helloworld.databinding.ActivityDetailedBinding;

public class DetailedActivity extends AppCompatActivity {
    private static final int DETAILED_ACTIVITY_REQUEST_CODE = 1;

    private ActivityDetailedBinding binding;
    private String index = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailedBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        Intent intent = getIntent();
        if (intent != null) {
            index = intent.getStringExtra("index");
            String data = intent.getStringExtra("number");
            binding.etNumber.setText(data);
        }

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = binding.etNumber.getText().toString();
                Intent intent1 = new Intent();
                intent1.putExtra("indexPassBack", index);
                intent1.putExtra("numberPassBack", data);
                setResult(DETAILED_ACTIVITY_REQUEST_CODE, intent1);
                finish();
            }
        });
    }
}