package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.ToDo;

import java.util.ArrayList;

/**
 * This is the AddToDoCommand class to represent add ToDo commands passed to Duke.
 * Encapsulates the information needed to add an ToDo object to the TaskList.
 */
public class AddToDoCommand extends AddCommand {
    /**
     * Creates a new AddToDoCommand.
     * @param tokens {@inheritDoc}
     * @throws DukeException when exceptions are encountered in creating the ToDo.
     */
    public AddToDoCommand(ArrayList<String> tokens) throws DukeException {
        super(tokens);
        if (tokens.size() == 1) {
            throw new DukeException("todo cannot have no description!");
        }
        String name = String.join(" ", tokens.subList(1, tokens.size()));
        ToDo newToDo = new ToDo(name);
        super.setTaskToAdd(newToDo);
    }
}
