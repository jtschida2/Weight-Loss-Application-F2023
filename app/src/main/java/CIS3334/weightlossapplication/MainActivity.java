package CIS3334.weightlossapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    TextView textViewSteps, textViewCalories, textViewGoalCalories;
    Button viewDiaryButton;
    ProgressBar progressBarCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupDiaryButton();
        setupProgressBar();
        setupTextViews();
    }


    private void setupTextViews() {
        textViewCalories = findViewById(R.id.TextViewCalories);
        textViewSteps = findViewById(R.id.TextViewSteps);
        textViewGoalCalories = findViewById(R.id.TextViewGoalCalories);
    }

    private void setupProgressBar() {
        progressBarCalories = findViewById(R.id.progressBarCalories);
        progressBarCalories.setMax(2000);                                    //Integer.parseInt(textViewGoalCalories.toString()));  //sets max value of progress bar
        progressBarCalories.setProgress(50);                           //Integer.parseInt(textViewCalories.toString()));
    /*TODO *Find out code for progress bar, and code ability for it to be modified using:
            -STEPS
            -FOOD EATEN
            -GOAL - food + exercise
    */
    }

    private void setupDiaryButton() {
        viewDiaryButton = findViewById(R.id.ButtonViewDiary);
        viewDiaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // variables for the Firebase database
                Log.d("CIS3334", "Connect Database");
                FirebaseDatabase database;
                DatabaseReference myRef;
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("Oct-24");
                myRef.setValue("Hello World");
            }
        });
    }
}