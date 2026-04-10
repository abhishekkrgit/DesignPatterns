
# Chain of Responsibility Pattern

## 📌 Overview
The **Chain of Responsibility** is a behavioral design pattern that allows an object to pass a request along a chain of potential handlers. Upon receiving a request, each handler decides either to process the request or to pass it to the next handler in the chain.

In this project, we demonstrate the pattern by simulating an **HTTP Request Middleware Pipeline**.



---

## ⚙️ How It Works
1. **Handler Interface/Abstract Class**: Defines a method for executing a request and a method for setting the next handler in the chain.
2. **Concrete Handlers**: These classes contain the actual processing logic. They decide whether to handle the request or pass it further down the line.
3. **Client**: Instead of sending requests to a specific receiver, the client sends the request to the first link in the chain.

---

## 🛠️ Implementation Details
The project consists of an `HttpRequest` object flowing through four distinct stages:

| Handler | Responsibility |
| :--- | :--- |
| **Authentication** | Verifies the identity of the requester. |
| **Rate Limiting** | Ensures the user hasn't exceeded their request quota. |
| **Validation** | Checks if the request body and headers are well-formed. |
| **Business Logic** | The final destination where the actual work is performed. |



---

## 🚀 Key Features

* **Decoupling**: The sender of a request (the client) doesn't need to know which object will ultimately handle it.
* **Single Responsibility Principle**: You can split classes that implement different behaviors into separate files.
* **Open/Closed Principle**: You can introduce new handlers into the app without breaking existing client code.
* **Flexibility**: You can dynamically reorder the chain or add/remove steps at runtime.

---

## 💻 Usage Example

To build and execute the chain, we link the handlers together and start the process from the "head" of the chain:

```java
// Create handlers
BaseRequestHandler auth = new AuthenticationHandler();
BaseRequestHandler limit = new RateLimitingHandler();
BaseRequestHandler logic = new BusinessLogicHandler();

// Configure the chain
auth.setNextHandler(limit);
limit.setNextHandler(logic);

// Execute
auth.handle(new HttpRequest("/api/resource", "GET", null));
```

---

## ⚠️ Important Considerations
* **The "Broken Link":** If a handler fails to call `forwardRequest()` and isn't the final handler (like a `BusinessLogicHandler`), the request will "drop" and never complete.
* **Short-Circuiting:** This is actually a feature! If authentication fails, the handler should **not** call `forwardRequest()`, effectively blocking unauthorized access to the rest of the system.
* **Stack Depth:** Extremely long chains can lead to deep recursion; however, for standard middleware (5-10 steps), this is rarely an issue in modern JVMs.

---
