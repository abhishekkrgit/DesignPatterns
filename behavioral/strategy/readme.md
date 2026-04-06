```markdown
# Strategy Design Pattern

## 📌 Overview
The **Strategy Design Pattern** is a behavioral design pattern that allows you to define a family of algorithms, encapsulate each one in a separate class, and make them interchangeable at runtime. 

Instead of using massive `if-else` or `switch` blocks to select a behavior, the **Context** delegates the work to a **Strategy** object.

---

## 🏗️ Architecture & Components

The pattern is composed of three main parts:

1.  **Strategy Interface**: Defines a common contract for all supported versions of an algorithm.
2.  **Concrete Strategies**: Implements different variations of the algorithm (e.g., Credit Card, PayPal, Crypto).
3.  **Context**: Maintains a reference to one of the concrete strategies and communicates with it only via the strategy interface.



---

## ⚖️ Why Use the Strategy Pattern?

### 1. Eliminates Conditional Logic
It replaces complex conditional statements used to select a behavior with **Polymorphism**. This makes the code cleaner and easier to read.

### 2. Open/Closed Principle
You can introduce new algorithms (strategies) without having to change the **Context** or existing strategies. The system is "Open for extension, but closed for modification."

### 3. Separation of Concerns
The business logic (Context) is separated from the implementation details of the algorithms (Strategies).

### 4. Runtime Interchangeability
You can swap the behavior of an object while the application is running based on user input or environmental factors.

---

## 🚀 Benefits vs. Trade-offs

| Feature | Strategy Pattern Benefit |
| :--- | :--- |
| **Maintenance** | Low risk of breaking existing code when adding new features. |
| **Testing** | Each strategy can be unit-tested in isolation. |
| **Flexibility** | Switch behaviors dynamically without restarting the app. |
| **Complexity** | *Trade-off:* Increases the total number of classes in the project. |

---

## 🛠️ Complete Implementation (Java)

Here is a full example of a **Payment Processing System** using the Strategy Pattern.

### 1. The Strategy Interface
```java
public interface PaymentStrategy {
    void processPayment(double amount);
}
```

### 2. Concrete Strategies
```java
// Strategy A: Credit Card
public class CreditCardPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

// Strategy B: PayPal
public class PayPalPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}
```

### 3. The Context
```java
public class CheckoutService {
    private PaymentStrategy paymentStrategy;

    // The strategy is injected here
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }

    public void executeCheckout(double amount) {
        if (paymentStrategy == null) {
            System.out.println("Please select a payment method!");
            return;
        }
        paymentStrategy.processPayment(amount);
    }
}
```

### 4. Client Code (Usage)
```java
public class Main {
    public static void main(String[] args) {
        CheckoutService checkout = new CheckoutService();

        // User selects PayPal at runtime
        checkout.setPaymentStrategy(new PayPalPayment());
        checkout.executeCheckout(150.00);

        // User changes mind to Credit Card
        checkout.setPaymentStrategy(new CreditCardPayment());
        checkout.executeCheckout(200.00);
    }
}
```

---
## 🏁 Conclusion
The Strategy Pattern is a "must-know" for LLD interviews. It is the best solution when you have multiple ways of performing a task and want to keep your code decoupled, testable, and scalable.
```