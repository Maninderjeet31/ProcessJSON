package org.example.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.Order;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Performs the task of mapping JSON string to a Java class.
 */
public class Conversion {

    /**
     * Reads the JSON and converts to a JAVA class.
     *
     * @param orderJson The JSON string to be read and converted
     * @return A List of type Order
     * @throws IllegalArgumentException if JSON is null or is empty
     */
    public List<Order> processJson(String orderJson) {

        if (orderJson.isBlank() || orderJson == null){
            throw new IllegalArgumentException("Input JSON must not be null, empty, or blank.");
        }
        //Initializing an empty response ArrayList to return
        List<Order> response = new ArrayList<>();

        //Gson object to process mapping from JSON
        Gson gson = new Gson();

        // Create a List<Order> by utilizing type interface
        Type orderListType = new TypeToken<List<Order>>() {}.getType();

        //Converting and assigning Order List to response
        response = gson.fromJson(orderJson, orderListType);

        return response;

    }
}
