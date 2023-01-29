package donkeychat;

import java.util.Scanner;

/**
 * Chatbot that handles task tracking.
 */
public class Duke {

    private TaskList tasks = new TaskList();
    private Storage storage = new Storage();
    private Parser parser = new Parser();
    private Ui ui = new Ui();

    public Duke() {
        tasks = new TaskList();
        storage = new Storage();
    }

    /**
     * Starts the chatbot.
     */
    public void run() {
        ui.displayIntro();
        boolean isRunning = true;
        storage.loadSave(tasks);
        Scanner input = new Scanner(System.in);
        while (isRunning) {
            String currInput = input.nextLine();
            isRunning = parser.parse(currInput, tasks, storage, ui);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }


}
