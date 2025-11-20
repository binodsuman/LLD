# LLD required items and Some Important Quesitons and Hints
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
   
Below is the **improved and structured version** of 20 LLD questions.
Each question now has **(1) Question Details** and **(2) Expected Discussion** with the exact bullet points you requested:

✔ Entities
✔ Design Pattern
✔ OOPS
✔ Data Flow
✔ DB (if required)
✔ Class & Interface Names
✔ Association Types
✔ Trade-offs

---

Below is the **enhanced version** of all **20 LLD questions**, now including a **new bullet point for SOLID Principles** inside *Expected Discussion* for every question.

---

# **🔵 20 Important LLD Interview Questions (Including SOLID Principles)**

---

# **1. Parking Lot System**

### **Question Details:**

Design a scalable multi-floor parking lot system.

### **Expected Discussion:**

* **Entities:** ParkingLot, Floor, Slot, Ticket, Vehicle, Payment.
* **Design Pattern:** Factory (Vehicle), Strategy (Pricing), Singleton (Lot Manager).
* **OOPS:** Inheritance (Car/Bike→Vehicle), abstraction for slot allocation, composition (Floor→Slots).
* **SOLID:**

  * **S:** Slot class handles only slot logic; Pricing separate.
  * **O:** Adding new Vehicle types without modifying existing code.
  * **L:** Car/Bike replace Vehicle without breaking logic.
  * **I:** Separate interfaces for pricing, allocation.
  * **D:** Depend on abstractions like IPricingStrategy.
* **Data Flow:** Entry → Allocate slot → Issue ticket → Exit → Payment.
* **DB:** VehicleType, Slot, Floor, Ticket.
* **Class & Interfaces:** Vehicle, Slot, IPricingStrategy, ParkingManager.
* **Association:** ParkingLot *has-many* Floors; Floor *has-many* Slots.
* **Trade-offs:** Nearest-slot allocation vs random; memory vs speed.

---

# **2. Elevator Control System**

### **Question Details:**

Design multi-elevator management.

### **Expected Discussion:**

* **Entities:** ElevatorCar, Controller, Request, Door, Panel.
* **Design Pattern:** State, Observer.
* **OOPS:** State machine for movement, encapsulation of queues.
* **SOLID:**

  * **S:** ElevatorCar only moves; Controller schedules.
  * **O:** Add new scheduling algorithms easily.
  * **L:** All state classes behave like IElevatorState.
  * **I:** Separate interfaces for movement/state.
  * **D:** Controller depends on IElevatorStrategy.
* **Data Flow:** Request → Scheduler → Elevator → State transitions.
* **DB:** Not required.
* **Class/Interface:** IElevatorState, IdleState, MoveUpState.
* **Association:** Controller *has-many* Elevators.
* **Trade-offs:** Simple FCFS vs smart scheduling.

---

# **3. Library Management System**

### **Question Details:**

Design issue/return operations.

### **Expected Discussion:**

* **Entities:** Book, User, Catalog, Transaction.
* **Design Pattern:** Singleton (Catalog), Factory (User).
* **OOPS:** Inheritance for User types, polymorphic search.
* **SOLID:**

  * **S:** Book handles book-only logic.
  * **O:** Add new search criteria without modifying existing.
  * **L:** BookItem must substitute Book.
  * **I:** Search interfaces separated.
  * **D:** Services rely on abstractions.
* **Data Flow:** Search → Issue → Return → Update availability.
* **DB:** Books, Users, Transactions.
* **Class/Interface:** Catalog, TransactionService.
* **Association:** User *has-many* Transactions.
* **Trade-offs:** Cached vs real-time inventory.

---

# **4. Notification Service**

### **Question Details:**

Design system to send Email/SMS/Push notifications.

### **Expected Discussion:**

* **Entities:** Notification, Template, Channel, Message.
* **Design Pattern:** Strategy (channels), Builder (message).
* **OOPS:** Channel polymorphism.
* **SOLID:**

  * **S:** Each channel class only sends via one medium.
  * **O:** Add WhatsApp channel easily.
  * **L:** All channels behave like INotificationChannel.
  * **I:** Separate interface per channel.
  * **D:** NotificationService depends on channel abstractions.
