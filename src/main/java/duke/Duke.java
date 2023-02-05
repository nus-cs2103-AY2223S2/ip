package duke;

import java.io.IOException;

/**
 * Represents the main Duke class.
 */
public class Duke {

    private static final String SAVE_PATH = "./taskList.txt";
    private Storage storage;
    private TaskList taskList;
    private static final Ui UI = new Ui();
    private static final Parser PARSER = new Parser();

    /**
     * Constructor for a Duke object.
     */
    public Duke() {
        try {
            this.storage = new Storage(SAVE_PATH);
            this.taskList = storage.loadData();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) throws IOException, DukeException {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() throws IOException, DukeException{
        UI.start();
        String[] input = UI.readLine();
        while (!input[0].equals("bye")) {
            try {
                UI.displayLine();
                PARSER.readInput(input, taskList);
                UI.displayLine();
                input = UI.readLine();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                UI.displayLine();
                input = UI.readLine();
            }
        }
        UI.goodbye();
        storage.storeData(this.taskList.getTasks());
    }
}
