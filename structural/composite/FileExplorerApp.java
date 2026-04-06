package structural.composite;

import java.util.*;

interface FileSystemItems {
    int getSize();

    void printStructure(String indent);

    void delete();
}

class File implements FileSystemItems {
    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void printStructure(String indent) {
        System.out.println(indent + "- " + name + "(" + size + ")" + "kb");
    }

    @Override
    public void delete() {
        System.out.println("deleting file: " + name);
    }
}

class Folder implements FileSystemItems {
    private String name;
    private final List<FileSystemItems> items = new ArrayList<>();;

    public Folder(String name) {
        this.name = name;
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for (FileSystemItems item : items) {
            totalSize += item.getSize();
        }
        return totalSize;
    }

    public void addItems(FileSystemItems item) {
        items.add(item);
    }

    public void remove(FileSystemItems item) {
        items.remove(item);
    }

    @Override
    public void printStructure(String indent) {
        System.out.println(indent + "+ " + name + "/");
        for (FileSystemItems item : items) {
            item.printStructure(indent + " ");
        }
    }

    @Override
    public void delete() {
        for (FileSystemItems item : items) {
            item.delete();
        }
        System.out.println("Folder " + name + " deleted..");
    }
}

public class FileExplorerApp {

    public static void main(String[] args) {

        File abhishek = new File("abhishek.jpg", 5);
        File nope = new File("nope.jpg", 10);
        Folder photos = new Folder("personalImg");
        photos.addItems(abhishek);
        photos.addItems(nope);

        Folder documents = new Folder("documents");
        File resume = new File("Resume.pdf", 10);
        File offerLetter = new File("OfferLetter.pdf", 20);
        documents.addItems(resume);
        documents.addItems(offerLetter);

        Folder home = new Folder("home");
        home.addItems(documents);
        home.addItems(photos);
        home.printStructure("");

        System.out.println("\nTotal Size: " + home.getSize());
    }

}
