package duke;

import duke.command.Command;

public class Duke {

    private static Storage storage;
    private static TaskList listOfTasks = new TaskList();
    private Ui ui;

    public void run() {
        this.ui = new Ui();
        storage = new Storage("src/main/tasks.txt");

        try {
            listOfTasks = storage.loadFile();
        } catch (InvalidCommandException e) {
            ui.showCreateNewFile();
            listOfTasks = new TaskList();
        }

        ui.showWelcome();
        boolean isExit = false;
        Parser parser = new Parser(listOfTasks);

        while (!isExit) {
            try {
                String[] currentInputArray = ui.readLine();
                Command currentCommand = parser.parse(currentInputArray[0], currentInputArray);    
                currentCommand.handleCommand(ui);
                isExit = currentCommand.isExit();
                ui.showPartition();
            } catch (InvalidCommandException e) {

            }
        }
        storage.writeToFile(listOfTasks);
    }
    public static void main(String[] args) {
        Duke duke  = new Duke();
        duke.run();
    }
}
