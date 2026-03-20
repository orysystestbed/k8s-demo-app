import java.util.*;
import java.sql.*;

public class SonarTestIssues {

    // Hardcoded credentials (Security Issue)
    private static final String PASSWORD = "admin123";

    // Unused field (Code Smell)
    private int unusedField = 10;

    public static void main(String[] args) {

        // Null pointer risk
        String text = null;
        if (text.equals("test")) {   // NPE
            System.out.println("Text is test");
        }

        // SQL Injection vulnerability
        String userInput = "admin' OR '1'='1";
        String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
        executeQuery(query);

        // Resource leak (Connection not closed)
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", PASSWORD);
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace(); // Bad practice
        }

        // Infinite loop (Bug)
        while (true) {
            break; // suspicious logic
        }

        // Duplicate code (Code Smell)
        int a = 5;
        int b = 10;
        System.out.println(a + b);

        int x = 5;
        int y = 10;
        System.out.println(x + y);

        // Inefficient loop (Performance issue)
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add("Item " + i);
        }

        for (int i = 0; i < list.size(); i++) { // size() called repeatedly
            System.out.println(list.get(i));
        }

        // Dead code (Unreachable)
        if (false) {
            System.out.println("This will never execute");
        }

        // Magic numbers (Code Smell)
        int result = calculate(5, 10);

        // Exception swallowed
        try {
            int num = 10 / 0;
        } catch (Exception e) {
            // ignored
        }
    }

    // Poor naming + complexity
    public static int calculate(int a, int b) {
        if (a > 0) {
            if (b > 0) {
                if (a > b) {
                    return a - b;
                } else {
                    return a + b;
                }
            }
        }
        return 0;
    }

    // SQL execution method (bad practice)
    public static void executeQuery(String query) {
        System.out.println("Executing: " + query);
    }
}