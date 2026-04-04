public enum DatabaseService {
    // 1. The Single Instance
    // This is thread-safe and protected against Reflection/Serialization by the JVM.
    INSTANCE;

    // 2. Fields (State)
    private String connectionUrl;
    private boolean isInitialized = false;

    // 3. The Private Constructor
    // Called exactly once by the JVM when the class is first referenced.
    DatabaseService() {
        this.connectionUrl = "jdbc:mysql://localhost:3306/mydb";
        // Initialize heavy resources here
        this.isInitialized = true;
    }

    // 4. Global Access Point (Optional but recommended for clean APIs)
    public static DatabaseService getInstance() {
        return INSTANCE;
    }

    // 5. Business Logic (Example Methods)
    public void executeQuery(String query) {
        if (isInitialized) {
            System.out.println("Executing: " + query + " on " + connectionUrl);
        }
    }

    public String getConnectionStatus() {
        return isInitialized ? "Connected" : "Disconnected";
    }
}