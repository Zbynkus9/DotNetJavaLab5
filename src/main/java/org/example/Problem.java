package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Problem {
    public int n;
    public int seed;
    public int lowerBound;
    public int upperBound;
    public List<Item> items;

    public Problem(int n, int seed, int lowerBound, int upperBound) {
        this.n = n;
        this.seed = seed;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.items = new ArrayList<>();

        Random random = new Random(seed);
        int range = upperBound - lowerBound + 1;

        for (int i = 0; i < n; i++) {
            int value = random.nextInt(range) + lowerBound;
            int weight = random.nextInt(range) + lowerBound;
            items.add(new Item(i, weight, value));
        }
    }


    public Result Solve(int capacity) {
        List<Item> sortedItems = new ArrayList<>(items);
        sortedItems.sort((i1, i2) -> {
            double ratio1 = (double) i1.value / i1.weight;
            double ratio2 = (double) i2.value / i2.weight;
            return Double.compare(ratio2, ratio1);
        });

        Result result = new Result();
        int currentCapacity = capacity;

        for (Item item : sortedItems) {
            while (currentCapacity >= item.weight) {
                result.addItem(item);
                currentCapacity -= item.weight;
            }
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : items) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }
}
