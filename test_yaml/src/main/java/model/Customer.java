package model;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */
public class Customer {
    private int id;
    private String name;
    private List<String> names;
    private Customer1 customer1;

    public int getId() {
        return id;
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

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public Customer1 getCustomer1() {
        return customer1;
    }

    public void setCustomer1(Customer1 customer1) {
        this.customer1 = customer1;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", names=" + names +
                ", customer1=" + customer1.toString() +
                '}';
    }
}
