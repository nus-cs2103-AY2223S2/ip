package commands;

import commands.AddCommand;
import exceptions.DukeException;
import tasks.ToDo;

import java.util.ArrayList;

public class AddToDoCommand extends AddCommand {
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
