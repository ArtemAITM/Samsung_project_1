package com.example.samsung_project_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.samsung_project_1.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(v -> {
            Intent i;
            i = new Intent(MainActivity.this, secondActivity.class);
            String func = binding.function.getText().toString();
            if (func.isEmpty())
                Toast.makeText(this, "Уравнение не задано!", Toast.LENGTH_SHORT).show();
            else {
                i.putExtra("equation", func);
                startActivity(i);
            }
        });
    }
}
