package duke.command;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.task.Event;

public class CreateEvent extends Command {
    protected static ArrayList<String> aliases = new ArrayList<>(Arrays.asList("event", "e"));
    private String description;
    private LocalDate start;
    private LocalDate end;

    public CreateEvent(String description, LocalDate start, LocalDate end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Integer size = tasks.size();
        tasks.add(new Event(description, start, end, false));
        assert size + 1 == tasks.size();
        return ui.showAdd(tasks.get(tasks.size() - 1), tasks.size());
    }

    public static boolean checkAlias(String alias) {
       return aliases.contains(alias);
    } 
}
