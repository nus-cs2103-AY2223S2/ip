import java.util.Scanner;

public class Duke {
    private UI ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    public Duke() throws DukeException {
        ui = new UI();
        parser = new Parser();
        storage = new Storage();
        taskList = storage.readFromFile();

        ui.printWelcomeMessage();
        String userInput = ui.readCommand();

        while (!userInput.equals("bye")) {
            try {
                Command command = parser.parseCommand(userInput);
                command.assign(taskList, ui);
                command.execute();
            } catch (DukeException e) {
                ui.printError(e);
            } finally {
                userInput = ui.readCommand();
            }
        }
        storage.writeToFile(taskList);
        ui.printByeMessage();
    }

    public static void main(String[] args) throws DukeException {
        new Duke();
    }
}
