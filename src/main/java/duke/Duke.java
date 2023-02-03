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
import duke.util.Parser;
import duke.util.State;
import duke.util.Stateful;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main class of the Duke program.
 */
public class Duke {

    public static final String DEFAULT_PATH = "data.txt";
    private final Ui ui;
    private final Parser parser;

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
        Command[] commands = new Command[]{
            new BasicCommand("exit", "exit the app",
                    () -> new Stateful(
                            new String[]{ "Goodbye." },
                            new State(true)
                    )
            ),
            new BasicCommand("help",
                    "show this help message",
                    () -> new Stateful(
                            ui.getHelpMsg(),
                            new State(false)
                    )
            ),
            new BasicCommand("list",
                    "list tasks",
                    () -> new Stateful(
                            taskList.stringify(),
                            new State(false)
                    )
            ),
            new ArgCommand("add",
                    "add task",
                    new String[]{ "\\s" },
                    args -> new Stateful(
                            taskList.add(args),
                            new State(false)
                    )
            ),
            new ArgCommand("mark",
                    "mark/unmark task as done",
                    new String[]{},
                    args -> new Stateful(
                            taskList.mark(args),
                            new State(false)
                    )
            ),
            new ArgCommand("delete",
                    "delete task",
                    new String[]{},
                    args -> new Stateful(
                            taskList.delete(args),
                            new State(false)
                    )
            ),
            new ArgCommand("find",
                    "find tasks containing text fragment",
                    new String[]{},
                    args -> new Stateful(
                            taskList.find(args),
                            new State(false)
                    )
            ),
        };
        ui.setHelpMsg(commands);
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
        while (scanner.hasNextLine()) {
            Stateful stateful = this.getResponse(scanner.nextLine());
            ui.print(stateful.output());
            if (stateful.state().doQuit()) {
                break;
            }
        }
        scanner.close();
    }

    public Stateful getResponse(String input) {
        try {
            String[] lineParts = input.split("\\s", 2);
            Command cmd = this.parser.parseCommand(lineParts[0]);
            if (cmd.hasParams()) {
                if (lineParts.length < 2 || lineParts[1].isEmpty()) {
                    throw new IllegalArgumentException("Missing argument.");
                }
                String[] arguments = Parser.parseArgs(lineParts[1], cmd);
                return cmd.execute(arguments);
            } else {
                return cmd.execute(new String[]{});
            }
        } catch (Exception e) {
            return new Stateful(e.toString().split("\\r?\\n"), new State(false));
        }
    }
}