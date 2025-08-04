package command;

import receiver.Receiver;
import receiver.Employee;
import exceptions.CustomException;

// Concrete Command
// Analogy: Specific orders like "Burger Order", "Pizza Order"
public class AddCommand implements Command {
    private Receiver receiver;
    private Employee employee;
    private boolean executed = false;

    // Constructor
    public AddCommand(Receiver receiver, Employee employee) {
        this.receiver = receiver;
        this.employee = employee;
    }

    @Override
    public void execute() throws CustomException {
        receiver.addEmployee(employee);
        executed = true;
    }

    @Override
    public void undo() throws CustomException {
        if (executed) {
            int lastIndex = receiver.getEmployeeCount() - 1;
            receiver.deleteEmployee(lastIndex);
            executed = false;
        }
    }
}
