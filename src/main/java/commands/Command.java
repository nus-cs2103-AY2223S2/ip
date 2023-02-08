package commands;

import dukeexceptions.DukeException;
import elems.Storage;
import elems.TaskList;
import elems.Ui;

import java.util.ArrayList;

public abstract class Command {
    private final String keyword;
    ArrayList<String> params;

    public Command(String keyword, ArrayList<String> params) {
        this.keyword = keyword;
        this.params = params;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
