package invoker;

import command.Command;
import exceptions.CustomException;

import java.util.Stack; // for command history

// CommandInvoker class, manages execution & history
// Analogy: Waiter who takes order to kitchen
public class Invoker {
    private Stack<Command> commandHistory = new Stack<>();

    public void executeCommand(Command command) throws CustomException {
        command.execute();
        commandHistory.push(command);
    }

    public void undoLastCommand() throws CustomException {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.pop();
            lastCommand.undo();
        }
    }
}
