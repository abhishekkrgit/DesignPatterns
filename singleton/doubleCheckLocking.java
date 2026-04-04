public class DatabaseConnection {
    // 1. MUST BE VOLATILE
    // Prevents instruction reordering and ensures memory visibility.
    private static volatile DatabaseConnection instance;

    private DatabaseConnection() {
        // Private constructor to prevent instantiation
    }

    public static DatabaseConnection getInstance() {
        // Check 1: No locking (fast)
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                // Check 2: With locking (safe)
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }
}