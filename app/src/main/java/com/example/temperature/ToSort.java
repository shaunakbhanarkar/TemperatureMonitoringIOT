package com.example.temperature;

public class ToSort implements Comparable<ToSort> {

    private Float val;
    private String id;

    public ToSort(Float val, String id){

    }

    @Override
    public int compareTo(ToSort f) {

        if (val.floatValue() > f.val.floatValue()) {
            return 1;
        }
        else if (val.floatValue() <  f.val.floatValue()) {
            return -1;
        }
        else {
            return 0;
        }

    }

    @Override
    public String toString(){
        return this.id;
    }
}