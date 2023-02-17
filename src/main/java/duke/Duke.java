package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Main program of Duke program.
 *
 * @author Yu Heng
 */
public class Duke {
    private final String LINE_BREAK = "\n\t ^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^\n";
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private boolean hasGui;

    /**
     * Constructor method for Duke Chatbot.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            taskList = new TaskList();
            storage.loadData(taskList);
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }

    }

    /**
     * Set Gui. If true, console off.
     * @param hasGui
     */
    public void setGui(boolean hasGui) {
        this.hasGui = hasGui;
    }

    /**
     * Main Method to call Duke.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.setGui(true);
        duke.run();
    }

    /**
     * Gets reply from bot to display in GUI.
     *
     * @param input input from user.
     * @return reply message
     */
    public String getResponse(String input) {
        String response;
        try {
            response = Parser.parse(input, taskList, ui);
            assert response != null : "Response is null";
            storage.writeToData(taskList.itemsToData());
            return response;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Get greeting from UI for GUI.
     *
     * @return greeting
     */
    public String getGreeting() {
        return ui.guiGreet();
    }

    /**
     * Run method to start Duke. Initializes scanner and parser
     * and contains main loop for commands.
     */
    private void run() {
        ui.greet();
        Scanner usrInput = new Scanner(System.in);
        Parser parser = new Parser(usrInput);
        while (usrInput.hasNextLine()) {
            try {
                parser.parse_cmds(taskList, ui);
                storage.writeToData(taskList.itemsToData());
            } catch (Exception e) {
                System.out.println(LINE_BREAK + "\t " + e.getMessage() + "\n" + LINE_BREAK);
            }
        }
    }
}
