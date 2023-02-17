package kude.processor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import kude.DukeException;
import kude.Storage;
import kude.models.Deadline;
import kude.models.Event;
import kude.models.TaskList;
import kude.models.Todo;

/**
 * Main terminal UI-based command line runner
 */
public class Processor {
    private final Ui ui;
    private final TaskList tasks;
    private final Storage storage;
    private final HashMap<String, Command> commands;

    /**
     * Create a new Processor
     * @param ui Ui to interface with
     * @param storage Storage instance to save to
     */
    public Processor(Ui ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;

        this.commands = new HashMap<>();
        registerCommands();

        TaskList tasks = null;
        try {
            tasks = storage.readTaskList();
        } catch (IOException ioe) {
            this.ui.writeError("Save file could not be read from, reinitializing");
        } catch (ClassNotFoundException cnfe) {
            this.ui.writeError("Save file corrupted or tampered with, reinitializing");
        }
        if (tasks == null) {
            tasks = new TaskList();
            saveTaskList();
        }
        this.tasks = tasks;
    }

    void registerCommands() {

        register("list", ctx -> {
            var idx = new Integer[] {0};

            ctx.getTasks().list().forEachOrdered(task -> {
                idx[0]++;
                ctx.getUi().writeLine(idx[0] + ". " + task);
            });
        });

        register("mark", ctx -> {
            var index = ctx.getIndexArg();
            var task = ctx.getTask(index);

            task.setIsDone(true);
            ctx.notifyMutated();

            ctx.getUi().writeLine("Marked " + task);
        });

        register("unmark", ctx -> {
            var index = ctx.getIndexArg();
            var task = ctx.getTask(index);

            task.setIsDone(false);
            ctx.notifyMutated();

            ctx.getUi().writeLine("Unmarked " + task);
        });

        register("todo", ctx -> {
            var content = ctx.getArg("content");
            var place = ctx.getParser().getNamedArg("place");
            var task = new Todo(content, place);
            ctx.getTasks().add(task);
            ctx.notifyAdded(task);
        });

        register("deadline", ctx -> {
            var content = ctx.getArg("content");
            var deadline = ctx.getNamedDateTimeArg("by", "deadline");
            var place = ctx.getParser().getNamedArg("place");
            var task = new Deadline(content, place, deadline);
            ctx.getTasks().add(task);
            ctx.notifyAdded(task);
        });

        register("event", ctx -> {
            var content = ctx.getArg("content");
            var from = ctx.getNamedDateTimeArg("from", "from");
            var to = ctx.getNamedDateTimeArg("to", "to");
            var place = ctx.getParser().getNamedArg("place");
            var task = new Event(content, place, from, to);
            ctx.getTasks().add(task);
            ctx.notifyAdded(task);
        });

        register("delete", ctx -> {
            var index = ctx.getIndexArg();
            var task = ctx.getTask(index);

            ctx.getTasks().delete(task);
            ctx.notifyDeleted(task);
        });

        register("find", ctx -> {
            var query = ctx.getArg("query");
            var idx = new Integer[] {0};

            ctx.getTasks().list()
                .filter(t -> t.getContent().toLowerCase().contains(query.toLowerCase()))
                .forEachOrdered(task -> {
                    idx[0]++;
                    ctx.getUi().writeLine(idx[0] + ". " + task);
                });
        });
    }

    /**
     * Registers a command for into the processor
     * @param command Command name
     * @param cmd Command to execute
     */
    public void register(String command, Command cmd) {
        this.commands.put(command, cmd);
    }

    /**
     * Runs the command in the given line
     * @param line Command line
     * @return true if the process should exit
     */
    public boolean runCommand(String line) {
        var parser = new Parser(line);
        var cmdName = parser.getCommand();
        assert cmdName != null;
        if ("bye".equals(cmdName)) {
            return true;
        }
        var context = new Context(parser, ui, this, tasks);
        var cmdOpt = Optional.ofNullable(this.commands.get(parser.getCommand()));
        cmdOpt.ifPresentOrElse(cmd -> {
            try {
                cmd.run(context);
            } catch (DukeException de) {
                ui.writeError(de.getMessage());
            } catch (Exception e) {
                ui.writeError("An unhandled exception occurred while running the command: " + e);
            }
        }, () -> ui.writeError("No such command!"));
        return false;
    }

    /**
     * Saves the current task list
     */
    public void saveTaskList() {
        try {
            storage.writeTaskList(tasks);
        } catch (IOException ioe) {
            this.ui.writeError("Save file could not be written to!");
            throw new RuntimeException(ioe);
        }
    }
}
