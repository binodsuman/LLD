package OCP;

// OCP (Open for extension and Closed for Modification)
public class Demo2 {

    public void drawAndExport(String shape, String exporter){
        if(shape.equals("circle")){
            System.out.println("Draw a Circle");
        }else if(shape.equals("rectangle")){
            System.out.println("Draw a Rectangle");
        }

        if(exporter.equals("jpg")){
            System.out.println("JPG image create");
        }else if(exporter.equals("svg")){
            System.out.println("SVG image create");
        }
    }

    // After fixing OCP (Open for extension and Closed for Modification)
    // Create Interface with exiting method parameter and create different class
    // with different condition.
    public void drawAndExport_2(Shape shape, Exporter exporter){
        shape.draw();
        exporter.export();
    }

    public static void main(String[] args) {
        Demo2 demo2 = new Demo2();
        demo2.drawAndExport("circle","jpg");

        // Create class of your requirement condition.
        // Client class is free to change anytime.
        Shape circle = new Circle();
        Exporter jpg = new JPG();
        demo2.drawAndExport_2(circle,jpg);
    }
}

// One interface for ONLY ONE responsibility.
interface Shape{
    public void draw();
}

class Circle implements Shape{
    public void draw(){
        System.out.println("Draw Circle");
    }
}

class Rectangle implements Shape{
    public void draw(){
        System.out.println("Draw a Rectangle");
    }
}

interface Exporter{
    public void export();
}

class JPG implements Exporter{
    public void export(){
        System.out.println("Export image as JPG");
    }
}

class SVG implements Exporter{
    public void export(){
        System.out.println("Export image as SVG");
    }
}
