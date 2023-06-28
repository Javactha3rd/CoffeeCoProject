/**
 * @author Yousif Sabri
 * @Version 1.0
 * @date 6/28/2023
 * CS 160 Lab Summer 2023
 */

import java.util.ArrayList;
import java.util.List;

public class BlackCoffee implements Coffee {

    @Override
    public double getCost() {
        return 1.0;
    }

    @Override
    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Black Coffee");
        return ingredients;

    }

    @Override
    public String printCoffee() {
        return "A black coffee";
    }
}
