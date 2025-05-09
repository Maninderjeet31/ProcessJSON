package org.example.utils;

import org.example.model.Items;
import org.example.model.Order;
import org.example.utils.commonTask.PrintTask;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * First class called to delegate and perform the required task.
 */
public class PrintJson {

    private static final String ORDER_SHIPPED = "shipped";

    PrintTask pTask = new PrintTask();
    double grandTotal = 0.0;

    /**
     * Computes and prints order details including items, pricing, and totals.
     *
     * @param order The order list provided
     * @return An OrderSummary containing totals and items details
     * @throws IllegalArgumentException if order is null or contains no items
     */
    public void computeOrders(Order order){

        // Null Check Validation
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null or empty.");
        }

        // Fetching the list of items per Order object
        List<Items> items = order.getItems();
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("No item provided in Orders. Please provide atleast one item.");
        }

        //Initial total-values setup
        double priceTotal = 0.0;
        double grandTotal = 0.0;

        for (Items i : items){

            //Prints customer details in header of receipt.
            //Calling from outside to not repeat with every iterate while printing.
            printCustomerDetails(order);

            //Sum the total amount per Item
            priceTotal += (i.getPrice() * i.getQuantity());

            //calling a common printing method for all tasks
            pTask.printOrder(i, priceTotal);

            //Summing the total amount for the customer to pay for order
            grandTotal += priceTotal;
        }

        //Print the grand total of all the Items in all the Orders.
        System.out.printf("%-31s %11.2f %n%n%n", "TOTAL SALE AMOUNT:", grandTotal);
    }

    /**
     * Prints items details and revenue generated from SHIPPED items in Order
     *
     * @param orders The order list provided
     * @return Item details containing totals and information
     * @throws IllegalArgumentException if order is null or contains no items
     */
    public void computeItemsAndRevenue(List<Order> orders){
        double totalRevenue = 0.0;

            //checking if ORDER List is not empty
        if (!orders.isEmpty()) {

            //Fetching all items from all orders
            List<Items> itemsList = orders.stream()
                    .flatMap(o -> o.getItems().stream())
                    .collect(Collectors.toList());

            //Print Items Summary from collected items
            pTask.itemsSummary(itemsList);

            //Filter items that have "Shipped" status
            List<Order> shippedOrder = orders.stream()
                    .filter(o -> o.getStatus().equals(ORDER_SHIPPED))
                    .collect(Collectors.toList());

            //If shipped items found
            if (!shippedOrder.isEmpty()) {
                //Calculate the revenue earned from shipped items
                pTask.printRevenue(shippedOrder, totalRevenue);
            } else {
                //Notifies of no shipped item found
                System.out.println("No order found with item-status: SHIPPED.");
            }
        } else {
            throw new IllegalArgumentException("The provided list is empty. Please try again.");
        }
    }

    /**
    * Prints Customer's details for the order
    * once before the order details prints
    *
    * @param order The order provided
    * @return Returning order id and Customer's name related to the order
    */
    private void printCustomerDetails(Order order) {
        //Customer details
        System.out.println("========================================");
        System.out.printf("Order Id: %-20s%n", order.orderId);
        System.out.printf("Customer Name: %-20s%n", order.getCustomer());
        System.out.println("========================================");
    }

}
