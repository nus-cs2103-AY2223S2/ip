package seedu.duke;

import java.util.Scanner;

public class Duke {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    private final static String[] COMMANDS_LIST =
            new String[]{"list", "bye", "todo", "mark", "unmark", "event", "deadline", "delete"};

    enum Commands {
        start, list, bye, todo, mark, unmark, event, deadline, delete
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
            String[] inputArr = input.split(" ");
            try {
                command = parser.executeCommand(inputArr, COMMANDS_LIST, this.tasks, this.storage, this.ui);
            } catch (DukeException err) {
                System.out.println(err.getErrorMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}

