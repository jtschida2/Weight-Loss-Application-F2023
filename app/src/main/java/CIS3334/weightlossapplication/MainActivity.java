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

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference myRef;
    TextView textViewSteps, textViewCalories, textViewGoalCalories, userDetails;
    Button viewDiaryButton, addFoodButton, newMeal, logoutButton;
    ProgressBar progressBarCalories;
    EditText editTextServings, editTextCalories, editTextProtein, editTextFoodName;
    Double calorieTracker = 0.0;
    List<Food> foodList;
    int mealCounter = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foodList = new ArrayList<>();
        setupProgressBar();
        setupTextViews();
        setupViewDiary();
        setupAddFoodButton();
        setupEditText();
        setupFirebaseAuth();
        setupNewMealButton();
        database = FirebaseDatabase.getInstance();
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
        progressBarCalories.setMax(2000);
        progressBarCalories.setProgress(0);
    }

    private void setupViewDiary(){
        viewDiaryButton = findViewById(R.id.ButtonViewDiary);
        viewDiaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Diary.class);
                ArrayList<Food> iFoodList = (ArrayList<Food>) foodList;
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)iFoodList);
                intent.putExtra("BUNDLE",args);
                startActivity(intent);
            }
        });
    }

    private void setupAddFoodButton(){
        addFoodButton = findViewById(R.id.buttonAddFood);
        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CIS3334", "Button Add Food Clicked");
                Food newFood = new Food(editTextFoodName.getText().toString(),
                        Double.parseDouble(editTextCalories.getText().toString()),
                        Double.parseDouble(editTextProtein.getText().toString()),
                        Double.parseDouble(editTextServings.getText().toString())
                );

                foodList.add(newFood);

                if(mealCounter ==1) {
                    myRef = database.getReference("1-Breakfast, " + LocalDate.now().toString());
                    myRef.push().setValue(newFood);
                }
                else if(mealCounter ==2) {
                    myRef = database.getReference("2-Lunch, " + LocalDate.now().toString());
                    myRef.push().setValue(newFood);
                }
                else if(mealCounter ==3) {
                    myRef = database.getReference("3-Dinner, " + LocalDate.now().toString());
                    myRef.push().setValue(newFood);
                }

                calorieTracker += newFood.getCalories();
                progressBarCalories.setProgress((int)Math.round(calorieTracker));
                textViewGoalCalories.setText(calorieTracker.toString());


                editTextFoodName.setText("");
                editTextCalories.setText("");
                editTextProtein.setText("");
                editTextServings.setText("");
            }
        });
    }

    private void setupNewMealButton(){
        newMeal = findViewById(R.id.newMeal);
        newMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mealCounter == 1 || mealCounter ==2){
                    mealCounter++;
                }
                else{
                    mealCounter = 1;
                }
            }
        });
    }
}