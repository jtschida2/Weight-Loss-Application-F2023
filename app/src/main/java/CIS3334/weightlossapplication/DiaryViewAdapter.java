package CIS3334.weightlossapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DiaryViewAdapter extends RecyclerView.Adapter<DiaryViewHolder> {

    Context context;
    ArrayList<Food> foodItems;

    public DiaryViewAdapter(Context context, ArrayList<Food> foodItems){
        this.context = context;
        this.foodItems = foodItems;
    }

    @NonNull
    @Override
    public DiaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DiaryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryViewHolder holder, int position) {
        holder.textViewProtein.setText("Protein: " + foodItems.get(position).getProtein().toString());
        holder.textViewName.setText(foodItems.get(position).getFoodName());
        holder.textViewCalories.setText("Calories: " + foodItems.get(position).getCalories().toString());
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }
}
