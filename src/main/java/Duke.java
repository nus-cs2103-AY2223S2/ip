public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
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
            String response = "";
            this.parser = new Parser();
            this.parser.parse(textInput);

            if (this.parser.action == "bye") {
                this.ui.goodbyeUser();
                return;
            }

            else if (this.parser.action == "list") {
                this.ui.listTasks(tasks);
                continue;
            }

            else if (this.parser.action == "delete") {
                response = this.tasks.delete(textInput);
                this.storage.writeTxt(tasks);
            }

            else if (this.parser.action == "mark") {
                response = this.tasks.mark(textInput);
                this.storage.writeTxt(tasks);
            }

            else if (this.parser.action == "unmark") {
                response = this.tasks.unmark(textInput);
                this.storage.writeTxt(tasks);
            }

            else if (this.parser.action == "todo") {
                try {
                    response = this.tasks.todo(textInput);
                    this.storage.writeTxt(tasks);             
                } catch (DukeException e) {
                    response = e.toString();
                    this.ui.printResponse(response);
                    continue;
                }
            }

            else if (this.parser.action == "deadline") {
                response = this.tasks.deadline(textInput);
                this.storage.writeTxt(tasks);
            }

            else if (this.parser.action == "event") {
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
        new Duke("../../../data/duke.txt").run();
    }
}