package duke;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private Ui ui = new Ui();

    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            taskList = new TaskList();
        }
        this.parser = new Parser(taskList);
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.runApp();
    }

    public void runApp() {
        ui.showDuke();
        ui.greetingMessage();
        boolean enteredBye = false;
        while (!enteredBye) {
            try {
                String input = ui.readInput();
                if (input.equals("bye")) {
                    enteredBye = true;
                } else {
                    parser.parseInput(input);
                }
                storage.save(taskList);
            } catch (DukeException e) {
                ui.printMessage(e.getMessage());
            }
        }
        ui.close();
        ui.goodbyeMessage();
    }
}





