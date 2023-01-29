package donkeychat;

import java.util.Scanner;

/**
 * Chatbot that handles task tracking.
 */
public class Duke {

    private TaskList taskList = new TaskList();
    private Storage storage = new Storage();
    private Parser parser = new Parser();
    private Ui ui = new Ui();

    public Duke() {
        taskList = new TaskList();
        storage = new Storage();
    }

    /**
     * Starts the chatbot.
     */
    public void run() {
        ui.displayIntro();
        boolean isRunning = true;
        storage.loadSave(taskList);
        Scanner input = new Scanner(System.in);
        while (isRunning) {
            String currInput = input.nextLine();
            isRunning = parser.parse(currInput, taskList, storage, ui);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }


}
