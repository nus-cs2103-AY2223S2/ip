package duke;

public class Duke {

    private final String FILE_PATH = System.getProperty("user.dir") + "/data/duke.txt";
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private Parser parser;

    /**
     * Creates a Duke object that references storage from the desired file path
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Gets the response from the Duke bot
     *
     * @param textInput the input from the user
     * @return the response from Duke
     */
    public String getResponse(String textInput) {
        if (textInput.equals("hi")) {
            return this.ui.welcomeUser();
        }
        String response;
        this.parser = new Parser();
        this.parser.parse(textInput);

        if (this.parser.action.equals("bye")) {
            response = this.ui.goodbyeUser();

        } else if (this.parser.action.equals("list")) {
            response = this.ui.listTasks(tasks);

        } else if (this.parser.action.equals("delete")) {
            response = this.tasks.delete(textInput);
            this.storage.writeTxt(tasks);

        } else if (this.parser.action.equals("mark")) {
            response = this.tasks.mark(textInput);
            this.storage.writeTxt(tasks);

        } else if (this.parser.action.equals("unmark")) {
            response = this.tasks.unmark(textInput);
            this.storage.writeTxt(tasks);

        } else if (this.parser.action.equals("find")) {
            try {
                String[] parts = textInput.split(" ", 2);
                response = this.tasks.find(parts);
            } catch (DukeException e) {
                response = e.toString();
            }

        } else if (this.parser.action.equals("todo")) {
            try {
                String[] parts = textInput.split(" ", 2);
                response = this.tasks.todo(parts);
                this.storage.writeTxt(tasks);
            } catch (DukeException e) {
                response = e.toString();
            }

        } else if (this.parser.action.equals("deadline")) {
            try {
                String[] parts = textInput.split("/");
                assert parts.length >= 2 : "Invalid input for deadline task";
                response = this.tasks.deadline(parts);
                this.storage.writeTxt(tasks);
            } catch (AssertionError e) {
                response = e.toString();
            }

        } else if (this.parser.action.equals("event")) {
            try {
                String[] parts = textInput.split("/");
                assert parts.length >= 3 : "Invalid input for event task";
                response = this.tasks.event(parts);
                this.storage.writeTxt(tasks);
            } catch (AssertionError e) {
                response = e.toString();
            }
        } else {
            response = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }

        return response;
    }

}