package com.example.recycleviewexample;

public class FruitData {
    public String mName;
    public String mVitaminC;

    public FruitData(String n, String d) {
        mName = n;
        mVitaminC = d;
    }

    static final FruitData[] getList1() {
        return new FruitData[] {
                new FruitData("Apricots", "5000"),
                new FruitData("Apple", "5000"),
                new FruitData("Banana", "10.000"),
                new FruitData("Blackberries", "150.000"),
                new FruitData("Cherries", "10.000"),
                new FruitData("Grapefruit", "40.000"),
                new FruitData("Grapes", "3000")
        };
    }

    static final FruitData[] getList2() {
        return new FruitData[] {
                new FruitData("Kiwi", "70.000"),
                new FruitData("Lemon", "40.000"),
                new FruitData("Lychee", "23.000"),
                new FruitData("Mango", "23.000"),
                new FruitData("Orange", "49.000"),
                new FruitData("Peach", "7000"),
                new FruitData("Pear", "4000"),
                new FruitData("Pineapple", "25.000"),
                new FruitData("Plum", "5000"),
                new FruitData("Pumpkin", "16.000"),
                new FruitData("Raspberries", "5000")
        };

    }
}
