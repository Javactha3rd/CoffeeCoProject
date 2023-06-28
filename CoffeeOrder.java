/**
 * @author Yousif Sabri
 * @Version 1.0
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CoffeeOrder {
    private List<Coffee> coffees;
    private LocalDateTime orderDate;

    public CoffeeOrder() {
        coffees = new ArrayList<Coffee>();
        orderDate = LocalDateTime.now();
    }

    /**
     * @param orderDate
     */
    public CoffeeOrder(LocalDateTime orderDate) {
        coffees = new ArrayList<Coffee>();
        this.orderDate = orderDate;
    }

    public void addCoffee(Coffee c) {
        coffees.add(c);
    }

    public List<Coffee> getCoffees() {
        return coffees;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * @Return total: this is the total price of a specific coffee
     */
    public double getTotal() {
        double total = 0;
        for (Coffee coffee : coffees) {
            total += coffee.getCost();
        }
        return total;
    }

    /**
     * @Return order.toString(): this is the order string which incorporates the date, price, and coffee name
     */
    public String printOrder() {
        StringBuilder order = new StringBuilder("ORDER RECEIPT\n");
        order.append(String.format("Timestamp: %s%n", orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mma"))));
        for (int i = 0; i < coffees.size(); i++) {
            Coffee coffee = coffees.get(i);
            order.append(String.format("Item %d: %s - %.2f%n", i + 1, coffee.printCoffee(), coffee.getCost()));
        }
        order.append(String.format("TOTAL = %.2f%n", getTotal()));
        return order.toString();
    }
}
