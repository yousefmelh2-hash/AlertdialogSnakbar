package com.example.yousef;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private String currentGoal = "";
    Button btnCompleteGoal, btnClearGoal, btnEditGoal;
    TextView tvGoal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        setListeners();
    }

    private void setListeners() {
        btnEditGoal.setOnClickListener(v -> openGoalDialog());
    }

    private void initComponents() {
        btnCompleteGoal = findViewById(R.id.btnCompleteGoal);
        btnClearGoal = findViewById(R.id.btnClearGoal);
        btnEditGoal = findViewById(R.id.btnEditGoal);
        tvGoal = findViewById(R.id.tvGoal);

    }
   private void openGoalDialog(){
       View dialogView = getLayoutInflater()
               .inflate(R.layout.dialog_goal, null);
       EditText etGoal = dialogView.findViewById(R.id.etGoal);
       new AlertDialog.Builder(this)
               .setTitle("My goal")
               .setView(dialogView)
               .setNegativeButton("Cancel", null)
               .setPositiveButton("Save", (dialog, which) -> {
                if (!etGoal.getText().toString().isEmpty()){
                    currentGoal = etGoal.getText().toString();
                    tvGoal.setText(currentGoal);
                }
               })
        .show();
    }
}