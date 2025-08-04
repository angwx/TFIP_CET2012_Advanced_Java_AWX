package command;

import receiver.Receiver;
import exceptions.CustomException;

public class ListCommand implements Command {
    private Receiver receiver;

    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() throws CustomException {
        receiver.listEmployees();
    }

    @Override
    public void undo() throws CustomException {
        // List command cannot be undone - it doesn't change state
        System.out.println("Cannot undo list operation.");
    }
}
