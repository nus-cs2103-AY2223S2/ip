package duke.commands;

import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.exceptions.DukeException;

import java.util.ArrayList;

public abstract class Command {
    ArrayList<String> tokens;
    public Command(ArrayList<String> tokens) {
        this.tokens = tokens;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;


    public abstract boolean isExit();
}
