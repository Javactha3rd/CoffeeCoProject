/**
 * @author Yousif Sabri
 * @Version 1.0
 */

import java.util.ArrayList;
import java.util.List;

public class WithFlavor extends CoffeeDecorator {

    enum Syrup {
        CARAMEL,
        MOCHA,
        VANILLA
    }

    private Syrup flavor;

    /**
     * @param c,s : c is the coffee object, and s is the specific syrup used.
     */
    public WithFlavor(Coffee c, Syrup s) {
        super(c);
        flavor = s;
    }

    /**
     * @return super.getCost() + 0.35: the price of the coffee object passed in and the price of the specific syrup used.
     */
    @Override
    public double getCost() {
        return super.getCost() + 0.35;
    }

    /**
     * @return ingredients: Returns the ingredients used inside the coffee object and adds the syrup used as well.
     */
    @Override
    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>(super.getIngredients());
        ingredients.add(flavor + " Syrup");
        return ingredients;
    }

    /**
     * @return super.printCoffee() + " with " + flavor: Returns the name of the coffee object and adds the specific flavor used from the syrups
     */
    @Override
    public String printCoffee() {
        return super.printCoffee() + " with " + flavor;
    }
}
