package org.example;

public class Item {
    public int id;
    public int weight;
    public int value;

    public Item(int id, int weight, int value) {
        this.id = id;
        this.weight = weight;
        this.value = value;
    }

    @Override
    public String toString() {
        return "No: " + id + " v: " + value + " w: " + weight;
    }
}