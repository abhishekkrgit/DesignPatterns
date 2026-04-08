
---

# The State Design Pattern

The **State Pattern** is a behavioral design pattern that allows an object to change its behavior when its internal state changes. It appears as if the object changed its class.

Instead of using multiple conditional statements (`if/else` or `switch`), the behavior is delegated to different state objects.

---

## 1. Core Idea

* Encapsulate varying behavior for different states into separate classes.
* Make the main object (Context) delegate behavior to the current state object.
* Allow dynamic switching between states at runtime.

---

## 2. When to Use

Use the **State Pattern** when:

* An object’s behavior depends on its state.
* You have complex conditional logic based on state.
* You want to avoid large `if-else` or `switch-case` blocks.
* State transitions need to be flexible and maintainable.

---

## 3. Structure

### Components:

1. **Context**

   * Maintains a reference to the current state.
   * Delegates behavior to the current state.

2. **State (Interface / Abstract Class)**

   * Declares methods that all concrete states must implement.

3. **Concrete States**

   * Implement behavior specific to a state.
   * Can trigger state transitions.

---

## 4. Example (Java)

### Step 1: State Interface

```java
public interface State {
    void handle(Context context);
}
```

---

### Step 2: Concrete States

```java
public class ConcreteStateA implements State {
    @Override
    public void handle(Context context) {
        System.out.println("State A handling request");
        context.setState(new ConcreteStateB());
    }
}
```

```java
public class ConcreteStateB implements State {
    @Override
    public void handle(Context context) {
        System.out.println("State B handling request");
        context.setState(new ConcreteStateA());
    }
}
```

---

### Step 3: Context Class

```java
public class Context {
    private State currentState;

    public Context(State state) {
        this.currentState = state;
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public void request() {
        currentState.handle(this);
    }
}
```

---

### Step 4: Usage

```java
public class Main {
    public static void main(String[] args) {
        Context context = new Context(new ConcreteStateA());

        context.request(); // A -> B
        context.request(); // B -> A
        context.request(); // A -> B
    }
}
```

---

## 5. Real-World Example

* **Traffic Light System**

  * States: Red, Yellow, Green
  * Behavior changes based on current light.

* **Media Player**

  * States: Playing, Paused, Stopped

* **Order Processing**

  * States: Created → Paid → Shipped → Delivered

---

## 6. Pros & Cons

### ✅ Advantages

* Eliminates complex conditional logic
* Makes code more maintainable and scalable
* Follows **Open/Closed Principle**
* Clear separation of concerns

---

### ❌ Disadvantages

* Increases number of classes
* Can be overkill for simple state logic
* Slightly harder to understand initially

---

## 7. State vs Strategy Pattern

| Feature     | State Pattern                  | Strategy Pattern               |
| ----------- | ------------------------------ | ------------------------------ |
| Purpose     | Change behavior based on state | Choose algorithm at runtime    |
| Awareness   | States know each other         | Strategies usually independent |
| Transitions | Managed within states          | Managed by client              |

---

## 8. Key Takeaways

* Think of it as **"object behavior changes with state"**.
* Replace conditionals with polymorphism.
* Each state becomes a class → cleaner and extensible design.

---

If you want, I can also give:

* UML diagram
* Real production example (like payment/order workflow)
* Comparison with other patterns for interviews 👍
