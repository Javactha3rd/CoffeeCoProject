/**
 * @author Yousif Sabri
 * @Version 1.0
 * @see WithSugar,WithFlavor,WithMilk,WithWhippedCream,WithHotWater
 */

import java.util.List;

public abstract class CoffeeDecorator implements Coffee {
    private Coffee coffee;

    /**
     * @param c: the coffee object name
     */
    public CoffeeDecorator(Coffee c) {
        coffee = c;
    }

    /**
     * @return coffee.getCost: Returns the price of a specific coffee
     */
    public double getCost() {
        return coffee.getCost();
    }

    /**
     * @return List: The ingredients of a specific coffee.
     */
    public List<String> getIngredients() {
        List<String> ingredients = coffee.getIngredients();
        return ingredients;
    }

    /**
     * @return coffee.printCoffee: Returns the string of the decorated coffee
     */
    public String printCoffee() {
        return coffee.printCoffee();
    }
}
