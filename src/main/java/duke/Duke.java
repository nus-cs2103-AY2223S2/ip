/**
 * CS2103T Individual Project.
 *
 * @author Wang Jiefan
 * @version 1.0
 * @since 1/20/2023
 */

package duke;

import duke.command.ArgCommand;
import duke.command.BasicCommand;
import duke.command.Command;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main class of the Duke program.
 */
public class Duke {

    public static final String DEFAULT_PATH = "data.txt";
    private final Ui ui;
    private final Parser parser;
    private Boolean hasQuit = false; // in case there are more commands that affect
    // program execution or state, might replace
    // with a proper State() class

    public Duke() {
        this(DEFAULT_PATH);
    }

    public Duke(String filename) {
        this.ui = new Ui();
        TaskList taskList;
        TaskList temp;
        try {
            temp = new TaskList(filename);
        } catch (Exception e) {
            ui.loadError(e);
            temp = new TaskList(new ArrayList<>());
        }
        taskList = temp;
        Command[] commands = new Command[]{ new BasicCommand("exit", "exit the app", hasQuit -> new String[]{ "Goodbye." }), new BasicCommand("help", "show this help message", hasQuit -> ui.getHelpMsg()), new BasicCommand("list", "list tasks", hasQuit -> taskList.stringify()), new ArgCommand("add", "add task", new String[]{ "\\s" }, (args, hasQuit) -> taskList.add(args)), new ArgCommand("delete", "delete task", new String[]{}, (args, hasQuit) -> taskList.delete(args)),
                // The following commands can take any number of space-delimited
                // unnamed integer arguments (dang that was a mouthful). See
                // implementation details in TaskList.java.
                new ArgCommand("mark", "mark/unmark task as done", new String[]{}, (args, hasQuit) -> taskList.mark(args)), new ArgCommand("find", "find tasks containing text fragment", new String[]{}, (args, hasQuit) -> taskList.find(args)), };
        ui.setCommands(commands);
        this.parser = new Parser(commands);
    }

    /**
     * The start of execution of the Duke program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        this.ui.printIntro();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine() && !hasQuit) {
            this.getResponse(scanner.nextLine());
        }
        scanner.close();
    }

    /**
     * I/O handler for Duke. Entrypoint for {@link duke.controller.MainWindow}.
     *
     * @param input An input string.
     * @return output for given input.
     */
    public String getResponse(String input) {
        try {
            String[] lineParts = input.split("\\s", 2);
            Command cmd = this.parser.parseCommand(lineParts[0]);
            if (cmd.getName().equals("exit")) {
                this.hasQuit = false;
            }
            if (cmd.hasParams()) {
                if (lineParts.length < 2 || lineParts[1].isEmpty()) {
                    throw new IllegalArgumentException("Missing argument.");
                }
                String[] arguments = Parser.parseArgs(lineParts[1], cmd);
                return this.join(cmd.execute(arguments, hasQuit));
            } else {
                return this.join(cmd.execute(new String[]{}, hasQuit));
            }
        } catch (Exception e) {
            return e.toString();
        }
    }

    private String join(String[] lines) {
        StringBuilder outputs = new StringBuilder();
        for (String str : lines) {
            outputs.append("\t").append(str).append("\n");
        }
        return outputs.toString();
    }
}