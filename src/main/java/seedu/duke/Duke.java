package seedu.duke;

public class Duke {
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;
    private TaskList tasks;

    private static final String[] LIST_OF_COMMANDS =
            new String[]{"list", "bye", "todo", "mark", "unmark", "event", "deadline", "delete", "find", "schedule"};

    private static final String SAVE_FILE_NAME = "duke.txt";

    enum Commands {
        list, bye, todo, mark, unmark, event, deadline, delete, find, schedule
    }

    public Duke() {
        this(SAVE_FILE_NAME);
    }

    public Duke(String filePath) {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.readFile();
        } catch (DukeException err) {
            System.out.println(ui.showLoadingError());
            System.out.println(err.getErrorMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     *  You should have your own function to generate a response to user input.
     *  Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            this.tasks = storage.readFile();
            String[] inputStrings = input.split(" ");
            assert inputStrings.length != 0 : "There should be an input given";
            String response = this.parser.executeCommand(inputStrings,
                    LIST_OF_COMMANDS, this.tasks, this.storage, this.ui);
            assert !response.equals("") : "Response should not be empty!";
            return response;
        } catch (DukeException err)  {
            return err.getErrorMessage();
        }
    }
}

