package org.example.model;

public class Ingredients {
    private int water;
    private int sugar;
    private int fructose;
    private String chocolateType; // Type of chocolate for chocolate candies
    private int vanilla;

    public Ingredients() {}

    public Ingredients(int water, int sugar, int fructose, String chocolateType, int vanilla) {
        this.water = water;
        this.sugar = sugar;
        this.fructose = fructose;
        this.chocolateType = chocolateType;
        this.vanilla = vanilla;
    }

    public int getWater() { return water; }
    public void setWater(int water) { this.water = water; }

    public int getSugar() { return sugar; }
    public void setSugar(int sugar) { this.sugar = sugar; }

    public int getFructose() { return fructose; }
    public void setFructose(int fructose) { this.fructose = fructose; }

    public String getChocolateType() { return chocolateType; }
    public void setChocolateType(String chocolateType) { this.chocolateType = chocolateType; }

    public int getVanilla() { return vanilla; }
    public void setVanilla(int vanilla) { this.vanilla = vanilla; }

    @Override
    public String toString() {
        return "Ingredients{" +
                "water=" + water +
                ", sugar=" + sugar +
                ", fructose=" + fructose +
                ", chocolateType='" + chocolateType + '\'' +
                ", vanilla=" + vanilla +
                '}';
    }
}

