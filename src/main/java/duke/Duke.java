package duke; // Tan Matthew Simon Castaneda

import duke.helper.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates ChadGPT in its full majesty.
 *
 */
public class Duke {

    private String filepath;
    private Storage storage;
    private TaskList taskList;

    private Ui ui;

    /**
     * Helper function to initialize ChadGPT's context.
     *
     * @param filepath Relative filepath of ChadGPT's storage.
     */
    public Duke(String filepath) {
        this.filepath = filepath;
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.taskList = storage.loadTask();
        } catch (Exception e) {
            System.out.println("honggan");
            System.out.println(e.getMessage());
        }
        ui.startUp();



    }

    /**
     * Runs ChadGPT in its full majesty.
     */
    public void run() {

        while (true) {
            String input = ui.readLine();
            String[] command = input.split(" ");

            if (input.equals("bye")) {
                this.storage.saveTask(this.taskList);
                break;
            } else {
                Parser.run(input, command, this.taskList);
            }
        }
        ui.close();
    }

    /**
     * Method to start up ChadGPT.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke("./duke.txt").run();
    }
}
