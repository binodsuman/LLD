package Decorative_Pattern.Without;

public class Demo {
    public static void main(String[] args) {
        Coffee coffee = new MilkAndSugarCoffee();
        System.out.println(coffee.getDescription() + ": $" + coffee.getCost());

        // Problem: What if someone wants sugar but no milk?
        // We need to create yet another class!
    }
}

interface Coffee{
    int getCost();
    String getDescription();
}

class SimpleCoffee implements Coffee{
    public int getCost(){return 10;}
    public String getDescription(){return "Simple Coffee";}
}

class MileCoffee implements Coffee{
    public int getCost(){return 20;}
    public String getDescription(){return "Coffee with milk";}
}

class MilkAndSugarCoffee implements Coffee {
    public int getCost() { return 30; }
    public String getDescription() { return "Coffee with milk and sugar"; }
}

class MilkSugarVanillaCoffee implements Coffee {
    public int getCost() { return 40; }
    public String getDescription() { return "Coffee with milk, sugar and vanilla"; }
}

//// And many more combinations... This becomes unmaintainable!

/**
 * The Problems with This Approach:
 * Class explosion - Too many classes for combinations
 * Rigid - Can't dynamically add new toppings at runtime
 * Hard to maintain - Changing price of milk affects many classes
 * Not scalable - Adding new topping requires creating many new classes
 */
