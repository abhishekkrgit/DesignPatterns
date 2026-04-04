public class DatabaseManager {

    // 1. Private constructor prevents instantiation from other classes
    private DatabaseManager() {
        System.out.println("Heavy Database Connection Initialized...");
    }

    // 2. The "Holder" - a private static inner class
    // This class is NOT loaded into memory until getInstance() is called.
    private static class Holder {
        // The JVM handles the thread-safe creation of this constant
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    // 3. Global Access Point
    public static DatabaseManager getInstance() {
        // When this is called, the Holder class is loaded for the first time
        return Holder.INSTANCE;
    }

    public void connect() {
        System.out.println("Connected to DB.");
    }
}