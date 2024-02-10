package com.example.samsung_project_1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.samsung_project_1.databinding.ActivityMainBinding;

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
        binding.info.setOnClickListener(v -> {
            сreateDialog(this);
        });

    }
    public void сreateDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("DesmosOPM")
                .setMessage("тут нет информации о приложение ааххахахаххахаххаххааххахаххахха")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();
    }
}
