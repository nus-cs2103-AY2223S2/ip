package kude.tui;

import kude.DukeException;
import kude.models.Deadline;
import kude.models.Event;
import kude.models.ItemList;
import kude.models.Todo;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class Processor {
    private final InputStream inStream;
    private final Scanner inStreamScanner;
    private final PrintStream outStream;
    private final Output output;
    private final ItemList items;
    private final HashMap<String, Command> commands;

    public Processor(InputStream in, PrintStream out, ItemList items) {
        this.inStream = in;
        this.outStream = out;
        this.inStreamScanner = new Scanner(in);
        this.items = items;
        this.output = new Output(out);

        this.commands = new HashMap<>();
        registerCommands();
    }

    void registerCommands() {

        register("list", ctx -> {
            var idx = new Integer[] {0};

            ctx.getItems().list().forEachOrdered(item -> {
                idx[0]++;
                ctx.getOutput().writeLine(idx[0] + ". " + item);
            });
        });

        register("mark", ctx -> {
            var index = ctx.getIndexArg();
            var item = ctx.getItem(index);

            item.setIsDone(true);

            ctx.getOutput().writeLine("Marked " + item);
        });

        register("unmark", ctx -> {
            var index = ctx.getIndexArg();
            var item = ctx.getItem(index);

            item.setIsDone(false);

            ctx.getOutput().writeLine("Unmarked " + item);
        });

        register("todo", ctx -> {
            var content = ctx.getArg("content");
            var item = new Todo(content);
            ctx.getItems().add(item);
            ctx.notifyAdded(item);
        });

        register("deadline", ctx -> {
            var content = ctx.getArg("content");
            var deadline = ctx.getNamedDateTimeArg("by", "deadline");
            var item = new Deadline(content, deadline);
            ctx.getItems().add(item);
            ctx.notifyAdded(item);
        });

        register("event", ctx -> {
            var content = ctx.getArg("content");
            var from = ctx.getNamedDateTimeArg("from", "from");
            var to = ctx.getNamedDateTimeArg("to", "to");
            var item = new Event(content, from, to);
            ctx.getItems().add(item);
            ctx.notifyAdded(item);
        });

        register("delete", ctx -> {
            var index = ctx.getIndexArg();
            var item = ctx.getItem(index);

            ctx.getItems().delete(item);
            ctx.notifyDeleted(item);
        });
    }

    public void register(String command, Command cmd) {
        this.commands.put(command, cmd);
    }

    public void run() {
        outStream.println("<<<\tHello, I'm Kude!");

        while (true) {
            outStream.print("> ");
            var line = inStreamScanner.nextLine();
            if (line.equals("bye")) {
                outStream.println("<<<\tBye");
                break;
            }
            var parser = new Parser(line);
            var context = new Context(parser, items, output);
            var cmdOpt = Optional.ofNullable(this.commands.get(parser.getCommand()));
            cmdOpt.ifPresentOrElse(cmd -> {
                try {
                    cmd.run(context);
                } catch (DukeException de) {
                    output.writeErr(de.getMessage());
                } catch (Exception e) {
                    output.writeErr("An unhandled exception occurred while running the command: " + e);
                }
            }, () -> output.writeErr("No such command!"));
        }
    }
}