* **Data Flow:** Create message → Format → Send → Log.
* **DB:** Logs, Templates, Preferences.
* **Class/Interface:** INotificationChannel, EmailChannel.
* **Association:** Notification uses Channel.
* **Trade-offs:** Sync vs async.

---

# **5. Coffee Vending Machine**

### **Question Details:**

Design beverage preparation machine.

### **Expected Discussion:**

* **Entities:** Drink, Ingredient, MachineState, Order.
* **Design Pattern:** State, Factory.
* **OOPS:** Encapsulation for recipe logic.
* **SOLID:**

  * **S:** Each state handles only its own transitions.
  * **O:** Add new drinks easily.
  * **L:** All Drink subclasses behave consistently.
  * **I:** Separate interfaces for drink preparation.
  * **D:** Machine depends on IDrink.
* **Data Flow:** Order → Check → Mix → Dispense.
* **DB:** Optional inventory.
* **Class/Interface:** IDrink, Coffee, IdleState.
* **Association:** Drink *has-many* Ingredients.
* **Trade-offs:** Dynamic recipe vs fixed.

---

# **6. Tic-Tac-Toe Game**

### **Question Details:**

Design a simple 3x3 game.

### **Expected Discussion:**

* **Entities:** Board, Cell, Player, Move, Game.
* **Design Pattern:** Strategy (AI).
* **OOPS:** Encapsulation of board.
* **SOLID:**

  * **S:** Board manages grid; Game manages turns.
  * **O:** Support NxN boards.
  * **L:** AIPlayer should behave like Player.
  * **I:** Separate interface for AI vs Human.
  * **D:** Game depends on IPlayer.
* **Data Flow:** Move → Validate → Update → Check win.
* **DB:** Not needed.
* **Class/Interface:** IPlayer, Game, Board.
* **Association:** Board has Cells.
* **Trade-offs:** Array vs object grid.

---

# **7. Movie Ticket Booking**

### **Question Details:**

Design seat selection & booking.

### **Expected Discussion:**

* **Entities:** Movie, Show, Seat, Booking, Payment.
* **Design Pattern:** Observer, Factory.
* **OOPS:** Encapsulation of seat availability.
* **SOLID:**

  * **S:** SeatLockService only locks seats.
  * **O:** Add new payment modes.
  * **L:** Seat types must work like base Seat.
  * **I:** ISeat, IPayment separated.
  * **D:** BookingService depends on abstract Payment.
* **Data Flow:** Seat lock → Payment → Confirm.
* **DB:** Shows, Seats, Bookings.
* **Class/Interface:** SeatLockService, BookingService.
* **Association:** Show has Seats.
* **Trade-offs:** Lock duration vs fairness.

---

# **8. Splitwise**

### **Question Details:**

Design group expense sharing.

### **Expected Discussion:**

* **Entities:** User, Group, Expense, Split.
* **Design Pattern:** Strategy (Equal/Percent).
* **OOPS:** Polymorphism for splitting.
* **SOLID:**

  * **S:** Each split strategy has its own class.
  * **O:** Add new split types easily.
  * **L:** EqualSplitStrategy behaves like ISplitStrategy.
  * **I:** Different strategies implement minimal interfaces.
  * **D:** ExpenseService depends on ISplitStrategy.
* **Data Flow:** Expense → Strategy → Balances.
* **DB:** Groups, Expenses, Splits.
* **Class/Interface:** ISplitStrategy, ExpenseService.
* **Association:** Group has Users.
* **Trade-offs:** Recompute vs store balances.

---

# **9. URL Shortener**

### **Question Details:**

Generate short URLs & redirect.

### **Expected Discussion:**

* **Entities:** UrlMapping, ClickEvent.
* **Design Pattern:** Singleton (ID generator).
* **OOPS:** Encapsulation of hashing logic.
* **SOLID:**

  * **S:** Hashing vs storage separated.
  * **O:** Add custom domains without modifying base code.
  * **L:** Any ID generator must work like IIdGenerator.
  * **I:** Separate repository and generator interfaces.
  * **D:** Service depends on abstractions.
* **Data Flow:** Encode → Store → Redirect.
* **DB:** URL table.
* **Class/Interface:** UrlService, IIdGenerator.
* **Association:** Url *has-many* clicks.
* **Trade-offs:** Hash collision vs speed.

---

# **10. Rate Limiter**

### **Question Details:**

Design per-user request limiting.

### **Expected Discussion:**

