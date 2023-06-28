/**
 * @author Yousif Sabri
 * @Version 1.0
 */

import java.util.ArrayList;
import java.util.List;

public class Espresso implements Coffee {

    /**
     * @Return 1.75: returns the specific price of this ingredient
     */
    @Override
    public double getCost() {
        return 1.75;
    }

    /**
     * @Return ingredients: adds Espresso to the ingredients arraylist and returns it
     */
    @Override
    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Espresso");
        return ingredients;
    }

    /**
     * @Return An espresso: returns the name of the specific ingredient name
     */
    @Override
    public String printCoffee() {
        return "An espresso";
    }
}

