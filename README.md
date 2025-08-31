# LLD
Important stuff for Low Level Design

### SOLID Principle ###
<br> S - Single Responsibility Principle (SRP)
<br> O - Open/Closed Principle (OCP)
<br> L - Liskov Substitution Principle (LSP)
<br> I - Interface Segregation Principle (ISP)
<br> D - Dependency Inversion Principle (DIP)
<br> 

### Important Design Pattern ###
1. Builder Design Pattern
2. Decorative Design Pattern



Here are the **golden tips and mental models** for LLD interviews that apply to almost any problem:

## 🎯 **7 Golden Rules for Any LLD Interview**

### 1. **Identify Core Entities & Relationships First**
```java
// BAD: Jump straight to code
// GOOD: First identify entities and relationships

// Entities: User, Restaurant, Order, MenuItem, Payment, DeliveryAgent
// Relationships: 
// - User places Order (1-to-many)
// - Order contains MenuItems (many-to-many)  
// - Order has Payment (1-to-1)
// - DeliveryAgent delivers Order (1-to-many)
```

### 2. **Find What Varies - Encapsulate with Interfaces**
```java
// When you see "different types of X", create interface
interface PaymentMethod {
    void processPayment(double amount);
}

class CreditCard implements PaymentMethod {
    public void processPayment(double amount) { /* logic */ }
}

class PayPal implements PaymentMethod {
    public void processPayment(double amount) { /* logic */ }
}

class UPI implements PaymentMethod {
    public void processPayment(double amount) { /* logic */ }
}

// Usage
PaymentMethod payment = PaymentFactory.getPaymentMethod("credit_card");
payment.processPayment(100.0);
```

### 3. **Apply SOLID Principles Systematically**
- **S**: Each class should have single responsibility
- **O**: Open for extension, closed for modification
- **L**: Subclasses should substitute parent classes
- **I**: Create focused interfaces (ISP)
- **D**: Depend on abstractions, not concretions

### 4. **Master Composition Over Inheritance**
```java
// BAD: Inheritance hierarchy
class Vehicle {}
class Car extends Vehicle {}
class ElectricCar extends Car {} // Multiple inheritance issues

// GOOD: Composition
interface Engine { void start(); }
interface FuelSystem { void refuel(); }

class Car {
    private Engine engine;
    private FuelSystem fuelSystem;
    
    public Car(Engine engine, FuelSystem fuelSystem) {
        this.engine = engine;
        this.fuelSystem = fuelSystem;
    }
}

class ElectricEngine implements Engine { /* implementation */ }
class GasolineEngine implements Engine { /* implementation */ }
```

### 5. **Use Design Patterns Judiciously**
```java
// Common patterns to recognize:
// - Strategy: Different algorithms (Payment, Sorting, Discount)
// - Decorator: Adding features (Coffee toppings, Pizza toppings)
// - Observer: Notifications (Order status updates)
// - Factory: Object creation (Payment methods, Vehicle types)
// - Singleton: Single instance (Logger, Database connection)
```

### 6. **Think About Lifecycle & State Management**
```java
// Model state transitions clearly
enum OrderStatus {
    PLACED, CONFIRMED, PREPARING, OUT_FOR_DELIVERY, DELIVERED, CANCELLED
}

class Order {
    private OrderStatus status;
    
    public void updateStatus(OrderStatus newStatus) {
        // Validate state transition
        if (isValidTransition(this.status, newStatus)) {
            this.status = newStatus;
            notifyObservers(); // Observer pattern
        }
    }
}
```

### 7. **Consider Concurrency & Thread Safety**
```java
class Restaurant {
    private Map<String, Integer> inventory;
    
    // Make thread-safe collections
    public Restaurant() {
        this.inventory = new ConcurrentHashMap<>();
    }
    
    public synchronized boolean reserveItem(String itemId, int quantity) {
        // Thread-safe reservation logic
    }
}
```

## 🔄 **Step-by-Step Approach for Any Problem**

### **Step 1: Requirement Clarification**
```java
// Ask clarifying questions:
// - Scope? (What's in/out of scope)
// - Scale? (Number of users, transactions)
// - Specific requirements? (Notifications, payments, etc.)
```

### **Step 2: Identify Core Entities**
```java
// For Uber/Ola:
// - User, Driver, Ride, Vehicle, Payment, Location

// For E-commerce:
// - User, Product, Order, Cart, Payment, Inventory

// For Library:
// - Book, Member, Loan, Reservation, Fine
```

### **Step 3: Define Relationships**
```java
// Use UML mentally:
// - User --(places)--> Order (1-to-many)
// - Order --(contains)--> Product (many-to-many)
// - Order --(has)--> Payment (1-to-1)
// - User --(has)--> Address (1-to-many)
```

### **Step 4: Identify What Varies**
```java
// Look for:
// - "different types of payments"
// - "various notification channels" 
// - "multiple discount strategies"
// - "various vehicle types"
```

### **Step 5: Apply Design Principles**
```java
// Apply SOLID:
// - Single Responsibility: Split large classes
// - Open/Closed: Use interfaces for extension
// - Liskov: Ensure proper inheritance
// - Interface Segregation: Create focused interfaces
// - Dependency Inversion: Depend on abstractions
```

### **Step 6: Consider Edge Cases**
```java
// Think about:
// - Concurrent bookings
// - Payment failures
// - Inventory management
// - Error handling
// - Timeouts and retries
```

## 🏗️ **Common Patterns Cheat Sheet**

| **Scenario** | **Pattern to Use** |
|:---|:---|
| Different algorithms/behaviors | **Strategy Pattern** |
| Adding features dynamically | **Decorator Pattern** |
| Object creation logic | **Factory Pattern** |
| State management | **State Pattern** |
| Notifications/updates | **Observer Pattern** |
| Single instance needed | **Singleton Pattern** |
| Complex object construction | **Builder Pattern** |

## 💡 **Pro Tips During Interview**

1. **Think aloud** - Explain your thought process
2. **Start simple** - Basic implementation first, then optimize
3. **Ask questions** - Clarify requirements upfront
4. **Consider tradeoffs** - Discuss pros/cons of your approach
5. **Write clean code** - Proper naming, encapsulation, modularity
6. **Test your design** - Walk through sample scenarios

## 📝 **Final Checklist**

- [ ] Identified all core entities
- [ ] Defined relationships properly
- [ ] Used interfaces for varying behaviors
- [ ] Applied appropriate design patterns
- [ ] Considered thread safety
- [ ] Handled edge cases
- [ ] Followed SOLID principles
- [ ] Code is clean and modular

Remember: **Interfaces for what varies, composition over inheritance, and always think about relationships first!** 🚀
   

