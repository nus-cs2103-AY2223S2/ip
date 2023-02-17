package kude.processor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import kude.DukeException;
import kude.models.Task;
import kude.models.TaskList;

/**
 * Contextual information for {@link Command}
 */
public class Context {
    private final Parser parser;
    private final TaskList tasks;
    private final Ui ui;
    private final Processor processor;
    private final DateTimeFormatter parseDateTimeFormat;

    /**
     * Initializes a new Context.
     */
    public Context(Parser parser, Ui ui, Processor processor, TaskList tasks) {
        this.parser = parser;
        this.tasks = tasks;
        this.ui = ui;
        this.processor = processor;
        this.parseDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    /**
     * Gets the Command Parser
     */
    public Parser getParser() {
        return parser;
    }

    /**
     * Gets the Task list
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Gets the User Interface abstraction
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Notify that a task has been added. Calls {@link Context#notifyMutated} underneath as well.
     */
    public void notifyAdded(Task task) {
        notifyMutated();
        ui.writeLine("Added " + task);
        ui.writeLine("List now contains " + tasks.list().count() + " tasks");
    }

    /**
     * Notify that a task has been deleted. Calls {@link Context#notifyMutated} underneath as well.
     */
    public void notifyDeleted(Task task) {
        notifyMutated();
        ui.writeLine("Deleted " + task);
        ui.writeLine("List now contains " + tasks.list().count() + " tasks");
    }

    /**
     * Notify that a task has been mutated.
     */
    public void notifyMutated() {
        processor.saveTaskList();
    }

    /**
     * Gets the main argument for the command
     * @param provideName Field name to ask from user
     */
    public String getArg(String provideName) {
        return parser.getArg().orElseThrow(() -> new DukeException("Provide " + provideName));
    }

    /**
     * Gets the main argument for the command as an index into the Task List
     */
    public Integer getIndexArg() {
        var arg = getArg("index");
        var index = parseInt(arg)
                .orElseThrow(() -> new DukeException("Index must be integer"));
        return index - 1;
    }

    /**
     * Gets a named argument for the command
     * @param name Name for argument
     * @param provideName Field name to ask from user
     */
    public String getNamedArg(String name, String provideName) {
        return parser.getNamedArg(name).orElseThrow(() -> new DukeException("Provide " + provideName));
    }

    /**
     * Gets a named argument for the command as a {@link LocalDateTime}
     * @param name Name for argument
     * @param provideName Field name to ask from user
     */
    public LocalDateTime getNamedDateTimeArg(String name, String provideName) {
        return parseDateTime(getNamedArg(name, provideName)).orElseThrow(() ->
                new DukeException("Invalid format for " + provideName + ". Use `2023-02-25 23:00`"));
    }

    /**
     * Gets the task at the specified index
     */
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
