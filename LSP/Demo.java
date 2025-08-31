package LSP;

/**
 * Fixing Liskov's Substitution Principle
 * Devired class must be substibute of their base class.
 */
public class Demo {
}

abstract class Vehicle{
    abstract void speedUp();
    abstract void speedDown();
    abstract void engineStart();
}

class Car extends Vehicle{
    void speedUp() { System.out.println("Car Seepd UP");}
    void speedDown() {System.out.println("Car Seepd Down");}
    void engineStart() {System.out.println("Car Engine Started");}
}

class Bicycle extends Vehicle{
    void speedUp() { System.out.println("Bicycle Seepd UP");}
    void speedDown() {System.out.println("Bicycle Seepd Down");}
    void engineStart() {
        throw new EngineNotFoundException("Engine Missing");
    }
}

class EngineNotFoundException extends RuntimeException {
    public EngineNotFoundException(String engine_missing) {
        super(engine_missing);
    }
}

// ****** Fixing *******
interface Movable{
    void speedUp();
    void speedDown();
}

interface EnginePowered{
    void engineStart();
}

abstract class AVehicle implements Movable, EnginePowered{

}

// Actually Derived only one parent either Abstract class or Interface
class Car2 extends AVehicle{
    // You must have to use Public as interface by-default is public abstract and you can not reduce visibility.
    public void speedUp() { System.out.println("Car Speed UP");}
    public void speedDown() {System.out.println("Car Speed Down");}
    public void engineStart() {System.out.println("Car Engine Started");}
}

class Bicycle2 implements Movable{
    public void speedUp() { System.out.println("Car Speed UP");}
    public void speedDown() {System.out.println("Car Speed Down");}

}