package CIS3334.weightlossapplication;

import java.io.Serializable;

public class Food implements Serializable {
    private String foodName;
    private Double serving, protein, caloriesPerServing;

    public Food(String foodName, Double caloriesPerServing, Double protein, Double serving){
        this.foodName = foodName;
        this.caloriesPerServing = caloriesPerServing;
        this.protein = protein;
        this.serving = serving;
    }

    public Double getProtein(){
        Double total = serving * protein;
        return total;
    }

    public Double getCalories(){
        Double total = serving * caloriesPerServing;
        return total;
    }

    public Double getServing() {
        return serving;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public void setServing(Double serving) {
        this.serving = serving;
    }

    public void setCaloriesPerServing(Double caloriesPerServing) {
        this.caloriesPerServing = caloriesPerServing;
    }
}
