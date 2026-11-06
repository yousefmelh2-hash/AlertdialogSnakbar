package com.example.yousef;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private String currentGoal = "";
    Button btnCompleteGoal, btnClearGoal, btnEditGoal;
    TextView tvGoal;
    ConstraintLayout mainRoot;
    //-----------------------------------------------------
    ConstraintLayout feedbackBox; // XML message
    ImageView feedbackIcon; //  XML message
    TextView feedbackText; // XML message

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        setListeners();
    }

    private void setListeners() {
        btnEditGoal.setOnClickListener(v -> openGoalDialog());
        btnClearGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentGoal = tvGoal.getText().toString();
                tvGoal.setText("");
                String prevoiusGoal = currentGoal;
                if (!currentGoal.isEmpty()){
                    Snackbar.make(mainRoot, "Goal deleted", Snackbar.LENGTH_LONG)
                            .setAction("UNDO", v -> {
                                currentGoal = prevoiusGoal;
                                tvGoal.setText(currentGoal);
                            })
                            .show();

                }
            }
        });
        btnCompleteGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentGoal = tvGoal.getText().toString();
                if (currentGoal.isEmpty()){
                    showFeedback("Please set a goal first", R.drawable.ic_warning);
                }
                else {
                    showFeedback("Goal completed", R.drawable.ic_success);
                }
            }
        });
    }

    private void initComponents() {
        btnCompleteGoal = findViewById(R.id.btnCompleteGoal);
        btnClearGoal = findViewById(R.id.btnClearGoal);
        btnEditGoal = findViewById(R.id.btnEditGoal);
        tvGoal = findViewById(R.id.tvGoal);
        mainRoot = findViewById(R.id.mainRoot);
        feedbackBox = findViewById(R.id.feedbackBox);
        feedbackIcon = findViewById(R.id.ivFeedbackIcon);
        feedbackText = findViewById(R.id.tvFeedbackText);
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
    private void showFeedback(String message, int iconId) {
        feedbackIcon.setImageResource(iconId);
        feedbackText.setText(message);
        feedbackBox.setVisibility(View.VISIBLE);
        feedbackBox.postDelayed(
                () -> feedbackBox.setVisibility(View.GONE), 3000);



    }
}