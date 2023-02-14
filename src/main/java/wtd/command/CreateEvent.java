package wtd.command;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import wtd.Storage;
import wtd.TaskList;
import wtd.Ui;
import wtd.exceptions.WtdException;
import wtd.task.Event;

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
    public String getCommand() {
        return "event";
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WtdException {
        Integer size = tasks.size();
        tasks.add(new Event(description, start, end, false));
        assert size + 1 == tasks.size();
        return ui.showAdd(tasks.get(tasks.size() - 1), tasks.size());
    }

    public static boolean checkAlias(String alias) {
       return aliases.contains(alias);
    } 
}
