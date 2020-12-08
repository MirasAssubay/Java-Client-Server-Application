package sample;

import java.util.ArrayList;

public class Alcohol {
    private int id;
    private String name;
    private int strength;
    private String type;
    private int amount;
    private int cost;
    private boolean basket = false;

    public Alcohol() {
    }

    public Alcohol(String name, int strength, String type, int amount, int cost) {
        this.name = name;
        this.strength = strength;
        this.type = type;
        this.amount = amount;
        this.cost = cost;
    }

    public Alcohol(int id, String name, int strength, String type, int amount, int cost) {
        this.id = id;
        this.name = name;
        this.strength = strength;
        this.type = type;
        this.amount = amount;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public boolean isBasket() { return basket; }

    public void setBasket(boolean basket) { this.basket = basket;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Alcohol{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", strength=" + strength +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", cost=" + cost +
                '}';
    }
}
