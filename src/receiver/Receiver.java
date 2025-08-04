package receiver;

import java.util.ArrayList; // for data store
import java.util.List;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import exceptions.CustomException;

// DataStore class, handles employee data storage and file operations
// Analogy: Kitchen staff who prepares food
public class Receiver {
    private ArrayList<Employee> employees = new ArrayList<>();
    private static final String FILE_PATH = "src/dataStore.txt";

    // Constructor
    public Receiver() {
        this.employees = new ArrayList<>();
        loadFromFile(); // Load existing data on startup
    }

    /**
     * Adds employee to the data store
     */
    public void addEmployee(Employee employee) throws CustomException {
        if (!EmailValidator.isValid(employee.getEmail())) {
            throw new CustomException("Invalid email format: " + employee.getEmail());
        }
        employees.add(employee);
    }

    /**
     * Updates employee at given index
     */
    public void updateEmployee(int index, String firstName, String lastName, String email)
            throws CustomException {
        if (index < 0 || index >= employees.size()) {
            throw new CustomException("Invalid index: " + (index + 1));
        }

        Employee emp = employees.get(index);

        // Update only non-null/non-empty values
        if (firstName != null && !firstName.trim().isEmpty()) {
            emp.setFirstName(firstName);
        }
        if (lastName != null && !lastName.trim().isEmpty()) {
            emp.setLastName(lastName);
        }
        if (email != null && !email.trim().isEmpty()) {
            if (!EmailValidator.isValid(email)) {
                throw new CustomException("Invalid email format: " + email);
            }
            emp.setEmail(email);
        }
    }

    /**
     * Deletes employee at given index
     */
    public Employee deleteEmployee(int index) throws CustomException {
        if (index < 0 || index >= employees.size()) {
            throw new CustomException("Invalid index: " + (index + 1));
        }
        return employees.remove(index);
    }

    /**
     * Lists all employees
     */
    public void listEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        for (int i = 0; i < employees.size(); i++) {
            System.out.printf("%02d. %s%n", (i + 1), employees.get(i));
        }
    }

    /**
     * Gets employee count
     */
    public int getEmployeeCount() {
        return employees.size();
    }

    /**
     * Gets employee at index (for undo operations)
     */
    public Employee getEmployee(int index) {
        return employees.get(index);
    }

    /**
     * Stores data to file
     */
    public void storeToFile() throws CustomException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Employee emp : employees) {
                writer.println(emp.getFirstName() + "," + emp.getLastName() + "," + emp.getEmail());
            }
        } catch (IOException e) {
            throw new CustomException("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Loads data from file on startup
     */
    private void loadFromFile() {
        try {
            if (!Files.exists(Paths.get(FILE_PATH))) {
                return; // File doesn't exist yet, that's okay
            }

            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            for (String line : lines) {
                String[] parts = line.split(",", 3);
                if (parts.length == 3) {
                    Employee emp = new Employee(parts[0].trim(), parts[1].trim(), parts[2].trim());
                    employees.add(emp); // Direct add, assume file data is valid
                }
            }
        } catch (IOException e) {
            System.err.println("Warning: Could not load data from file: " + e.getMessage());
        }
    }
}
