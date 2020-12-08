package sample;

public class Order extends Alcohol {
    private double payment;
    private String address;

    public Order() {
    }

    public Order(int id, String name, int strength, String type, int amount, int cost, double payment, String address) { // from db
        super(id, name, strength, type, amount, cost);
        this.payment = payment;
        this.address = address;
    }

    public Order(String name, int strength, String type, int amount, int cost, double payment, String address) { //to db
        super(name, strength, type, amount, cost);
        this.payment = payment;
        this.address = address;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return super.toString() + "Order{" +
                "payment=" + payment +
                ", address='" + address + '\'' +
                '}';
    }
}
