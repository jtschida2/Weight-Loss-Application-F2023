package CIS3334.weightlossapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
//class responsible for Diary's UI and displaying the data which is passed in from the main activity
public class Diary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        //gets the arraylist passed in from the other activity, which is displayed by RecyclerView
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<Food> foodItems = (ArrayList<Food>) args.getSerializable("ARRAYLIST");

        //define recyclerView into the UI of Diary Page
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //sets up recycler view so that it displays the food items
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new DiaryViewAdapter(getApplicationContext(),foodItems));
    }
}