* **Entities:** RateLimiter, TokenBucket.
* **Design Pattern:** Strategy (limiter).
* **OOPS:** Polymorphism for limiter types.
* **SOLID:**

  * **S:** Each algorithm has separate class.
  * **O:** Add new algorithms easily.
  * **L:** TokenBucketLimiter replaces IRateLimiter.
  * **I:** Minimal IRateLimiter interface.
  * **D:** High-level code depends on limiter abstraction.
* **Data Flow:** Request → Check → Allow/Deny.
* **DB:** For distributed system.
* **Class/Interface:** IRateLimiter, TokenBucketLimiter.
* **Association:** User ↔ TokenBucket.
* **Trade-offs:** Memory vs accuracy.

---

# **11. File Storage System**

### **Question Details:**

Design folders, files, permissions.

### **Expected Discussion:**

* **Entities:** FileNode, FolderNode, Version, Permission.
* **Design Pattern:** Composite, Prototype.
* **OOPS:** Inheritance for folder/file nodes.
* **SOLID:**

  * **S:** FolderNode doesn’t manage content logic.
  * **O:** Add new Node types easily.
  * **L:** FolderNode and FileNode replace base Node.
  * **I:** Interfaces for read/write.
  * **D:** Services depend on INode.
* **Data Flow:** Create → Upload → Share.
* **DB:** Files, permissions.
* **Class/Interface:** INode, FileNode, FolderNode.
* **Association:** Folder contains Nodes.
* **Trade-offs:** Full version vs diff.

---

# **12. Auction System**

### **Question Details:**

Online bidding system.

### **Expected Discussion:**

* **Entities:** Auction, Bid, Item, User.
* **Design Pattern:** Observer.
* **OOPS:** Encapsulation of bidding rules.
* **SOLID:**

  * **S:** AuctionManager only manages auctions.
  * **O:** Add new auction types.
  * **L:** DutchAuction behaves like Auction.
  * **I:** IBidListener etc.
  * **D:** AuctionService depends on abstract IAuction.
* **Data Flow:** Bid → Update → Notify.
* **DB:** Bids, Auctions.
* **Class/Interface:** AuctionService, IAuction.
* **Association:** Auction has Bids.
* **Trade-offs:** Real-time vs event-based.

---

# **13. Payment Gateway**

### **Question Details:**

Orchestrate card/UPI/netbanking flows.

### **Expected Discussion:**

* **Entities:** Payment, Transaction, Merchant.
* **Design Pattern:** Strategy, Template Method.
* **OOPS:** Polymorphism for payment channels.
* **SOLID:**

  * **S:** Each payment method is isolated.
  * **O:** Add PayPal, Crypto, etc.
  * **L:** CardPayment fits IPaymentMethod.
  * **I:** Separate interface per method.
  * **D:** PaymentService depends on abstractions.
* **Data Flow:** Request → Method → Bank → Confirm.
* **DB:** Transactions, Users.
* **Class/Interface:** IPaymentMethod, CardPayment.
* **Association:** Payment uses method.
* **Trade-offs:** One big orchestrator vs modular services.

---

# **14. LRU Cache**

### **Question Details:**

Design LRU eviction.

### **Expected Discussion:**

* **Entities:** Cache, Node, DLL.
* **Design Pattern:** None mandatory.
* **OOPS:** Encapsulation of eviction logic.
* **SOLID:**

  * **S:** Node only stores data; eviction separate.
  * **O:** Extend with TTL eviction.
  * **L:** Any cache replacement must implement ICache.
  * **I:** Separate interface for eviction.
  * **D:** Cache depends on IEvictionPolicy.
* **Data Flow:** Put/Get → Update DLL → Evict.
* **DB:** Not needed.
* **Class/Interface:** LruCache, DllNode.
* **Association:** Cache has DLL.
* **Trade-offs:** More memory vs faster access.

---

# **15. Logging Framework**

### **Question Details:**

Console/File/DB logging.

### **Expected Discussion:**

* **Entities:** Logger, Appender, Formatter.
* **Design Pattern:** Chain of Responsibility, Factory.
* **OOPS:** Abstraction for appenders.
* **SOLID:**

  * **S:** Each appender writes only to its output.
  * **O:** Add new appenders easily.
  * **L:** FileAppender behaves like IAppender.
  * **I:** Split interfaces per appender.
  * **D:** Logger depends on IAppender.
