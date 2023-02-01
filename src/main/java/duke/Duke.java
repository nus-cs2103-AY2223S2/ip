package duke;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import duke.commands.Command;
import duke.exceptions.EmptyCommandException;
import duke.exceptions.InvalidCmdValueException;
import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidTaskTypeException;
import duke.exceptions.InvalidTimeException;
import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.utils.Parser;
import duke.utils.Storage;


/**
 * Duke is the class that represents the chat-bot.
 */
public class Duke {

    private static final File savedFile = new File("savedFile.txt");
    private Parser parser;
    private Storage storage;
    private TaskList commandList;
    private Ui ui;

    /**
     * Before setup for Duke
     */
    public Duke() {
        parser = new Parser();
        commandList = new TaskList();
        ui = new Ui();
    }

    /**
     * Main function to run duke
     * @param args No need to pass in any arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Runs Duke.
     */
    public void run() {
        //Creating/Loading from storage
        this.storage = new Storage();
        boolean isCreated = savedFile.exists();
        if (isCreated) {
            this.storage.loadFromFile(new File("savedFile.txt"));
            this.commandList.setTaskList(this.storage.getStorage());
        } else {
            try {
                savedFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Unable to create file" + e);
            }
        }

        ui.greetingMessage();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String userCommands = scanner.nextLine();
            Command command;
            try {
                command = parser.parse(userCommands, commandList, storage, ui, savedFile);
                command.action();
            } catch (InvalidCmdValueException | InvalidTaskTypeException
                     | EmptyCommandException | InvalidTimeException
                     | InvalidDateException e) {
                System.out.println(Ui.HORIZONTAL_LINE + "\n" + e.getMessage()
                        + "\n" + Ui.HORIZONTAL_LINE);
            }
        }
    }
}


