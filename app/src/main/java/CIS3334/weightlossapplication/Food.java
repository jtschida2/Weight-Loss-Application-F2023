package CIS3334.weightlossapplication;

public class Food {
    private String foodName;
    private Double serving, protein, carb, sodium, caloriesPerServing;

    public Food(String foodName, Double caloriesPerServing, Double protein, Double carb, Double sodium, Double serving){
        this.foodName = foodName;
        this.caloriesPerServing = caloriesPerServing;
        this.protein = protein;
        this.carb = carb;
        this.sodium = sodium;
        this.serving = serving;
    }

    public Double getProtein(){
        Double total = serving * protein;
        return total;
    }

    public Double getCarb(){
        Double total = serving * carb;
        return total;
    }

    public Double getSodium(){
        Double total = serving * sodium;
        return total;
    }

    public Double getCalories(){
        Double total = serving * caloriesPerServing;
        return total;
    }


}
