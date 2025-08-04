package command;

import receiver.Receiver;
import receiver.Employee;

import exceptions.CustomException;

public class DeleteCommand implements Command {
    private Receiver receiver;
    private int index;
    private Employee deletedEmployee;
    private boolean executed = false;

    public DeleteCommand(Receiver receiver, int index) {
        this.receiver = receiver;
        this.index = index;
    }

    @Override
    public void execute() throws CustomException {
        deletedEmployee = receiver.deleteEmployee(index);
        executed = true;
    }

    @Override
    public void undo() throws CustomException {
        if (executed && deletedEmployee != null) {
            // Re-insert at the same position
            receiver.addEmployee(deletedEmployee);
            // Note: This is simplified - you might need to implement insertAt() method
            executed = false;
        }
    }
}