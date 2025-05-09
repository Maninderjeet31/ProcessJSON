package org.example.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    public String orderId;
    public String customer;
    public List<Items> items;
    public String status;
}
