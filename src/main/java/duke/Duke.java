package duke;

public class Duke {

    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private Parser parser;

    /**
     * Creates a Duke object that references storage from the desired file path
     *
     * @param filePath the file path to the storage txt file
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Begins the Duke chat loop
     */
    public void run() {

        this.ui.welcomeUser();

        while (true) {
            String textInput = this.ui.readInput();
            String response;
            this.parser = new Parser();
            this.parser.parse(textInput);

            if (this.parser.action.equals("bye")) {
                this.ui.goodbyeUser();
                return;
            } else if (this.parser.action.equals("list")) {
                this.ui.listTasks(tasks);
                continue;
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
                    this.ui.printResponse(response);
                    continue;
                }
            } else if (this.parser.action.equals("todo")) {
                try {
                    String[] parts = textInput.split(" ", 2);
                    response = this.tasks.todo(parts);
                    this.storage.writeTxt(tasks);
                } catch (DukeException e) {
                    response = e.toString();
                    this.ui.printResponse(response);
                    continue;
                }
            } else if (this.parser.action.equals("deadline")) {
                String[] parts = textInput.split("/");
                response = this.tasks.deadline(parts);
                this.storage.writeTxt(tasks);
            } else if (this.parser.action.equals("event")) {
                String[] parts = textInput.split("/");
                response = this.tasks.event(textInput);
                this.storage.writeTxt(tasks);
            } else {
                response = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            }

            this.ui.printResponse(response);
        }
    }

    public static void main(String[] args) {
        new Duke(System.getProperty("user.dir") + "/data/duke.txt").run();
    }
}