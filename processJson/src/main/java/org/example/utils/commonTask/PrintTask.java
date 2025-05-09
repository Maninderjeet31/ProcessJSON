package org.example.utils.commonTask;

import org.example.model.Items;
import org.example.model.Order;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Performs the task of printing the details of items and total sum.
 */
public class PrintTask {

    /**
     * Printing item details.
     *
     * @param i The item inside an order.
     * @param priceTotal The total price of all individual items.
     * @return A summary of totals and items details
     */
    public void printOrder(Items i, double priceTotal){
        //Printing it in receipt format

        //Item details
        System.out.printf("%-15s %5s %10s %10s%n", "Item", "Qty", "Price", "Total");
        System.out.println("--------------------------------------------");
        System.out.printf("%-15s %5d %10.2f %10.2f%n",
                i.getProduct(), i.getQuantity(), i.getPrice(), priceTotal);

        //Total amount per item
        System.out.println("--------------------------------------------");
        System.out.printf("%-31s %11.2f", "Total Amount:", priceTotal);
        System.out.printf("%n%n");
    }

    /**
     * Printing item's breif summary.
     *
     * @param items The items List.
     * @return A breif of items.
     * @throws IllegalArgumentException if items list is null.
     */
    public void itemsSummary(List<Items> items) {

        if (items.isEmpty()){
            throw new IllegalArgumentException("Items list provided is null. Nothing to print.");
        }

        System.out.println("----------------------- ITEMS DETAIL -----------------------");
        for (Items i: items) {
            //Item details
            System.out.printf("%-15s %5s %10s %10s%n", "Item", "Qty", "Price", "Total");
            System.out.println("--------------------------------------------");
            System.out.printf("%-15s %5d %10.2f %10.2f%n",
                    i.getProduct(), i.getQuantity(), i.getPrice(), (i.getPrice()*i.getQuantity()));
        }
    }

    /**
     * Printing revenue generated on Shipped items.
     *
     * @param shippedOrder The items List with status as SHIPPED.
     * @param totalRevenue To store total revenue generated value in.
     * @return A Prints the values as a receipt.
     * @throws IllegalArgumentException if items list is null.
     */
    public void printRevenue(List<Order> shippedOrder, double totalRevenue){
        if (shippedOrder.isEmpty()){
            throw new IllegalArgumentException("No item found inside list. No revenue generated.");
        }

        System.out.println("\n\n------------- REVENUE GENERATED ------------");
        totalRevenue += shippedOrder.stream()
                .flatMap(or -> or.getItems().stream())
                .reduce(0.0, (revTotal, item) ->
                                revTotal + (item.getQuantity() * item.getPrice()),
                        Double::sum);
        System.out.printf("%-10s %8.2f%n","REVENUE EARNED FROM SHIPPED ITEMS:", totalRevenue);
    }
}
