package duke;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

// A chatbot
public class Duke {

    private static TaskList taskList = new TaskList();
    private static Parser parser = new Parser();
    private static Storage storage;
    private static Ui ui = new Ui();

    private Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = storage.load(parser);
        } catch (DukeException e) {
            ui.printMessage(e.toString());
        }
    }

    private void run() {
        ui.greet();
        acceptCommands();
        ui.sayGoodbye();
    }

    // Loop for user input
    private void acceptCommands() {
        String input;
        while (true) {
            ui.printPromptForInput();
            input = ui.getInputFromUser();

            // Split into two parts at the first space
            try {
                Command command = parser.parseCommand(input);
                 if(command.isExit()) {
                     return;
                 } else {
                     command.execute(taskList, ui, storage);
                 }
                storage.save(taskList);
            } catch (DukeException e) {
                ui.printMessage(e.toString());
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
