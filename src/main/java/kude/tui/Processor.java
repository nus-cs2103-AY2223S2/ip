package kude.tui;

import kude.DukeException;
import kude.Storage;
import kude.models.Deadline;
import kude.models.Event;
import kude.models.TaskList;
import kude.models.Todo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

public class Processor {
    private final Ui ui;
    private final TaskList tasks;
    private final Storage storage;
    private final HashMap<String, Command> commands;

    public Processor(Ui ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;

        this.commands = new HashMap<>();
        registerCommands();

        TaskList tasks = null;
        try {
            tasks = storage.readTaskList();
        } catch (IOException ioe) {
            this.ui.writeErr("Save file could not be read from, reinitializing");
        } catch (ClassNotFoundException cnfe) {
            this.ui.writeErr("Save file corrupted or tampered with, reinitializing");
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
            var task = new Todo(content);
            ctx.getTasks().add(task);
            ctx.notifyAdded(task);
        });

        register("deadline", ctx -> {
            var content = ctx.getArg("content");
            var deadline = ctx.getNamedDateTimeArg("by", "deadline");
            var task = new Deadline(content, deadline);
            ctx.getTasks().add(task);
            ctx.notifyAdded(task);
        });

        register("event", ctx -> {
            var content = ctx.getArg("content");
            var from = ctx.getNamedDateTimeArg("from", "from");
            var to = ctx.getNamedDateTimeArg("to", "to");
            var task = new Event(content, from, to);
            ctx.getTasks().add(task);
            ctx.notifyAdded(task);
        });

        register("delete", ctx -> {
            var index = ctx.getIndexArg();
            var task = ctx.getTask(index);

            ctx.getTasks().delete(task);
            ctx.notifyDeleted(task);
        });
    }

    public void register(String command, Command cmd) {
        this.commands.put(command, cmd);
    }

    public void run() {
        ui.writeWelcome();

        while (true) {
            ui.writePrompt();
            var line = ui.getCommandLine();
            if (line.equals("bye")) {
                ui.writeBye();
                break;
            }
            var parser = new Parser(line);
            var context = new Context(parser, ui, this, tasks);
            var cmdOpt = Optional.ofNullable(this.commands.get(parser.getCommand()));
            cmdOpt.ifPresentOrElse(cmd -> {
                try {
                    cmd.run(context);
                } catch (DukeException de) {
                    ui.writeErr(de.getMessage());
                } catch (Exception e) {
                    ui.writeErr("An unhandled exception occurred while running the command: " + e);
                }
            }, () -> ui.writeErr("No such command!"));
        }
    }

    public void saveTaskList() {
        try {
            storage.writeTaskList(tasks);
        } catch (IOException ioe) {
            this.ui.writeErr("Save file could not be written to!");
            throw new RuntimeException(ioe);
        }
    }
}
