```markdown
# The Composite Design Pattern

The **Composite Pattern** is a structural design pattern that allows you to compose objects into tree structures to represent part-whole hierarchies. It lets clients treat individual objects and compositions of objects uniformly.

Think of it like a file system: a **Folder** (Composite) can contain **Files** (Leaf) or other **Folders** (Composite). Whether you are calculating the total size of a file or a folder, you want to call the same method without caring about the difference.

---

## 1. Core Concepts

* **Component**: The base interface or abstract class that defines common operations for both simple and complex objects.
* **Leaf**: The basic building block of the composition. It has no children and implements the behavior for the primitive objects.
* **Composite**: The complex object that has children (Leafs or other Composites). It implements methods to manipulate children and delegates work to them.
* **Client**: Manipulates objects in the composition through the Component interface.

---

## 2. When to Use It

* When you need to represent a **tree-like hierarchy** of objects.
* When you want the client code to **ignore the difference** between individual objects and collections of objects.
* When you want to simplify client code by providing a **single interface** for all elements in the tree.

---

## 3. Implementation in Java

In this example, we represent a **FileSystem**. A `File` is a Leaf, and a `Directory` is a Composite.

```java
import java.util.ArrayList;
import java.util.List;

// 1. The Component Interface
interface FileSystemComponent {
    void showDetails();
}

// 2. The Leaf (Individual Object)
class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("File: " + name);
    }
}

// 3. The Composite (Object containing children)
class Directory implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    public void removeComponent(FileSystemComponent component) {
        components.remove(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Directory: " + name);
        for (FileSystemComponent component : components) {
            // Uniformly calling the method regardless of type
            component.showDetails();
        }
    }
}

// 4. Usage
public class Main {
    public static void main(String[] args) {
        // Create individual files (Leafs)
        FileSystemComponent file1 = new File("resume.pdf");
        FileSystemComponent file2 = new File("photo.jpg");
        FileSystemComponent file3 = new File("notes.txt");

        // Create directories (Composites)
        Directory root = new Directory("Root");
        Directory documents = new Directory("Documents");
        Directory images = new Directory("Images");

        // Build the tree structure
        root.addComponent(documents);
        root.addComponent(images);

        documents.addComponent(file1);
        documents.addComponent(file3);
        images.addComponent(file2);

        // Display the entire structure uniformly
        root.showDetails();
    }
}
```

---

## 4. Key Advantages

* **Simplifies Client Code**: The client doesn't need to check whether an object is a leaf or a composite.
* **Easier to add new types**: You can add new Component subclasses without changing existing code.
* **Recursive Structure**: It naturally handles nested structures of any depth.

---

## 5. Potential Drawbacks

* **Over-generalization**: It can make the design too general. It’s hard to restrict the components of a composite to only certain types at compile time.
* **Complexity**: For very simple hierarchies, this pattern might be overkill.
```