package com.example.register_form2.model;

import java.util.List;
import java.util.Objects;

public class Ingredients {
    private List<Malt> malt;
    private List<Hops> hops;

    public Ingredients() {
    }

    public List<Malt> getMalt() {
        return malt;
    }

    public void setMalt(List<Malt> malt) {
        this.malt = malt;
    }

    public List<Hops> getHops() {
        return hops;
    }

    public void setHops(List<Hops> hops) {
        this.hops = hops;
    }

    @Override
    public String toString() {
        return "malt=" + malt +
                ", hops=" + hops +
                '}';
    }
}