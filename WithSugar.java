/**
 * @author Yousif Sabri
 * @Version 1.0
 */

import java.util.ArrayList;
import java.util.List;

public class WithSugar extends CoffeeDecorator {
    public WithSugar(Coffee c) {
        super(c);
    }

    /**
     * @return super.getCost(): The price of the coffee object passed in and adds 0.15.
     */
    @Override
    public double getCost() {
        return super.getCost() + 0.15;
    }

    /**
     * @return ingredients: Returns the ingredients used inside the coffee object and adds Sugar.
     */
    @Override
    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>(super.getIngredients());
        ingredients.add("Sugar");
        return ingredients;
    }

    /**
     * @return super.printCoffee() + " with " + flavor: Returns the name of the coffee object and adds with sugar.
     */
    @Override
    public String printCoffee() {
        return super.printCoffee() + " with sugar";
    }
}
