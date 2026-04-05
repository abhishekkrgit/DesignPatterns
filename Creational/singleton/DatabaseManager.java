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


/**
 * 1. Independence from the "Parent" InstanceIf the inner class were not static (a regular inner class), it would require an instance of the outer class to exist before the inner class could be loaded.Non-Static: You'd need a Singleton object to create the Holder. This defeats the purpose of a Singleton.Static: A static nested class acts like any other top-level class. It doesn't need an instance of the parent to exist. It belongs to the class level, not the object level.2. The "Lazy Loading" MagicThis is the most important technical reason. In Java, a class is only loaded by the JVM when it is first used.Even if you load the Singleton class (perhaps by calling another static utility method inside it), the Holder class stays "dormant." It is not loaded, and the INSTANCE is not created until the exact moment someone calls getInstance().PhaseWhat happens?App StartsSingleton class is available, but Holder is NOT loaded.Call getInstance()JVM says "I need Holder now," loads the class, and initializes INSTANCE.Subsequent CallsJVM uses the already-initialized INSTANCE.
 * 
 */
