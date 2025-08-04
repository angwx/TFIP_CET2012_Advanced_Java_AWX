package command;

import receiver.Receiver;
import receiver.Employee;
import exceptions.CustomException;

public class UpdateCommand implements Command {
    private Receiver receiver;
    private int index;
    private String newFirstName, newLastName, newEmail;
    private String oldFirstName, oldLastName, oldEmail;
    private boolean executed = false;

    public UpdateCommand(Receiver receiver, int index, String firstName, String lastName, String email) {
        this.receiver = receiver;
        this.index = index;
        this.newFirstName = firstName;
        this.newLastName = lastName;
        this.newEmail = email;
    }

    @Override
    public void execute() throws CustomException {
        // Store old values for undo
        Employee oldEmp = receiver.getEmployee(index);
        oldFirstName = oldEmp.getFirstName();
        oldLastName = oldEmp.getLastName();
        oldEmail = oldEmp.getEmail();

        // Update with new values
        receiver.updateEmployee(index, newFirstName, newLastName, newEmail);
        executed = true;
    }

    @Override
    public void undo() throws CustomException {
        if (executed) {
            receiver.updateEmployee(index, oldFirstName, oldLastName, oldEmail);
            executed = false;
        }
    }
}
