````md
# Strategy Design Pattern

## 📌 Overview
The **Strategy Design Pattern** is a behavioral design pattern that allows you to define a family of algorithms, encapsulate each one in a separate class, and make them interchangeable at runtime.

Instead of using massive `if-else` or `switch` blocks to select a behavior, the **Context** delegates the work to a **Strategy** object.

---

## 🏗️ Architecture & Components

The pattern is composed of three main parts:

1. **Strategy Interface**  
   Defines a common contract for all supported versions of an algorithm.

2. **Concrete Strategies**  
   Implements different variations of the algorithm (e.g., Credit Card, PayPal, Crypto).

3. **Context**  
   Maintains a reference to one of the concrete strategies and communicates with it only via the strategy interface.

---

## ⚖️ Why Use the Strategy Pattern?

### 1. Eliminates Conditional Logic
Replaces complex conditional statements with **polymorphism**, making the code cleaner and easier to maintain.

### 2. Open/Closed Principle
You can introduce new strategies without modifying existing code. The system is **open for extension but closed for modification**.

### 3. Separation of Concerns
Separates the business logic (**Context**) from algorithm implementations (**Strategies**).

### 4. Runtime Interchangeability
Allows switching behavior dynamically at runtime based on user input or system conditions.

---

## 🚀 Benefits vs. Trade-offs

| Feature        | Strategy Pattern Impact |
|----------------|------------------------|
| **Maintenance** | Low risk when adding new features |
| **Testing**     | Strategies can be unit tested independently |
| **Flexibility** | Behaviors can be switched dynamically |
| **Complexity**  | ⚠️ Increases number of classes |

---

## 🛠️ Complete Implementation (Java)

### 1. Strategy Interface
```java
public interface PaymentStrategy {
    void processPayment(double amount);
}
````

---

### 2. Concrete Strategies

```java
public class CreditCardPayment implements PaymentStrategy {
    public void processPayment(double amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}
```

```java
public class PayPalPayment implements PaymentStrategy {
    public void processPayment(double amount) {
        System.out.println("Paid " + amount + " using PayPal");
    }
}
```

```java
public class CryptoPayment implements PaymentStrategy {
    public void processPayment(double amount) {
        System.out.println("Paid " + amount + " using Cryptocurrency");
    }
}
```

---

### 3. Context Class

```java
public class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }

    public void pay(double amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy not set");
        }
        paymentStrategy.processPayment(amount);
    }
}
```

---

### 4. Client Code

```java
public class Main {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setPaymentStrategy(new CreditCardPayment());
        context.pay(1000);

        context.setPaymentStrategy(new PayPalPayment());
        context.pay(500);

        context.setPaymentStrategy(new CryptoPayment());
        context.pay(200);
    }
}
```

---

## 🧠 When to Use Strategy Pattern

* When you have multiple ways to perform a task
* When you want to avoid large conditional statements
* When algorithms may change frequently
* When you want runtime flexibility

---

## ❌ When NOT to Use

* If you only have 1–2 simple algorithms
* When adding extra classes increases unnecessary complexity
* When behavior rarely changes

---

## 🧩 Real-World Examples

* Payment methods in e-commerce apps
* Sorting algorithms (quick sort, merge sort, etc.)
* Compression strategies (ZIP, RAR)
* Navigation systems (car, walking, biking routes)

---

## 🔚 Summary

The **Strategy Pattern** promotes flexibility, scalability, and clean code by decoupling algorithms from the context in which they are used. It’s especially useful when behavior needs to change dynamically at runtime.

```
