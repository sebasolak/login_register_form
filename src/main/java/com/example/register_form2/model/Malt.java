package com.example.register_form2.model;

import java.util.Objects;

public class Malt {

    private String name;
    private Amount amount;

    public Malt() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}