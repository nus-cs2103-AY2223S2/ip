package chattime;

import java.util.Scanner;

import chattime.command.Command;
import chattime.exception.ChattimeException;
import chattime.parser.Parser;
import chattime.storage.Storage;
import chattime.ui.Ui;

/**
 * A chatbot that receives user's input on various predetermined command types and performs relevant functions.
 */
public class Chattime {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Initializes a bot with uiobjects a nd a storage space with provided path to store list.
     *
     * @param filePath Path to storage.
     */
    public Chattime(String filePath) {
        ui = new Ui();
        storage = new Storage(ui, filePath);
        tasks = new TaskList(storage.loadData());
    }

    /**
     * Runs the bot and handles user's input.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        String userInput;
        Command cmd;

        while (ui.getExecuteStatus()) {

            userInput = sc.nextLine();

            try {
                cmd = Parser.parse(userInput);

                if (cmd != null) {
                    cmd.execute(ui, tasks, storage);
                }
            } catch (ChattimeException e) {
                ui.printError(e.getMessage());
            }
        }

    }

    /**
     * Initiates the whole bot program.
     *
     * @param args Supplied command-line arguments.
     */
    public static void main(String[] args) {
        new Chattime("").run();
    }

}
