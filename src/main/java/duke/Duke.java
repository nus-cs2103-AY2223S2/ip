/**
 * CS2103T Individual Project.
 *
 * @author Wang Jiefan
 * @version 1.0
 * @since 1/20/2023
 */

package duke;

import duke.command.Command;
import duke.command.NestCommand;
import duke.util.Parser;
import duke.util.State;
import duke.util.Stateful;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The main class of the Duke program.
 */
public class Duke {

    public static final String DEFAULT_PATH = "data.txt";
    private final Ui ui;
    private final Parser parser;

    private State state;

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
            new Command("exit", "exit the app",
                    stateful -> stateful.next("Goodbye.", stateful.getState().next(true))
            ),
            new Command("help",
                    "show this help message",
                    (stateful, input) -> stateful.next(ui.getHelpMsg())
            ),
            new Command("list",
                    "list tasks",
                    stateful -> stateful.next(taskList.stringify())
            ),
            new NestCommand("add",
                    "add task",
                    new Command[]{
                        new Command("todo",
                                "add todo task",
                                (stateful, input) -> {
                                    String desc = String.join(" ", input);
                                    return stateful.next(taskList.addTodo(desc));
                                }
                        ),
                        new Command("deadline",
                                "add deadline task",
                                (stateful, input) -> {
                                    HashMap<String, String> args = Parser.extractTokensWithJoin(Set.of("/by"), input);
                                    return stateful.next(taskList.addDeadline(args.get(""), args.get("/by")));
                                }
                        ),
                        new Command("event",
                                "add event task",
                                (stateful, input) -> {
                                    HashMap<String, String> args = Parser.extractTokensWithJoin(Set.of("/from","/to"), input);
                                    return stateful.next(taskList.addEvent(args.get(""), args.get("/from"), args.get("/to")));
                                }
                        ),
                    }
            ),
            new NestCommand("mark",
                    "toggle task mark",
                    (stateful, input) -> stateful.next(taskList.toggleMark(input, null)),
                    new Command[]{
                            new Command("/done",
                                    "mark succeeding tasks as done",
                                    (stateful, input) -> stateful.next(taskList.toggleMark(input, true))
                            ),
                            new Command("/notdone",
                                    "unmark succeeding tasks",
                                    (stateful, input) -> stateful.next(taskList.toggleMark(input, false))
                            ),
                    }
            ),
            new Command("delete",
                    "delete task at index",
                    (stateful, input) -> stateful.next(taskList.delete(input))
            ),
            new Command("find",
                    "find tasks by description",
                    (stateful, input) -> stateful.next(taskList.find(String.join(" ", input)))
            ),
            new NestCommand("alias",
                    "add / remove command alias",
                    new Command[]{
                            new Command("/list",
                                    "list aliases",
                                    (stateful, input) -> {
                                        if (stateful.getState().getAliases().isEmpty()) {
                                            return stateful.next("No aliases found");
                                        }
                                        stateful.next("aliases:");
                                        stateful.getState().getAliases().entrySet().stream()
                                                .map(e -> "\t" + e.getKey() + " -> " + e.getValue())
                                                .forEach(stateful::next);
                                        return stateful;
                                    }
                            ),
                            new Command("/add",
                                    "add alias",
                                    (stateful, input) -> {
                                        HashMap<String, String> args = Parser.extractTokensWithJoin(Set.of("/is"), input);
                                        stateful.getState().getAliases().put(args.get(""), args.get("/is"));
                                        return stateful.next("alias added: " + args.get("") + " -> " + args.get("/is"));
                                    }
                            ),
                            new Command("/delete",
                                    "delete alias",
                                    (stateful, input) -> {
                                        String name = String.join(" ", input);
                                        String orig = stateful.getState().getAliases().remove(name);
                                        return stateful.next("alias deleted: " + name + " -> " + orig);
                                    }
                            ),
                    }
            ),
        };
        ui.setHelpDict(commands);
        commands[1].setFunction((stateful, input) -> {
            if (input == null || input.isEmpty()) {
                return stateful.next(ui.getHelpMsg());
            }
            List<String> commandList = input.stream()
                    .map(s -> ui.getHelpDict().get(s))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            if (commandList.isEmpty()) {
                return stateful.next("No such command.");
            }
            return stateful.next(Ui.getHelpMsg(commandList));
        });
        HashMap<String, String> aliases = new HashMap<>();
        aliases.put("q", "exit");
        aliases.put("quit", "exit");
        aliases.put("ls", "list");
        aliases.put("put", "add");
        aliases.put("push", "add");
        aliases.put("X", "mark");
        aliases.put("pop", "delete");
        aliases.put("drop", "delete");
        aliases.put("remove", "delete");
        aliases.put("rm", "delete");
        aliases.put("search", "find");
        aliases.put("rename", "alias");
        this.state = new State(false, aliases);
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
            this.state = stateful.getState();
            ui.print(String.join("\n",stateful.getOutputs()));
            if (this.state.isDoQuit()) {
                break;
            }
        }
        scanner.close();
    }

    /**
     * Get the response from the input.
     *
     * @param input The input string.
     * @return The response.
     */
    public Stateful getResponse(String input) {
        try {
            Queue<String> words = new LinkedList<>(Arrays.asList(input.split("\\s")));
            Command cmd = this.parser.parseCommand(words.remove(), this.state.getAliases());
            if (cmd.hasSubCommands()) {
                if (words.isEmpty()) {
                    throw new IllegalArgumentException("Missing argument for command: "  + cmd.getName());
                }
                Function<Stateful, Stateful> composedCommands = this.parser.parseArgs(words, (NestCommand) cmd);
                return composedCommands.apply(new Stateful(new LinkedList<>(), this.state));
            }
            return cmd.execute(new Stateful(new LinkedList<>(), this.state), words);
        } catch (Exception e) {
            return new Stateful(new LinkedList<>(Arrays.asList(e.toString().split("\\r?\\n"))),
                    this.state);
        }
    }
}