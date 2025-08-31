package OCP;

//OCP (Open for extension and Closed for Modification)
public class Demo {

    int dicount;

    public int getDicount(String pay){
        if(pay.equals("Debit"))return 10;
        if(pay.equals("Credit"))return 20;
        if(pay.equals("PayTM"))return 30;
        return 5;
    }

    // After fixing OCP (Open for extension and Closed for Modification)
    // Create Interface with exiting method parameter and create different class
    // with different condition.
    public int getDicount_2(IDiscount iDiscount){
        return iDiscount.getDiscount();
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        System.out.println(demo.getDicount("Credit"));

        IDiscount credit = new Credit();
        System.out.println(demo.getDicount_2(credit));
        //credit.m1();
        System.out.println(IDiscount.add(37,57));



    }
}

interface IDiscount{
    public int getDiscount();
    // Just to test Java 8
    // This method can either skip, override or use as it in implementation class.
    // Purpose: To add new methods to an interface without breaking existing implementations.
    default public void m1(){
        System.out.println("This is m1 method");
    }
    static int add(int a, int b) {
        return a + b;
    }
}

class Debit implements IDiscount{
    @Override
    public int getDiscount() {
        return 10;
    }
}

class Credit implements IDiscount{
    public int getDiscount(){
        return 20;
    }
}

class PayTM implements IDiscount{
    public int getDiscount(){
        return 30;
    }
}
