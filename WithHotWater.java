/**
 * @author Yousif Sabri
 * @Version 1.0
 */

import java.util.ArrayList;
import java.util.List;

public class WithHotWater extends CoffeeDecorator {
    public WithHotWater(Coffee c) {
        super(c);
    }

    /**
     * @return super.getCost(): The price of the coffee object passed in.
     */
    @Override
    public double getCost() {
        return super.getCost();
    }

    /**
     * @return ingredients: Returns the ingredients used inside the coffee object and adds Hot Water.
     */
    @Override
    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>(super.getIngredients());
        ingredients.add("Hot Water");
        return ingredients;
    }

    /**
     * @return super.printCoffee() + " with " + flavor: Returns the name of the coffee object and adds with hot water
     */
    @Override
    public String printCoffee() {
        return super.printCoffee() + " with hot water";
    }
}
