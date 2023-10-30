package CIS3334.weightlossapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    Button logoutButton;
    FirebaseUser user;
    TextView textViewSteps, textViewCalories, textViewGoalCalories, userDetails;
    Button viewDiaryButton, addFoodButton;
    ProgressBar progressBarCalories;
    EditText editTextServings, editTextCalories, editTextProtein, editTextFoodName;
    Double calorieTracker = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupDiaryButton();
        setupProgressBar();
        setupTextViews();
        setupAddFoodButton();
        setupEditText();
        setupFirebaseAuth();
    }

    private void setupFirebaseAuth(){
        auth = FirebaseAuth.getInstance();
        logoutButton = findViewById(R.id.buttonLogout);
        userDetails = findViewById(R.id.textViewUserDetails);
        user = auth.getCurrentUser();
        if(user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
        else{
            userDetails.setText(user.getEmail());
        }

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void setupEditText(){
        editTextCalories = findViewById(R.id.editTextCalories);
        editTextProtein = findViewById(R.id.editTextProtein);
        editTextServings = findViewById(R.id.editTextServings);
        editTextFoodName = findViewById(R.id.editTextFoodName);
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

    private void setupAddFoodButton(){
        addFoodButton = findViewById(R.id.buttonAddFood);
        //addFoodButton.setEnabled(false);
        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CIS3334", "Button Add Food Clicked");
                Food newFood = new Food(editTextFoodName.toString(),
                        Double.parseDouble(editTextCalories.getText().toString()),
                        Double.parseDouble(editTextProtein.getText().toString()),
                        Double.parseDouble(editTextServings.getText().toString())
                );
                calorieTracker += newFood.getCalories();
                textViewGoalCalories.setText(calorieTracker.toString());


                editTextFoodName.setText("");
                editTextCalories.setText("");
                editTextProtein.setText("");
                editTextServings.setText("");
            }
        });
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
                myRef.setValue("Hello Worl");
            }
        });
    }
}