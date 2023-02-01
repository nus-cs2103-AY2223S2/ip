package seedu.duke;

import java.util.Scanner;

public class Duke {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    private static final String[] LIST_OF_COMMANDS =
            new String[]{"list", "bye", "todo", "mark", "unmark", "event", "deadline", "delete", "find"};

    enum Commands {
        start, list, bye, todo, mark, unmark, event, deadline, delete, find
    }

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.readFile();
        } catch (DukeException err) {
            ui.showLoadingError();
            System.out.println(err.getErrorMessage());
            this.tasks = new TaskList();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        Commands command = Commands.start;
        this.ui.sayGreetings();
        while (!command.equals(Commands.bye)) {
            String input = sc.nextLine();
            String[] inputStrings = input.split(" ");
            try {
                command = parser.executeCommand(inputStrings, LIST_OF_COMMANDS, this.tasks, this.storage, this.ui);
            } catch (DukeException err) {
                System.out.println(err.getErrorMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}

