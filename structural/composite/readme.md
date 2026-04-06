# Composite Design Pattern

The **Composite Design Pattern** is a structural design pattern that allows you to compose objects into tree structures to represent part-whole hierarchies. It lets clients treat individual objects and compositions of objects uniformly.

---

## 🏗️ Core Concept

Imagine a file system. A **File** is an individual object, and a **Folder** is a container that can hold both **Files** and other **Folders**. In code, if you want to calculate the total size of a folder, you shouldn't care if you are looking at a single file or a sub-folder—you just want to call `getSize()`.

### Key Participants
1. **Component**: An interface or abstract class that defines operations common to both simple and complex objects (e.g., `execute()`, `getSize()`).
2. **Leaf**: A basic element that has no children. It implements the Component interface.
3. **Composite**: A container that stores child components (Leafs or other Composites). It implements the Component interface by delegating work to its children.

---

## 💻 Code Example: File System (Java)

This is a standard LLD implementation showing how the pattern handles recursive structures.

```java
import java.util.ArrayList;
import java.util.List;

// 1. Component
interface FileSystemComponent {
    void showDetails();
    long getSize();
}

// 2. Leaf
class File implements FileSystemComponent {
    private String name;
    private long size;

    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void showDetails() {
        System.out.println("File: " + name + " (" + size + "KB)");
    }

    @Override
    public long getSize() {
        return size;
    }
}

// 3. Composite
class Folder implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    @Override
    public void showDetails() {
        System.out.println("--- Folder: " + name + " ---");
        for (FileSystemComponent component : components) {
            component.showDetails();
        }
    }

    @Override
    public long getSize() {
        return components.stream().mapToLong(FileSystemComponent::getSize).sum();
    }
}