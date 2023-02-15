package duke;

public class Duke {

    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private Parser parser;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

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
            }

            else if (this.parser.action.equals("list")) {
                this.ui.listTasks(tasks);
                continue;
            }

            else if (this.parser.action.equals("delete")) {
                response = this.tasks.delete(textInput);
                this.storage.writeTxt(tasks);
            }

            else if (this.parser.action.equals("mark")) {
                response = this.tasks.mark(textInput);
                this.storage.writeTxt(tasks);
            }

            else if (this.parser.action.equals("unmark")) {
                response = this.tasks.unmark(textInput);
                this.storage.writeTxt(tasks);
            }

            else if (this.parser.action.equals("find")) {
                try {
                    response = this.tasks.find(textInput);
                } catch (DukeException e) {
                    response = e.toString();
                    this.ui.printResponse(response);
                    continue;
                }
            }

            else if (this.parser.action.equals("todo")) {
                try {
                    response = this.tasks.todo(textInput);
                    this.storage.writeTxt(tasks);             
                } catch (DukeException e) {
                    response = e.toString();
                    this.ui.printResponse(response);
                    continue;
                }
            }

            else if (this.parser.action.equals("deadline")) {
                response = this.tasks.deadline(textInput);
                this.storage.writeTxt(tasks);
            }

            else if (this.parser.action.equals("event")) {
                response = this.tasks.event(textInput);
                this.storage.writeTxt(tasks);
            }
            
            else {
                response = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            }

            this.ui.printResponse(response);
        }
    }
    
    public static void main(String[] args) {
        new Duke(System.getProperty("user.dir") + "/data/duke.txt").run();
    }
}