package org.example.model;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Items {
    public String product;
    public int quantity;
    public double price;
}
