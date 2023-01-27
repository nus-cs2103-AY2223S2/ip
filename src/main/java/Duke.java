import java.util.Scanner;

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
        Duke duke = new Duke("./data/tasks.txt");
        duke.runApp();
    }

    public void runApp() {
        ui.greetingMessage();

        boolean enteredBye = false;
        while (!enteredBye) {
            String input = ui.readInput();
            try {
                if (input.equals("bye")) {
                    enteredBye = true;
                } else {
                    parser.parseInput(input);
                }
                storage.save(taskList.getList());
            } catch (DukeException e) {
                ui.printMessage(e.getMessage());
            }
        }
        ui.goodbyeMessage();
    }
}





