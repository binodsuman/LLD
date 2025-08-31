package ISP.Fixed_Code;



/**
 * Interface Segregation Principle (ISP)
 * Real-world Analogy
 * LSP: A company hires you as an "Employee" promising you can "submitReports()". If they assign you to
 * a department where you physically cannot submit reports (no computer, no system access), LSP is violated.
 *
 * ISP: The company gives every employee a 100-page manual that includes procedures for: accounting,
 * IT support, marketing, and janitorial work. Most employees only need 5 pages relevant to their job -
 * this violates ISP.
 *
 *
 * LSP is about "Can I trust this subclass to behave like its parent?" ✅
 *
 * ISP is about "Does this interface make sense for all its clients?" ✅
 */
public class Demo_After_Fix {
    public static void main(String[] args) {
        Developer dev = new Developer();
        Designer designer = new Designer();
        Robot robot = new Robot();

        // Each class only has methods it actually needs
        dev.work();
        dev.eat();
        dev.code();

        designer.work();
        designer.eat();
        designer.design();

        robot.work();
        robot.code();
        robot.design();

        // These would cause compile errors (which is good!):
        // designer.code();    // ERROR: Designer doesn't code
        // robot.eat();        // ERROR: Robot doesn't eat
        // dev.design();       // ERROR: Developer doesn't design
    }
}

// One big interface that does too much
// interface Worker {
interface Workable {
    void work();

}
interface Eatable {
    void eat();

}
interface Codable {
    void code();

}
interface Designable{
    void design();
}

// Developer is forced to implement all methods
class Developer implements Workable, Eatable, Codable{
    public void work() { System.out.println("Developer working"); }
    public void eat() { System.out.println("Developer eating"); }
    public void code() { System.out.println("Developer coding"); }

}

// Designer is forced to implement all methods
class Designer implements Workable, Eatable, Designable {
    public void work() { System.out.println("Designer working"); }
    public void eat() { System.out.println("Designer eating"); }
    public void design() { System.out.println("Designer designing"); }
}

class Robot implements Workable, Codable, Designable{
    public void work() { System.out.println("Robot working"); }
    public void code() { System.out.println("Robot coding"); }
    public void design() { System.out.println("Robot designing"); }
}
