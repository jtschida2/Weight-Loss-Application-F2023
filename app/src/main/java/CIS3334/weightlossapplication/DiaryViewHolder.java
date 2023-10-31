package CIS3334.weightlossapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//declarations of UI variables and ViewHolder piece of
//recyclerview structure
public class DiaryViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textViewName, textViewProtein, textViewCalories;

    //defines UI elements of Diary Page
    public DiaryViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        textViewName = itemView.findViewById(R.id.mealName);
        textViewProtein = itemView.findViewById(R.id.ProteinItemView);
        textViewCalories = itemView.findViewById(R.id.CaloriesItemView);
    }
}
