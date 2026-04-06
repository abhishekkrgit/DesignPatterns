package structural.decorator;

// component inteface
interface Coffee {
    double getCost();
    String getDescription();
}

// concrete component
class SimpleCoffee implements Coffee {
    @Override
    public double getCost(){
        return 20.0;
    }

    @Override
    public String getDescription(){
        return "Simple Coffee";
    }
}

// abstract decorator

abstract class CoffeeDecorator implements Coffee {
    public final Coffee inner;

    CoffeeDecorator(Coffee inner){
        this.inner = inner;
    }
}

// concrete Decorator 

class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee inner) {
        super(inner);
    }

    @Override 
    public double getCost(){
        return inner.getCost() + 5.0;
    }

    @Override
    public String getDescription(){
        return inner.getDescription() + ", milk";
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee inner){
        super(inner);
    }

    @Override
    public double getCost() {
        return inner.getCost() + 2.0;
    }

    public String getDescription() {
        return inner.getDescription() + ", sugar";
    }
}

class WhippedCreamDecorator extends CoffeeDecorator {

    public WhippedCreamDecorator(Coffee inner) {
        super(inner);
    }

    @Override
    public double getCost(){
        return inner.getCost() + 10.0;
    }

    @Override
    public String getDescription() {
        return inner.getDescription() + ", Whipped Cream";
    }
}

public class CoffeeShop {
    public static void main(String[] args) {
        
        Coffee order1 = new SimpleCoffee();
        System.out.println("Order 1: " + order1.getDescription() + "with cost: "+
         String.format("%.2f", order1.getCost()));

        //Order 2
        Coffee order2 = new SugarDecorator(new MilkDecorator(new SimpleCoffee()));
        System.out.println("Order 2: " + order2.getDescription() + "with cost: "+
         String.format("%.2f", order2.getCost()));

                //Order 2
        Coffee order3 = new WhippedCreamDecorator(new MilkDecorator(new SimpleCoffee()));
        System.out.println("Order 3: " + order3.getDescription() + "with cost: "+
         String.format("%.2f", order3.getCost()));

                      //Order 2
        Coffee order4 = new WhippedCreamDecorator(new MilkDecorator(new SugarDecorator(new SimpleCoffee())));
        System.out.println("Order 4: " + order4.getDescription() + "with cost: "+
         String.format("%.2f", order4.getCost()));

        System.out.println(" Enjoy!");
    }
    
}
