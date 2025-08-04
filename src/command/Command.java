package command;

import exceptions.CustomException;

// Command interface
// Analogy: Written order slip with all details
public interface Command {
    void execute() throws CustomException;
    void undo() throws CustomException; // For undo function
}
