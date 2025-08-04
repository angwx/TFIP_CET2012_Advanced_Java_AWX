
import command.*;
import exceptions.CustomException;
import invoker.Invoker;
import receiver.Receiver;
import receiver.Employee;

// Main class, handles user input
// Analogy: Customer who places order
public class Client {
    public static void main(String[] args) throws CustomException {
        Receiver dataStore = new Receiver();
        Invoker commandInvoker = new Invoker();

        // Create and execute commands
        Employee emp = new Employee("John", "Doe", "john@example.com");
        Command addCmd = new AddCommand(dataStore, emp);
        commandInvoker.executeCommand(addCmd);
    }
}
