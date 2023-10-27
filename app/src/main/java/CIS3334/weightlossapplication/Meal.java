package CIS3334.weightlossapplication;

import java.util.ArrayList;
import java.util.List;

public class Meal {
    List<Food> foodList = new ArrayList<>();

    public Meal(List<Food> foodList){
        this.foodList = foodList;
    }
}
