package CIS3334.weightlossapplication;

import java.io.Serializable;
//Food class which represents food items that the user eats and logs into the program
//The food is modified by this class on display for total calories and protein
public class Food implements Serializable {
    private String foodName;
    private Double serving, protein, caloriesPerServing;
    //Food Constructor for taking in info from the UI
    public Food(String foodName, Double caloriesPerServing, Double protein, Double serving){
        this.foodName = foodName;
        this.caloriesPerServing = caloriesPerServing;
        this.protein = protein;
        this.serving = serving;
    }
    //returns total protein by multiplying servings and protein
    public Double getProtein(){
        Double total = serving * protein;
        return total;
    }
    //returns total calories by multiplying servings and calories
    public Double getCalories(){
        Double total = serving * caloriesPerServing;
        return total;
    }
    //returns serving number
    public Double getServing() {
        return serving;
    }
    //returns name of the inputted food
    public String getFoodName() {
        return foodName;
    }
    }
