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