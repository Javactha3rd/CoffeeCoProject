/**
 * @author Yousif Sabri
 * @Version 1.0
 * @see WithFlavor,WithMilk,WithSugar,WithHotWater,WithWhippedCream,Espresso,BlackCoffee
 */

import java.util.List;

public interface Coffee {
    /**
     * @return double: The price of a specific coffee.
     */
    public double getCost();

    /**
     * @return List: The ingredients of a specific coffee.
     */
    public List<String> getIngredients();

    /**
     * @return String: String of the name of a specific coffee.
     */
    public String printCoffee();
}
