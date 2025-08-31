package Decorative_Pattern.With;

public class Demo {

    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + ": $" + coffee.getCost());

        // Coffee with milk
        Coffee milkCoffee = new MileCoffee(new SimpleCoffee());
        System.out.println(milkCoffee.getDescription() + ": $" + milkCoffee.getCost());

        // Coffee with milk and sugar
        Coffee milkSugarCoffee = new SugarDecorator(new MileCoffee(new SimpleCoffee()));
        System.out.println(milkSugarCoffee.getDescription() + ": $" + milkSugarCoffee.getCost());

        // Fancy coffee with everything!
        Coffee fancyCoffee = new WhippedCreamDecorator(
                new VanillaDecorator(
                        new SugarDecorator(
                                new MileCoffee(
                                        new SimpleCoffee()))));

        System.out.println(fancyCoffee.getDescription() + ": $" + fancyCoffee.getCost());

    }
}

// Step 1: Base interface (same as before)
interface Coffee{
    int getCost();
    String getDescription();
}

// Step 2: Concrete component
class SimpleCoffee implements Coffee{
    public int getCost(){return 10;}
    public String getDescription(){return "Simple Coffee";}
}

// Step 3: Abstract decorator (the key!) ******* IMP
// BOTH implements and Association
class DecorativeCoffee implements Coffee{
    Coffee decorativeCoffee;
    DecorativeCoffee(Coffee decorativeCoffee){
        this.decorativeCoffee = decorativeCoffee;
    }
    public int getCost(){return decorativeCoffee.getCost();}
    public String getDescription(){return decorativeCoffee.getDescription();}
}

// Step 4: Concrete decorators (toppings)
class MileCoffee extends DecorativeCoffee{
    Coffee coffee;
    MileCoffee(Coffee coffee){
        super(coffee);
    }
    public int getCost(){
        return super.getCost()+10;
    }
    public String getDescription(){
        return super.getDescription()+" Milk Coffee";
    }
}

class SugarDecorator extends DecorativeCoffee {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    public int getCost() {
        return super.getCost() + 5;
    }

    public String getDescription() {
        return super.getDescription() + ", sugar";
    }
}

class VanillaDecorator extends DecorativeCoffee {
    public VanillaDecorator(Coffee coffee) {
        super(coffee);
    }

    public int getCost() {
        return super.getCost() + 10;
    }

    public String getDescription() {
        return super.getDescription() + ", vanilla";
    }
}

class WhippedCreamDecorator extends DecorativeCoffee {
    public WhippedCreamDecorator(Coffee coffee) {
        super(coffee);
    }

    public int getCost() {
        return super.getCost() + 9;
    }

    public String getDescription() {
        return super.getDescription() + ", whipped cream";
    }
}
