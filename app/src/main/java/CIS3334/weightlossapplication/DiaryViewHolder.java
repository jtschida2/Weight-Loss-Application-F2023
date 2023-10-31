package CIS3334.weightlossapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public class DiaryViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView textViewName, textViewProtein, textViewCalories;

    public DiaryViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        textViewName = itemView.findViewById(R.id.mealName);
        textViewProtein = itemView.findViewById(R.id.ProteinItemView);
        textViewCalories = itemView.findViewById(R.id.CaloriesItemView);
    }
}
