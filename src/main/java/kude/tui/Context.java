package kude.tui;

import kude.DukeException;
import kude.models.Task;
import kude.models.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class Context {
    private final Parser parser;
    private final TaskList tasks;
    private final Ui ui;
    private final Processor processor;
    private final DateTimeFormatter parseDateTimeFormat;

    public Context(Parser parser, Ui ui, Processor processor, TaskList tasks) {
        this.parser = parser;
        this.tasks = tasks;
        this.ui = ui;
        this.processor = processor;
        this.parseDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    public Parser getParser() {
        return parser;
    }

    public TaskList getTasks() {
        return tasks;
    }

    public Ui getUi() {
        return ui;
    }

    public void notifyAdded(Task task) {
        notifyMutated();
        ui.writeLine("Added " + task);
        ui.writeLine("List now contains " + tasks.list().count() + " tasks");
    }

    public void notifyDeleted(Task task) {
        notifyMutated();
        ui.writeLine("Deleted " + task);
        ui.writeLine("List now contains " + tasks.list().count() + " tasks");
    }

    public void notifyMutated() {
        processor.saveTaskList();
    }

    public String getArg(String provideName) {
        return parser.getArg().orElseThrow(() -> new DukeException("Provide " + provideName));
    }

    public Integer getIndexArg() {
        var arg = getArg("index");
        var index = parseInt(arg)
                .orElseThrow(() -> new DukeException("Index must be integer"));
        return index - 1;
    }

    public String getNamedArg(String name, String provideName) {
        return parser.getNamedArg(name).orElseThrow(() -> new DukeException("Provide " + provideName));
    }

    public LocalDateTime getNamedDateTimeArg(String name, String provideName) {
        return parseDateTime(getNamedArg(name, provideName)).orElseThrow(() ->
                new DukeException("Invalid format for " + provideName + ". Use `2023-02-25 23:00`"));
    }

    public Task getTask(int index) {
        return tasks.get(index).orElseThrow(() -> new DukeException("Invalid index"));
    }

    private Optional<Integer> parseInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException nfe) {
            return Optional.empty();
        }
    }

    private Optional<LocalDateTime> parseDateTime(String s) {
        try {
            return Optional.of(LocalDateTime.parse(s, parseDateTimeFormat));
        } catch (DateTimeParseException nfe) {
            return Optional.empty();
        }
    }
}
