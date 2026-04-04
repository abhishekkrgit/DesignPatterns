# Factory Method Design Pattern

## 📖 What is it?
The **Factory Method** is a creational design pattern that defines an interface for creating an object but lets subclasses decide which class to instantiate. 

Instead of the client calling `new ConcreteProduct()`, it calls a **Factory Method** inside a **Creator** class.

---

## ❓ Why Use This Pattern?

In SDE-2 interviews (like at Amazon), we use this to ensure our code is **Scalable** and **Decoupled**:

1.  **Open/Closed Principle:** You can add new product types (e.g., `WhatsAppNotification`) without changing any existing code. You just add a new subclass.
2.  **Decoupling:** The client doesn't need to know the specific class names of the products. It only works with the `Notification` interface.
3.  **Single Responsibility:** Creation logic is isolated in one place (the Creator), making the "Main" logic cleaner.

---

## 🏗️ 4-Step Implementation Strategy
Follow this "Bottom-Up" order to build the pattern correctly:

### Step 1: The Product Interface
Define the common "contract" (behavior) all items must follow.
* *Example:* `interface Notification { void send(String msg); }`

### Step 2: Concrete Products
Implement the actual versions of the interface.
* *Example:* `EmailNotification`, `SmsNotification`.

### Step 3: The Abstract Creator
Create an abstract class that declares the **Factory Method**. This method returns the Product interface. It often includes a "helper" method that uses the product.
* *Example:* `abstract class NotificationCreator { abstract Notification create(); }`

### Step 4: Concrete Creators
Implement a specialized factory for every product. Each one overrides the factory method to return its own version.
* *Example:* `EmailCreator` returns `new EmailNotification()`.

---

## 💻 Code Implementation (Java)

```java
// 1. Product Interface
interface Notification {
    void send(String message);
}

// 2. Concrete Products
class EmailNotification implements Notification {
    public void send(String message) {
        System.out.println("Sending Email: " + message);
    }
}

class SmsNotification implements Notification {
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

// 3. Abstract Creator
abstract class NotificationCreator {
    // The Factory Method
    public abstract Notification createNotification();

    // High-level logic using the factory method
    public void sendNotification(String message) {
        Notification note = createNotification();
        note.send(message);
    }
}

// 4. Concrete Creators
class EmailCreator extends NotificationCreator {
    @Override
    public Notification createNotification() {
        return new EmailNotification();
    }
}

class SmsCreator extends NotificationCreator {
    @Override
    public Notification createNotification() {
        return new SmsNotification();
    }
}

// CLIENT CODE
public class Main {
    public static void main(String[] args) {
        // We choose the Creator, the pattern handles the rest
        NotificationCreator creator = new EmailCreator();
        creator.sendNotification("Welcome to the platform!");
    }
}