package org.example;

import org.example.model.Order;
import org.example.utils.Conversion;
import org.example.utils.PrintJson;

import java.util.List;

/**
 * Main Class
 */
public class App 
{
    public static void main( String[] args )
    {
        //Order JSON
        String orderJson = "[{\"orderId\":\"1001\",\"customer\":\"Alice\",\"items\":[{\"product\":\"Widget\",\"quantity\":2,\"price\":10.0},{\"product\":\"Gadget\",\"quantity\":1,\"price\":15.0}],\"status\":\"shipped\"},{\"orderId\":\"1002\",\"customer\":\"Bob\",\"items\":[{\"product\":\"Widget\",\"quantity\":1,\"price\":10.0}],\"status\":\"pending\"}]";

        //Utility package class Object to process json and get Order List
        Conversion conversion = new Conversion();

        //PrintJson class object to perform various printing jobs
        PrintJson printJson = new PrintJson();

        //Getting a list of type Order from method in business-logic class
        List<Order> orderList = conversion.processJson(orderJson);

        /*
        - print Order list with total cost and item count included
        - summary of total orders and total revenue from "shipped" orders
         */
        if (!orderList.isEmpty()){
            //Compute orders with total cost and items count
            for (Order order: orderList) {
                printJson.computeOrders(order);
            }
            //Compute the SHIPPED items revenue and summary
            printJson.computeItemsAndRevenue(orderList);
        } else {
            System.out.println("The provided JSON is empty.");
        }

    }
}
