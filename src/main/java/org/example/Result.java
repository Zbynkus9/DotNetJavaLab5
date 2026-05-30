package org.example;

import java.util.ArrayList;
import java.util.List;

public class Result {
    public List<Item> itemsInKnapsack;
    public int totalWeight;
    public int totalValue;

    public Result() {
        this.itemsInKnapsack = new ArrayList<>();
        this.totalWeight = 0;
        this.totalValue = 0;
    }

    public void addItem(Item item) {
        this.itemsInKnapsack.add(item);
        this.totalWeight += item.weight;
        this.totalValue += item.value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-------\n");
        for (Item item : itemsInKnapsack) {
            sb.append(item.toString()).append("\n");
        }
        sb.append("Weight: ").append(totalWeight).append("\n");
        sb.append("Value: ").append(totalValue);
        return sb.toString();
    }
}
