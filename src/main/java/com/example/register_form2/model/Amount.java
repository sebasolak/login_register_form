package com.example.register_form2.model;

import java.util.Objects;

public class Amount {
    private String value;
    private String unit;

    public Amount() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "value='" + value + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