* **Data Flow:** Build log → Format → Append.
* **DB:** Optional.
* **Class/Interface:** ILogger, ConsoleAppender.
* **Association:** Logger has appenders.
* **Trade-offs:** Sync vs async.

---

# **16. Chess Game**

### **Question Details:**

8×8 chess with move validation.

### **Expected Discussion:**

* **Entities:** Piece, Board, Move, Game.
* **Design Pattern:** Strategy.
* **OOPS:** Inheritance for pieces.
* **SOLID:**

  * **S:** Each piece only knows its own moves.
  * **O:** Add new piece types.
  * **L:** Pawn replaces Piece without issues.
  * **I:** IMoveValidator separated.
  * **D:** Game depends on IPiece.
* **Data Flow:** Move → Validate → Execute.
* **DB:** Optional.
* **Class/Interface:** IPiece, King, ChessGame.
* **Association:** Board has Pieces.
* **Trade-offs:** Validation complexity.

---

# **17. Online Food Delivery**

### **Question Details:**

Order placement & tracking.

### **Expected Discussion:**

* **Entities:** Restaurant, MenuItem, Order, DeliveryPerson.
* **Design Pattern:** Strategy (assignment), Observer (tracking).
* **OOPS:** Abstraction for assignment strategies.
* **SOLID:**

  * **S:** OrderService only handles order workflow.
  * **O:** Add new assignment algorithms.
  * **L:** DeliveryStrategy behaves like IAssignmentStrategy.
  * **I:** Separate driver/location services.
  * **D:** Services depend on abstractions.
* **Data Flow:** Browse → Order → Assign → Track → Deliver.
* **DB:** Orders, Restaurants.
* **Class/Interface:** OrderService, IAssignmentStrategy.
* **Association:** Restaurant has MenuItems.
* **Trade-offs:** Real-time accuracy vs battery usage.

---

# **18. Text Editor**

### **Question Details:**

Undo–redo mechanism.

### **Expected Discussion:**

* **Entities:** Document, Command, History, Cursor.
* **Design Pattern:** Command, Memento.
* **OOPS:** Encapsulation of text buffer.
* **SOLID:**

  * **S:** Command only executes action.
  * **O:** Add new commands (Replace, Copy).
  * **L:** All commands implement ICommand.
  * **I:** Fine-grained command interfaces.
  * **D:** Editor depends on ICommand.
* **Data Flow:** Execute → Store → Undo/Redo.
* **DB:** Optional.
* **Class/Interface:** ICommand, InsertCommand.
* **Association:** History has Commands.
* **Trade-offs:** Full snapshot vs diff.

---

# **19. Ride Sharing**

### **Question Details:**

Match rider with driver.

### **Expected Discussion:**

* **Entities:** Rider, Driver, RideRequest, Trip.
* **Design Pattern:** Strategy (matching), Observer (status).
* **OOPS:** Abstraction for matching algorithms.
* **SOLID:**

  * **S:** Matcher only matches requests.
  * **O:** Add surge-aware matching.
  * **L:** NearestStrategy must work like IMatchingStrategy.
  * **I:** Separate interfaces for tracking.
  * **D:** Services depend on abstractions.
* **Data Flow:** Request → Match → Accept → Start → End.
* **DB:** Drivers, Trips.
* **Class/Interface:** IMatchingStrategy, TripService.
* **Association:** Trip has Rider & Driver.
* **Trade-offs:** Accuracy vs latency.

---

# **20. Shopping Cart**

### **Question Details:**

Manage cart items & price.

### **Expected Discussion:**

* **Entities:** Cart, CartItem, Product, Discount.
* **Design Pattern:** Strategy (discount), Decorator.
* **OOPS:** Encapsulation of price logic.
* **SOLID:**

  * **S:** Each DiscountStrategy handles one rule.
  * **O:** Add new discount rules without modification.
  * **L:** All strategies must work interchangeably.
  * **I:** Interfaces separated by discount type.
  * **D:** Cart depends on IDiscountStrategy.
* **Data Flow:** Add → Recalculate → Checkout.
* **DB:** Cart, Products, Discounts.
* **Class/Interface:** CartService, IDiscountStrategy.
* **Association:** Cart has CartItems.
* **Trade-offs:** Recalc on update vs on checkout.

---


