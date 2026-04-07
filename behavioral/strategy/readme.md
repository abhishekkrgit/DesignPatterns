# The Strategy Design Pattern

The **Strategy Pattern** is a behavioral design pattern that allows you to define a family of algorithms, encapsulate each one, and make them interchangeable at runtime.

It follows the **Open/Closed Principle**: You can introduce new strategies without modifying existing code. Instead of using large `if-else` or `switch` statements, the behavior is selected dynamically.

---

## 1. Core Concepts
* **Strategy**: An interface that defines a common contract for all algorithms.
* **Concrete Strategies**: Different implementations of the strategy (e.g., Credit Card, PayPal, Crypto).
* **Context**: The class that uses a Strategy object to execute the behavior.

---

## 2. When to Use It
* When you have multiple ways to perform a task.
* When you want to avoid large conditional statements.
* When algorithms may change frequently.
* When you need runtime flexibility to switch behavior.

---

## 3. Implementation in Java

In this example, we have a payment system where different payment methods are implemented as strategies.

```java
// 1. Strategy Interface
interface PaymentStrategy {
    void processPayment(double amount);
}

// 2. Concrete Strategy A: Credit Card
class CreditCardPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

// 3. Concrete Strategy B: PayPal
class PayPalPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Paid " + amount + " using PayPal");
    }
}

// 4. Concrete Strategy C: Crypto
class CryptoPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Paid " + amount + " using Cryptocurrency");
    }
}

// 5. Context
class PaymentContext {
    private PaymentStrategy strategy;

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void pay(double amount) {
        if (strategy == null) {
            throw new IllegalStateException("Payment strategy not set");
        }
        strategy.processPayment(amount);
    }
}

// 6. Usage
public class Main {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setStrategy(new CreditCardPayment());
        context.pay(1000);

        context.setStrategy(new PayPalPayment());
        context.pay(500);

        context.setStrategy(new CryptoPayment());
        context.pay(200);
    }
}

---

## 4. Key Benefits

* Eliminates complex conditional logic using polymorphism.
* Follows Open/Closed Principle.
* Promotes separation of concerns.
* Enables runtime behavior switching.

---

## 5. Trade-offs

* Increases number of classes.
* Can add slight complexity for simple use cases.

---

## Summary

The **Strategy Pattern** helps in writing flexible and maintainable code by decoupling the algorithm from the context. It is especially useful when behavior needs to change dynamically at runtime.

