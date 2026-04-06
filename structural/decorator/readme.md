# The Decorator Design Pattern

The **Decorator Pattern** is a structural design pattern that allows you to dynamically attach new behaviors to objects by placing these objects inside special wrapper objects that contain the behaviors.

It follows the **Open/Closed Principle**: Classes should be open for extension but closed for modification. Instead of inheriting a class to add functionality (which happens at compile time), you "wrap" it at runtime.

---

## 1. Core Concepts
* **Component**: The interface or abstract class defining the methods that can be decorated.
* **Concrete Component**: The original object to which additional responsibilities can be attached.
* **Decorator**: An abstract class that implements the Component interface and contains a reference to a Component object.
* **Concrete Decorator**: Classes that extend the Decorator and add specific state or behavior.

---

## 2. When to Use It
* When you want to add responsibilities to individual objects dynamically without affecting other objects.
* When using inheritance is impractical because it would lead to an explosion of subclasses (the "class explosion" problem).
* When you need to withdraw responsibilities from an object at runtime.

---

## 3. Implementation in Java

In this example, we have a basic `Coffee` and we want to add "decorators" like `Milk` and `Sugar` to it.

```java
// 1. The Component Interface
interface Coffee {
    String getDescription();
    double getCost();
}

// 2. The Concrete Component
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }

    @Override
    public double getCost() {
        return 5.0;
    }
}

// 3. The Decorator (Abstract Class)
abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

// 4. Concrete Decorator A: Milk
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Milk";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 1.5;
    }
}

// 5. Concrete Decorator B: Sugar
class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Sugar";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.5;
    }
}

// 6. Usage
public class Main {
    public static void main(String[] args) {
        // Order a simple coffee
        Coffee myCoffee = new SimpleCoffee();
        System.out.println(myCoffee.getDescription() + " $" + myCoffee.getCost());

        // Add Milk to it
        myCoffee = new MilkDecorator(myCoffee);
        System.out.println(myCoffee.getDescription() + " $" + myCoffee.getCost());

        // Add Sugar to it (Double decoration!)
        myCoffee = new SugarDecorator(myCoffee);
        System.out.println(myCoffee.getDescription() + " $" + myCoffee.getCost());
    }
}